package hai913i.main.java.utils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import org.apache.commons.io.FileUtils;


public class Parser {
	private ClassDeclarationVisitor classDeclaration;
	public static final String projectPath = "D:\\telechargement_chrome\\mini_project_in_project\\project";
	public static final String jrePath = "C:\\Program Files\\Java\\jre1.8.0_51\\lib\\rt.jar";
	public static final String projectSourcePath = projectPath + "\\src";
	private ArrayList<CompilationUnit> compilationUnits = new ArrayList<CompilationUnit>();

	static ASTParser parser;

	public Parser() throws IOException
	{
		classDeclaration = new ClassDeclarationVisitor();
		final File folder = new File(projectPath);
		ArrayList<File> javaFiles = listJavaFilesForFolder(folder);

		for (File fileEntry : javaFiles) {
			String content = FileUtils.readFileToString(fileEntry);

			CompilationUnit parse = parse(content.toCharArray());
			compilationUnits.add(parse);
			parse.accept(classDeclaration);
		}
	}
	// read all java files from specific folder
	public static ArrayList<File> listJavaFilesForFolder(final File folder) {
		ArrayList<File> javaFiles = new ArrayList<File>();
		for (File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				javaFiles.addAll(listJavaFilesForFolder(fileEntry));
			} else if (fileEntry.getName().contains(".java")) {
				//System.out.println(fileEntry.getName());
				javaFiles.add(fileEntry);
			}
		}

		return javaFiles;
	}
	// create AST
	private static CompilationUnit parse(char[] classSource) {
		parser = ASTParser.newParser(AST.JLS4); // java +1.6
		parser.setResolveBindings(true);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
 
		parser.setBindingsRecovery(true);
 
		Map options = JavaCore.getOptions();
		parser.setCompilerOptions(options);
 
		parser.setUnitName("");
 
		String[] sources = { projectSourcePath }; 
		String[] classpath = {jrePath};
 
		parser.setEnvironment(classpath, sources, new String[] { "UTF-8"}, true);
		parser.setSource(classSource);
		
		return (CompilationUnit) parser.createAST(null); // create and parse
	}
	public List<MethodOfClass> getMethodsOfClass(TypeDeclaration c)
	{
		return classDeclaration.getMethodsOfClass(c);
	}
	public HashMap<MethodOfClass, ArrayList<MethodInvocation>> getInvocationTree()
	{
		return classDeclaration.getInvocationTree();
	}
	public List<TypeDeclaration> getClassesDeclaration()
	{
		return classDeclaration.getClasses();
	}
}

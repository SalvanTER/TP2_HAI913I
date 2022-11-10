package hai913i.main.java.utils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import hai913i.main.java.graph.Point;
import hai913i.main.java.graph.PointTypeDeclaration;

import org.apache.commons.io.FileUtils;


public class Parser implements IParser{
	private ClassDeclarationVisitor classDeclaration;
	public static  String projectPath = "D:\\telechargement_chrome\\mini_project_in_project\\project";
	public static String jrePath = "C:\\Program Files\\Java\\jre1.8.0_51\\lib\\rt.jar";
	public static String projectSourcePath;
	private ArrayList<CompilationUnit> compilationUnits = new ArrayList<CompilationUnit>();
	private ArrayList<Point> classes; 
	static ASTParser parser;

	public Parser(String projectpath) throws IOException
	{
		Parser.projectPath = projectpath;
		projectSourcePath = projectPath + "\\src";
		classDeclaration = new ClassDeclarationVisitor();
		classes = new ArrayList<Point>();
		final File folder = new File(projectPath);
		ArrayList<File> javaFiles = listJavaFilesForFolder(folder);

		for (File fileEntry : javaFiles) {
			String content = FileUtils.readFileToString(fileEntry);

			CompilationUnit parse = parse(content.toCharArray());
			compilationUnits.add(parse);
			parse.accept(classDeclaration);
		}
		for(TypeDeclaration t : classDeclaration.getClasses())
		{
			classes.add(new PointTypeDeclaration(t));
		}
	}
	// read all java files from specific folder
	public static ArrayList<File> listJavaFilesForFolder(final File folder) {
		ArrayList<File> javaFiles = new ArrayList<File>();
		for (File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				javaFiles.addAll(listJavaFilesForFolder(fileEntry));
			} else if (fileEntry.getName().contains(".java")) {
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
	public List<IMethodOfClass> getMethodsOfClass(TypeDeclaration c)
	{
		return classDeclaration.getMethodsOfClass(c);
	}
	public List<TypeDeclaration> getClassesDeclaration()
	{
		return classDeclaration.getClasses();
	}
	@Override
	public List<Point> getClasses() {

		return classes;
	}
	@Override
	public List<IMethodOfClass> getMethodsOfClass(Point c1) {
		return getMethodsOfClass((TypeDeclaration)c1.getEntity());
	}
	@Override
	public int countMethodsInClass(IMethodOfClass mc, Point p) {
		int res = 0;
		ArrayList<MethodInvocation> mis = classDeclaration.getInvocationTree().get(mc);
		for(MethodInvocation mi : mis)
		{
			if(p.getName().equals(getObjectType(mi)))
			{
				res++;
			}
		}
		return res;
	}
	private String getObjectType(MethodInvocation mCalled) {
		if(mCalled.getExpression()!=null) {
			if(mCalled.getExpression().resolveTypeBinding()!=null) {
				return mCalled.getExpression().resolveTypeBinding().getName();	
			}
		}
		else {
			if(mCalled.resolveMethodBinding()!=null)
			return mCalled.resolveMethodBinding().getDeclaringClass().getName();	
		}
		return "";
	}
}

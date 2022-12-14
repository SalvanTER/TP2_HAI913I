package hai913i.main.java.spoon.parsers;

import hai913i.main.java.parsers.Parser;
import spoon.Launcher;
import spoon.processing.Processor;
import spoon.reflect.declaration.CtClass;

public class SpoonParser extends Parser<Launcher> {

	public SpoonParser(String projectPath) {
		super(projectPath);
	}
	
	public void setLauncher(
			boolean autoImports, boolean commentsEnabled) {
		parser = new Launcher(); // create launcher
		parser.addInputResource(getProjectSrcPath()); // set project source path
		parser.getEnvironment().setSourceClasspath(new String[] {getProjectBinPath()}); // set project classpath
		parser.getEnvironment().setAutoImports(autoImports); // set auto imports
		parser.getEnvironment().setCommentEnabled(commentsEnabled); // set comments enabled
	}

	
	public void configure() {
		setLauncher(true, true);
	}
	
	public void addProcessor(Processor<CtClass> processor) {
		parser.addProcessor(processor);
	}
	
	public void run() {
		parser.run();
	}
}

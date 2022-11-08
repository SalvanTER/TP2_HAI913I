package hai913i.main.java.spoon.processors;

import hai913i.main.java.processors.Processor;
import hai913i.main.java.spoon.parsers.SpoonParser;

public class SpoonProcessor extends Processor<SpoonParser> {

	public SpoonProcessor(String projectPath) {
		super(projectPath);
	}

	
	public void setParser(String projectPath) {
		parser = new SpoonParser(projectPath);
	}
	
	public void setParser(SpoonParser parser) {
		this.parser = parser;
	}

}

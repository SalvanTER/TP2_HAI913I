package hai913i.main.java.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class AbstractMain {
	public static String TEST_PROJECT_PATH;
	public static final String QUIT = "0";
	
	protected static void setTestProjectPath(BufferedReader inputReader) 
			throws IOException {
		
		System.out.println("Please provide the path to a java project folder: ");
		String userInput = inputReader.readLine();
		verifyTestProjectPath(inputReader, userInput);
	}
	
	protected static void verifyTestProjectPath(BufferedReader inputReader, 
			String userInput) throws IOException {
		
		while (!isJavaProject(userInput)) {
			System.err.println("Error: "+userInput+
					" either doesn't exist or isn't a java project folder. "
					+ "Please try again: ");
			userInput = inputReader.readLine();
		}
		
		TEST_PROJECT_PATH = userInput;
	}
	
	protected static boolean isJavaProject(String projectPath) {
		return new File(projectPath+File.separator+"src"+File.separator).exists();
	}
	protected static void getJavaPath(String[] args)
	{
		BufferedReader inputReader = null;
		try {
			inputReader = new BufferedReader(new InputStreamReader(System.in));
			if (args.length < 1)
				setTestProjectPath(inputReader);
			else
				verifyTestProjectPath(inputReader, args[0]);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

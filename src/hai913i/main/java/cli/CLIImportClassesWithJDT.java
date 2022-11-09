package hai913i.main.java.cli;

import hai913i.main.java.utils.Parser;

public class CLIImportClassesWithJDT extends IChoice{
    Parser parser;
    String path;
    public CLIImportClassesWithJDT(String path) {
        super("Importer les classes avec JDT");
        this.path = path;
    }

    @Override
    public void todo() {
        System.out.println("Récupération des classes avec JDT...");
		try {
			parser = new Parser(path);
		}catch(Exception e)
		{
			System.out.println(e.toString());
		}
        closeMenu();
    }
    public Parser getParser()
    {
        return parser;
    }
}

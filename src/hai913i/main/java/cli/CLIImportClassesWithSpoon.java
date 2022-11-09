package hai913i.main.java.cli;

import hai913i.main.java.spoon.ParserS;

public class CLIImportClassesWithSpoon extends IChoice {
    ParserS parserS;
    String path;
    public CLIImportClassesWithSpoon(String path) {
        super("Importer les classes avec Spoon");
        this.path = path;
    }

    @Override
    public void todo() {
        System.out.println("Récupération des classes avec Spoon...");
        parserS = new ParserS(path);
		parserS.run();
        closeMenu();
    }
    public ParserS getParser()
    {
        return parserS;
    }
}

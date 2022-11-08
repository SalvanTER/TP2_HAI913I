package cli;
public class ChoiceQuit extends IChoice {
    public ChoiceQuit() {
        super("Quitter");
    }

    @Override
    public void todo() {
        System.exit(0);
    }
}
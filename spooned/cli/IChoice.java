package cli;
public abstract class IChoice {
    private String title;

    public IChoice(String title) {
        this.title = title;
    }

    public abstract void todo();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
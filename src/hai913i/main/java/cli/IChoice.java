package hai913i.main.java.cli;

public abstract class IChoice {
    private String title;
    private boolean closeMenu;
    public IChoice(String title)
    {
        this.title = title;
        closeMenu = false;
    }
    public abstract void todo();
    public String getTitle()
    {
        return title;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }
    public boolean menuClosed()
    {
        return closeMenu;
    }
    public void closeMenu()
    {
        closeMenu = true;
    }
}

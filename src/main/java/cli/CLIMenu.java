package cli;

import java.util.ArrayList;
import java.util.Scanner;

public class CLIMenu {
    private ArrayList<IChoice> choices;
    public CLIMenu()
    {
        choices = new ArrayList<IChoice>();
        choices.add(new ChoiceQuit());
    }
    public void run()
    {
        while (true)
        {
            printMenu();
            choices.get(getChoice()-1).todo();
        }
    }
    public void addChoice(IChoice choice)
    {
        choices.add(choice);
    }
    private int getChoice()
    {
        int res = 0;
        do {
            Scanner reader = new Scanner(System.in);
            System.out.println("Choose an element: ");
            res = isValidInput(reader);
        }while(res == -1);
        return res;
    }
    private int isValidInput(Scanner reader)
    {
        try{
            return testRangeChoice(reader.nextInt());
        }catch (Exception e)
        {
            System.out.println("PLease enter a number.");
            return -1;
        }

    }
    private int testRangeChoice(int n)
    {
        if (n-1 > choices.size() || n <= 0)
        {
            System.out.println("" + n + " is not a valid option.");
            return -1;
        }
        return n;
    }
    private void printMenu()
    {
        int j = 1;
        System.out.println("//////////////////////////////////////////////////");
        for(IChoice i : choices)
        {
            System.out.println("" + j + ". "+ i.getTitle());
            j++;
        }
    }
}

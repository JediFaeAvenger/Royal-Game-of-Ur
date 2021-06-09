public class boardGameAI
{
    String tOG = "";
    public boardGameAI(String textOrGUI)
    {
        tOG = textOrGUI;
    }

    public int doStuff()
    {
        if (tOG.equals("t"))
            return (int)(Math.random()*7)+1;
        else if (tOG.equals("g"))
            return 1;
        else
            return 0;
    }
}

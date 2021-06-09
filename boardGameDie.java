import java.util.Random;

public class boardGameDie
{
    public boardGameDie()
    {}

    public int roll()
    {
        int count = 0;
        Random gen = new Random();

        // simulates the situation of the real roll, where 4 dice have a 50% chance of generating a true value, and the number of
        // trues that result from a roll are the spaces that can be moved
        for (int i = 0; i < 4; i++)
        {
            if (gen.nextInt(5) >= 2)
                count++;
        }
        return count;
    }
}

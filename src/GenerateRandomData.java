import java.util.Random;

public class GenerateRandomData
{
    public String generateData(int k)
    {
        Random rand = new Random();
        StringBuilder D =  new StringBuilder();

        for(int i=0;i<k;i++)
        {
            D.append(rand.nextInt(2));
        }
        return D.toString();
    }
}

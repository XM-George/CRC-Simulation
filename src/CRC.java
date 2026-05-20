import java.util.Random;
public class CRC {

    public String generateFCS(String data,String p)
    {
        StringBuilder dataBuilder = new StringBuilder(data);
        for(int i = 0; i<p.length() - 1; i++)
        {
            dataBuilder.append("0");
        }
        data = dataBuilder.toString();

        char[] dataCopy = divide(data,p).toCharArray();

        StringBuilder f = new StringBuilder();

        for(int i = dataCopy.length - (p.length() - 1); i<dataCopy.length; i++)
        {
            f.append(dataCopy[i]);
        }

        return f.toString();
    }

    private String divide(String data, String p)
    {
        char[] dataCopy = data.toCharArray();
        char[] pCopy = p.toCharArray();

        for(int i = 0; i<dataCopy.length - p.length() + 1; i++)
        {
            if(dataCopy[i] == '1')
            {
                for(int j = 0; j<p.length(); j++)
                {
                    dataCopy[i + j] = XOR(dataCopy[i + j],pCopy[j]);
                }
            }
        }
        return new String(dataCopy);
    }


    char XOR(char a, char b)
    {
        if(a == b)
        {
            return '0';
        }
        return '1';
    }

    public String channelSimulationBER(String t, double ber)
    {
        Random rand = new Random();

        char[] tCopy = t.toCharArray();
        for(int i=0; i<t.length(); i++)
        {
            if(rand.nextDouble() < ber)
            {
                if('1' == tCopy[i])
                {
                    tCopy[i] = '0';
                }
                else
                {
                    tCopy[i] = '1';
                }
            }
        }

        return new String(tCopy);
    }

    public boolean checkCrc(String t,String p)
    {
        String div = divide(t,p);
        for(int i = 0; i<div.length(); i++)
        {
            if(div.charAt(i) != '0')
            {
                return false;
            }
        }

        return true;
    }
}

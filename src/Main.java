import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        GenerateRandomData GD = new GenerateRandomData();
        CRC crc = new CRC();

        Scanner scanner = new Scanner(System.in);


        int k = 0;

        while (k <= 0)
        {
            System.out.println("Δώσε τα k bits για κάθε μπλοκ δεδομένων πρέπει να είναι αριθμός μεγαλύτερος του μηδενός");
            if(scanner.hasNextInt())
            {
                k = scanner.nextInt();
            }
            else
            {
                scanner.next();
            }
        }

        scanner.nextLine();

        System.out.println("Δώσε τον δυαδικό αριθμό Ρ για τον υπολογισμό του CRC");
        String p;
        p=scanner.nextLine();

        char[] pCopy;

        boolean valid = false;

        while(!valid)
        {
            valid=true;

            pCopy = p.toCharArray();
            for(char c : pCopy)
            {
                if(c!='1' && c!='0')
                {
                    valid = false;

                    System.out.println("To P πρέπει να είναι δυαδικός αριθμός, ξανά δώσε το P");

                    p=scanner.nextLine();

                    break;
                }
            }

            if (p.length()<2 || p.charAt(0) != '1' || p.charAt(p.length()-1) != '1')
            {
                System.out.println("Το P πρέπει να ξεκινά και να τελειώνει με 1");
                valid = false;
            }
        }

        double ber = -1;

        while (ber <= 0 || ber>1)
        {
            System.out.println("Δώσε το BER, το BER πρέπει να είναι μεταξύ 0 και 1");
            if(scanner.hasNextDouble())
            {
                ber = scanner.nextDouble();
            }
            else
            {
                scanner.next();
            }
        }

        scanner.close();


        for(int i=0;i<PrintStatistics.totalMessages;i++)
        {
            String data = GD.generateData(k);
            String f = crc.generateFCS(data,p);

            //System.out.println(f);

            String t = data + f;

            //System.out.println(t);

            String receivedT = crc.channelSimulationBER(t, ber);

            if(!t.equals(receivedT))
            {
                PrintStatistics.wrongReceivedMessages++;
            }

            if(!crc.checkCrc(receivedT,p))
            {
                PrintStatistics.detectedWrongMessages++;
            }

            if(!t.equals(receivedT) && crc.checkCrc(receivedT,p))
            {
                PrintStatistics.undetectedWrongMessages++;
            }
        }


        PrintStatistics.printStatisticsToConsole();
    }
}

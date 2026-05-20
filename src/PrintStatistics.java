public class PrintStatistics
{
    public static long totalMessages = 100000000;
    public static int wrongReceivedMessages = 0;
    public static int detectedWrongMessages = 0;
    public static int undetectedWrongMessages = 0;

    public static void printStatisticsToConsole()
    {
        System.out.println("Στα " + totalMessages + " συνολικά μηνύματα τα " + wrongReceivedMessages + " φθάνουν με σφάλμα");
        System.out.println("Από τα " + wrongReceivedMessages + " εσφαλμένα μηνύματα τα " + detectedWrongMessages +" ανιχνεύονται και τα " + undetectedWrongMessages + " δεν ανιχνεύονται");
        System.out.println("Συνολικά μηνύματα: " + totalMessages);
        System.out.println("Εκ των οποίων εσφαλμένα το " + (double)wrongReceivedMessages/(double)totalMessages*100 + "%");
        System.out.println("Ποσοστό εσφαλμένων μηνυμάτων που ανιχνεύτηκαν: " + (double)detectedWrongMessages/(double)wrongReceivedMessages*100 + "%");
        System.out.println("Ποσοστό εσφαλμένων μηνυμάτων που δεν ανιχνεύτηκαν: " + (double)undetectedWrongMessages/(double)wrongReceivedMessages*100 + "%");
    }
}

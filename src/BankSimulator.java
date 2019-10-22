
public class BankSimulator {
    private BankersQueue<BankClient> bank = new BankersQueue<>();
    private static final int numMaximumClients = 100;
    private static final int clientTime = 15;
    private static final int serviceTime = 120;

    private int numCurrentClients = 0;
    private int time = 0;
    private int mean = 0;

    public static void main(String[] args) {
        BankSimulator main = new BankSimulator();
        int numMaxBankers = 10;
        main.run(1, numMaxBankers);
    }

    private void run(int numBankers, int numMaxBankers){
        if(numBankers <= numMaxBankers){
            int[] lastExitTimes = new int[numBankers];
            bank = new BankersQueue<>();
            simulation(numBankers, lastExitTimes);

            mean = mean/numMaximumClients;
            System.out.println("Simulació amb " + numBankers + " caixers: " + mean);
            time = numCurrentClients = mean = 0;

            run(numBankers + 1, numMaxBankers);
        }
    }

    private void simulation(int numBankers, int[] lastExitTimes) {
        if (numCurrentClients < numMaximumClients) {
            addClients(0, numBankers, lastExitTimes);
            simulation(numBankers, lastExitTimes);
        }
    }

    private void addClients(int currentBanker, int numBankers, int[] lastExitTimes) {
        if (numCurrentClients < numMaximumClients && currentBanker < numBankers) {
            int exitTime = (time >= lastExitTimes[currentBanker]) ? time + serviceTime : lastExitTimes[currentBanker] + serviceTime;
            bank.add(new BankClient(time, exitTime));
            lastExitTimes[currentBanker] = exitTime;

            ++numCurrentClients;
            mean += exitTime - time;
            time += clientTime;
            addClients(currentBanker + 1, numBankers, lastExitTimes);
        }

    }
}

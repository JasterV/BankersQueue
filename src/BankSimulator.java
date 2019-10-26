public class BankSimulator {
    private BankersQueue<BankClient> bank = new BankersQueue<>();
    private static final int numMaximumClients = 100;
    private static final int clientTime = 15;
    private static final int serviceTime = 120;
    private static final int numMaxBankers = 10;
    private float mean = 0;
    private static float[] meanDataSet = new float[numMaxBankers];

    void run() {
        for (int numBankers = 1; numBankers <= numMaxBankers; ++numBankers) {
            simulation(numBankers);
            meanDataSet[numBankers - 1] = mean;
            System.out.println(String.format("Simulació amb %d caixers: %.1f", numBankers, mean));
            mean = 0;
        }
    }

    private void simulation(int numBankers) {
        for (int numCurrentClients = 0, time = 0; numCurrentClients < numMaximumClients; numCurrentClients++, time += clientTime) {
            int exitTime = checkPreviousClients(numCurrentClients, time, numBankers);
            bank.add(new BankClient(time, exitTime));
            mean += exitTime - time;
        }
        removeRemainingClients();
        mean = mean / numMaximumClients;
    }

    private int checkPreviousClients(int numCurrentClients, int time, int numBankers) {
        if (numCurrentClients < numBankers)
            return time + serviceTime;
        else {
            BankClient firstClientOnQueue = bank.element();
            bank.remove();
            return (time >= firstClientOnQueue.getExitTime()) ? time + serviceTime : firstClientOnQueue.getExitTime() + serviceTime;
        }
    }

    private void removeRemainingClients() {
        while (!bank.isEmpty()) {
            bank.remove();
        }
    }

    public float getMean(int i) {
        return meanDataSet[i];

    }

    public static void main(String[] args) {
        BankSimulator main = new BankSimulator();
        main.run();
    }
}

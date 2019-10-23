
public class BankSimulator {
    private BankersQueue<BankClient> bank = new BankersQueue<>();
    private static final int numMaximumClients = 100;
    private static final int clientTime = 15;
    private static final int serviceTime = 120;
    private static final int numMaxBankers = 10;
    private int mean = 0;

    private void run() {
        for (int numBankers = 1; numBankers <= numMaxBankers; ++numBankers) {
            simulation(numBankers);
            mean = mean / numMaximumClients;
            System.out.println("Simulació amb " + numBankers + " caixers: " + mean);
            mean = 0;
        }
    }

    private void simulation(int numBankers) {
        int numCurrentClients = 0;
        int time = 0;
        simulation(numCurrentClients, time, numBankers);
    }

    private void simulation(int numCurrentClients, int time, int numBankers) {
        if (numCurrentClients < numMaximumClients) {
            int exitTime = checkPreviousClients(numCurrentClients, time, numBankers);
            bank.add(new BankClient(time, exitTime));
            mean += exitTime - time;
            simulation(numCurrentClients + 1, time + clientTime, numBankers);
        }
        removeRemainingClients();
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
        if (!bank.isEmpty()) {
            bank.remove();
        }
    }

    public static void main(String[] args) {
        BankSimulator main = new BankSimulator();
        main.run();
    }
}

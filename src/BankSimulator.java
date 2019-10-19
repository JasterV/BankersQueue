public class BankSimulator {

    BankersQueue[] bankers;

    public static void main(String[] args) {
        BankSimulator bank = new BankSimulator();
        bank.run();
    }

    private void run() {
        for (int i = 1; i <= 10; ++i) {
            //
        }
    }

    private void createClients(int numClients) {
        bankers = new BankersQueue[numClients];
        for (int i = 0; i < numClients; ++i) {
            bankers[i] = new BankersQueue();
        }
    }
}

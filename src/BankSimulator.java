import java.util.LinkedList;

public class BankSimulator {

    private LinkedList<BankersQueue<BankClient>> bank = new LinkedList<>();
    private static final int numOfClients = 100;
    private static final int clientTime = 15;
    private static final int serviceTime = 30;
    private int mean = 0;

    private void run(){
        for (int i = 0; i < 10; ++i) {
            bank.add(new BankersQueue<>());
            mean = simulation();
            System.out.println(mean);
            mean = 0;
        }
    }

    private int simulation() {
        int clients = 0;
        boolean noMoreClients = false;
        for (int sec = 0; !noMoreClients; ++sec) {
            if (clients < numOfClients && sec % clientTime == 0) {
                enterNewClient(sec);
                ++clients;
            }
            noMoreClients = updateQueues(sec);
        }
        mean /= numOfClients;
        return mean;
    }

    private void enterNewClient(int seconds) {
        BankersQueue<BankClient> queue = shortestQueue();
        queue.add(new BankClient(seconds));
    }

    private BankersQueue<BankClient> shortestQueue() {
        BankersQueue<BankClient> queue = new BankersQueue<>();
        int min = numOfClients;
        for (BankersQueue<BankClient> b : bank) {
            if (b.size() < min) {
                min = b.size();
                queue = b;
            }
        }
        return queue;
    }

    private boolean updateQueues(int time) {
        boolean noMoreClients = true;
        for (BankersQueue<BankClient> queue : bank) {
            if (!queue.isEmpty()) {
                updateServedClient(queue, time);
                noMoreClients = false;
            }
        }
        return noMoreClients;
    }

    private void updateServedClient(BankersQueue<BankClient> queue, int time) {
        BankClient client = queue.element();
        newServedclient(client, time);

        if (time == client.getExitTime()) {
            mean += client.getExitTime() - client.getArrivalTime();
            queue.remove();

            if (!queue.isEmpty()) {
                client = queue.element();
                newServedclient(client, time);
            }
        }

    }

    private void newServedclient(BankClient client, int time) {
        if (client.getExitTime() == -1) {
            int waitTime = time - client.getArrivalTime();
            client.setExitTime(client.getArrivalTime() + waitTime + serviceTime);
        }
    }

    public static void main(String[] args) {
        BankSimulator main = new BankSimulator();
        main.run();
    }

}

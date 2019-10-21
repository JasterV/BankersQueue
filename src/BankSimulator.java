import java.util.LinkedList;

public class BankSimulator {

    private LinkedList<BankersQueue<BankClient>> bank = new LinkedList<>();
    private static final int numOfClients = 100;
    private static final int clientTime = 15;
    private static final int serviceTime = 30;
    private int mean = 0;

    private void run(){
        for (int i = 1; i <= 10; ++i) {
            bank.add(new BankersQueue<>());
            mean = simulation();
            System.out.println(mean);
            mean = 0;
        }
    }

    private int simulation() {
        int clients = 0;
        for (int sec = 0; !checkQueues(clients); ++sec) {
            updateQueues(sec);
            if (clients < numOfClients && sec % clientTime == 0) {
                addNewClient(sec);
                ++clients;
            }
            updateQueues(sec);
        }
        mean /= numOfClients;
        return mean;
    }

    private boolean checkQueues(int clients){
        if(clients > 0){
            for(BankersQueue queue : bank){
                if(!queue.isEmpty())
                    return false;
            }
            return true;
        }else
            return false;
    }

    private void addNewClient(int seconds) {
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

    private void updateQueues(int time) {
        for (BankersQueue<BankClient> queue : bank) {
            if (!queue.isEmpty()) {
                updateServedClient(queue, time);
            }
        }
    }

    private void updateServedClient(BankersQueue<BankClient> queue, int time) {
        BankClient client = queue.element();
        newServedclient(client, time);
        if (time == client.getExitTime()) {
            mean += client.getExitTime() - client.getArrivalTime();
            queue.remove();
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

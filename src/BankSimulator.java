import java.util.LinkedList;
import java.util.ListIterator;

public class BankSimulator {

    private LinkedList<BankersQueue<BankClient>> bankers = new LinkedList<>();
    private static final int numOfClients = 100;
    private static final int clientTime = 15;
    private static final int serviceTime = 30;
    private int mean = 0;

    public static void main(String[] args) {
        BankSimulator bank = new BankSimulator();
        bank.run();
    }

    private void run() {
        for (int i = 1; i <= 10; ++i) {
            bankers.add(new BankersQueue<>());
            simulation();
            mean /= numOfClients;
            System.out.println(mean);
            mean = 0;
        }
    }

    private void simulation() {
        int clients = 0;
        boolean noMoreClients = false;
        for (int sec = 0; !noMoreClients; ++sec) {
            if (clients < numOfClients && sec % clientTime == 0) {
                enterNewClient(sec);
                ++clients;
            }
            noMoreClients = updateQueues(sec);
        }
    }

    private void enterNewClient(int seconds) {
        int emptiest = searchTheEmptiestQueue();
        bankers.get(emptiest).add(new BankClient(seconds));
    }

    private int searchTheEmptiestQueue(){
        int queue = 0;
        int min = bankers.get(0).size();
        for (int i = 0; i < bankers.size(); ++i) {
            int size = bankers.get(i).size();
            if (size < min) {
                min = size;
                queue = i;
            }
        }
        return queue;
    }

    private boolean updateQueues(int time) {
        boolean noMoreClients = true;
        for(BankersQueue<BankClient> queue : bankers){
            if(!queue.isEmpty()){
                updateServiceTime(queue, time);
                noMoreClients = false;
            }
        }
        return noMoreClients;
    }

    private void updateServiceTime(BankersQueue<BankClient> queue, int time){
        BankClient client = queue.element();
        if(client.updateServiceTime(1) == serviceTime){
            client.setExitTime(time);
            mean += client.getExitTime() - client.getArrivalTime() + 1;
            queue.remove();
        }
    }

}

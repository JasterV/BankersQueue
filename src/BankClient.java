public class BankClient {
    private int arrivalTime;
    private int exitTime;

    public BankClient(int arrivalTime, int exitTime) {
        this.arrivalTime = arrivalTime;
        this.exitTime = exitTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getExitTime(){
        return exitTime;
    }

}

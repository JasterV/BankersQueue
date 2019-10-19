public class BankClient {
    private int arrivalTime;
    private int exitTime;
    private int waitTime;
    private static final int SERVICE_TIME = 30;

    public BankClient(){
        this.arrivalTime = 0;
        this.exitTime = 0;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setWaitTime(int attendTime){
        waitTime = attendTime - arrivalTime;
    }

    public void setExitTime(int exitTime) {
        this.exitTime = arrivalTime + waitTime + SERVICE_TIME;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getExitTime() {
        return exitTime;
    }
}

public class BankClient {
    private int arrivalTime;
    private int exitTime;

    public BankClient(int arrivalTime){
        this.arrivalTime = arrivalTime;
        this.exitTime = 0;
    }

    public BankClient(int arrivalTime, int exitTime) {
        this.arrivalTime = arrivalTime;
        this.exitTime = exitTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime){
        this.arrivalTime = arrivalTime;
    }

    public int getExitTime(){
        return exitTime;
    }

    public void setExitTime(int exitTime){
        this.exitTime = exitTime;
    }

}

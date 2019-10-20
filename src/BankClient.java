public class BankClient {
    private int arrivalTime;
    private int exitTime;

    public BankClient(int arrivalTime) {
        this.arrivalTime = arrivalTime;
        this.exitTime = -1;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setExitTime(int time){
        exitTime = time;
    }

    public int getExitTime(){
        return exitTime;
    }

}

public class BankClient {
    private int arrivalTime;
    private int exitTime;
    private int serviceTime;

    public BankClient(int arrivalTime) {
        this.arrivalTime = arrivalTime;
        this.exitTime = 0;
        this.serviceTime = 0;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setExitTime(int time){
        exitTime = time;
    }

    public int updateServiceTime(int time){
        serviceTime += time;
        return serviceTime;
    }

    public int getExitTime(){
        return exitTime;
    }
}

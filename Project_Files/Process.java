package Project_Files;

public class Process {
    private String idd;
    private final int timeArrival;
    private int serviceTime;
    private int executionTime;
    private final int priority;
    private int responseTime;
    boolean inputOutput;

    public Process(String idd,int ta, int et, int pr){
        this.idd=idd;
        timeArrival = ta;
        executionTime = et;
        priority = pr;
        inputOutput = false;
        responseTime = -1;
    }

    public int getArrivalTime() { return timeArrival; }
    public int getServiceTime() { return serviceTime; }
    public void updateServiceTime(int st) { serviceTime = st; }
    public int getResponseTime() {return responseTime - timeArrival; }
    public int getTurnAroundTime() {return serviceTime - timeArrival; }

    public void updateInputOutput(boolean IO) {inputOutput = IO;}
    public boolean getIO(){return inputOutput;}
    public int getPriority() {return priority;}
    public int getExecutionTime() {return executionTime;}
    public void run(int i){
        if (responseTime == -1) responseTime = i;
        executionTime--;
    }
    
    public String getprocessname(){
       return idd;
    }
    
  
}

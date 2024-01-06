package ergasia_sls;

public class Process {
    private final int timeArrival;
    private int serviceTime;
    private int executionTime;
    private final int priority;
    private int responseTime;
    boolean inputOutput;

    public Process(int ta, int et, int pr){
        timeArrival = ta;
        executionTime = et;
        priority = pr;
        inputOutput = false;
        responseTime = -1;
    }

    public void updateServiceTime(int st) { serviceTime = st; }
    public int getResponseTime() {return responseTime - timeArrival; }
    public int getTurnAroundTime() {return serviceTime - timeArrival; }

    public void updateInputOutput(boolean IO) {inputOutput = IO;}
    public int getPriority() {return priority;}
    public int getExecutionTime() {return executionTime;}
    public void run(int i){
        if (responseTime == -1) responseTime = i;
        executionTime--;
    }
}

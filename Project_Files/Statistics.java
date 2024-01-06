package Project_Files;

public class Statistics {
    private int numberOfProcesses;
    private int totalIOneeded;
    private int totalTurnAroundTime;
    private int totalResponseTime;
    
   

    public Statistics(){
        numberOfProcesses = totalIOneeded = totalResponseTime = totalTurnAroundTime = 0;
    }

    public void increaseNumberOfProcesses(){ numberOfProcesses++; }
    public void increasetotalIOneeded(){ totalIOneeded++; }
    public void increaseTotalResponseTime(int totalResponseTime) { this.totalResponseTime += totalResponseTime; }
    public void increaseTotalTurnAroundTime(int totalTurnAroundTime) {this.totalTurnAroundTime += totalTurnAroundTime; }

    public void printStatistics(){
        System.out.println("Number of Processes which Created: " + numberOfProcesses);
        System.out.println("Number of times I/O was needed: " + totalIOneeded);
        System.out.println("Average Response Time: " + (double)(totalResponseTime)/numberOfProcesses);
        System.out.println("Average Turnaround Time: " + (double)(totalTurnAroundTime)/numberOfProcesses);
    }
}

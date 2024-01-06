package Project_Files;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        CPU myCPU = new CPU();
        Queue mainList = new Queue(2000);
        Queue blockedList = new Queue(1000);
        Statistics myStatistics = new Statistics();
        Scheduler myScheduler = new Scheduler(myCPU, mainList, blockedList, myStatistics);
        boolean isCompleted = false;
        int timeInSeconds = 1000;
        int i=0;
        while (!isCompleted || i < timeInSeconds){
            if (i < timeInSeconds){
                boolean spawnProcess = Math.random() < .5;
                if (spawnProcess){
                    int execution_time = (int) ((Math.random() * (10-5))+ 2);
                    int priority = (int) ((Math.random() * 5));
                    Process newProcess = new Process(i, execution_time,priority);
                    myScheduler.scheduleNewProcess(newProcess);
                    myStatistics.increaseNumberOfProcesses();
                }
            }
            i++;
            isCompleted = !myScheduler.scheduling(i);
        }
        myStatistics.printStatistics();
    }
}
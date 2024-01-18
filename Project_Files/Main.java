package Project_Files;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or

import java.util.List;

// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        CPU myCPU = new CPU();
        Queue mainList = new Queue(2000);
        Queue blockedList = new Queue(1000);
        Statistics myStatistics = new Statistics();
        Scheduler myScheduler = new Scheduler(myCPU, mainList, blockedList, myStatistics);
        boolean isCompleted = false;
        int timeInSeconds = 0;
        int quantum=0;
        double spawn_probability=0,IO_propability=0;
        String option;
        String option_io = null;
        
        do{
          System.out.println("User default parameters or custom? (y/n): ");  
          option=UserInput.getString();
        }while(!(option.equals("y") || option.equals("n")));
        
        
        if(option.equals("n"))
        {
           do{
            System.out.println("Set the time (in seconds) duration of the simulation: ");
            timeInSeconds=UserInput.getInteger();
           }while(timeInSeconds<=0);
         
        
           do{
            System.out.println("Set probability of the creation of a new Process (between 0.1-0.9): ");
            spawn_probability=UserInput.getDouble();
           }while(spawn_probability<0.1 || spawn_probability>0.9);
        
        
           do{
             System.out.println("Set probability of the Proccess needing I/O (between 0.1-0.9 or 0 to remove I/O completely): ");
             IO_propability=UserInput.getDouble();
           }while(IO_propability<0 && IO_propability>0.9);
           myCPU.setIO_propability(IO_propability);
          
          
           do{
            System.out.println("Set the quantum for the Round Robin Algorithm: ");
            quantum=UserInput.getInteger();
           }while(quantum<0);
           myScheduler.setIO_propability(quantum);
        }
        else if(option.equals("y"))
        {
            
           System.out.println("Have I/O? (y/n): ");
             option_io=UserInput.getString();  
           }while(!(option_io.equals("y") || option_io.equals("n")));
           
           if(option_io.equals("n")){
             IO_propability=0;
             }
           else if(option_io.equals("y")){
             IO_propability=0.7;
           }
             timeInSeconds=20;
             spawn_probability=0.5;
             quantum=4;
             myCPU.setIO_propability(IO_propability);
             myScheduler.setIO_propability(quantum);
           
             System.out.println("Default values loaded");
             System.out.println("Time in seconds:20");
             System.out.println("Process Creation probability:0.5");
             System.out.println("Process needing I/O probability:"+IO_propability);
             System.out.println("Quantum for the Round Robin Algorithm:4");
             System.out.println(" ");
 
           
        

        
        int i=0;
        while (!isCompleted || i < timeInSeconds){
            if (i < timeInSeconds){
                boolean spawnProcess = Math.random() < spawn_probability; 
                if (spawnProcess){
                    int execution_time = (int) ((Math.random() * (10-5))+ 2);
                    int priority = (int) ((Math.random() * 5));
                    String name="p"+i;
                    Process newProcess = new Process(name,i, execution_time,priority);
                    myScheduler.scheduleNewProcess(newProcess);
                    myStatistics.increaseNumberOfProcesses();
                   
                }
            }
            
            i++;
            isCompleted = !myScheduler.scheduling(i);
        }
        System.out.println(" ");
        List<Process> finishedProcesses = myScheduler.getfinishedProcesses();
        for(Process p: finishedProcesses){
           System.out.println("Process:"+p.getprocessname());
           System.out.println("Priority:"+p.getPriority());
           System.out.println("Time Arrival: " + p.getArrivalTime());
           System.out.println("Time Service: " + p.getServiceTime());
           System.out.println("Turnaround Time: " + p.getTurnAroundTime());
           System.out.println("Response Time: " + p.getResponseTime());
           System.out.println("---------------------------------------------------------");
        }
        myStatistics.printStatistics();
    }
}
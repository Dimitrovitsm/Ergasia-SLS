package Project_Files;

public class Scheduler {
    private CPU cpu;
    private final Queue mainList;
    private final Queue blockedList;
    private int quantum,initquantum;

    private final Statistics statistics;

    public Scheduler(CPU cpu, Queue mainList, Queue blockedList, Statistics statistics){
        this.cpu = cpu;
        this.mainList = mainList;
        this.blockedList = blockedList;
        this.statistics = statistics;
        //initializeQuantum();
    }

    public void scheduleNewProcess(Process process){
        mainList.insert(process); // every time a new process is created, scheduler add it to the main queue
    }

    private void moveFromBlockedToMainList(){
        if (blockedList.getSize() > 0){ // if the blocked queue has processes
            boolean canMove = Math.random() < 0.7; // randomly select if we can move that process with the highest priority
            if (canMove) { // if we can move it
                mainList.insert(blockedList.extractMax()); // we insert it to the main queue
                blockedList.removeMax(); // and remove it from the blocked queue
            }
        }
    }

    private boolean isCPURunningProcess(){
        // if there isn't process in CPU then no process is executed
        if (cpu.getCurrentProcess() != null)
            return true;
        return false;
    }

    private boolean hasJob(){
        // a scheduler hasn't got another job if the CPU is available and the lists are empty.
        if (cpu.getCurrentProcess() == null && mainList.getSize() == 0 && blockedList.getSize() == 0)
            return false;
        return true;
    }

    private void initializeQuantum(){
          quantum = initquantum;
    }
    
    
    public void setIO_propability(int quantum){
       this.quantum=quantum;
       initquantum=quantum;
       
     }
     
     
    public double getIO_propability(){
       return quantum;
   }

    private void updateStatistics(){
        statistics.increaseTotalResponseTime(cpu.getCurrentProcess().getResponseTime());
        statistics.increaseTotalTurnAroundTime(cpu.getCurrentProcess().getTurnAroundTime());
    }

    private CPUProcessStatus executeInCPU(int i){
        CPUProcessStatus status;
        // if CPU doesn't run any process and there are processes in Queue
        if (!isCPURunningProcess() && mainList.getSize() > 0){
            Process process = mainList.extractMax(); // get that process with the highest priority
            mainList.removeMax(); // remove it from queue
            cpu.dispatch(process); // and dispatch it to cpu
            quantum--;
            status = cpu.execute(i); // start immediately the execution and decrease the quantum
        }else{ // in other case the process that is already in cpu is executed
            status = cpu.execute(i);
            quantum--;
        }
        return status; // return the status of the process in the CPU.
    }
    public boolean scheduling(int i){
        moveFromBlockedToMainList(); // first check if there is a process that have to be moved in main queue from blocked queue
        CPUProcessStatus status = executeInCPU(i); // make a cycle in CPU

        if (status == CPUProcessStatus.IO_STATUS){  // if the process needs IO
            blockedList.insert(cpu.getCurrentProcess()); // move the process from CPU to blocked queue
            cpu.dispatch(null); // and make the CPU available for other process
        }else if (status == CPUProcessStatus.COMPLETED_STATUS){ // if the process has been completed
            updateStatistics(); // update the statistics
            cpu.dispatch(null); // make the CPU available for use.
        }else if (status == CPUProcessStatus.RUNNING_STATUS){ // if the process is still running
            if (quantum == 0 && mainList.getSize() > 0){ // but the quantum has reached
                Process process = mainList.extractMax(); // get another process from the queue
                if (process.getPriority() <= cpu.getCurrentProcess().getPriority()){ // if the process has higher priority
                    mainList.removeMax();
                    mainList.insert(cpu.getCurrentProcess()); // add the previous running process in queue
                    cpu.dispatch(process); // and dispatch the new process to the CPU
                }
                initializeQuantum(); // initialize the quantum value in any case.
            }
        }
        return hasJob(); // if there is another job to be done returns false.
    }

}

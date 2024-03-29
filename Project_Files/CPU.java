package Project_Files;

public class CPU {
    private Process currentProcess; // the current process that is executed
    private double IO_propability;
    
    public CPU(){currentProcess = null;} // initially no process is executed
    
    
    public Process getCurrentProcess() { return currentProcess;}
    public void dispatch(Process process) {currentProcess = process;} // add a process to CPU
    public CPUProcessStatus execute(int i){ // execute a process
        if (currentProcess != null){  // if there is a process in CPU
            System.out.println(currentProcess.getprocessname()+" is executed");
            
            boolean IO = Math.random() < IO_propability; // randomly select if that process needs IO 
            currentProcess.run(i); // we execute the process
            currentProcess.updateInputOutput(IO); // and update the status of IO
            currentProcess.updateServiceTime(i); // and the service time
            if (IO)  return CPUProcessStatus.IO_STATUS;
            if (currentProcess.getExecutionTime() <= 0)  return CPUProcessStatus.COMPLETED_STATUS; // if the execution time is zero the process has been completed
        }
        return CPUProcessStatus.RUNNING_STATUS;
    }


    
     public void setIO_propability(double IO_propability){
       this.IO_propability=IO_propability;
     }
     
     
     public double getIO_propability(){
       return IO_propability;
   }
     

    
}

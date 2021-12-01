/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpuscheduling;

import java.util.Scanner;

/**
 *
 * @author sam
 */
public class CPUScheduling {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println(" ---- CPU Scheduling ----");
        System.out.println("1) First come first Served");
        System.out.println("2) Non-Premptive  Shortest Job First");
        System.out.println("3) Premptive Shortest Job First");
        System.out.println("4) Priority Scheduling");
        System.out.println("5) Round Robin");
        System.out.print("Enter:");
        Scanner sc = new Scanner(System.in);
        int choice = Integer.parseInt(sc.nextLine());
        System.out.println();
        
        //Get processes
        System.out.print("Enter the number of processes:");
        int nProcess = Integer.parseInt(sc.nextLine());
        int[] process = new int[nProcess];
        for(int i = 0; i < nProcess; i++){
            process[i] = i+1;
        }
        System.out.println();
        
        
        switch(choice) {
            
            //FCFS
            case 1: {
                
                System.out.println("--- FCFS ---");
                System.out.println();
                
                //Get burst times
                int[] burstTime = new int[nProcess];
                System.out.println("Enter burst time for each process");
                for (int i = 0; i < nProcess; i++) {
                    System.out.print("Process " + (i + 1) + ":");
                    
                    int btime = Integer.parseInt(sc.nextLine());
                    burstTime[i] = btime;
                }
                
                
                FCFS(nProcess, process, burstTime);
                
            } break;
            
            
            //SJF-NP
            case 2: {
                System.out.println("--- SJF-NP ---");
                System.out.println();
                
                //Get burst times
                int[] burstTime = new int[nProcess];
                System.out.println("Enter burst time for each process");
                for (int i = 0; i < nProcess; i++) {
                    System.out.print("Process " + (i + 1) + ":");
                    int btime = Integer.parseInt(sc.nextLine());
                    burstTime[i] = btime;
                }
                System.out.println();
                
                //Get arrival times
                int[] arrivalTime = new int[nProcess];
                System.out.println("Enter arrival time for each process");
                for (int i = 0; i < nProcess; i++) {
                    System.out.print("Process " + (i + 1) + " bt-" + burstTime[i] + ":");
                    int atime = Integer.parseInt(sc.nextLine());
                    arrivalTime[i] = atime;
                }
                
                SJF_NP(nProcess, process, burstTime, arrivalTime);
                
                
                
                
            } break;
            
            //SJF-P Shortest Remaining Time First
            case 3: {
                System.out.println("--- SJF-P ---");
                System.out.println();
                
                //Get burst times
                int[] burstTime = new int[nProcess];
                System.out.println("Enter burst time for each process");
                for (int i = 0; i < nProcess; i++) {
                    System.out.print("Process " + (i + 1) + ":");
                    int btime = Integer.parseInt(sc.nextLine());
                    burstTime[i] = btime;
                }
                System.out.println();
                
                //Get arrival times
                int[] arrivalTime = new int[nProcess];
                System.out.println("Enter arrival time for each process");
                for (int i = 0; i < nProcess; i++) {
                    System.out.print("Process " + (i + 1) + " bt-" + burstTime[i] + ":");
                    int atime = Integer.parseInt(sc.nextLine());
                    arrivalTime[i] = atime;
                }
                
                SJF_P(nProcess, process, burstTime, arrivalTime);
                
                    
                
            } break;
            
            //Priority
            case 4: {
                System.out.println("--- PRIORITY ---");
                System.out.println();
                
                //Get burst times
                int[] burstTime = new int[nProcess];
                System.out.println("Enter burst time for each process");
                for (int i = 0; i < nProcess; i++) {
                    System.out.print("Process " + (i + 1) + ":");
                    int btime = Integer.parseInt(sc.nextLine());
                    burstTime[i] = btime;
                }
                System.out.println();
                
                //Get priority
                int[] priority = new int[nProcess];
                System.out.println("Enter priority for each process");
                for (int i = 0; i < nProcess; i++) {
                    System.out.print("Process " + (i + 1) + " bt-" + burstTime[i] + ":");
                    int pri = Integer.parseInt(sc.nextLine());
                    priority[i] = pri;
                }
                System.out.println();
                
                //Get arrival times
                int[] arrivalTime = new int[nProcess];
                System.out.println("Enter arrival time for each process");
                for (int i = 0; i < nProcess; i++) {
                    System.out.print("Process " + (i + 1) + " bt-" + burstTime[i] + ":");
                    int atime = Integer.parseInt(sc.nextLine());
                    arrivalTime[i] = atime;
                }
                
                
                Priority(nProcess, process, burstTime, priority, arrivalTime);
                
                
            } break;
            
            //RR
            case 5: {
                System.out.println("--- RR ---");
                System.out.println();
                
                //Get burst times
                int[] burstTime = new int[nProcess];
                System.out.println("Enter burst time for each process");
                for (int i = 0; i < nProcess; i++) {
                    System.out.print("Process " + (i + 1) + ":");
                    
                    int btime = Integer.parseInt(sc.nextLine());
                    burstTime[i] = btime;
                }
                System.out.println();
                
                
                System.out.print("Enter Time Quantum:");
                int quantum = Integer.parseInt(sc.nextLine());
                
                RR(nProcess, process, burstTime, quantum);
                
                
            } break;
            
        }

    }
    
    
    private static void FCFS(int nProcess, int[] processes, int[] burstTime){
        
        int[] waitTime = new int[nProcess];
        int[] taTime = new int[nProcess];
        
        //Wait time
        
        waitTime[0] = 0;
        for (int i=1; i < nProcess; i++){
            waitTime[i] = burstTime[i-1] + waitTime[i-1];
        }
        
        //Turnaround time
        
        for (int i=0; i < nProcess; i++){
            taTime[i] = burstTime[i] + waitTime[i];
        }
        
        //Total wait time
        //Total turnaround time
        
        int totalWT = 0;
        int totalTA = 0;
        
        //Display as a table
        System.out.println();
        String[] header = new String[]{"PROCESS", "BURST TIME", "WAIT TIME", "TURNAROUND TIME"};
        System.out.format("%10s  |  %10s  | %10s  | %10s\n", (Object[]) header);

        for (int i = 0; i < nProcess; i++) {
            totalWT += waitTime[i];
            totalTA += taTime[i];

            System.out.format("%10s     %10s    %10s    %10s\n", processes[i], burstTime[i], waitTime[i], taTime[i]);
        }

        //Calculate Average Waiting time
        System.out.println();
        float averageWT = (float) totalWT / (float) nProcess;
        float averageTA = (float) totalTA / (float) nProcess;
        System.out.println("Average Waiting Time: " + averageWT);
        System.out.println("Averate Turnaround Time: " + averageTA);

    }
    
    private static void SJF_P(int nProcess, int[] process, int[] burstTime, int[] arrivalTime) {
         
         int[] waitTime = new int[nProcess];
         int[] taTime = new int[nProcess];
         
         
         sortByArrival(process, burstTime, arrivalTime, nProcess); //Pass by reference
         System.out.println();
         
         int systemTime = 0;
         waitTime[0] = 0;
         
         //Duplicate the burstTime into remaining time
         int remainTime[] = new int[nProcess];
         System.arraycopy(burstTime, 0, remainTime, 0, nProcess);
         
         int mini = Integer.MAX_VALUE;
         int miniIndex = 0;
         
         int done = nProcess;
         int infiniteLoopBreak = 50;
         
         while(done > 0) {
             
             //Check the current time (system) againt arrival time
             //Find the minimum values that have remaining time
             
             for (int i = 0; i < nProcess; i++) {
                 //if the process has executed yet
                 if(arrivalTime[i] <= systemTime) {
                     
                     if(remainTime[i] < mini && remainTime[i] > 0) {
                         
//                         System.out.print("i:" + i);
//                         System.out.print(" rt:" + remainTime[i]);
//                         System.out.print(" min:" + mini);
//                         System.out.print(" minInd:" + miniIndex);
//                         System.out.println(" syst:" + systemTime);
//                         System.out.println();
                         mini = remainTime[i];
                         miniIndex = i;
                         
                         
                     }
                 }
             }
             
             remainTime[miniIndex]--;
             mini = remainTime[miniIndex];
             
             if(mini == 0 ){
                 mini = Integer.MAX_VALUE;
             }
             
             
             if(remainTime[miniIndex] == 0 ){
                 done--;
                 taTime[miniIndex] = (systemTime + 1) - arrivalTime[miniIndex];
                 waitTime[miniIndex] = taTime[miniIndex] - burstTime[miniIndex];
             }
             
             
             systemTime++;
             if(infiniteLoopBreak > 0) {
                infiniteLoopBreak--;
             } else {
                 break;
             }
         }
         System.out.println();
         
//         for (int i = 0; i < nProcess; i++) {
//             System.out.println(waitTime[i]);
//         }



         //Total wait time
         //Total turnaround time
         int totalWT = 0;
         int totalTA = 0;

         //Display as a table
         System.out.println();
         String[] header = new String[]{"PROCESS", "ARRIVAL TIME", "BURST TIME", "WAIT TIME", "TURNAROUND TIME"};
         System.out.format("%10s  |  %10s  | %10s  | %10s    |   %10s    \n", (Object[]) header);

         for (int i = 0; i < nProcess; i++) {
             totalWT += waitTime[i];
             totalTA += taTime[i];

             System.out.format("%10s     %10s    %10s    %10s    %10s\n", process[i], arrivalTime[i], burstTime[i], waitTime[i], taTime[i]);
         }

         //Calculate Average Waiting time
         System.out.println();
         float averageWT = (float) totalWT / (float) nProcess;
         float averageTA = (float) totalTA / (float) nProcess;
         System.out.println("Average Waiting Time: " + averageWT);
         System.out.println("Averate Turnaround Time: " + averageTA);
         
         
     }
    
    private static void sortByArrival(int[] process, int[] burstTime, int[] arrivalTime, int nProcess) {

        int temp;
        
        //Bubble Sort Algorithm
        for (int j = 0; j < nProcess; j++) {
            for (int i = 1; i < nProcess; i++) {

                if (arrivalTime[i] < arrivalTime[i - 1]) {

                    //Swap arrival time
                    temp = arrivalTime[i - 1];
                    arrivalTime[i - 1] = arrivalTime[i];
                    arrivalTime[i] = temp;

                    //Swap processes
                    temp = process[i - 1];
                    process[i - 1] = process[i];
                    process[i] = temp;

                    //Swap burstTime
                    temp = burstTime[i - 1];
                    burstTime[i - 1] = burstTime[i];
                    burstTime[i] = temp;

                } else if (arrivalTime[i] == arrivalTime[i - 1]) {
                    //If the same arrival time
                    if (burstTime[i] < burstTime[i - 1]) {
                        
                        //Swap arrival time
                        temp = arrivalTime[i - 1];
                        arrivalTime[i - 1] = arrivalTime[i];
                        arrivalTime[i] = temp;

                        //Swap processes
                        temp = process[i - 1];
                        process[i - 1] = process[i];
                        process[i] = temp;

                        //Swap burstTime
                        temp = burstTime[i - 1];
                        burstTime[i - 1] = burstTime[i];
                        burstTime[i] = temp;
                        
                    }
                }

            }

        }
        
        
        
        
        
        
        
    }
    
    private static void sortByArrivalP(int[] process, int[] burstTime, int[] arrivalTime, int nProcess, int[] priority) {

        int temp;
        
        //Bubble Sort Algorithm
        for (int j = 0; j < nProcess; j++) {
            for (int i = 1; i < nProcess; i++) {

                if (arrivalTime[i] < arrivalTime[i - 1]) {

                    //Swap arrival time
                    temp = arrivalTime[i - 1];
                    arrivalTime[i - 1] = arrivalTime[i];
                    arrivalTime[i] = temp;

                    //Swap processes
                    temp = process[i - 1];
                    process[i - 1] = process[i];
                    process[i] = temp;

                    //Swap priority
                    temp = priority[i - 1];
                    priority[i - 1] = priority[i];
                    priority[i] = temp;

                    //Swap burstTime
                    temp = burstTime[i - 1];
                    burstTime[i - 1] = burstTime[i];
                    burstTime[i] = temp;
                    
                    

                } else if (priority[i] == priority[i - 1]) {
                    //If the same arrival time
                    if (priority[i] < priority[i - 1]) {
                        
                        //Swap arrival time
                        temp = arrivalTime[i - 1];
                        arrivalTime[i - 1] = arrivalTime[i];
                        arrivalTime[i] = temp;

                        //Swap processes
                        temp = process[i - 1];
                        process[i - 1] = process[i];
                        process[i] = temp;

                        //Swap burstTime
                        temp = burstTime[i - 1];
                        burstTime[i - 1] = burstTime[i];
                        burstTime[i] = temp;

                        //Swap priority
                        temp = priority[i - 1];
                        priority[i - 1] = priority[i];
                        priority[i] = temp;
                    }
                }

            }

        }
        
        
        
        
        
        
        
    }
    
    private static void sortByProirity(int[] process, int[] burstTime, int[] proirity, int nProcess) {

        int temp;
        
        //Bubble Sort Algorithm
        for (int j = 0; j < nProcess; j++) {
            for (int i = 1; i < nProcess; i++) {

                if (proirity[i] < proirity[i - 1]) {

                    //Swap arrival time
                    temp = proirity[i - 1];
                    proirity[i - 1] = proirity[i];
                    proirity[i] = temp;

                    //Swap processes
                    temp = process[i - 1];
                    process[i - 1] = process[i];
                    process[i] = temp;

                    //Swap burstTime
                    temp = burstTime[i - 1];
                    burstTime[i - 1] = burstTime[i];
                    burstTime[i] = temp;

                } else if (proirity[i] == proirity[i - 1]) {
                    //If the same arrival time
                    if (burstTime[i] < burstTime[i - 1]) {
                        
                        //Swap arrival time
                        temp = proirity[i - 1];
                        proirity[i - 1] = proirity[i];
                        proirity[i] = temp;

                        //Swap processes
                        temp = process[i - 1];
                        process[i - 1] = process[i];
                        process[i] = temp;

                        //Swap burstTime
                        temp = burstTime[i - 1];
                        burstTime[i - 1] = burstTime[i];
                        burstTime[i] = temp;
                        
                    }
                }

            }

        }
        
        
        
        
        
        
        
    }

    private static void SJF_NP(int nProcess, int[] process, int[] burstTime, int[] arrivalTime) {
        
        int[] waitTime = new int[nProcess];
        int[] taTime = new int[nProcess];
        
        //Sort the process table by arrival time
        //If the same arrival time sort by burst time
        sortByArrival(process, burstTime, arrivalTime, nProcess); //Pass by reference
        System.out.println();
        
//        System.out.format("%15s   %15s    %15s\n", "Arrival Time", "Burst Time", "Process");
//        for(int i = 0; i < nProcess; i++){
//            System.out.format("%15d    %15d    %15d\n", arrivalTime[i], burstTime[i], process[i]);
//        }
        
        //Gantt chart <system time> process <system time> 
        System.out.println("Gantt chart");
 
        //Find waiting time
        int systemTime = burstTime[0];
        waitTime[0] = 0;
        
        int[] full = new int[nProcess]; //Used to find the empty spots in waitTime
        full[0] = 1;
        System.out.print("<0t> p1 ");
        for (int j = 1; j < nProcess; j++) {
            //If the process has arrived after previous burst time
            if (arrivalTime[j] >= systemTime) {
                

                int minBurst = 0;
                int count = 0;
                
                //Find minimum burst time and assign the system time
                //Only look through indexs that dont have waitTime assigned
                for (int k = 1; k < j; k++) {
                   
                    if (full[k] == 0) {
                        if (count == 0) {
                            minBurst = k;
                        } else {

                            if (burstTime[k] < burstTime[minBurst]) {
                                minBurst = k;
                            }

                        }
                    }
                    count++;
                }
                
                
                System.out.print("<" + systemTime + "t>  p" + process[minBurst] + " ");
                
                //Set waitTime to systemTime
                //Set the empty spot in waitTime to 1
                //Increment systemTime with burstTime
                waitTime[minBurst] = systemTime - arrivalTime[minBurst];
                full[minBurst] = 1;
                systemTime += burstTime[minBurst];
                
            }
        }
        //Find the minimum burst time for the remaining
        for (int i = 0; i < nProcess; i++) {
            int minBurst = 0;
            int count = 0;
            if (full[i] == 0) {
                for (int k = 0; k < nProcess; k++) {
                    if (full[k] == 0) {
                        if (count == 0) {
                            minBurst = k;
                        } else {
                            
                            if (burstTime[k] < burstTime[minBurst]) {
                                minBurst = k;
                            } else if(burstTime[k] == burstTime[minBurst]){
                                if(arrivalTime[k] < arrivalTime[minBurst]){
                                    minBurst = k;
                                    
                                }
                            }

                        }
                    }
                    count++;
                }
                
                System.out.print("<" + systemTime + "t>  p" + process[minBurst] + " ");
                waitTime[minBurst] = systemTime - arrivalTime[minBurst];
                full[minBurst] = 1;
                systemTime += burstTime[minBurst];

            }
            
            //The last empty waitTime
            if(i == (nProcess-1)){
                for(int k = 0; k < nProcess; k++) {
                    if(full[k] == 0) {
                        waitTime[k] = systemTime - arrivalTime[k];
                        full[k] = 1;
                        
                        System.out.print("<" + systemTime + "t>  p" + process[k] + " ");
                        systemTime += burstTime[k];
                    }
                }
            }
            
            
        }
        System.out.print("<" + systemTime + "t>");
        System.out.println();
        
//        System.out.println();
//        for(int i = 0; i < nProcess; i++){
//            System.out.println(waitTime[i]);
//        }
        
        
        //Turnaround time
        for (int i=0; i < nProcess; i++){
            taTime[i] = burstTime[i] + waitTime[i];
        }
        
        //Total wait time
        //Total turnaround time
        int totalWT = 0;
        int totalTA = 0;

        //Display as a table
        System.out.println();
        String[] header = new String[]{"PROCESS", "ARRIVAL TIME", "BURST TIME", "WAIT TIME", "TURNAROUND TIME"};
        System.out.format("%10s  |  %10s  | %10s  | %10s    |   %10s    \n", (Object[]) header);

        for (int i = 0; i < nProcess; i++) {
            totalWT += waitTime[i];
            totalTA += taTime[i];

            System.out.format("%10s     %10s    %10s    %10s    %10s\n", process[i], arrivalTime[i], burstTime[i], waitTime[i], taTime[i]);
        }

        //Calculate Average Waiting time
        System.out.println();
        float averageWT = (float) totalWT / (float) nProcess;
        float averageTA = (float) totalTA / (float) nProcess;
        System.out.println("Average Waiting Time: " + averageWT);
        System.out.println("Averate Turnaround Time: " + averageTA);

    }
    
    private static void Priority(int nProcess, int[] processes, int[] burstTime, int[] priority, int[]arrivalTime) {
        
        
        int[] waitTime = new int[nProcess];
        int[] taTime = new int[nProcess];
        
        sortByArrivalP(processes, burstTime, arrivalTime, nProcess, priority);
        
        
        waitTime[0] = 0;
        taTime[0] = waitTime[0] + burstTime[0];
        int minIndex = 0;
        int min = Integer.MAX_VALUE;
        int count = nProcess-1;
        int[] full = new int[nProcess];
        full[0] = 1;
        int systemTime = burstTime[0];
        while(count > 0) {
            
            boolean done = false;

            for (int i = 0; i < nProcess; i++) {
                if(arrivalTime[i] <= systemTime){
                    if(priority[i] < min && full[i] == 0){    

                        min = priority[i];
                        minIndex = i;
                    }
                }
                
            }
            
            if(min != Integer.MAX_VALUE) {
                count--;
                full[minIndex] = 1;
                systemTime += burstTime[minIndex];
                taTime[minIndex] = systemTime - arrivalTime[minIndex];
                waitTime[minIndex] = taTime[minIndex] - burstTime[minIndex];
                
                min = Integer.MAX_VALUE;
            } else {
                systemTime += 1;
            }
            
            //Infinite loop break
//            if(systemTime > 50){
//                break;
//            }
            
            
        }

        //Total wait time
        //Total turnaround time
        int totalWT = 0;
        int totalTA = 0;

        //Display as a table
        System.out.println();

        String[] header = new String[]{"PROCESS", "PRIORITY", "ARRIVAL TIME", "BURST TIME", "WAIT TIME", "TURNAROUND TIME"};
        System.out.format("%10s     |   %10s    |   %10s    |   %10s    |   %10s    | %10s  |\n", (Object[]) header);

        for (int i = 0; i < nProcess; i++) {
            totalWT += waitTime[i];
            totalTA += taTime[i];

            System.out.format("%10s         %10s        %10s        %10s       %10s          %10s\n", processes[i], priority[i], arrivalTime[i], burstTime[i], waitTime[i], taTime[i]);
        }

        //Calculate Average Waiting time
        System.out.println();
        float averageWT = (float) totalWT / (float) nProcess;
        float averageTA = (float) totalTA / (float) nProcess;
        System.out.println("Average Waiting Time: " + averageWT);
        System.out.println("Averate Turnaround Time: " + averageTA);
        
        
        
    }
    
    private static void Priority(int nProcess, int[] processes, int[] burstTime, int[] priority) {
        
        int[] waitTime = new int[nProcess];
        int[] taTime = new int[nProcess];
        
        //Sort process table by priority
        sortByProirity(processes, burstTime, priority, nProcess);
        
        //Wait time
        waitTime[0] = 0;
        for (int i=1; i < nProcess; i++){
            waitTime[i] = burstTime[i-1] + waitTime[i-1];
        }
        
        //Turnaround time
        
        for (int i=0; i < nProcess; i++){
            taTime[i] = burstTime[i] + waitTime[i];
        }
        
        //Total wait time
        //Total turnaround time
        
        int totalWT = 0;
        int totalTA = 0;
        
        //Display as a table
        System.out.println();
        
        String[] header = new String[]{"PROCESS", "PRIORITY","BURST TIME", "WAIT TIME", "TURNAROUND TIME"};
        System.out.format("%10s     |   %10s    |   %10s    |   %10s    |   %10s\n", (Object[]) header);

        for (int i = 0; i < nProcess; i++) {
            totalWT += waitTime[i];
            totalTA += taTime[i];

            System.out.format("%10s         %10s        %10s     %10s     %10s\n", processes[i], priority[i],burstTime[i], waitTime[i], taTime[i]);
        }

        //Calculate Average Waiting time
        System.out.println();
        float averageWT = (float) totalWT / (float) nProcess;
        float averageTA = (float) totalTA / (float) nProcess;
        System.out.println("Average Waiting Time: " + averageWT);
        System.out.println("Averate Turnaround Time: " + averageTA);
        
    }
    
    private static void RR(int nProcess, int[] processes, int[] burstTime, int quantum) {
        
        int[] waitTime = new int[nProcess];
        int[] taTime = new int[nProcess];
        
        //Wait time
        
        waitTime[0] = 0;
        int systemTime = 0;
        
        int remainTime[] = new int[nProcess];
        System.arraycopy(burstTime, 0, remainTime, 0, nProcess);
        
        while(true){
            
            boolean done = true;
            
            for(int i = 0; i < nProcess; i++) {
                if(remainTime[i] > 0) {
                    
                    done = false;
                    if(remainTime[i] > quantum) {
                        
                        systemTime += quantum;
                        remainTime[i] -= quantum;
                    } else {
                        systemTime = systemTime + remainTime[i];
                        taTime[i] = systemTime;
                        waitTime[i] = systemTime - burstTime[i];
                        remainTime[i] = 0;
                    }
                }
            }
            
            if(done) {
                break;
            }
        }
        
        //Total wait time
        //Total turnaround time
        
        int totalWT = 0;
        int totalTA = 0;
        
        //Display as a table
        System.out.println();
        String[] header = new String[]{"PROCESS", "BURST TIME", "WAIT TIME", "TURNAROUND TIME"};
        System.out.format("%10s  |  %10s  | %10s  | %10s\n", (Object[]) header);

        for (int i = 0; i < nProcess; i++) {
            totalWT += waitTime[i];
            totalTA += taTime[i];

            System.out.format("%10s     %10s    %10s    %10s\n", processes[i], burstTime[i], waitTime[i], taTime[i]);
        }

        //Calculate Average Waiting time
        System.out.println();
        float averageWT = (float) totalWT / (float) nProcess;
        float averageTA = (float) totalTA / (float) nProcess;
        System.out.println("Average Waiting Time: " + averageWT);
        System.out.println("Averate Turnaround Time: " + averageTA);  
        
        
        
        
    }
    
    
}



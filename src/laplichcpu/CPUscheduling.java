package laplichcpu;

import Entity.Process;
import GanttChart.TestNumberic;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.jfree.ui.RefineryUtilities;

public class CPUscheduling {

    public static int S2I(String str) {
        return Integer.parseInt(str);
    } 
    public static Process[] InitDataProcesses(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner sc = new Scanner(file);
        int numberOfProcess = Integer.parseInt(sc.nextLine());
        Process arrProcess[] = new Process[numberOfProcess];
        int index = 0;
         while (sc.hasNextLine()) {            
             String curr = sc.nextLine();
             String[] arrCurr = curr.split(" ", 0); 
             arrProcess[index] = new Process(S2I(arrCurr[0]), S2I(arrCurr[1]), S2I(arrCurr[2]), 0, 0, 0);
             index++;
         }
         return arrProcess;
    }
    public static void drawFCSC(String TypeSheduling, String path) throws FileNotFoundException {
        Process[] arrProcesses = InitDataProcesses(path);
        
        for (int i = 0; i < 4; i++) {
            for (int j = i+1; j < 4; j++) {
                if (arrProcesses[i].getArrTime() > arrProcesses[j].getArrTime()) {
                    Process temp = new Process(0, 0, 0, 0, 0, 0);
                    temp = arrProcesses[i];
                    arrProcesses[i] = arrProcesses[j];
                    arrProcesses[j] = temp;
                }
            }
        }
        
        arrProcesses[0].setCompTime(arrProcesses[0].getArrTime() + arrProcesses[0].getBurtTime());
        for(int i = 1; i < 4; i++) {
            arrProcesses[i].setCompTime(arrProcesses[i-1].getCompTime() + arrProcesses[i].getBurtTime());
        }
        for(int i = 0; i < 4; i++) {
            arrProcesses[i].setTurnArrTime(arrProcesses[i].getCompTime() - arrProcesses[i].getArrTime());
            arrProcesses[i].setWaitingTime(arrProcesses[i].getTurnArrTime() - arrProcesses[i].getBurtTime());
        }
        
        
        System.out.println("Process\t\tAT\t\tBT\t\tCT\t\tTAT\t\tWT");
        for(int i = 0; i < 4; i++) {
            System.out.println(arrProcesses[i].getPid()+"\t\t\t" + arrProcesses[i].getArrTime()+ "\t\t" + arrProcesses[i].getBurtTime() + "\t\t" + arrProcesses[i].getCompTime() + "\t\t" + arrProcesses[i].getTurnArrTime() + "\t\t" + arrProcesses[i].getWaitingTime());
        }
        
        System.out.println("gantt chart: ");
        for(int i = 0; i < 4; i++) {
            System.out.print("P" + arrProcesses[i].getPid() +" ");
        }
        System.out.println();
        System.out.print("0 ");
        for(int i = 0; i < 4; i++) {
            System.out.print(arrProcesses[i].getCompTime() +" ");
        }
        
        TestNumberic ganttdemo = new TestNumberic(TypeSheduling, arrProcesses);
        ganttdemo.pack();
        RefineryUtilities.centerFrameOnScreen(ganttdemo);
        ganttdemo.setVisible(true);
    }

    
    
    public static void drawSJF(String TypeSheduling, String path) throws FileNotFoundException {
        Process[] arrProcesses = InitDataProcesses(path);
        
        
        int st=0, tot=0;
        while(true)
        {
            int c=arrProcesses.length, min = 999999;

            if (tot == arrProcesses.length)
                break;

            for (int i=0; i<arrProcesses.length; i++)
            {

                if ((arrProcesses[i].getArrTime() <= st) && (arrProcesses[i].getFocusCheck() == 0) && (arrProcesses[i].getBurtTime()<min))
                {
                    min=arrProcesses[i].getBurtTime();
                    c=i;
                }
            }
            if (c==arrProcesses.length)
                st++;
            else
            {
                arrProcesses[c].setCompTime(st + arrProcesses[c].getBurtTime());
                st+= arrProcesses[c].getBurtTime();
                arrProcesses[c].setTurnArrTime(arrProcesses[c].getCompTime()- arrProcesses[c].getArrTime());
                arrProcesses[c].setWaitingTime(arrProcesses[c].getTurnArrTime() - arrProcesses[c].getBurtTime() );
                arrProcesses[c].setFocusCheck(1);
                arrProcesses[tot].setPid(c+1);
                tot++;
                
            }
        }
        
        System.out.println("Process\t\tAT\t\tBT\t\tCT\t\tTAT\t\tWT");
        for(int i = 0; i < arrProcesses.length; i++) {
            System.out.println(arrProcesses[i].getPid()+"\t\t\t" + arrProcesses[i].getArrTime()+ "\t\t" + arrProcesses[i].getBurtTime() + "\t\t" + arrProcesses[i].getCompTime() + "\t\t" + arrProcesses[i].getTurnArrTime() + "\t\t" + arrProcesses[i].getWaitingTime());
        }
        
        System.out.println("gantt chart: ");
        for(int i = 0; i < arrProcesses.length; i++) {
            System.out.print("P" + arrProcesses[i].getPid() +" ");
        }
        System.out.println();
        System.out.print("0 ");
        for(int i = 0; i < arrProcesses.length; i++) {
            System.out.print(arrProcesses[arrProcesses[i].getPid() - 1].getCompTime() +" ");
        }
        
        
        TestNumberic ganttdemo = new TestNumberic(TypeSheduling, arrProcesses);
        ganttdemo.pack();
        RefineryUtilities.centerFrameOnScreen(ganttdemo);
        ganttdemo.setVisible(true);
    
    }
}

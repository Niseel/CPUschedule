package GanttChart;

import Entity.Process;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import laplichcpu.CPUscheduling;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.ui.ApplicationFrame;


public class TestNumberic extends ApplicationFrame {

    public TestNumberic(String titleScheduling, Process[] arrProcesses) {
        super(titleScheduling);
        JPanel jpanel = createDemoPanel(titleScheduling, arrProcesses);
        jpanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(jpanel);
    }

    private static JFreeChart createChart(String title, IntervalCategoryDataset dataset) {
        final JFreeChart chart = GanttChartFactory.createGanttChart(
            title + " Scheduling", "Task", "Time", dataset, true, true, false);
        return chart;
    }

    private static IntervalCategoryDataset createDataset(String title, Process[] arrProcesses) {
        TaskSeries taskseries = new TaskSeries(title);
        Task task;
        switch(title)
            {
                case "FCFS":
                     task= new TaskNumeric("Process 1", 0, arrProcesses[0].getCompTime());
                    taskseries.add(task);

                    for (int i=1; i<arrProcesses.length; i++) {

                        int numberProcess = i+1;
                        String processTittle = "Process " + numberProcess;
                        System.out.println(processTittle);
                        Task task1 = new TaskNumeric(processTittle, arrProcesses[i-1].getCompTime(), arrProcesses[i].getCompTime());
                        taskseries.add(task1);
                    }
                    break;
                case "SJF":
                    task = new TaskNumeric("Process " + arrProcesses[0].getPid() , 0, arrProcesses[arrProcesses[0].getPid()-1].getCompTime());
                    taskseries.add(task);

                    for (int i=1; i<arrProcesses.length; i++) {
                        String processTittle = "Process " + arrProcesses[i].getPid();
                        System.out.println(processTittle);
                        Task task1 = new TaskNumeric(processTittle, arrProcesses[arrProcesses[i-1].getPid()-1].getCompTime(), arrProcesses[arrProcesses[i].getPid()-1].getCompTime());
                        taskseries.add(task1);
                    }
                    break;
                default:
                    break;
            }
        

        TaskSeriesCollection taskseriescollection = new TaskSeriesCollection();
        taskseriescollection.add(taskseries);
        return taskseriescollection;
    }

    public static JPanel createDemoPanel(String title, Process[] arrProcesses) {
        JFreeChart jfreechart = createChart(title, createDataset(title, arrProcesses));
        ChartPanel chartpanel = new ChartPanel(jfreechart);
        chartpanel.setMouseWheelEnabled(true);
        return chartpanel;
    }

//    public static void main(String args[]) {
//        
//    }



}
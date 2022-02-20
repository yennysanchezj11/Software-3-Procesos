package com.uptc.controller;

import com.uptc.models.Process;
import com.uptc.models.States;
import com.uptc.reports.Report;
import static com.uptc.models.States.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ExecuteProcess {

    private final Queue<Process> processes;
    private final List<Process> allProcess;

    private int timeProcess;   // cambia --
    private int timeCPU;       // lo que atiende la cpu
    private int totalTime;     // total de atencion de los procesos
    private Report report;

    public ExecuteProcess() {
        this.processes = new LinkedList<>();
        this.allProcess = new LinkedList<>();
        timeProcess = 0;
        totalTime = 0;
    }

    public void addProcessToQueue(Process p) {
        this.allProcess.add(p);
        this.processes.add(p);
        totalTime += p.getTime();
        p.states(0, 0, READY, INIT);
    }

    public void init() {
        this.timeCPU = 5;
        while (!processes.isEmpty()) {
            Process p = processes.poll();
            System.out.println("ATENDER");
            attendProcessCPU(p);
        }
    }

    private void attendProcessCPU(Process p) {
        System.out.println("ATENDIENDO PROCESO" + p.getName());
        System.out.println(p.getName()+"tiempo"+p.getTime());
        if (p.getTime() > timeCPU) { // 500 - 100
            p.setTime(timeCPU);
            isSuspendedReady(p,READY);
            p.states(timeProcess, timeProcess += timeCPU, EXECUTE, READY);
             if (p.isLocked()) {
                p.states(timeProcess, timeProcess, LOCKED, EXECUTE);
                System.out.println("BLOQUEADO NORMAL");
                isSuspendedLocked(p,LOCKED);
            } else {
                isSuspendedReady(p,EXECUTE);
            }
            processes.add(p);
        } else { // 50 100
            int timePi = p.getTime();
            p.setTime(timePi);
            isSuspendedReady(p,READY);
            p.states(timeProcess, timeProcess += timePi, EXECUTE, READY);
            if (p.isLocked()) {
                p.states(timeProcess, timeProcess, LOCKED, EXECUTE);
                isSuspendedLocked(p, LOCKED);
            } else {
                isSuspendedReady(p, EXECUTE);
            }
            
        }
    }

    private void isSuspendedReady(Process p, States lastState) {
        if(p.getisSuspendedReady() && lastState==READY || lastState==EXECUTE){
            System.out.println("suspender LISTO");
        p.states(timeProcess, timeProcess, SUSPENDEDREADY, lastState);
          if((p.getTime() > timeCPU)){
            p.states(timeProcess, timeProcess, READY, SUSPENDEDREADY);
          }else {
            p.states(timeProcess, timeProcess, EXIT, SUSPENDEDREADY); 
          }
        
        }
    }

    private void isSuspendedLocked(Process p, States lastState) {
        if(p.getIsSuspendedLocked() && lastState==LOCKED){
            System.out.println("suspender bloqueado");
            p.states(timeProcess, timeProcess, SUSPENDEDLOCKED, lastState);
            if(p.getEndEvent() && p.getisSuspendedReady()){
                p.states(timeProcess, timeProcess, SUSPENDEDREADY, SUSPENDEDLOCKED);
            }
            p.states(timeProcess, timeProcess, LOCKED, SUSPENDEDLOCKED);
        } else if(p.getEndEvent() && lastState==LOCKED){
            if((p.getTime() > timeCPU)){
            p.states(timeProcess, timeProcess, READY, lastState);
            } else {
            p.states(timeProcess, timeProcess, EXIT, lastState);
            }
        }
    }

  
    public void reports() {
        report = new Report(allProcess, totalTime, timeCPU);
    }

    public ArrayList<Object[]> reportMissingTimeProcess(){
        return report.getReportMissingTimeProcess();
    }

    public ArrayList<Object[]> reportStatusChangeProcess() {
        return report.getReportForStatusChangeProcess();
    }

    public ArrayList<Object[]> reportByReadyStates(){
        return report.getReportByReadyStates();
    }

    public ArrayList<Object[]> reportByExitState(){
        return report.getReportByExitState();
    }

    public ArrayList<Object[]> reportByLockedStates(){
        return report.getReportByLockedStates();
    }

   /* public ArrayList<Object[]> reportForStatusChange(){
        return report.getReportForStatusChange();
    }

*/
    public ArrayList<Object[]> reportByCpuExecuteOrder() {
        return report.reportByCpuExecuteOrder();
    }

    public ArrayList<Object[]> reportSuspendedReadyProcess() {
        return report.getReportBySuspendedReadyState();
    }

    public ArrayList<Object[]> reportSuspendedLockedProcess() {
        return report.getReportBySuspendedLockedState();
    }

    public String[] reportHeadersTable() {
        return report.headerTable();
    }
   
}

package com.uptc.controller;

import com.uptc.models.Process;
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
        this.processes.add(p);
        this.allProcess.add(p);
        System.out.println("PROCESOS EN LA LISTA"+ this.processes.size());
        System.out.println("PROCESOS EN LA LISTA 2"+ this.allProcess.size());
        totalTime += p.getTime();
        p.states(0, 0, READY, INIT);
    }

    public void init(int timeCPU) {
        this.timeCPU = timeCPU;
        while (!processes.isEmpty()) {
            Process p = processes.poll();
            attendProcessCPU(p);
        }
    }

    private void attendProcessCPU(Process p) {
        System.out.println("ATENDIENDO PROCESO"+ p.getName());
        if (p.getTime() > timeCPU) { // 500 - 100
            p.setTime(timeCPU);
            p.states(timeProcess, timeProcess += timeCPU, EXECUTE, READY);
            if (p.isLocked()) {
                p.states(timeProcess, timeProcess, LOCKED, EXECUTE);
                p.states(timeProcess, timeProcess, READY, LOCKED);
            } else {
                p.states(timeProcess, timeProcess, READY, EXECUTE);
            }

            processes.add(p);
        } else {   // 50 100
            int timePi = p.getTime();
            p.setTime(timePi);
            p.states(timeProcess, timeProcess += timePi, EXECUTE, READY);
            p.states(timeProcess, timeProcess, EXIT, EXECUTE);
        }
    }

    public void reports() {
        report = new Report(allProcess, totalTime, timeCPU);
        report.init();
    }

    public ArrayList<Object[]> reportMissingTimeProcess(){
        return report.getReportMissingTimeProcess();
    }

    public ArrayList<String[]> reportStatusChangeProcess() {
        return report.getReportForStatusChangeProcess();
    }

    public ArrayList<String[]> reportByReadyStates(){
        return report.getReportByReadyStates();
    }

    public ArrayList<String[]> reportByExitState(){
        return report.getReportByExitState();
    }

    public ArrayList<String[]> reportByLockedStates(){
        return report.getReportByLockedStates();
    }

    public ArrayList<Object[]> reportForStatusChange(){
        return report.getReportForStatusChange();
    }

    public ArrayList<String[]> reportByCpuExecuteOrder() {
        return report.reportByCpuExecuteOrder();
    }

   
}

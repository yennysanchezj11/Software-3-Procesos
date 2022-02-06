package com.uptc.reports;

import com.uptc.models.Process;
import com.uptc.models.Register;
import com.uptc.models.States;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.uptc.models.States.*;

public class Report {

    public static final ArrayList<Object[]> getReportByExecuteStates = null;
    private final List<Process> processes;
    private final Map<States, List<Register>> registers;
    private final int totalTime;
    private final int timeCPU;
    private final int timeApprox;

    public Report(List<Process> processList, int totalTime, int timeCPU) {
        this.processes = processList;
        this.timeCPU = timeCPU;
        this.totalTime = totalTime;
        this.timeApprox = getFinal();
        this.registers = getTotalRegisters().stream()
                .collect(Collectors.groupingBy(Register::getStatus));
    }

    public void init() {
       // out.println("TIEMPO TOTAL DE EJECUCION: " + totalTime + " MINUTOS");
        //reportTables();
        //reportByGroup();
        //getReportTotalProcess();
    }

    public ArrayList<Object[]> getReportMissingTimeProcess(){
        ArrayList<Object[]> aux= new ArrayList<>();
        processes.stream()
        .sorted(Comparator.comparing(Process::getName))
        .forEach(x -> aux.add(x.getTableByTime(timeApprox, timeCPU)));
        return aux;
    }

    public ArrayList<String[]> getReportForStatusChangeProcess() {
        ArrayList<String[]> aux= new ArrayList<>();
        processes.stream()
                .sorted(Comparator.comparing(Process::getName))
                .forEach(x -> aux.add(x.getTableByState(timeApprox, timeCPU)));
        return aux;
    }

    public ArrayList<String[]> getReportByReadyStates() {
        ArrayList<String[]> aux= new ArrayList<>();
            registers.get(READY).stream()
                    .sorted(Comparator.comparingInt(Register::getTimeEnd))
                    .map(x -> (aux.add(new String[]{ ""+x.getTimeEnd(),x.getProcess().getName()})));
    return aux;
    }

    public ArrayList<String[]> getReportByLockedStates() {
        ArrayList<String[]> aux= new ArrayList<>();
        registers.get(LOCKED).stream()
                .sorted(Comparator.comparingInt(Register::getTimeEnd))
                .map(x -> (aux.add(new String[]{ ""+x.getTimeEnd(),x.getProcess().getName()})));
    return aux;
    }
    
    public ArrayList<String[]> getReportByExitState() {
        ArrayList<String[]> aux= new ArrayList<>();
        registers.get(EXIT).stream()
                .sorted(Comparator.comparingInt(Register::getTimeEnd))
                .map(x -> (aux.add(new String[]{ ""+x.getTimeEnd(),x.getProcess().getName()})));
    return aux;
    }

    public ArrayList<String[]> reportByCpuExecuteOrder() {
        ArrayList<String[]> aux= new ArrayList<>();
        registers.get(EXECUTE).stream()
                .distinct()
                .sorted(Comparator.comparingInt(Register::getTimeInit))
                .map(x -> (aux.add(new String[]{ ""+x.getTimeInit(),""+x.getTimeEnd(),x.getProcess().getName()})));
        return aux;
    }

    public ArrayList<Object[]> getReportForStatusChange() {
        return null;
    }


   
    private void reportTables() {
        String head = headerTable();  
      /*  out.println("TABLA DEL TIEMPO FALTANTE POR PROCESO DESDE EL TIEMPO t=0");
        out.println(head);
        processes.stream()
                .sorted(Comparator.comparing(Process::getName))
                .forEach(x -> out.println(x.getTableByTime(timeApprox, timeCPU)));
        int size = head.split("\n")[0].length();
        out.println("-".repeat(size));
        out.println("\n".repeat(3));

        out.println("TABLA DEL CAMBIO DE ESTADOS DE LOS PROCESOS. DONDE");
        out.println("\tL=LISTOS, E= EJECUCION, B= BLOQUEO, S= SALIDA");
        out.println(head);
        processes.stream()
                .sorted(Comparator.comparing(Process::getName))
                .forEach(x -> out.println(x.getTableByState(timeApprox, timeCPU)));
        out.println("-".repeat(size)); */
    }

    private String headerTable() {
        StringBuilder head = new StringBuilder("P\\t ");
        for (int i = 0; i <= timeApprox; i += timeCPU) {
            head.append("| ").append(i).append(" ".repeat(4 - String.valueOf(i).length()));
        }
        head.append("\n");
        int size = head.length();
        head.append("-".repeat(size));
        head.insert(0, "-".repeat(size) + "\n");
        return head.toString();
    }

    private void reportByGroup() {
      /*  out.println("\n".repeat(2));
        out.println("REPORTES POR ESTADOS.");
        for (States state : new States[]{EXIT, READY, LOCKED}) {
            out.println("_".repeat(50));
            out.println("REPORTE POR ORDEN EN EL ESTADO: " + state.getName());
            out.println();
            out.println(" t -> P");
            out.println("--------");
            registers.get(state).stream()
                    .sorted(Comparator.comparingInt(Register::getTimeEnd))
                    .map(x -> x.getTimeEnd() + " -> " + x.getProcess().getName())
                    .forEach(out::println);
            out.println("_".repeat(50));
            out.println();
        }
        out.println("_".repeat(50));
        out.println("REPORTE POR ORDEN DE EJECUCION EN LA CPU");
        out.println();
        out.println("ti - tf : P");
        out.println("------------");
        registers.get(EXECUTE).stream()
                .distinct()
                .sorted(Comparator.comparingInt(Register::getTimeInit))
                .map(x -> x.getTimeInit() + " - " + x.getTimeEnd() + " : " + x.getProcess().getName())
                .forEach(out::println);
        out.println("_".repeat(50)); */
    }

    private List<Register> getTotalRegisters() {
        List<Register> totalRegisters = new ArrayList<>();
        processes.forEach(x -> totalRegisters.addAll(x.getAllRegisters()));
        return totalRegisters;
    }

    public void getReportTotalProcess() {
       /* out.println();
        out.println("REPORTE DEL PROCESO DE CAMBIO DE ESTADOS");
        out.println();
        getTotalRegisters().stream().distinct()
                .sorted((x, y) -> {
                    if (x.getTimeEnd() == y.getTimeEnd())
                        return x.getProcess().getName().compareTo(y.getProcess().getName());
                    else
                        return x.getTimeEnd() - y.getTimeEnd();
                }).forEach(this::printRegister); */
    }

    private void printRegister(Register x) {
        States state = x.getPreviousState();
        String nameProcess = x.getProcess().getName();
        switch (state) {
            case READY: // despachar
              // out.println("despachar(" + nameProcess + "): listo -> en_ejecucion");
                break;
            case INIT: // nuevo
               //  out.println("insertar(" + nameProcess + "): nuevo -> listo");
                break;
            case LOCKED:  // despertar
               // out.println("despertar(" + nameProcess + "): bloqueado-> listo");
                break;
            case EXECUTE: // salio- bloqueado-listos
                evaluateStatus(x, nameProcess);
                break;
        }
    }

    private void evaluateStatus(Register x, String name) {
        switch (x.getStatus()) {
            case READY:
              // out.println("tiempo_expirado(" + name + "): en_ejecucion -> listo");
                break;
            case LOCKED:
               // out.println("bloquear(" + name + "): en_ejecucion-> bloqueado");
                break;
            case EXIT:
               // out.println("finalizar(" + name + "): en_ejecucion-> salida");
                break;
        }
    }

    private int getFinal() {
        int aux = totalTime;
        while (aux % timeCPU != 0) aux++;
        return aux;
    }



}

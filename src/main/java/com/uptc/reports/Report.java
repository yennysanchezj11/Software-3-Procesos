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

    public ArrayList<Object[]> getReportMissingTimeProcess(){
        ArrayList<Object[]> aux= new ArrayList<>();
        processes.stream()
        .sorted(Comparator.comparing(Process::getName))
        .forEach(x -> aux.add(x.getTableByTime(timeApprox, timeCPU)));
        return aux;
    }

    public ArrayList<Object[]> getReportForStatusChangeProcess() {
        ArrayList<Object[]> aux= new ArrayList<>();
        processes.stream()
                .sorted(Comparator.comparing(Process::getName))
                .forEach(x -> aux.add(x.getTableByState(timeApprox, timeCPU)));
        return aux;
    }

    public ArrayList<Object[]> getReportByReadyStates() {
        ArrayList<Object[]> aux= new ArrayList<>();
            registers.get(READY).stream()
                    .sorted(Comparator.comparingInt(Register::getTimeEnd))
                    .map(x -> (aux.add(new String[]{ ""+x.getTimeEnd(),x.getProcess().getName()})))
                    .forEach(System.out::print);
    return aux;
    }

    public ArrayList<Object[]> getReportByLockedStates() {
        ArrayList<Object[]> aux= new ArrayList<>();
        registers.get(LOCKED).stream()
                .sorted(Comparator.comparingInt(Register::getTimeEnd))
                .map(x -> (aux.add(new String[]{ ""+x.getTimeEnd(),x.getProcess().getName()})))
                .forEach(System.out::print);
    return aux;
    }

    public ArrayList<Object[]> getReportBySuspendedLockedState() {
        ArrayList<Object[]> aux= new ArrayList<>();
            registers.get(SUSPENDEDLOCKED).stream()
                    .sorted(Comparator.comparingInt(Register::getTimeEnd))
                    .map(x -> (aux.add(new String[]{ ""+x.getTimeEnd(),x.getProcess().getName()})))
                    .forEach(System.out::print);
    return aux;
    }

    public ArrayList<Object[]> getReportBySuspendedReadyState() {
        ArrayList<Object[]> aux= new ArrayList<>();
            registers.get(SUSPENDEDREADY).stream()
                    .sorted(Comparator.comparingInt(Register::getTimeEnd))
                    .map(x -> (aux.add(new String[]{ ""+x.getTimeEnd(),x.getProcess().getName()})))
                    .forEach(System.out::print);
    return aux;
    }
    
    public ArrayList<Object[]> getReportByExitState() {
        ArrayList<Object[]> aux= new ArrayList<>();
        registers.get(EXIT).stream()
                .sorted(Comparator.comparingInt(Register::getTimeEnd))
                .map(x -> (aux.add(new String[]{ ""+x.getTimeEnd(),x.getProcess().getName()})))
                .forEach(System.out::print);
    return aux;
    }

    public ArrayList<Object[]> reportByCpuExecuteOrder() {
        ArrayList<Object[]> aux= new ArrayList<>();
        registers.get(EXECUTE).stream()
                .distinct()
                .sorted(Comparator.comparingInt(Register::getTimeInit))
                .map(x -> (aux.add(new String[]{ ""+x.getTimeInit(),""+x.getTimeEnd(),x.getProcess().getName()})))
                .forEach(System.out::print);
        return aux;
    }
  
    public String[] headerTable() {
        int size=Math.round(timeApprox/timeCPU)+2;
        int pos=0;
        String[] headers= new String[size];
        headers[pos]="Proceso / Tiempo";
        pos++;
        for (int i = 0; i <= timeApprox; i += timeCPU) {
                headers[pos]=""+i;
                pos++;
        }
       
        return headers;
    }


    private List<Register> getTotalRegisters() {
        List<Register> totalRegisters = new ArrayList<>();
        processes.forEach(x -> totalRegisters.addAll(x.getAllRegisters()));
        return totalRegisters;
    }

    public ArrayList<Object[]> getReportForSuspendedTransition() {
        ArrayList<Object[]> aux= new ArrayList<>();
        getTotalRegisters().stream().distinct()
                .sorted((x, y) -> {
                    if (x.getTimeEnd() == y.getTimeEnd())
                        return x.getProcess().getName().compareTo(y.getProcess().getName());
                    else
                        return x.getTimeEnd() - y.getTimeEnd();
                }).forEach(x -> aux.add(getReportForSuspendedTransition(x))); 
        return aux;
    }

    public ArrayList<Object[]> getReportForResumeTransition() {
        ArrayList<Object[]> aux= new ArrayList<>();
        getTotalRegisters().stream().distinct()
                .sorted((x, y) -> {
                    if (x.getTimeEnd() == y.getTimeEnd())
                        return x.getProcess().getName().compareTo(y.getProcess().getName());
                    else
                        return x.getTimeEnd() - y.getTimeEnd();
                }).forEach(x -> aux.add(getReportForResumeTransition(x))); 
        return aux;
    }

    public ArrayList<Object[]> getReportForSendTransition() {
        ArrayList<Object[]> aux= new ArrayList<>();
        getTotalRegisters().stream().distinct()
                .sorted((x, y) -> {
                    if (x.getTimeEnd() == y.getTimeEnd())
                        return x.getProcess().getName().compareTo(y.getProcess().getName());
                    else
                        return x.getTimeEnd() - y.getTimeEnd();
                }).forEach(x -> aux.add(getReportForSendTransition(x))); 
        return aux;
    }

    public ArrayList<Object[]> getReportForTimeTransition() {
        ArrayList<Object[]> aux= new ArrayList<>();
        getTotalRegisters().stream().distinct()
                .sorted((x, y) -> {
                    if (x.getTimeEnd() == y.getTimeEnd())
                        return x.getProcess().getName().compareTo(y.getProcess().getName());
                    else
                        return x.getTimeEnd() - y.getTimeEnd();
                }).forEach(x -> aux.add(getReportForTimeExpiredTransition(x))); 
        return aux;
    }

    public ArrayList<Object[]> getReportForTerminatioOperationTransition() {
        ArrayList<Object[]> aux= new ArrayList<>();
        getTotalRegisters().stream().distinct()
                .sorted((x, y) -> {
                    if (x.getTimeEnd() == y.getTimeEnd())
                        return x.getProcess().getName().compareTo(y.getProcess().getName());
                    else
                        return x.getTimeEnd() - y.getTimeEnd();
                }).forEach(x -> aux.add(getReportForTerminationOperation(x))); 
        return aux;
    }

    public ArrayList<Object[]> getReportForInitTransition() {
        ArrayList<Object[]> aux= new ArrayList<>();
        getTotalRegisters().stream().distinct()
                .sorted((x, y) -> {
                    if (x.getTimeEnd() == y.getTimeEnd())
                        return x.getProcess().getName().compareTo(y.getProcess().getName());
                    else
                        return x.getTimeEnd() - y.getTimeEnd();
                }).forEach(x -> aux.add(getReportForInit(x))); 
        return aux;
    }

    public ArrayList<Object[]> getReportForExitTransition() {
        ArrayList<Object[]> aux= new ArrayList<>();
        getTotalRegisters().stream().distinct()
                .sorted((x, y) -> {
                    if (x.getTimeEnd() == y.getTimeEnd())
                        return x.getProcess().getName().compareTo(y.getProcess().getName());
                    else
                        return x.getTimeEnd() - y.getTimeEnd();
                }).forEach(x -> aux.add(getReportForExit(x))); 
        return aux;
    }

    private Object[] getReportForSendTransition(Register x){
        States state = x.getPreviousState();
        States stateNext = x.getStatus();
        String nameProcess = x.getProcess().getName();
        Object[] exit=new Object[1];
        if(state==READY && stateNext==EXECUTE){
            exit[0]="despachar("+(nameProcess)+"): listo -> en_ejecucion";
        }
        return exit;
    }

    private Object[] getReportForTimeExpiredTransition(Register x){
        States state = x.getPreviousState();
        States stateNext = x.getStatus();
        String nameProcess = x.getProcess().getName();
        Object[] exit=new Object[1];
        if(state==EXECUTE && stateNext==READY){
            exit[0]="tiempo_expirado ("+(nameProcess)+"): en_ejecucion -> listo";
          }
          return exit;
        }
        

    private Object[] getReportForSuspendedTransition(Register x){
        States state = x.getPreviousState();
        States stateNext = x.getStatus();
        String nameProcess = x.getProcess().getName();
        Object[] exit=new Object[1];
        if(state==READY && stateNext==SUSPENDEDREADY){
            exit[0]="suspender ("+(nameProcess)+"): listo -> suspendido_listo";
        } else 
        if(state==LOCKED && stateNext==SUSPENDEDLOCKED){
            exit[0]="suspender ("+(nameProcess)+"): bloqueado -> suspendido_bloqueado";
        } 
        else 
        if(state==EXECUTE && stateNext==SUSPENDEDREADY){
            exit[0]="suspender ("+(nameProcess)+"): En ejecución -> suspendido_listo";
        } 
        return exit;
    }

    private Object[] getReportForTerminationOperation(Register x){
        States state = x.getPreviousState();
        States stateNext = x.getStatus();
        String nameProcess = x.getProcess().getName();
        Object[] exit=new Object[1];
        if(state==SUSPENDEDLOCKED&& stateNext==SUSPENDEDREADY){
            exit[0]="completar ("+(nameProcess)+"): suspendido_bloqueado -> suspendido_listo";
        }
        return exit;
    }

    private Object[] getReportForResumeTransition(Register x){
        States state = x.getPreviousState();
        States stateNext = x.getStatus();
        String nameProcess = x.getProcess().getName();
        Object[] exit=new Object[1];
        if(state==SUSPENDEDREADY && stateNext==READY){
            exit[0]="reanudar ("+(nameProcess)+"): suspendido_listo -> listo";
        } else 
        if(state==SUSPENDEDLOCKED && stateNext==LOCKED){
            exit[0]="reanudar ("+(nameProcess)+"): suspendido_bloqueado -> bloqueado";
        } 
        return exit;
    }

    private Object[] getReportForInit(Register x){
        States state = x.getPreviousState();
        String nameProcess = x.getProcess().getName();
        Object[] exit=new Object[1];
        if(state==INIT){
            exit[0]="insertar ("+(nameProcess)+"):  nuevo -> listo";
        }
        return exit;
    }

    private Object[] getReportForExit(Register x){
        States state = x.getPreviousState();
        States stateNext = x.getStatus();
        String nameProcess = x.getProcess().getName();
        Object[] exit=new Object[1];
        if(state==EXECUTE && stateNext==EXIT){
            exit[0]="finalizar ("+(nameProcess)+"): en_ejecucion -> salida";
        } else 
        if(state==LOCKED && stateNext==EXIT){
            exit[0]="finalizar ("+(nameProcess)+"): bloqueado -> salida";
        } else 
        if(state==SUSPENDEDREADY && stateNext==EXIT){
            exit[0]="finalizar ("+(nameProcess)+"): suspendido_listo -> salida";
        }
        return exit;
    } 
    private int getFinal() {
        int aux = totalTime;
        while (aux % timeCPU != 0) aux++;
        return aux;
    }



}

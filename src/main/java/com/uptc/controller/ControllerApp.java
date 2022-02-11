package com.uptc.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import com.uptc.models.Process;
import com.uptc.reports.Report;
import com.uptc.viewer.Constants;
import com.uptc.viewer.JFramePrincipal;
import com.uptc.viewer.reports.ReportTable;

public class ControllerApp implements ActionListener {
	ExecuteProcess executeProcess;
	JFramePrincipal jPrincipal;
	Report reportClass;
	String [] headersReports;
	ReportTable reportTable;

	public ControllerApp() {
		executeProcess = new ExecuteProcess();
		jPrincipal = new JFramePrincipal(this,  headersReports);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (Commands.valueOf(e.getActionCommand())) {
		case C_ADD_PROCESS:
			// agregar proceso a la tabla de procesos
			addProcessTable(this);
			System.out.println("agrege");

			break;
		case C_EXECUTE_PROCESS:
			// Ejecutar lista de procesos
			System.out.println("entre");
			executeProcess(this);
			System.out.println("ejecute");
			break;

		case C_CLOSE_APP:
			// Cerrar la app
			jPrincipal.close();
			break;

		case C_REPORT_MISSING_TIME_PER_PROCESS:
			// reporte por tiempo faltante por proceso
			reportTable.assignHeaders(Constants.headersR1);
			reportMissingTimeProcess();
			jPrincipal.reportTableVisibility(true);
			break;
		case C_REPORT_FOR_STATUS_CHANGE_PROCESS:
			// reporte por cambio de estado de los procesos
			reportTable.assignHeaders(Constants.headersR2);
			reportStatusChangeProcess();
			jPrincipal.reportTableVisibility(true);
			break;
		case C_REPORT_BY_READY_STATES:
			// reporte por orden en el estado en listo
			reportTable.assignHeaders(Constants.headersR3);
			reportByReadyStates();
			jPrincipal.reportTableVisibility(true);
			break;
		case C_REPORT_BY_EXECUTE_STATES:
			// reporte por orden en el estado de en ejecución
			reportTable.assignHeaders(Constants.headersR4);
			reportByCpuExecuteOrder();
			jPrincipal.reportTableVisibility(true);
			break;
		case C_REPORT_BY_LOCKED_STATES:
			// reporte por orden en el estado en bloqueo
			reportTable.assignHeaders(Constants.headersR5);
			reportByLockedStates();
			jPrincipal.reportTableVisibility(true);
			break;
		case C_REPORT_BY_EXIT_STATE:
			// reporte por orden en el estado en salida
			reportTable.assignHeaders(Constants.headersR6);
			reportByExitState();
			jPrincipal.reportTableVisibility(true);
			break;
		case C_REPORT_FOR_STATUS_CHANGE:
			// reporte por cambios de estado de cada proceso
			reportTable.assignHeaders(Constants.headersR7);
			reportForStatusChange();
			jPrincipal.reportTableVisibility(true);
			break;

		case C_DELETTE_PROCESS:
			// reporte por orden en el estado en salida
			System.out.println("se eliminooooo");
			break;

		default:
			break;
		}
	}

	public void addProcessTable(ActionListener actionListener) {
		jPrincipal.setInformationProcessTable(actionListener);
	}

	public void executeProcess(ActionListener actionListener) {
		// set time
		int time = jPrincipal.getTimeCPU();
		// set lista de procesos
		executeListProcess(time, jPrincipal.getInformation(actionListener));
	}

	public void executeListProcess(int time, ArrayList<Object[]> listProcess) {
		int timeCPU = time;
		// listProcess.forEach(x -> executeProcess.addProcessToQueue(new Process(
		// x[0],x[1] ,x[2])));
		for (int i = 0; i < listProcess.size(); i++) {
			Object[] vector = (Object[]) listProcess.get(i);
			System.out.println("---" + listProcess.get(i)[0]);
			executeProcess.addProcessToQueue(new Process("" + listProcess.get(i)[0], Integer.parseInt("" + vector[1]),
					Boolean.parseBoolean("" + vector[2])));

		}
		executeProcess.init(timeCPU);
		executeProcess.reports();
	}

	public ArrayList<Object[]> reportMissingTimeProcess() {
		/*
		 * for (int i = 0; i < executeProcess.reportMissingTimeProcess().size(); i++) {
		 * System.out.println(executeProcess.reportMissingTimeProcess().get(i)); }
		 */
		return executeProcess.reportMissingTimeProcess();
	}

	public ArrayList<String[]> reportStatusChangeProcess() {
		/*
		 * for (int i = 0; i < executeProcess.reportStatusChangeProcess().size(); i++) {
		 * System.out.println(executeProcess.reportStatusChangeProcess().get(i)); }
		 */
		return executeProcess.reportStatusChangeProcess();
	}

	public ArrayList<String[]> reportByCpuExecuteOrder() {
		return executeProcess.reportByCpuExecuteOrder();
	}

	public ArrayList<String[]> reportByReadyStates() {
		return executeProcess.reportByReadyStates();
	}

	public ArrayList<String[]> reportByLockedStates() {
		return executeProcess.reportByLockedStates();
	}

	public ArrayList<String[]> reportByExitState() {
		return executeProcess.reportByExitState();
	}

	public ArrayList<Object[]> reportForStatusChange() {
		return executeProcess.reportForStatusChange();
	}

}

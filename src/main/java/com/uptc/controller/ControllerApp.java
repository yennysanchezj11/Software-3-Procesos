package com.uptc.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.uptc.models.Process;
import com.uptc.reports.Report;
import com.uptc.viewer.Constants;
import com.uptc.viewer.HeaderProcess;
import com.uptc.viewer.JFramePrincipal;
import com.uptc.viewer.reports.ReportDialog;

public class ControllerApp implements ActionListener {
	ExecuteProcess executeProcess;
	JFramePrincipal jPrincipal;
	Report reportClass;
	String [] headersReports;
	ReportDialog reportTable;

	public ControllerApp() {
		executeProcess = new ExecuteProcess();
		jPrincipal = new JFramePrincipal(this,  headersReports);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try{
		switch (Commands.valueOf(e.getActionCommand())) {
		case C_ADD_PROCESS:
		System.out.println("ENTRO A agregar");
			// agregar proceso a la tabla de procesos
			addProcessTable(this);
			jPrincipal.cleanFieldsTable();
			break;
		case C_EXECUTE_PROCESS:
			// Ejecutar lista de procesos
			System.out.println("ENTRO A EJECUTAR");
			executeProcess();
			break;

		case C_CLOSE_APP:
			// Cerrar la app
			jPrincipal.close();
			break;

		case C_REPORT_BY_EXIT_STATE:
			// reporte por orden en el estado en salida
			reportTable= new ReportDialog(jPrincipal,Constants.TOP_T_MENUITEM_REPORT2);
			reportTable.assignHeaders(this,Constants.headersEstados,Constants.TOP_T_MENUITEM_REPORT2);
			reportTable.cleanRowsTable();
			System.out.println("controler"+Constants.headersEstados[0]);
			reportTable.addElementToTable(reportByExitState());
			jPrincipal.reportTableVisibility(true,reportTable);
			break;

		case C_REPORT_BY_READY_STATES:
			// reporte por orden en el estado en listo
			reportTable= new ReportDialog(jPrincipal,Constants.TOP_T_MENUITEM_REPORT3);
			reportTable.assignHeaders(this, Constants.headersEstados,Constants.TOP_T_MENUITEM_REPORT3);
			reportTable.cleanRowsTable();
			reportTable.addElementToTable(reportByReadyStates());
			jPrincipal.reportTableVisibility(true,reportTable);
			break;

			case C_REPORT_BY_LOCKED_STATES:
			// reporte por orden en el estado en bloqueo
			reportTable= new ReportDialog(jPrincipal,Constants.TOP_T_MENUITEM_REPORT4);
			reportTable.assignHeaders(this, Constants.headersEstados,Constants.TOP_T_MENUITEM_REPORT4);
			reportTable.cleanRowsTable();
			reportTable.addElementToTable(reportByLockedStates());
			jPrincipal.reportTableVisibility(true,reportTable);
			break;
		
			case C_REPORT_BY_SUSPENDEDLOCKED_PROCESS:
			// reporte por orden en el estado en bloqueo
			reportTable= new ReportDialog(jPrincipal,Constants.TOP_T_MENUITEM_REPORT5);
			reportTable.assignHeaders(this, Constants.headersEstados,Constants.TOP_T_MENUITEM_REPORT5);
			reportTable.cleanRowsTable();
			reportTable.addElementToTable(reportBySuspendedLockedProcess());
			jPrincipal.reportTableVisibility(true,reportTable);
			break;

			case C_REPORT_BY_SUSPENDEDREADY_PROCESS:
			// reporte por orden en el estado en bloqueo
			reportTable= new ReportDialog(jPrincipal,Constants.TOP_T_MENUITEM_REPORT6);
			reportTable.assignHeaders(this, Constants.headersEstados,Constants.TOP_T_MENUITEM_REPORT6);
			reportTable.cleanRowsTable();
			reportTable.addElementToTable(reportBySuspendedReadyProcess());
			jPrincipal.reportTableVisibility(true,reportTable);
			break;
	

		case C_REPORT_BY_EXECUTE_STATES:
			// reporte por orden en el estado de en ejecución
			reportTable= new ReportDialog(jPrincipal,Constants.TOP_T_MENUITEM_REPORT7);
			reportTable.assignHeaders(this,Constants.headersR6,Constants.TOP_T_MENUITEM_REPORT7);
			reportTable.cleanRowsTable();
			reportTable.addElementToTable(reportByCpuExecuteOrder());
			jPrincipal.reportTableVisibility(true,reportTable);
			break;

		case C_REPORT_FOR_READY_SUSPENDED_READY_TRANSITION:
			// reporte por cambios de estado de cada proceso
			reportTable= new ReportDialog(jPrincipal,Constants.TOP_T_MENUITEM_REPORT8);
			reportTable.assignHeaders(this,Constants.headersR7,Constants.TOP_T_MENUITEM_REPORT8);
			reportTable.cleanRowsTable();
			reportTable.addElementToTable(getReportForReadySuspendedReadyTransition());
			jPrincipal.reportTableVisibility(true,reportTable);
			break; 

		case C_REPORT_FOR_EXECUTE_SUSPENDED_READY_TRANSITION:
			// reporte por cambios de estado de cada proceso
			reportTable= new ReportDialog(jPrincipal,Constants.TOP_T_MENUITEM_REPORT8);
			reportTable.assignHeaders(this,Constants.headersR7,Constants.TOP_T_MENUITEM_REPORT8);
			reportTable.cleanRowsTable();
			reportTable.addElementToTable(getReportForExecuteSuspendedReadyTransition());
			jPrincipal.reportTableVisibility(true,reportTable);
			break; 

		case C_REPORT_FOR_LOCKED_SUSPENDED_LOCKED_TRANSITION:
			// reporte por cambios de estado de cada proceso
			reportTable= new ReportDialog(jPrincipal,Constants.TOP_T_MENUITEM_REPORT8);
			reportTable.assignHeaders(this,Constants.headersR7,Constants.TOP_T_MENUITEM_REPORT8);
			reportTable.cleanRowsTable();
			reportTable.addElementToTable(getReportForLockedSuspendedLockedTransition());
			jPrincipal.reportTableVisibility(true,reportTable);
			break; 
		
		case C_REPORT_FOR_RESUME_TRANSITION:
			// reporte por cambios de estado de cada proceso
			reportTable= new ReportDialog(jPrincipal,Constants.TOP_T_MENUITEM_REPORT9);
			reportTable.assignHeaders(this,Constants.headersR7,Constants.TOP_T_MENUITEM_REPORT9);
			reportTable.cleanRowsTable();
			reportTable.addElementToTable(getReportForResumeTransition());
			jPrincipal.reportTableVisibility(true,reportTable);
			break; 		

		case C_REPORT_FOR_SEND_TRANSITION:
			// reporte por cambios de estado de cada proceso
			reportTable= new ReportDialog(jPrincipal,Constants.TOP_T_MENUITEM_REPORT10);
			reportTable.assignHeaders(this,Constants.headersR7,Constants.TOP_T_MENUITEM_REPORT10);
			reportTable.cleanRowsTable();
			reportTable.addElementToTable(getReportForSendTransition());
			jPrincipal.reportTableVisibility(true,reportTable);
			break; 	

		case C_REPORT_FOR_TIME_EXPIRED_TRANSITION:
			// reporte por cambios de estado de cada proceso
			reportTable= new ReportDialog(jPrincipal,Constants.TOP_T_MENUITEM_REPORT11);
			reportTable.assignHeaders(this,Constants.headersR7,Constants.TOP_T_MENUITEM_REPORT11);
			reportTable.cleanRowsTable();
			reportTable.addElementToTable(getReportForTimeExpiredTransition());
			jPrincipal.reportTableVisibility(true,reportTable);
			break; 	

		case C_REPORT_FOR_TERMINATION_OPERATION_TRANSITION:
			// reporte por cambios de estado de cada proceso
			reportTable= new ReportDialog(jPrincipal,Constants.TOP_T_MENUITEM_REPORT12);
			reportTable.assignHeaders(this,Constants.headersR7,Constants.TOP_T_MENUITEM_REPORT12);
			reportTable.cleanRowsTable();
			reportTable.addElementToTable(getReportForTerminationOperation());
			jPrincipal.reportTableVisibility(true,reportTable);
			break; 

		case C_REPORT_FOR_EXIT_TRANSITION:
			// reporte por cambios de estado de cada proceso
			reportTable= new ReportDialog(jPrincipal,Constants.TOP_T_MENUITEM_REPORT13);
			reportTable.assignHeaders(this,Constants.headersR7,Constants.TOP_T_MENUITEM_REPORT13);
			reportTable.cleanRowsTable();
			reportTable.addElementToTable(getReportForExitTransition());
			jPrincipal.reportTableVisibility(true,reportTable);
			break;
			
		case C_REPORT_FOR_INIT_TRANSITION:
			// reporte por cambios de estado de cada proceso
			reportTable= new ReportDialog(jPrincipal,Constants.TOP_T_MENUITEM_REPORT14);
			reportTable.assignHeaders(this,Constants.headersR7,Constants.TOP_T_MENUITEM_REPORT14);
			reportTable.cleanRowsTable();
			reportTable.addElementToTable(getReportForInitTransition());
			jPrincipal.reportTableVisibility(true,reportTable);
			break; 	

		case C_CLOSE_DIALOG:
			reportTable.setVisible(false);
			break;

		case C_DELETTE_PROCESS: ///////
			System.out.println("eliminar");
			this.deleteProcess(Integer.valueOf(e.getActionCommand()));
			break;

		default:

			break;
		}
	}catch (Exception ex) {
		deleteProcess(Integer.valueOf(e.getActionCommand()));
	}
	}



	public void addProcessTable(ActionListener actionListener) {
		jPrincipal.setInformationProcessTable(actionListener);
	}

	public void executeProcess() {
		// set lista de procesos
		executeListProcess(jPrincipal.getInformation());

	}

	public void executeListProcess(ArrayList<Object[]> listProcess) {
		for (int i = 0; i < listProcess.size(); i++) {
			Object[] vector = (Object[]) listProcess.get(i);
			executeProcess.addProcessToQueue(new Process("" + vector[0], Integer.parseInt("" + vector[1]),
					Boolean.parseBoolean("" + vector[2]),Boolean.parseBoolean("" + vector[3]),
					Boolean.parseBoolean("" + vector[4])));
		}
		executeProcess.init();
		executeProcess.reports();
		JOptionPane.showMessageDialog(null, "Ejecucion realizada correctamente");
	}


	public String[] getHeadersTable() {
		return executeProcess.reportHeadersTable();
	}

	public ArrayList<Object[]> reportMissingTimeProcess() {
		return executeProcess.reportMissingTimeProcess();
	}

	public ArrayList<Object[]> reportStatusChangeProcess() {
		return executeProcess.reportStatusChangeProcess();
	}

	private ArrayList<Object[]> reportBySuspendedReadyProcess() {
		return executeProcess.reportSuspendedReadyProcess();
	}


	private ArrayList<Object[]> reportBySuspendedLockedProcess() {
		return executeProcess.reportSuspendedLockedProcess();
	}

	public ArrayList<Object[]> reportByCpuExecuteOrder() {
		return executeProcess.reportByCpuExecuteOrder();
	}

	public ArrayList<Object[]> reportByReadyStates() {
		return executeProcess.reportByReadyStates();
	}

	public ArrayList<Object[]> reportByLockedStates() {
		return executeProcess.reportByLockedStates();
	}

	public ArrayList<Object[]> reportByExitState() {
		return executeProcess.reportByExitState();
	}

	
	private ArrayList<Object[]> getReportForReadySuspendedReadyTransition() {
		return executeProcess.getReportForReadySuspendedReadyTransition();
	}
	private ArrayList<Object[]> getReportForResumeTransition() {
		return executeProcess.getReportForResumeTransition();
	}

	private ArrayList<Object[]> getReportForSendTransition() {
		return executeProcess.getReportSendTransition();
	}
	
	private ArrayList<Object[]> getReportForTimeExpiredTransition() {
		return executeProcess.getReportForTimeExpiredTransition();
	}

	private ArrayList<Object[]> getReportForTerminationOperation() {
		return executeProcess.getReportForTerminationOperationTransition();
	}

	private ArrayList<Object[]> getReportForExitTransition() {
		return executeProcess.getReportForExitTransition();
	}

	private ArrayList<Object[]> getReportForInitTransition() {
		return executeProcess.getReportForInitTransition();
	}


	private ArrayList<Object[]> getReportForExecuteSuspendedReadyTransition() {
		return executeProcess.getReportForExecuteSuspendedReadyTransition();
	}

	private ArrayList<Object[]> getReportForLockedSuspendedLockedTransition() {
		return executeProcess.getReportForLockeddSuspendedLockedTransition();
	}

	public void deleteProcess(int id) {
		if(JOptionPane.showConfirmDialog(jPrincipal, "¿Seguro que desea borrar el proceso con Id: " + id +"?",
				"Pregunta", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
			jPrincipal.deleteProcess(id,this);
		}
	}
}

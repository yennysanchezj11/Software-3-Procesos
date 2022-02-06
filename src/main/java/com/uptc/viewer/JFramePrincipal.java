package com.uptc.viewer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.uptc.viewer.reports.ExecuteState;
import com.uptc.viewer.reports.ExitState;
import com.uptc.viewer.reports.LockedState;
import com.uptc.viewer.reports.MissingTimePerProcess;
import com.uptc.viewer.reports.ReadyState;
import com.uptc.viewer.reports.StatusChageProcess;
import com.uptc.viewer.reports.StatusChange;

public class JFramePrincipal extends JFrame{

	private static final long serialVersionUID = 1L;

	private JPanel jPanelPrincipal;
	private HeaderProcess headerProcess;
	private JTableData centerTable;
	private MenuBarReports menuBarr;
	private MissingTimePerProcess missingTimePerProcess;
	private StatusChageProcess statusChageProcess;
	private StatusChange statusChange;
	private ExecuteState executeState;
	private ExitState exitState;
	private ReadyState readyState;
	private LockedState lockedState;
	
	String [] headers = {"Nombre del proceso", "Tiempo del proceso", "Bloqueado", "Editar", "Eliminar"};
	
	public JFramePrincipal(ActionListener actionListener) {
		super("Process");
		this.setSize(900,600);
		this.jPanelPrincipal = new JPanel();
		this.headerProcess = new HeaderProcess(actionListener);
		this.centerTable = new JTableData(headers);
		this.menuBarr = new MenuBarReports(actionListener);
		this.missingTimePerProcess = new MissingTimePerProcess(actionListener, this);
		this.statusChageProcess = new StatusChageProcess(actionListener, this);
		this.statusChange = new StatusChange(actionListener, this);
		this.executeState = new ExecuteState(actionListener, this);
		this.exitState = new ExitState(actionListener, this);
		this.readyState = new ReadyState(actionListener, this);
		this.lockedState = new LockedState(actionListener, this);
		this.initComponents(actionListener);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	
	private void initComponents(ActionListener actionListener) {
		jPanelPrincipal.setBackground(Color.WHITE);
		jPanelPrincipal.setLayout(new BorderLayout());
		jPanelPrincipal.add(headerProcess, BorderLayout.NORTH);
		
		jPanelPrincipal.add(centerTable, BorderLayout.CENTER);

		jPanelPrincipal.add(menuBarr, BorderLayout.SOUTH);
		
		this.add(jPanelPrincipal);
	}
	
	public void cleanRowsTable() {
		centerTable.cleanRowsTable();
	}

	public ArrayList<Object[]> getInformation(){
		return centerTable.getProcessInformation();
	}
	
	
	public void addElementToTablePrincipalTable() {
		centerTable.addElementToTable(getInformation());
	}
	
	public void setInformationProcessTable(){
		Object[] data={headerProcess.getNameProcess(),headerProcess.getProcessTime(),headerProcess.getBlockedProcess()};
		centerTable.addElementUniqueToTable(data);
	}

	public int getTimeCPU() {
		return headerProcess.setTimeCPU();
	}
	
	public void dialogVisibilitiReportMissingTimePerProcess(boolean visibility) {
		missingTimePerProcess.setVisible(visibility);
	}
	
	public void dialogVisibilitiStatusChangeProcess(boolean visibility) {
		statusChageProcess.setVisible(visibility);
	}
	
	public void dialogVisibilitiStatusChange(boolean visibility) {
		statusChange.setVisible(visibility);
	}
	
	public void dialogVisibilitiExecuteState(boolean visibility) {
		executeState.setVisible(visibility);
	}
	
	public void dialogVisibilitiExitState(boolean visibility) {
		exitState.setVisible(visibility);
	}
	
	public void dialogVisibilitiLockedState(boolean visibility) {
		lockedState.setVisible(visibility);
	}
	
	public void dialogVisibilitiReadyState(boolean visibility) {
		readyState.setVisible(visibility);
	}
}
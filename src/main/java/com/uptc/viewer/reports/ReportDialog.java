package com.uptc.viewer.reports;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.uptc.viewer.Constants;
import com.uptc.viewer.JFramePrincipal;
import com.uptc.viewer.Utilities;

public class ReportDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jPanelPrincipal, northPanel;
	private JTableDataReport centerTable;
	private String [] headersReports;

	public ReportDialog(JFramePrincipal jFramePrincipal,String title) {
		jPanelPrincipal= new JPanel();
		setModal(true);
		this.setLayout(new BorderLayout(5, 5));
		this.setBackground(Color.WHITE);
		this.setSize(1200, 700);
		setTitleFrame(title);
		this.getContentPane().setBackground(Color.WHITE);
		this.setLocationRelativeTo(jFramePrincipal);
	}
	
	public void setTitleFrame(String title){
		this.setTitle(title);
	}
	
	public void assignHeaders(ActionListener actionListener,String [] headersReport, String title) {
		this.headersReports=headersReport;
		this.centerTable = new JTableDataReport(headersReports);
		this.addComponentsCenter(actionListener,title);
	}
	
	private void addComponentsCenter(ActionListener actionListener,String title) {
		jPanelPrincipal.setBackground(Color.WHITE);
		jPanelPrincipal.setLayout(new BorderLayout());
		jPanelPrincipal.add(northPanel(title), BorderLayout.NORTH);
		jPanelPrincipal.add(centerTable, BorderLayout.CENTER);
		this.add(jPanelPrincipal);
	}

	private Component northPanel(String title) {
		northPanel = new JPanel();
		northPanel.setBackground(Constants.COLOR_MENUBAR);
		northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));	
		setTitlePanel(title);
		northPanel.setVisible(true);
		return northPanel;
	}

	public void setTitlePanel(String titleReport){
		JLabel title = new JLabel();
		JLabel help = new JLabel();
		northPanel.add(Utilities.text(title, Constants.FONT_MENUBAR, titleReport, Color.BLACK));
		if(titleReport=="TABLA DEL CAMBIO DE ESTADOS DE LOS PROCESOS"){
			northPanel.add(Utilities.text(help, Constants.FONT_MENUBAR, "DONDE L=LISTOS, E= EJECUCION, B= BLOQUEO, S= SALIDA", Color.BLACK));
		}
	}

	public void cleanRowsTable() {
		centerTable.cleanRowsTable();
	} 

	public void addElementToTable(ArrayList<Object[]> datasList){
		centerTable.addElementToTable(datasList);
	}
}

package com.uptc.viewer.reports;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.uptc.viewer.Constants;
import com.uptc.viewer.JFramePrincipal;
import com.uptc.viewer.JTableData;
import com.uptc.viewer.Utilities;

public class ReportTable extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel centerPanel, northPanel;
	private JTableData table;
	private String [] headersReports;
	

	public ReportTable(ActionListener actionListener, JFramePrincipal jFramePrincipal) {
		setModal(true);
		this.setTitle(Constants.TOP_T_MENUITEM_REPORT1);
		this.setLayout(new BorderLayout(5, 5));
		this.setBackground(Color.WHITE);
		this.setSize(1200, 700);
		this.getContentPane().setBackground(Color.WHITE);
		this.table = new JTableData(headersReports);
		this.setLocationRelativeTo(jFramePrincipal);
		this.addComponentsCenter(actionListener);
	}
	
	
	public String [] assignHeaders(String [] headersReports) {
		return headersReports;
	}
	
	private void addComponentsCenter(ActionListener actionListener) {
		this.add(northPanel(), BorderLayout.NORTH);
		this.add(centerPanel(actionListener, assignHeaders(headersReports)), BorderLayout.CENTER);
	}

	private Component northPanel() {
		northPanel = new JPanel();
		northPanel.setBackground(Constants.COLOR_MENUBAR);
		northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel tittle = new JLabel();
		northPanel.add(Utilities.text(tittle, Constants.FONT_MENUBAR, Constants.TOP_T_MENUITEM_REPORT1, Color.BLACK));
		northPanel.setVisible(true);
		return northPanel;
	}

	private Component centerPanel(ActionListener actionListener, String [] headers) {
		centerPanel = new JPanel(new BorderLayout());
		centerPanel.setBackground(Color.WHITE);

		table = new JTableData(headers);
		centerPanel.add(table, BorderLayout.CENTER);

		centerPanel.setVisible(true);
		return centerPanel;
	}
}

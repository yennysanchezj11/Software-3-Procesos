package com.uptc.viewer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.uptc.controller.Commands;

public class JTableData extends JPanel {

	private static final long serialVersionUID = 1L;
	private DefaultTableModel dtmElements;
	private String[] headers;
	private JTable jtElements;
	private JScrollPane jsTable;

	public JTableData(String[] headers) {
		this.headers = headers;
		initComponents();
	}

	private void initComponents() {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setBackground(Color.decode("#30373D"));
		dtmElements = new DefaultTableModel();
		dtmElements.setColumnIdentifiers(headers);
		jtElements = new JTable();
		jtElements.setModel(dtmElements);
		jtElements.getTableHeader().setResizingAllowed(false);
		jtElements.getTableHeader().setReorderingAllowed(false);
		jtElements.getTableHeader().setBackground(Constants.DATA_PANEL_HEADERS_TABLE_COLOR);
		jtElements.getTableHeader().setPreferredSize(new Dimension(0, 60));
		jtElements.getTableHeader().setForeground(Color.BLACK);
		jtElements.getTableHeader().setFont(Constants.DATA_PANEL_HEADERS_TABLE_FONT);
		jtElements.setBackground(Color.WHITE);
		jtElements.setFont(Constants.DATA_PANEL_HEADERS_TABLE_FONT);
		jtElements.setFillsViewportHeight(true);
		jtElements.setRowHeight(50);
		jtElements.setBorder(null);
		jtElements.setDefaultRenderer(Object.class, new RenderTable());
		jsTable = new JScrollPane(jtElements, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		jsTable.setForeground(Color.RED);
		jsTable.setBorder(null);
		jsTable.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.add(jsTable, BorderLayout.PAGE_END);
		this.setBorder(null);

	}

	public DefaultTableModel getDtmElements() {
		return dtmElements;
	}

	public void setDtmElements(DefaultTableModel dtmElements) {
		this.dtmElements = dtmElements;
	}

	public void addElementToTable(ArrayList<Object[]> datasList) {
		for (Object[] datasObject : datasList) {
			dtmElements.addRow(datasObject);
		}
	}

	public void addElementUniqueToTable(Object[] datasList, ActionListener actionListener) {
		Object[] row = new Object[] { datasList[0], datasList[1], datasList[2], createButton(actionListener) };
		dtmElements.addRow(row);
	}

	public ArrayList<Object[]> getProcessInformation() {
		ArrayList<Object[]> infoProcess = new ArrayList<>();
		for (int i = 0; i < dtmElements.getRowCount(); i++) {
			Object[] row = new Object[4];
			row[0] = dtmElements.getValueAt(i, 0);
			row[1] = dtmElements.getValueAt(i, 1);
			row[2] = dtmElements.getValueAt(i, 2);
			row[3] = dtmElements.getValueAt(i, 3);
			infoProcess.add(row);
		}
		return infoProcess;
	}

	public void cleanRowsTable() {
		dtmElements.setNumRows(0);
	}

	public JButton createButton(ActionListener actionListener) {
		JButton deletteButton = new JButton("Eliminar");
		deletteButton.addActionListener(actionListener);
		deletteButton.setActionCommand(Commands.C_CLOSE_APP.toString());
		deletteButton.setBackground(Color.decode("#DF3A01"));
		deletteButton.setForeground(Color.WHITE);
		deletteButton.setHorizontalAlignment(JLabel.CENTER);
		deletteButton.setVisible(true);
		return deletteButton;
	}

}
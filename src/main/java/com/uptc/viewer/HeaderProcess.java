package com.uptc.viewer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.uptc.controller.Commands;

public class HeaderProcess extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JTextField CPUTime, processTime, nameProcess;
	private JLabel tittle;
	private JPanel tittlePanel, dataProcess;
	private JButton saveButton;
	private JCheckBox blockedProcess;
	private int numProcess;
	
	public HeaderProcess(ActionListener actionListener) {
		super();
		this.tittlePanel = new JPanel();
		new JPanel();
		this.dataProcess = new JPanel();
		this.initComponents(actionListener);
		numProcess=0;
	}
	
	private void initComponents(ActionListener actionListener) {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		tittlePanel.setBackground(Color.WHITE);
		tittlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		tittle = new JLabel();
		tittlePanel.add(Utilities.text(tittle, new Font("arial", Font.BOLD, 30), Constants.TITTLE_APP, Color.BLACK));
		this.add(tittlePanel);
		
	
		
		dataProcess.setLayout(new FlowLayout(FlowLayout.LEFT));
		dataProcess.setBackground(Color.WHITE);
		nameProcess = new JTextField();
		dataProcess.add(Utilities.textField(nameProcess, new Font("arial", Font.ITALIC, 15), "Ingresa el nombre del proceso", Color.GRAY, 150, 70));
		processTime= new JTextField();
		dataProcess.add(Utilities.textField(processTime, new Font("arial", Font.ITALIC, 15), "Ingresa el tiempo que el proceso requiere", Color.GRAY, 150, 70));
		blockedProcess = new JCheckBox();
		blockedProcess.setText("¿El proceso se bloqueara?");
		dataProcess.add(Utilities.checkBox(blockedProcess, new Font("arial", Font.ITALIC, 15), Color.GRAY, Color.WHITE, false));
		
		saveButton = new JButton();
		saveButton.addActionListener(actionListener);
		saveButton.setActionCommand(Commands.C_ADD_PROCESS.toString());
		dataProcess.add(Utilities.button(saveButton, new Dimension(100, 30), "Add"));
		this.add(dataProcess);
	}

     public String getNameProcess(){
		 return nameProcess.getText();
	 }

	 public String getProcessTime(){
		return processTime.getText();
	 }

	 public Boolean getBlockedProcess(){
		return blockedProcess.isSelected();
	 }

	public int setTimeCPU() {
		return Integer.parseInt(CPUTime.getText());
	}

    public int getId() {
        return numProcess;
    }

	public int incrementId() {
        return numProcess++;
    }

}
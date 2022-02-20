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


public class HeaderProcess extends JPanel  {

	private static final long serialVersionUID = 1L;
	
	private JTextField processTime, nameProcess;
	private JLabel tittle, lProcessTime, lNameProcess;
	private JPanel tittlePanel, dataProcess, checkProcess, labelsData; 
	private JButton saveButton;
	private JCheckBox blockedProcess, isSuspendedLocked, isSuspendedReady, isEndEvent;
	private int numProcess;
	private ActionListener actionListener;

	public HeaderProcess(ActionListener actionListener) {
		super();
		this.tittlePanel = new JPanel();
		new JPanel();
		this.actionListener = actionListener;
		this.dataProcess = new JPanel();
		this.checkProcess = new JPanel();
		this.labelsData = new JPanel();
		this.initComponents(actionListener);
		numProcess=0;
	}
	
	private void initComponents(ActionListener actionListener) {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		tittlePanel.setBackground(Constants.COLOR_TITTLE_PANEL);
		tittlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		tittle = new JLabel();
		tittlePanel.add(Utilities.text(tittle, Constants.FONT_TITTLE, Constants.TITTLE_APP, Color.BLACK));
		this.add(tittlePanel);

		labelsData.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
		labelsData.setBackground(Constants.COLOR_SET_DATA_PANEL);

		lNameProcess = new JLabel();
		labelsData.add(Utilities.text(lNameProcess, new Font("arial", Font.ITALIC, 15), "Ingresa el nombre del proceso", Color.BLACK));

		lProcessTime = new JLabel();
		labelsData.add(Utilities.text(lProcessTime, new Font("arial", Font.ITALIC, 15), "Ingresa el tiempo que el proceso requiere", Color.BLACK));

		this.add(labelsData);
		
		dataProcess.setLayout(new FlowLayout(FlowLayout.LEFT, 10,5));
		dataProcess.setBackground(Constants.COLOR_SET_DATA_PANEL);

		checkProcess.setLayout(new FlowLayout(FlowLayout.LEFT));
		checkProcess.setBackground(Constants.COLOR_SET_DATA_PANEL);

		nameProcess = new JTextField();
		dataProcess.add(Utilities.textField(nameProcess, new Font("arial", Font.ITALIC, 15), "", Color.GRAY, 210, 20));
		
		processTime= new JTextField();
		dataProcess.add(Utilities.textField(processTime, new Font("arial", Font.ITALIC, 15), "", Color.GRAY, 290, 20));
	
		blockedProcess = new JCheckBox();
		blockedProcess.setText("¿El proceso se bloqueara?");
		blockedProcess.setBackground(Constants.COLOR_SET_DATA_PANEL);
		dataProcess.add(Utilities.checkBox(blockedProcess, new Font("arial", Font.ITALIC, 15), Color.BLACK, Constants.COLOR_SET_DATA_PANEL, false));
	
		this.add(dataProcess);

		isSuspendedLocked = new JCheckBox();
		isSuspendedLocked.setText("El proceso pasa a Suspendido_Bloqueado");
		isSuspendedLocked.setBackground(Constants.COLOR_SET_DATA_PANEL);
		checkProcess.add(Utilities.checkBox(isSuspendedLocked, new Font("arial", Font.ITALIC, 15), Color.BLACK, Constants.COLOR_SET_DATA_PANEL, false));

		isSuspendedReady = new JCheckBox();
		isSuspendedReady.setText("El proceso pasa a Suspendido_Listo");
		isSuspendedReady.setBackground(Constants.COLOR_SET_DATA_PANEL);
		checkProcess.add(Utilities.checkBox(isSuspendedReady, new Font("arial", Font.ITALIC, 15), Color.BLACK, Constants.COLOR_SET_DATA_PANEL, false));

		isEndEvent = new JCheckBox();
		isEndEvent.setText("Evento de espera finaliza");
		isEndEvent.setBackground(Constants.COLOR_SET_DATA_PANEL);
		checkProcess.add(Utilities.checkBox(isEndEvent, new Font("arial", Font.ITALIC, 15), Color.BLACK, Constants.COLOR_SET_DATA_PANEL, false));

		saveButton = new JButton();
		saveButton.addActionListener(actionListener);
		saveButton.setActionCommand(Commands.C_ADD_PROCESS.toString());
		checkProcess.add(Utilities.button(saveButton, new Dimension(100, 30), "AGREGAR"));
		
		this.add(checkProcess);
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

    public int getId() {
        return numProcess;
    }

	public boolean getEndEvent() {
        return isEndEvent.isSelected();
    }

	public boolean getSuspendedLocked() {
        return isSuspendedLocked.isSelected();
    }

	public boolean getSuspendedReady() {
        return isSuspendedReady.isSelected();
    }

	public int incrementId() {
        return numProcess++;
    }

}
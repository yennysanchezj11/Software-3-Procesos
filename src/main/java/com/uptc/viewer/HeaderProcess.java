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
	
	private JTextField processTime, nameProcess, priorityProcess, nameProcessConection;
	private JLabel tittle;
	private JPanel tittlePanel, dataProcess, checkProcess; 
	private JButton saveButton;
	private JCheckBox blockedProcess, isRun, isDestroyed, isSuspended;
	private int numProcess;
	
	public HeaderProcess(ActionListener actionListener) {
		super();
		this.tittlePanel = new JPanel();
		new JPanel();
		this.dataProcess = new JPanel();
		this.checkProcess = new JPanel();
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
		
		dataProcess.setLayout(new FlowLayout(FlowLayout.LEFT));
		dataProcess.setBackground(Constants.COLOR_SET_DATA_PANEL);

		checkProcess.setLayout(new FlowLayout(FlowLayout.LEFT));
		checkProcess.setBackground(Constants.COLOR_SET_DATA_PANEL);

		nameProcess = new JTextField();
		dataProcess.add(Utilities.textField(nameProcess, new Font("arial", Font.ITALIC, 15), "Ingresa el nombre del proceso", Color.GRAY, 150, 70));
		
		processTime= new JTextField();
		dataProcess.add(Utilities.textField(processTime, new Font("arial", Font.ITALIC, 15), "Ingresa el tiempo que el proceso requiere", Color.GRAY, 150, 70));
		
		priorityProcess = new JTextField();
		dataProcess.add(Utilities.textField(priorityProcess, new Font("arial", Font.ITALIC, 15), "Ingresa la prioridad del proceso", Color.GRAY, 150, 70));

	//	newPriorityProcess = new JTextField();
	//	dataProcess.add(Utilities.textField(newPriorityProcess, new Font("arial", Font.ITALIC, 15), "Puedes asignar una nueva prioridad", Color.GRAY, 150, 70));
		
		blockedProcess = new JCheckBox();
		blockedProcess.setText("¿El proceso se bloqueara?");
		checkProcess.add(Utilities.checkBox(blockedProcess, new Font("arial", Font.ITALIC, 15), Color.GRAY, Color.WHITE, false));
	
		isRun = new JCheckBox();
		isRun.setText("¿El proceso se ejecutara?");
		checkProcess.add(Utilities.checkBox(isRun, new Font("arial", Font.ITALIC, 15), Color.GRAY, Color.WHITE, false));
		
		isDestroyed = new JCheckBox();
		isDestroyed.setText("¿El proceso se destruira?");
		checkProcess.add(Utilities.checkBox(isDestroyed, new Font("arial", Font.ITALIC, 15), Color.GRAY, Color.WHITE, false));
		
		isSuspended= new JCheckBox();
		isSuspended.setText("¿El proceso se suspendera?");
		checkProcess.add(Utilities.checkBox(isSuspended, new Font("arial", Font.ITALIC, 15), Color.GRAY, Color.WHITE, false));
		

		nameProcessConection = new JTextField();
		checkProcess.add(Utilities.textField(nameProcessConection, new Font("arial", Font.ITALIC, 15), "El proceso se conecta con", Color.GRAY, 150, 70));
		

		saveButton = new JButton();
		saveButton.addActionListener(actionListener);
		saveButton.setActionCommand(Commands.C_ADD_PROCESS.toString());
		dataProcess.add(Utilities.button(saveButton, new Dimension(100, 30), "AGREGAR"));
		this.add(dataProcess);
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

	public int incrementId() {
        return numProcess++;
    }

	public String getPriorityProcess() {
		return priorityProcess.getText();
	}

	public boolean getIsRun() {
		return isRun.isSelected();
	}

	public boolean getIsDestroyed() {
		return isDestroyed.isSelected();
	}

	public boolean getIsSuspended() {
		return isSuspended.isSelected();
	}

	public String getIsConnects() {
	String connect="No";
		if(nameProcessConection.getText()!="") {
			connect=nameProcessConection.getText();
		}
		return connect;
	}
}
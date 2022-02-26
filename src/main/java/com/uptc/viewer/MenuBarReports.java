package com.uptc.viewer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import com.uptc.controller.Commands;

public class MenuBarReports extends JMenuBar {

	private static final long serialVersionUID = 1L;

	private JMenu report;
	private JMenuItem report2, report3, report4, report5, report6, report7,
	report8, report9, report10, report11,report12,report13,report14,report15,report16,report17,report18;
	private JButton executeButton, closeApppButton;

	public MenuBarReports(ActionListener actionListener) {
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.setBackground(Color.WHITE);
		this.initComponents(actionListener);
		this.setVisible(true);
	}
	

	private void initComponents(ActionListener actionListener) {
		
		executeButton = new JButton();
		executeButton.addActionListener(actionListener);
		executeButton.setActionCommand(Commands.C_EXECUTE_PROCESS.toString());
		this.add(Utilities.button(executeButton, new Dimension(100, 30), "Ejecución"));
		
		report = new JMenu(Constants.TOP_PANEL_MENU_PRINCIPAL_REPORT);
		report.setFont(Constants.FONT_MENUBAR);
		report.setForeground(Color.BLACK);

		report2 = new JMenuItem(Constants.TOP_T_MENUITEM_REPORT2);
		report2.setFont(Constants.FONT_MENUBAR);
		report2.addActionListener(actionListener);
		report2.setActionCommand(Commands.C_REPORT_BY_EXIT_STATE.toString());
		report.add(report2);
		report.addSeparator();

		report3 = new JMenuItem(Constants.TOP_T_MENUITEM_REPORT3);
		report3.setFont(Constants.FONT_MENUBAR);
		report3.addActionListener(actionListener);
		report3.setActionCommand(Commands.C_REPORT_BY_READY_STATES.toString());
		report.add(report3);
		report.addSeparator();

		report4 = new JMenuItem(Constants.TOP_T_MENUITEM_REPORT4);
		report4.setFont(Constants.FONT_MENUBAR);
		report4.addActionListener(actionListener);
		report4.setActionCommand(Commands.C_REPORT_BY_LOCKED_STATES.toString());
		report.add(report4);
		report.addSeparator();

		report5 = new JMenuItem(Constants.TOP_T_MENUITEM_REPORT5);
		report5.setFont(Constants.FONT_MENUBAR);
		report5.addActionListener(actionListener);
		report5.setActionCommand(Commands.C_REPORT_BY_SUSPENDEDLOCKED_PROCESS.toString());
		report.add(report5);
		report.addSeparator();

		report6 = new JMenuItem(Constants.TOP_T_MENUITEM_REPORT6);
		report6.setFont(Constants.FONT_MENUBAR);
		report6.addActionListener(actionListener);
		report6.setActionCommand(Commands.C_REPORT_BY_SUSPENDEDREADY_PROCESS.toString());
		report.add(report6);
		report.addSeparator();

		report7 = new JMenuItem(Constants.TOP_T_MENUITEM_REPORT7);
		report7.setFont(Constants.FONT_MENUBAR);
		report7.addActionListener(actionListener);
		report7.setActionCommand(Commands.C_REPORT_BY_EXECUTE_STATES.toString());
		report.add(report7);
		report.addSeparator();

		report9 = new JMenuItem(Constants.TOP_T_MENUITEM_REPORT8);
		report9.setFont(Constants.FONT_MENUBAR);
		report9.addActionListener(actionListener);
		report9.setActionCommand(Commands.C_REPORT_FOR_READY_SUSPENDED_READY_TRANSITION.toString());
		report.add(report9);
		report.addSeparator();
   
		report17 = new JMenuItem(Constants.TOP_T_MENUITEM_REPORT15);
		report17.setFont(Constants.FONT_MENUBAR);
		report17.addActionListener(actionListener);
		report17.setActionCommand(Commands.C_REPORT_FOR_EXECUTE_SUSPENDED_READY_TRANSITION.toString());
		report.add(report17);
		report.addSeparator();

		   
		report18 = new JMenuItem(Constants.TOP_T_MENUITEM_REPORT16);
		report18.setFont(Constants.FONT_MENUBAR);
		report18.addActionListener(actionListener);
		report18.setActionCommand(Commands.C_REPORT_FOR_LOCKED_SUSPENDED_LOCKED_TRANSITION.toString());
		report.add(report18);
		report.addSeparator();

		report10 = new JMenuItem(Constants.TOP_T_MENUITEM_REPORT9);
		report10.setFont(Constants.FONT_MENUBAR);
		report10.addActionListener(actionListener);
		report10.setActionCommand(Commands.C_REPORT_FOR_RESUME_TRANSITION.toString());
		report.add(report10);
		report.addSeparator();

		report11 = new JMenuItem(Constants.TOP_T_MENUITEM_REPORT10);
		report11.setFont(Constants.FONT_MENUBAR);
		report11.addActionListener(actionListener);
		report11.setActionCommand(Commands.C_REPORT_FOR_SEND_TRANSITION.toString());
		report.add(report11);
		report.addSeparator();

		report12 = new JMenuItem(Constants.TOP_T_MENUITEM_REPORT11);
		report12.setFont(Constants.FONT_MENUBAR);
		report12.addActionListener(actionListener);
		report12.setActionCommand(Commands.C_REPORT_FOR_TIME_EXPIRED_TRANSITION.toString());
		report.add(report12);
		report.addSeparator();

		report13 = new JMenuItem(Constants.TOP_T_MENUITEM_REPORT12);
		report13.setFont(Constants.FONT_MENUBAR);
		report13.addActionListener(actionListener);
		report13.setActionCommand(Commands.C_REPORT_FOR_TERMINATION_OPERATION_TRANSITION.toString());
		report.add(report13);
		report.addSeparator();

		report14 = new JMenuItem(Constants.TOP_T_MENUITEM_REPORT13);
		report14.setFont(Constants.FONT_MENUBAR);
		report14.addActionListener(actionListener);
		report14.setActionCommand(Commands.C_REPORT_FOR_EXIT_TRANSITION.toString());
		report.add(report14);
		report.addSeparator();

		report15 = new JMenuItem(Constants.TOP_T_MENUITEM_REPORT14);
		report15.setFont(Constants.FONT_MENUBAR);
		report15.addActionListener(actionListener);
		report15.setActionCommand(Commands.C_REPORT_FOR_INIT_TRANSITION.toString());
		report.add(report15);
		report.addSeparator();
		
		this.add(report);
		
		closeApppButton = new JButton();
		closeApppButton.addActionListener(actionListener);
		closeApppButton.setActionCommand(Commands.C_CLOSE_APP.toString());
		this.add(Utilities.button(closeApppButton, new Dimension(100, 30), "Cerrar"));
	}
}
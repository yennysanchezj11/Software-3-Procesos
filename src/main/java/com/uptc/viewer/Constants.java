package com.uptc.viewer;

import java.awt.Color;
import java.awt.Font;

public class Constants {

	public static final Color DATA_PANEL_HEADERS_TABLE_COLOR = Color.decode("#f6f6f6");;
	public static final Font DATA_PANEL_HEADERS_TABLE_FONT = new Font("Open Sans", Font.BOLD, 12);
	public static final Color COLOR_MENUBAR = Color.decode("#e8e6e6");
	public static final Font FONT_MENUBAR = new Font("din-next-w01-light - 400", Font.PLAIN, 16);
	
	
	public static final String TITTLE_APP = "PROCESOS";
	public static final String LOGO_APP = "images/iconApp.png";
	public static final String TOP_PANEL_MENU_PRINCIPAL_REPORT = "Menu";
	public static final String TOP_T_MENUITEM_REPORT1 = "TABLA DEL TIEMPO FALTANTE POR PROCESO DESDE EL TIEMPO t=0";
	public static final String TOP_T_MENUITEM_REPORT2 = "TABLA DEL CAMBIO DE ESTADOS DE LOS PROCESOS";
	public static final String TOP_T_MENUITEM_REPORT3 = "REPORTE POR ORDEN EN EL ESTADO: SALIDA";
	public static final String TOP_T_MENUITEM_REPORT4 = "REPORTE POR ORDEN EN EL ESTADO: LISTOS";
	public static final String TOP_T_MENUITEM_REPORT5 = "REPORTE POR ORDEN EN EL ESTADO: BLOQUEADO";
	public static final String TOP_T_MENUITEM_REPORT6 = "REPORTE POR ORDEN DE EJECUCION EN LA CPU";
	public static final String TOP_T_MENUITEM_REPORT7 = "REPORTE DEL PROCESO DE CAMBIO DE ESTADOS";
	public static final int DO_NOTHING_ON_CLOSE = 0;
	
	public static final String [] headersR1 = {"P/T", "0", "3", "6", "9", "12", "15", "18", "21", "24", "27", "30", "33", "36", "39"};
	public static final String [] headersR2 = {"P/T", "0", "3", "6", "9", "12", "15", "18", "21", "24", "27", "30", "33", "36", "39"};
	public static final String [] headersR3 = {"T", "P"};
	public static final String [] headersR4 = {"T", "P"};
	public static final String [] headersR5 = {"T", "P"};
	public static final String [] headersR6 = {"Ti","Tf", "P"};
	public static final String [] headersR7 = {"Accion", "Estado actual", "Estado nuevo"};
	
	public static final String [] PRICIPAL_HEADERS = { "Nombre del proceso", "Tiempo del proceso", "Bloqueado", "Eliminar" };

	
}
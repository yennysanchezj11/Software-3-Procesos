package com.uptc.viewer;

import java.awt.Color;
import java.awt.Font;

import javax.swing.Icon;

public class Constants {

	public static final Color DATA_PANEL_HEADERS_TABLE_COLOR = Color.decode("#f6f6f6");;
	public static final Font DATA_PANEL_HEADERS_TABLE_FONT = new Font("Open Sans", Font.BOLD, 12);
	public static final Color COLOR_MENUBAR = Color.decode("#e8e6e6");
	public static final Font FONT_MENUBAR = new Font("din-next-w01-light - 400", Font.PLAIN, 16);
	public static final Font FONT_TITTLE = new Font("Agency FB", Font.BOLD, 30);
	public static final Color COLOR_TITTLE_PANEL = Color.decode("#FFDCB3");
	public static final Color COLOR_SET_DATA_PANEL = Color.decode("#CBD7FA");
	
	public static final String TITTLE_APP = "PROCESOS";
	public static final String LOGO_APP = "images/iconApp.png";
	public static final String TOP_PANEL_MENU_PRINCIPAL_REPORT = "Menu";
	public static final String TOP_T_MENUITEM_REPORT2 = "REPORTE POR ORDEN EN EL ESTADO: SALIDA";
	public static final String TOP_T_MENUITEM_REPORT3 = "REPORTE POR ORDEN EN EL ESTADO: LISTOS";
	public static final String TOP_T_MENUITEM_REPORT4 = "REPORTE POR ORDEN EN EL ESTADO: BLOQUEADO";
	public static final String TOP_T_MENUITEM_REPORT5 = "REPORTE POR ORDEN EN EL ESTADO: SUSPENDIDO BLOQUEADO";
	public static final String TOP_T_MENUITEM_REPORT6 = "REPORTE POR ORDEN EN EL ESTADO: SUSPENDIDO LISTO";
	public static final String TOP_T_MENUITEM_REPORT7 = "REPORTE POR ORDEN DE EJECUCION EN LA CPU";
	public static final String TOP_T_MENUITEM_REPORT8 = "REPORTE DE LA TRANSICIÓN DE LISTO A SUSPENDIDO_LISTO";
	public static final String TOP_T_MENUITEM_REPORT9 = "REPORTE DE LA TRANSICIÓN DE REANUDAR";
	public static final String TOP_T_MENUITEM_REPORT10 = "REPORTE DE LA TRANSICIÓN DESPACHAR";
	public static final String TOP_T_MENUITEM_REPORT11 = "REPORTE DE LA TRANSICIÓN TIEMPO EXPIRADO";
	public static final String TOP_T_MENUITEM_REPORT12 = "REPORTE DE LA TRANSICIÓN OPERACIÓN TERMINADA";
	public static final String TOP_T_MENUITEM_REPORT13 = "REPORTE DE LA TRANSICIÓN SALIDA";
	public static final String TOP_T_MENUITEM_REPORT14 = "REPORTE DE LA TRANSICIÓN CREAR";
	public static final int DO_NOTHING_ON_CLOSE = 0;
	
	public static final String [] headersEstados = {"Tiempo", "Proceso"};
	public static final String [] headersR6 = {"Tiempo Inicial","Tiempo Final", "Proceso"};
	public static final String [] headersR7 = {"Transacciones entre estados"};
	
	public static final String [] PRICIPAL_HEADERS = { "Id", "Nombre del proceso", "Tiempo del proceso", "Bloqueado","Suspendido Bloqueado","Suspendido Listo","Eliminar" };
    public static final String TOP_T_MENUITEM_REPORT15 = "REPORTE DE LA TRANSICIÓN DE EJECUTADO  A SUSPENDIDO_LISTO";
    public static final String TOP_T_MENUITEM_REPORT16 = "REPORTE DE LA TRANSICIÓN DE BLOQUEADO  A SUSPENDIDO_BLOQUEADO";

	
}
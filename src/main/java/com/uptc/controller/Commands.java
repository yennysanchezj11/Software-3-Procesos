package com.uptc.controller;

public enum Commands {
	C_ADD_PROCESS,C_EXECUTE_PROCESS,
	C_REPORT_FOR_STATUS_CHANGE_PROCESS,C_REPORT_BY_EXIT_STATE,
	C_REPORT_BY_READY_STATES,C_REPORT_BY_EXECUTE_STATES,
	C_REPORT_BY_LOCKED_STATES, C_CLOSE_APP,C_DELETTE_PROCESS,
	C_REPORT_BY_SUSPENDEDREADY_PROCESS,C_REPORT_BY_SUSPENDEDLOCKED_PROCESS,
	C_CLOSE_DIALOG,C_REPORT_FOR_SUSPENDED_TRANSITION,C_REPORT_FOR_RESUME_TRANSITION,
	C_REPORT_FOR_SEND_TRANSITION,C_REPORT_FOR_TIME_EXPIRED_TRANSITION,
	C_REPORT_FOR_TERMINATION_OPERATION_TRANSITION,C_REPORT_FOR_INIT_TRANSITION,
	C_REPORT_FOR_EXIT_TRANSITION
	;
}

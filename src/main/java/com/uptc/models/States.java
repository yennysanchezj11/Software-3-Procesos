package com.uptc.models;


public enum States  {

    INIT("INICIO"), READY("LISTOS"),EXECUTE("EJECUCION") ,LOCKED("BLOQUEADO"),SUSPENDEDREADY("SUSPENDIDO-LISTO"),
    SUSPENDEDLOCKED("SUSPENDIDO-LOCKED"),EXIT("SALIDA");

    private final String name;

    private States(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return name.charAt(0)+"";
    }

    public String getName() {
        return name;
    }
}

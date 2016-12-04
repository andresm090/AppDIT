package com.example.leandro.appdit;


/**
 * Clase que representa las caracteristicas de una materia.
 * Created by Andres on 18/06/2016.
 */
public class Preferencia {

    private String idCalendario = null;
    private String idMateria = null;
    private String inicial = null;
    private int color;

    /**
     * Consturctor de Preferencia
     * @param idCalendario String que representa el id del calendario
     * @param codMateria String que representa el codigo de materia
     * @param ini String que representa las iniciales de la materia
     * @param color int que representa el color que tendra la materia cuando se muestre al usuario
     */
    public Preferencia(String idCalendario, String codMateria, String ini, int color){
        this.idCalendario = idCalendario;
        this.idMateria = codMateria;
        this.inicial = ini;
        this.color = color;
    }

    /**
     * Consturctor de Preferencia
     * @param codMateria String que representa el codigo de materia
     * @param ini String que representa las iniciales de la materia
     * @param color int que representa el color que tendra la materia cuando se muestre al usuario
     */
    public Preferencia(String codMateria, String ini, int color){
        this.idMateria = codMateria;
        this.inicial = ini;
        this.color = color;
    }

    public String getCalendario(){
        return this.idCalendario;
    }

    public String getCodMateria(){
        return this.idMateria;
    }

    public String getInicial(){
        return this.inicial;
    }

    public int getColor(){
        return this.color;
    }
}

package com.example.leandro.appdit;


/**
 * Clase que representa las caracteristicas de una materia.
 */
public class Preferencia {

    private String idCalendario = null;
    private String idMateria = null;
    private String inicial = null;
    private int color;

    /**
     * Consturctor de Preferencia.
     * @param idCalendario String que representa el id del calendario.
     * @param codMateria String que representa el codigo de materia.
     * @param ini String que representa las iniciales de la materia.
     * @param color int que representa el color que tendra la materia cuando se muestre al usuario.
     */
    public Preferencia(String idCalendario, String codMateria, String ini, int color){
        this.idCalendario = idCalendario;
        this.idMateria = codMateria;
        this.inicial = ini;
        this.color = color;
    }

    /**
     * Consturctor de Preferencia.
     * @param codMateria String que representa el codigo de materia.
     * @param ini String que representa las iniciales de la materia.
     * @param color int que representa el color que tendra la materia cuando se muestre al usuario.
     */
    public Preferencia(String codMateria, String ini, int color){
        this.idMateria = codMateria;
        this.inicial = ini;
        this.color = color;
    }

    /**
     * Metodo que retorna el id del calendario.
     * @return String que representa el id del calendario.
     */
    public String getCalendario(){
        return this.idCalendario;
    }

    /**
     * Metodo que retorna el codigo de materia establecido de acuerdo al plan 2010.
     * @return String que representa el codigo de materia establecido de acuerdo al plan 2010.
     */
    public String getCodMateria(){return this.idMateria;}

    /**
     * Metodo que retorna las iniciales de la materia.
     * @return String que representa las iniciales de la materia.
     */
    public String getInicial(){return this.inicial;}

    /**
     * Metodo que retorna el color con el que se visualizara la materia.
     * @return int que representa el color con el que se visualizara la materia.
     */
    public int getColor(){return this.color;}
}

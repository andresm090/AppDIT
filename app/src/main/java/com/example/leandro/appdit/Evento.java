package com.example.leandro.appdit;

import com.amulyakhare.textdrawable.TextDrawable;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase que representa un Evento del Calendario
 * Created by Andres on 18/06/2016.
 */
public class Evento implements Comparable<Evento>{

    private String evento;
    private String materia;
    private String fecha;
    private String horaI = null;
    private String horaF = null;
    private String descripcion = null;
    SimpleDateFormat formatoCompleto = new SimpleDateFormat("dd/M/yyyy HH:mm");
    SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/M/yyyy");

    /**
     * Contructor de Evento
     * @param evento String que representa el nombre del evento
     * @param materia String que representa el nombre de la materia al que pertenece el evento
     * @param fecha String que representa la fecha del evento
     * @param horaI String que representa la hora de inicio del evento
     * @param horaF String que representa la hora de finalizacion del evento
     * @param descripcion String que representa una descripcion u comentario del evento
     */
    public Evento(String evento, String materia, String fecha, String horaI, String horaF, String descripcion){
        this.evento = evento;
        this.materia = materia;
        this.fecha = fecha;
        this.horaI = horaI;
        this.horaF = horaF;
        this.descripcion = descripcion;
    }

    /**
     * Contructor de Evento
     * @param evento String que representa el nombre del evento
     * @param materia String que representa el nombre de la materia al que pertenece el evento
     * @param fecha String que representa la fecha del evento
     * @param horaI String que representa la hora de inicio del evento
     * @param horaF String que representa la hora de finalizacion del evento
     */
    public Evento(String evento, String materia, String fecha, String horaI, String horaF){
        this.evento = evento;
        this.materia = materia;
        this.fecha = fecha;
        this.horaI = horaI;
        this.horaF = horaF;
    }

    /**
     * Contructor de Evento
     * @param evento String que representa el nombre del evento
     * @param materia String que representa el nombre de la materia al que pertenece el evento
     * @param fecha String que representa la fecha del evento
     */
    public Evento(String evento, String materia, String fecha){
        this.evento = evento;
        this.materia = materia;
        this.fecha = fecha;
    }

    public void setDescripcion(String d){this.descripcion = d;}

    public String getEvento(){ return String.format("(%s) %s", this.fecha, this.evento); }

    public String getMateria() { return this.materia;}

    public String getHora() {
        if (this.horaI == null){
            return null;
        }
        return this.horaI + " a " + this.horaF + " hs";
    }

    public String getHoraI () {return this.horaI;}

    public String getFecha () { return  this.fecha;}

    public String getDescripcion() {return this.descripcion;}

    public String getInicial(){ return Materia.getIniciales(this.materia);}

    public int getColor() { return  Materia.getColor(this.materia);}

    public TextDrawable getIcono() {
        return TextDrawable.builder().beginConfig().withBorder(6).endConfig().buildRoundRect(this.getInicial(), this.getColor(),10);
    }

    @Override
    public int compareTo(Evento ev1){
        Date fecha1;
        Date fecha2;
        if (ev1.horaI != null && this.horaI != null) {
            String stFecha1 = this.fecha + " " + this.horaI;
            String stFecha2 = ev1.getFecha() + " " + ev1.getHoraI();
            fecha1 = formatoCompleto.parse(stFecha1, new ParsePosition(0));
            fecha2 = formatoCompleto.parse(stFecha2, new ParsePosition(0));
        } else {
            fecha1 = formatoFecha.parse(this.fecha, new ParsePosition(0));
            fecha2 = formatoFecha.parse(ev1.getFecha(), new ParsePosition(0));
        }
        if (fecha1.equals(fecha2)){
            return 0;
        }
        if (fecha1.before(fecha2)){
            return -1;
        } else{
            return 1;
        }
    }
}

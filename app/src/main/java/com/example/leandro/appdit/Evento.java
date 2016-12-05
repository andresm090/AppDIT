package com.example.leandro.appdit;

import android.support.annotation.Nullable;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.amulyakhare.textdrawable.TextDrawable;

/**
 * Clase que representa un Evento del Calendario.
 */
public class Evento implements Comparable<Evento>{

    private String evento;
    private String materia;
    private String fecha;
    private String horaI = null;
    private String horaF = null;
    private String descripcion = null;
    private SimpleDateFormat formatoCompleto = new SimpleDateFormat("dd/M/yyyy HH:mm");
    private SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/M/yyyy");

    /**
     * Contructor de Evento
     * @param evento String que representa el nombre del evento.
     * @param materia String que representa el nombre de la materia al que pertenece el evento.
     * @param fecha String que representa la fecha del evento.
     * @param horaI String que representa la hora de inicio del evento.
     * @param horaF String que representa la hora de finalizacion del evento.
     * @param descripcion String que representa una descripcion o comentario del evento.
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
     * @param evento String que representa el nombre del evento.
     * @param materia String que representa el nombre de la materia al que pertenece el evento.
     * @param fecha String que representa la fecha del evento.
     * @param horaI String que representa la hora de inicio del evento.
     * @param horaF String que representa la hora de finalizacion del evento.
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
     * @param evento String que representa el nombre del evento.
     * @param materia String que representa el nombre de la materia al que pertenece el evento.
     * @param fecha String que representa la fecha del evento.
     */
    public Evento(String evento, String materia, String fecha){
        this.evento = evento;
        this.materia = materia;
        this.fecha = fecha;
    }

    /**
     * Metodo que compara este objeto con el objeto especificado por parámetro.
     * Devuelve un entero negativo, cero o un número entero positivo si este objeto es menor, igual o mayor que el objeto especificado.
     * @param ev1 Objeto Evento a comparar
     * @return Un entero negativo, cero o un número entero positivo si este objeto es menor que, igual a, o mayor que el objeto especificado.
     * @throws NullPointerException Si el objeto especificado es Null.
     * {@link #getFecha()}
     * {@link #getHoraI()}
     */
    @Override
    public int compareTo(Evento ev1){
        Date fecha1;
        Date fecha2;
        if (ev1.getHoraI() != null && this.getHoraI() != null) {
            String stFecha1 = this.getFecha() + " " + this.getHoraI();
            String stFecha2 = ev1.getFecha() + " " + ev1.getHoraI();
            fecha1 = formatoCompleto.parse(stFecha1, new ParsePosition(0));
            fecha2 = formatoCompleto.parse(stFecha2, new ParsePosition(0));
        } else {
            fecha1 = formatoFecha.parse(this.getFecha(), new ParsePosition(0));
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

    /**
     * Metodo que establece la descripción del Objeto Evento.
     * @param d String que representa la descripción del evento.
     */
    public void setDescripcion(String d){this.descripcion = d;}

    /**
     * Metodo que retorna la descripción del Objeto Evento.
     * @return String que representa la descripción del evento.
     */
    public String getDescripcion() {return this.descripcion;}

    /**
     * Metodo que establece el nombre de la materia al que pertenece el Objeto Evento.
     * @param m String que representa el nombre de la materia al que pertenece el evento.
     */
    public void setMateria(String m) {this.materia = m;}

    /**
     * Metodo que retorna el nombre de la materia al que pertenece el Objeto Evento.
     * @return String que representa el nombre de la materia al que pertenece el evento.
     */
    public String getMateria() {return this.materia;}

    /**
     * Metodo que retorna la fecha del evento
     * @return String que representa la fecha del evento.
     */
    public String getFecha() {return this.fecha;}

    /**
     * Metodo que retorna las iniciales de la materia al que pertenece el Objeto Evento.
     * @return String que representa las iniciales de la materia al que pertenece el evento.
     * {@link #getMateria()}
     * @see Materia#getIniciales(String)
     */
    public String getInicial(){return Materia.getIniciales(this.getMateria());}

    /**
     * Metodo que retorna el color con el que se visualizara la materia al que pertenece el Objeto Evento.
     * @return int que representa el color con el que se visualizara la materia al que pertenece el evento.
     * {@link #getMateria()}
     * @see Materia#getColor(String)
     */
    public int getColor() {return  Materia.getColor(this.getMateria());}

    /**
     * Metodo que retorna el nombre del Objeto Evento.
     * @return String que representa el nombre del evento.
     */
    public String getNombreEvento() {return this.evento;}

    /**
     * Metodo que retorna una descripcion con la fecha y el nombre del Objeto evento.
     * @return String que representa una descripcion con la fecha y el nombre del evento.
     * {@link #getFecha()}
     * {@link #getNombreEvento()}
     */
    public String getEvento(){return String.format("(%s) %s", this.getFecha(), this.getNombreEvento()); }

    /**
     * Metoddo que retorna la hora de inicio del Objeto Evento.
     * @return String que representa la hora de inicio del evento.
     */
    @Nullable
    public String getHoraI() {return this.horaI;}

    /**
     * Metodo que retorna la hora de finalizacion del Objeto Evento.
     * @return String que representa la hora de finalizacion del evento.
     */
    @Nullable
    public String getHoraF() {return this.horaF;}

    /**
     * Metodo que retorna el rango de horas de duracion del Objeto Evento
     * @return String que representa el rango de horas de duración del evento o null si no se definieron horas de inicio o fin.
     * {@link #getHoraI()}
     * {@link #getHoraF()}
     */
    @Nullable
    public String getHora() {
        if (this.getHoraI() == null){
            return null;
        }
        return this.getHoraI() + " a " + this.getHoraF() + " hs";
    }

    /**
     * Metodo que retorna la imagen, con la que se representa la materia a la que pertenece el evento.
     * @return TextDrawable que representa el icono de la materia a la que pertenece el evento.
     * {@link #getInicial()}
     * {@link #getColor()}
     * @see TextDrawable
     */
    public TextDrawable getIcono() {
        return TextDrawable.builder().beginConfig().withBorder(6).endConfig().buildRoundRect(this.getInicial(), this.getColor(),10);
    }

}

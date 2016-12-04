package com.example.leandro.appdit;

import com.amulyakhare.textdrawable.TextDrawable;

public class Lista_entrada {

	private Evento evento;

	public Lista_entrada (Evento e){
		this.evento = e;

	}

	public String get_textoEncima() { 
	    return this.evento.getMateria();
	}

	public String get_textoDebajo() { 
	    return this.evento.getEvento();
	}

	public TextDrawable get_idImagen() {return this.evento.getIcono();}

	/**
	 * Metodo que retorna una cadena con la descripcion completa de un determinado evento.
	 * @return String que representa la descripcion completa del evento.
     */
	public String get_textoCompleto(){
		if (evento.getHora() == null){
			if (evento.getDescripcion() == null){
				return this.get_textoEncima() + ":\n" +this.get_textoDebajo();
			} else {
				return this.get_textoEncima() + ":\n" +this.get_textoDebajo()+ " ("+ this.evento.getDescripcion()+")";
			}
		} else {
			if (evento.getDescripcion() == null){
				return this.get_textoEncima() + ":\n" +this.get_textoDebajo()+ " de " + this.evento.getHora();
			} else {
				return this.get_textoEncima() + ":\n" +this.get_textoDebajo()+ " de " + this.evento.getHora()+ " ("+ this.evento.getDescripcion()+")";
			}
		}

	}
}
package com.example.leandro.appdit;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase que mantiene toda la informacion referente a las materias de la carreras Analista Programador Universitario y
 * Licenciatura en Sistemas (Or. Planificacion, Gestion y Control de Proyectos Informaticos) Plan 2010.
 */
public class Materia {

    private static String[] materias = {"Álgebra","Elementos de Informática", "Expresion de Problemas y Algoritmos","Inglés",
            "Análisis Matemático - S", "Algoritmica y Programación I", "Elementos de Lógica y Mat. Discreta",
            "Sistemas y Organizaciones", "Arquitectura de Computadoras", "Algoritmica y Programación II",
            "Base de Datos I", "Estadistica", "Programación Orientada a Objetos", "Introducción a la Concurrencia",
            "Laboratorio de Programación y Lenguajes", "Sistemas Operativos - S", "Ingenieria de Software I - T",
            "Desarrollo de Software", "Ingenieria de Software II - T", "Fundamentos Teóricos de Informática",
            "Redes y Transmisión de Datos", "Base de Datos II", "Paradigmas y Lenguajes de Programación - T",
            "Administración de Redes y Seguridad", "Ingenieria de Software III - T", "Aspectos Legales y Profesionales",
            "Sistemas Distribuidos", "Administracion de Proyectos", "Aplicaciones Web", "Taller de Nuevas Tecnologias",
            "Modelos y Simulación", "Planificación y Gestión de Sistemas de Información", "Sistemas de Soporte para la Toma de Decisiones",
            "Introducción a la Metodología de la Investigación Científica y Técnicas Comunicacionales", "Mesas de Exámenes Finales"};

    private static Map<String, Preferencia> materiasPreferencias = new HashMap<String, Preferencia>(); // HashMap que contiene los codigos de las materias junto a sus preferencias

    /**
     * Metodo privado que inicializa la informacion referente a las materias.
     * Solo se inicaliza una vez.
     */
    private static void inicializar(){
        if (materiasPreferencias.isEmpty()){
            materiasPreferencias.put("Álgebra", new Preferencia("5cv4ur5p3enrbvjlt875us5qes@group.calendar.google.com", "MA045", "A", Color.BLUE));
            materiasPreferencias.put("Elementos de Informática", new Preferencia("aod40c9ls454d9jqsh7oetc910@group.calendar.google.com", "IF001", "EI", Color.GREEN));
            materiasPreferencias.put("Expresion de Problemas y Algoritmos", new Preferencia("jrjdur8jrdkktb4b87ctfh9nt4@group.calendar.google.com","IF002", "EPA", Color.CYAN));
            materiasPreferencias.put("Inglés", new Preferencia("d66nul3cokiiq62t655ms0qj7c@group.calendar.google.com", "FA007", "I", Color.YELLOW));
            materiasPreferencias.put("Análisis Matemático - S", new Preferencia("nnib87ebfm3jc3rf4q4vl6hvas@group.calendar.google.com", "MA048", "AM", Color.RED));
            materiasPreferencias.put("Algoritmica y Programación I", new Preferencia("no563qhav0opleeksuljh2igvk@group.calendar.google.com", "IF003", "API", Color.LTGRAY));
            materiasPreferencias.put("Elementos de Lógica y Mat. Discreta", new Preferencia("ulejanbq8o0vu0051uikgcrcts@group.calendar.google.com", "MA008", "MD", Color.CYAN));
            materiasPreferencias.put("Sistemas y Organizaciones", new Preferencia("u435g4290uurj04akttvu8oe8o@group.calendar.google.com", "IF004", "SyO", Color.GREEN));
            materiasPreferencias.put("Arquitectura de Computadoras", new Preferencia("0vv9158nrqj66m8382hu665p4c@group.calendar.google.com", "IF005", "AC", Color.RED));
            materiasPreferencias.put("Algoritmica y Programación II", new Preferencia("oigrphjfmk8dbhble3ed7m897s@group.calendar.google.com", "IF006", "APII", Color.CYAN));
            materiasPreferencias.put("Base de Datos I", new Preferencia("qf0f4fj3skov4kbm3nr3fnujkg@group.calendar.google.com", "IF007", "BD", Color.YELLOW));
            materiasPreferencias.put("Estadistica", new Preferencia("5sq3en190kr31qv66c0n7592s8@group.calendar.google.com", "MA006", "E", Color.BLUE));
            materiasPreferencias.put("Programación Orientada a Objetos", new Preferencia("d3v1k1v4lkme3b6bo8id57ksso@group.calendar.google.com", "IF008", "POO", Color.DKGRAY));
            materiasPreferencias.put("Introducción a la Concurrencia", new Preferencia("m3j2o5j14v3r58oh8do9tupt6c@group.calendar.google.com", "IF038", "C", Color.BLACK));
            materiasPreferencias.put("Laboratorio de Programación y Lenguajes", new Preferencia("44n2egpv8jtf50gklttqtrb3b0@group.calendar.google.com", "IF009", "LPyL", Color.GRAY));
            materiasPreferencias.put("Sistemas Operativos - S", new Preferencia("ct35ivinohqe7hjos7v53h8chg@group.calendar.google.com", "IF037", "SO", Color.YELLOW));
            materiasPreferencias.put("Ingenieria de Software I - T", new Preferencia("oifnjkdoarm4ccc1rqpej3fr20@group.calendar.google.com", "IF040", "IST", Color.CYAN));
            materiasPreferencias.put("Desarrollo de Software", new Preferencia("li7nr9tu7ckfrvb0ssqpcqjko0@group.calendar.google.com", "IF012", "D", Color.RED));
            materiasPreferencias.put("Ingenieria de Software II - T", new Preferencia("k8dpu029kq3be4vobh1bh0trv8@group.calendar.google.com", "IF043", "IS2", Color.LTGRAY));
            materiasPreferencias.put("Fundamentos Teóricos de Informática", new Preferencia("sb12dshqfp74gvb5ku3fq74csk@group.calendar.google.com", "IF013", "F", Color.GREEN));
            materiasPreferencias.put("Redes y Transmisión de Datos", new Preferencia("479kq0gffslu7jivobkgpu9aj4@group.calendar.google.com", "IF019", "R", Color.MAGENTA));
            materiasPreferencias.put("Base de Datos II", new Preferencia("5nff8m8hobmj2ujls74059cdk4@group.calendar.google.com", "IF044", "BDII", Color.YELLOW));
            materiasPreferencias.put("Paradigmas y Lenguajes de Programación - T", new Preferencia("91rftv0gds25df85t7khasju00@group.calendar.google.com", "IF020", "PyLP", Color.BLACK));
            materiasPreferencias.put("Administración de Redes y Seguridad", new Preferencia("5vuope2lv20ecaphvtco546m80@group.calendar.google.com", "IF046", "ARyS", Color.CYAN));
            materiasPreferencias.put("Ingenieria de Software III - T", new Preferencia("9cfuban51c8egirdi9s4c2rqgs@group.calendar.google.com", "IF047", "IS3", Color.MAGENTA));
            materiasPreferencias.put("Aspectos Legales y Profesionales", new Preferencia("gmqdq5ebs0qk64f9ho5vm4rg1o@group.calendar.google.com", "IF016", "ALyP", Color.LTGRAY));
            materiasPreferencias.put("Sistemas Distribuidos", new Preferencia("f333raq619gkck7296ef6j61lg@group.calendar.google.com", "IF022", "SD", Color.BLUE));
            materiasPreferencias.put("Administracion de Proyectos", new Preferencia("1lkt8vhajum08gptuvol23ru9g@group.calendar.google.com", "IF049", "AP", Color.GREEN));
            materiasPreferencias.put("Aplicaciones Web", new Preferencia("desntamfq0aevjn09lmv98dvrc@group.calendar.google.com", "IF050", "AW", Color.YELLOW));
            materiasPreferencias.put("Taller de Nuevas Tecnologias", new Preferencia("q4sjke29116cd0ld3275p9ch9g@group.calendar.google.com", "IF017", "TNT", Color.RED));
            materiasPreferencias.put("Modelos y Simulación", new Preferencia("eh20c0mot7q95ijetu7rhp3rr4@group.calendar.google.com", "IF027", "MyS", Color.CYAN));
            materiasPreferencias.put("Planificación y Gestión de Sistemas de Información", new Preferencia("rq64o17me91gd3bnh21hebo0ho@group.calendar.google.com", "IF053", "PSI", Color.DKGRAY));
            materiasPreferencias.put("Sistemas de Soporte para la Toma de Decisiones", new Preferencia("fdaci27fn9ip704k1rtb32cuq0@group.calendar.google.com", "IF054", "SSTD", Color.MAGENTA));
            materiasPreferencias.put("Introducción a la Metodología de la Investigación Científica y Técnicas Comunicacionales", new Preferencia("l75l6tt1tj1mlaicfn9l1ohf70@group.calendar.google.com", "FA104", "TC", Color.GRAY));
            materiasPreferencias.put("Mesas de Exámenes Finales", new Preferencia("fpjl7d9peh03r2r7oos2pc32es@group.calendar.google.com", "EXAMEN", "F", Color.RED));
        }
    }

    /**
     * Metodo estatico que arma un HashMap con las materias y sus correspondientes calendarios de acuerdo a las preferencias
     * del usuario.
     * @param pref SharedPreferences coleccion con las preferencias del usuario.
     * @return HashMap con las materias y sus correspondientes calendarios de acuerdo a las preferencias.
     * @see Preferencia#getCalendario()
     */
    public static Map<String, String> armarPreferencias(SharedPreferences pref){
        inicializar();
        Map<String, String> preferencias = new HashMap<String, String>();
        String id;
        ArrayList<String> materias = getPreferencias(pref);
        for (String materia: materias){
            id = materiasPreferencias.get(materia).getCalendario();
            if (id != null){
                preferencias.put(materia, id);
            }
        }
        return preferencias;
    }

    /**
     * Metodo estatico que arma un listado con las materias de preferencias del usuario.
     * @param pref SharedPreferences coleccion con las preferencias del usuario.
     * @return ArrayList de String que representa el listado de preferencias del usuario.
     */
    public static ArrayList<String> getPreferencias(SharedPreferences pref){
        ArrayList<String> preferencias = new ArrayList<String>();
        for (String materia: getMaterias()) {
            if (pref.getBoolean(materia,false)){
                preferencias.add(materia);
            }
        }
        return preferencias;
    }

    /**
     * Metodo que dada una materia, retorna su codigo de identificacion de acuerdo al plan.
     * @param materia String que representa el nombre de la materia.
     * @return String que representa el codigo de la materia pasada como parametro.
     * @see Preferencia#getCodMateria()
     */
    @Nullable
    public static String getCodigoMateria(String materia){
        inicializar();
        String codigo = materiasPreferencias.get(materia).getCodMateria();
        if (codigo != null){
            return codigo;
        }
        return null;
    }

    /**
     * Metodo que retorna el color con el que se mostrara la materia.
     * @param materia String que representa el nombre de la materia.
     * @return int que representa el color que tendra la materia cuando se muestre al usuario.
     * @see Preferencia#getColor()
     */
    public static int getColor(String materia){
        return materiasPreferencias.get(materia).getColor();
    }

    /**
     * Metodo que retorna las iniciales de la materia.
     * @param materia String que representa el nombre de la materia.
     * @return String que representa las iniciales de la materia.
     * @see Preferencia#getInicial()
     */
    public static String getIniciales(String materia){
        return materiasPreferencias.get(materia).getInicial();
    }

    /**
     * Metodo que retorna una lista completa de los nombres de las materias.
     * @return String[] - Lista de String que representan a las materias.
     */
    public static String[] getMaterias(){
        return materias;
    }
}

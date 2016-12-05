package com.example.leandro.appdit;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;


/**
 * Clase que se enncarga del manejo de los eventos de Geofencing y de enviar
 * la información correspondiente a la Web.
 */
public class Servicio extends IntentService{

    HttpURLConnection conexion;
    // Pasar a un archivo de configuración. Poner en R values
    static final String URL_CONN = "https://horariosv3-pazitos10.rhcloud.com/api/asistencia/";

    /**
     * Metodo Constructor
     */
    public Servicio () {
        super(Servicio.class.getSimpleName());
    }

    /**
     * Metodo encargado del manejo de los eventos de Geofencing
     * @param intent Objeto Intent inicializador del servicio.
     * @see GeofencingEvent
     * @see GeofencingEvent#fromIntent(Intent)
     * @see Geofence
     * @see Log
     */
    @Override
    protected void onHandleIntent(Intent intent) {

        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);

        // Get the transition type.
        int geofenceTransition = geofencingEvent.getGeofenceTransition();

        // Test that the reported transition was of interest.
        if (geofenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER){
            Log.i("EVENT","ENTRANDO");
            sendNotification("ENTRANDO");
            sendInfo();
        }
        if (geofenceTransition == Geofence.GEOFENCE_TRANSITION_DWELL) {

            Log.i("EVENT","ESTANDO");
            sendNotification("ESTANDO");
        }

        if (geofenceTransition == Geofence.GEOFENCE_TRANSITION_EXIT){

            Log.i("EVENT","SALIENDO");
            sendNotification("SALIENDO");
        }

    }

    /**
     * Metodo encargado de enviar la información necesaria para
     * registrar la asistencia al servidor Web.
     * @see URL
     * @see JSONObject
     * @see Log
     */
    private void sendInfo(){
        try {

            URL destino =  new URL(URL_CONN);
            conexion = (HttpURLConnection) destino.openConnection();
            conexion.setDoInput(true);
            conexion.setDoOutput(true);
            conexion.setRequestProperty("Content-Type", "application/json");
            conexion.setRequestProperty("Accept", "application/json");
            conexion.setRequestMethod("POST");
            conexion.connect();

            JSONObject data = new JSONObject();
            Date dia =  new Date();

            data.put("id_alumno","1");
            data.put("id_materia","IF002");
            //SimpleDateFormat fmtOut = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            //Object a = dia.toString();
            //data.put("fecha", fmtOut.format(dia.getTime()));
            data.put("fecha","2016-06-16T18:00:00Z");
            data.put("latitud","-43.4343");
            data.put("longitud","60.123213");
            OutputStream os = conexion.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(data.toString());
            writer.flush();
            writer.close();
            os.close();
            int response = conexion.getResponseCode();
            if (response == HttpURLConnection.HTTP_OK){
                Log.i("Servicio","Respuesta");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexion.disconnect();
            Log.i("Servicio", "Disconnect");
        }
    }

    /**
     * Metodo encargado de generar la notificacion del registro de asistencia
     * y ponerla a disposicion de la plataforma para mostrarla.
     * @param notificationDetails String que representa los datos a mostrar por la notificacion.
     */
    private void sendNotification(String notificationDetails) {
        // Create an explicit content Intent that starts the main Activity.
        Intent notificationIntent = new Intent(getApplicationContext(), MainActivity.class);

        // Construct a task stack.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);

        // Add the main Activity to the task stack as the parent.
        stackBuilder.addParentStack(MainActivity.class);

        // Push the content Intent onto the stack.
        stackBuilder.addNextIntent(notificationIntent);

        // Get a PendingIntent containing the entire back stack.
        PendingIntent notificationPendingIntent =
                stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        // Get a notification builder that's compatible with platform versions >= 4
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        // Define the notification settings.
        builder.setContentTitle(notificationDetails)
                .setSmallIcon(R.drawable.small_icon)
                .setContentText("Click notification to return to app")
                .setContentIntent(notificationPendingIntent);

        // Dismiss notification once the user touches it.
        builder.setAutoCancel(true);

        // Get an instance of the Notification manager
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Issue the notification
        mNotificationManager.notify(0, builder.build());

    }

}



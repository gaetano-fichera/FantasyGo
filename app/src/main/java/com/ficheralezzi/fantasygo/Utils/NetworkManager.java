package com.ficheralezzi.fantasygo.Utils;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.ficheralezzi.fantasygo.R;

import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by gaetano on 24/08/17.
 */

public class NetworkManager {

    private static final String TAG = "NetworkManager";

    private static final int TYPE_WIFI = 1;
    private static final int TYPE_MOBILE = 2;
    private static final int TYPE_NOT_CONNECTED = 0;

    public static final int TEST_CONNECTION = 499;
    public static final int TEST_DB = 400;
    public static final int UPDATE_LOCATION = 401;

    private static final String urlSeparator = "/";

    public static boolean isOnline(Activity activity) {
        ConnectivityManager cm =
                (ConnectivityManager) activity.getSystemService(activity.getApplicationContext().CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public static int getConnectivityStatus(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork) {
            if(activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                return TYPE_WIFI;

            if(activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                return TYPE_MOBILE;
        }
        return TYPE_NOT_CONNECTED;
    }

    public static String getConnectivityStatusString(Context context) {
        int conn = NetworkManager.getConnectivityStatus(context);
        String status = null;
        if (conn == NetworkManager.TYPE_WIFI) {
            status = "Wifi enabled";
        } else if (conn == NetworkManager.TYPE_MOBILE) {
            status = "Mobile data enabled";
        } else if (conn == NetworkManager.TYPE_NOT_CONNECTED) {
            status = "Not connected to Internet";
        }
        return status;
    }

    public static void showToastOffline(Context context){
        Toast.makeText(context, "Connessione non Disponibile", Toast.LENGTH_SHORT).show();
    }

    public static void updateLocationOnServer(Context context , Location location) throws JSONException {
        Messaggio messaggio = new Messaggio();

        ArrayList<String> datiGiocatore = new ArrayList<>();
        datiGiocatore.add("G00001");
        datiGiocatore.add(String.valueOf(location.getLatitude()));
        datiGiocatore.add(String.valueOf(location.getLongitude()));

        messaggio.setMessaggio(1);
        messaggio.setObject(datiGiocatore);

        Log.i(TAG, messaggio.toString());

        String Url = getUrl(context, TEST_DB);

        Volley.sendJSON(context, Url, messaggio);
    }

    public static void testConnection(Context context) throws JSONException {
        String Url = getUrl(context, TEST_CONNECTION);

        Volley.sendJSON(context, Url, new Messaggio());
    }

    //in base al valore di intention viene creato l'url desiderato
    public static String getUrl(Context context, int intention){
        String Url = context.getString(R.string.HTTP_Server_Address) + urlSeparator + context.getString(R.string.Server_Api);

        if (intention == TEST_DB) Url += (urlSeparator + context.getString(R.string.Test_Api) + urlSeparator + context.getString(R.string.TestDb_Api));
        else if (intention == TEST_CONNECTION) Url += (urlSeparator + context.getString(R.string.Test_Api) + urlSeparator + context.getString(R.string.TestConnection_Api));

        Log.i(TAG, "Url: " + Url);

        http://localhost:8080/ApiFantasyGo/ApiTest/TestConnection

        return Url;
    }
}

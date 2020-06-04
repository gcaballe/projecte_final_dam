package cat.cabapek.appdeconsulta;

import android.icu.text.IDNA;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import cat.cabapek.*;

public class DataManager  extends AsyncTask<String, String, String> {

    public ArrayList<InfoRuta> infoRutas;
    public InfoRuta infoRuta;
    public ArrayList<Punt> punts;

    int trampes = 0;
    @Override
    protected String doInBackground(String... strings) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(strings[0].equals("1")){
            //if(trampes == 0){
              //  trampes = 1;
                infoRutas =  getInfoRutas();
            //}
        }else if(strings[0].equals("2")){
            Integer id_ruta = Integer.parseInt(strings[1]);
            getInfoRuta(id_ruta);
        }
        return null;
    }

    private ArrayList<InfoRuta> getInfoRutas(){

        ArrayList<InfoRuta> arr_ir = new ArrayList<InfoRuta>();

        InetAddress host = null;
        try {
            host = InetAddress.getByName("192.168.1.217");
        } catch (UnknownHostException e) {
            Log.e("app", "UnknownHostException al conectar al host + " + host);
        }

        Socket socket = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;

        try {
            socket = new Socket(host.getHostName(), 9876);

            oos = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Sending request to Socket Server");
            Integer codi = 1;
            oos.writeObject(codi);

            ois = new ObjectInputStream(socket.getInputStream());
            Integer size = (Integer) ois.readObject();
            System.out.println("size: " + size);

            for(int i = 0; i < size; i++){
                int id = (int)ois.readObject();
                String titol = (String)ois.readObject();
                String descMarkDown = (String)ois.readObject();
                int desnivell = (int)ois.readObject();
                int alcadaMax = (int)ois.readObject();
                int alcadaMin = (int)ois.readObject();
                float distanciaKm = (float)ois.readObject();
                int tempsAprox = (int)ois.readObject();
                boolean circular = (boolean)ois.readObject();
                int dificultat = (int)ois.readObject();
                String gpxURL = (String)ois.readObject();
                String photoURL = (String)ois.readObject();
                String photoTitol = (String)ois.readObject();

                Integer idCat = (int)ois.readObject();
                String nomCat = (String)ois.readObject();

                Categoria c = new Categoria(idCat, nomCat);

                InfoRuta ir = new InfoRuta(id, titol, descMarkDown,desnivell, alcadaMax, alcadaMin, distanciaKm, tempsAprox, circular, dificultat, gpxURL, photoURL, photoTitol, c);
                arr_ir.add(ir);
            }

            ois.close();
            oos.close();

        } catch (IOException e) {
            Log.e("app", "IOException al conectar al JavaServer");
        } catch (ClassNotFoundException e) {
            Log.e("app", "ClassNotFoundException al conectar al JavaServer");
        }
        return arr_ir;
    }

    //carrego a memoria infoRUta + array de punts de la ruta
    private void getInfoRuta(Integer id_ruta) {

        Log.d("app","Entro a getInfoRuta(id_ruta="+id_ruta+")");

        punts = new ArrayList<Punt>();

        InetAddress host = null;
        try {
            host = InetAddress.getByName("192.168.1.217");
        } catch (UnknownHostException e) {
            Log.e("app", "UnknownHostException al conectar al host + " + host);
        }
        Socket socket = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;

        try {
            socket = new Socket(host.getHostName(), 9876);
        } catch (IOException e) {
            Log.e("app", "IOException al conectar al socket.");
        }

        try {
            oos = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Sending request to Socket Server");
            Integer codi = 2;
            oos.writeObject(codi);

            //envio id_ruta
            oos.writeObject(id_ruta);

            ois = new ObjectInputStream(socket.getInputStream());

            //getInfoRuta
            int id = (int)ois.readObject();
            String titol = (String)ois.readObject();
            String descMarkDown = (String)ois.readObject();
            int desnivell = (int)ois.readObject();
            int alcadaMax = (int)ois.readObject();
            int alcadaMin = (int)ois.readObject();
            float distanciaKm = (float)ois.readObject();
            int tempsAprox = (int)ois.readObject();
            boolean circular = (boolean)ois.readObject();
            int dificultat = (int)ois.readObject();
            String gpxURL = (String)ois.readObject();
            String photoURL = (String)ois.readObject();
            String photoTitol = (String)ois.readObject();

            Integer idCat = (int)ois.readObject();
            String nomCat = (String)ois.readObject();

            Categoria c = new Categoria(idCat, nomCat);

            infoRuta = new InfoRuta(id, titol, descMarkDown,desnivell, alcadaMax, alcadaMin, distanciaKm, tempsAprox, circular, dificultat, gpxURL, photoURL, photoTitol, c);

            //get num_punts
            Integer size = (Integer) ois.readObject();

            //get punts
            for(int i = 0; i < size; i++){
                int numero = (int)ois.readObject();
                String nom = (String)ois.readObject();
                String descripcio = (String)ois.readObject();
                int hora = (int)ois.readObject();
                double latitud = (double)ois.readObject();
                double longitud = (double)ois.readObject();
                int elevacio = (int)ois.readObject();
                photoURL = (String)ois.readObject();
                photoTitol = (String)ois.readObject();
                int ruta = (int)ois.readObject();

                Punt p = new Punt(numero, nom, descripcio, hora, latitud, longitud, elevacio, photoURL, photoTitol, ruta);
                punts.add(p);
            }

            ois.close();
            oos.close();

        } catch (IOException e) {
            Log.e("app", "IOException al conectar al JavaServer");
        } catch (ClassNotFoundException e) {
            Log.e("app", "ClassNotFoundException al conectar al JavaServer");
        }

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);


    }
}

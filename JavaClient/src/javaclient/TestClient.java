/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaclient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import cat.cabapek.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author guillem
 */
public class TestClient {
    
   
    // AQUEST MAIN TESTEJA EL CODI 1
    
    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{
        //get the localhost IP address, if server is running on some other IP, you need to use that
        InetAddress host = InetAddress.getByName("192.168.1.217");
        Socket socket = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        
            
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
                System.out.println("ir="+ir);
            }
            
            
            ois.close();
            oos.close();
           
        
    }

    
    //AQUEST MAIN TESTEJA CODI 2
    /*public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{
        //get the localhost IP address, if server is running on some other IP, you need to use that
        InetAddress host = InetAddress.getLocalHost();
        Socket socket = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        
            
            socket = new Socket(host.getHostName(), 9876);
            
            oos = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Sending request to Socket Server");
            Integer codi = 2;
            oos.writeObject(codi);
            
            //envio id_ruta
            Integer id_ruta = 1;
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
                
            InfoRuta ir = new InfoRuta(id, titol, descMarkDown,desnivell, alcadaMax, alcadaMin, distanciaKm, tempsAprox, circular, dificultat, gpxURL, photoURL, photoTitol, c);
            System.out.println("InfoRuta="+ir);
            
            //get num_punts
            Integer size = (Integer) ois.readObject();
            System.out.println("size: " + size);
            
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
                System.out.println("PUnt="+p);
            }
            
            ois.close();
            oos.close();
           
        
    }*/
    
}

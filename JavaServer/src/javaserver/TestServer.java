/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaserver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.Query;

/**
 *
 * @author guillem
 */
public class TestServer {
    
    private static ServerSocket server;
    private static Socket socket = null;
    private static Connection con;

    
    public static void main(String args[]) throws IOException, ClassNotFoundException{
        
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream("config.properties"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JavaServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JavaServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {  
            Class.forName(prop.getProperty("db.driver"));
            con=DriverManager.getConnection(prop.getProperty("db.url"),prop.getProperty("db.username"),prop.getProperty("db.password"));
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(JavaServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        int port = Integer.parseInt(prop.getProperty("server.port"));
        
        server = new ServerSocket(port);
        
        while(true){
            System.out.println("Waiting for the client request");
            
            Socket socket = server.accept();
            
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            
            Integer codi = (Integer) ois.readObject();
            System.out.println("Message Received: " + codi);
            
            
            
            if(codi == 1){
                //ObjectOutputStream oos = null;
                List<InfoRuta> arr = getListInfoRuta();
                //oos = new ObjectOutputStream(socket.getOutputStream());
                Integer size = arr.size();
                oos.writeObject(size);
                
                System.out.println("He retornat size="+size);
                
                for(int i = 0; i < size; i++){
                    InfoRuta ir = arr.get(i);
                    System.out.println("Envio "+ir);
                    oos.writeObject(ir.getId());
                    oos.writeObject(ir.getTitol());
                    oos.writeObject(ir.getDescMarkDown());
                    oos.writeObject(ir.getDesnivell());
                    oos.writeObject(ir.getAlçadaMax());
                    oos.writeObject(ir.getAlcadaMin());
                    oos.writeObject(ir.getDistanciaKm());
                    oos.writeObject(ir.getTempsAprox());
                    oos.writeObject(ir.isCircular());
                    oos.writeObject(ir.getDificultat());
                    oos.writeObject(ir.getGpxURL());
                    oos.writeObject(ir.getPhotoURL());
                    oos.writeObject(ir.getPhotoTitol());
                }
                oos.close();
            }else if (codi == 2){
                
                //get id_ruta
                Integer id_ruta = (Integer) ois.readObject();
                
                //envio InfoRuta
                InfoRuta ir = getInfoRuta(id_ruta);
                System.out.println("Envio "+ir);
                oos.writeObject(ir.getId());
                oos.writeObject(ir.getTitol());
                oos.writeObject(ir.getDescMarkDown());
                oos.writeObject(ir.getDesnivell());
                oos.writeObject(ir.getAlçadaMax());
                oos.writeObject(ir.getAlcadaMin());
                oos.writeObject(ir.getDistanciaKm());
                oos.writeObject(ir.getTempsAprox());
                oos.writeObject(ir.isCircular());
                oos.writeObject(ir.getDificultat());
                oos.writeObject(ir.getGpxURL());
                oos.writeObject(ir.getPhotoURL());
                oos.writeObject(ir.getPhotoTitol());
                
                //getArrayPunts
                List<Punt> arr_punts = getListPunts(id_ruta);
                
                int num_punts = arr_punts.size();
                //envio num_punts
                oos.writeObject(num_punts);
            
                //envio Arraypunts
                for(int x = 0; x < num_punts; x++){
                    Punt p = arr_punts.get(x);
                    System.out.println("Envio "+p);
                    oos.writeObject(p.getNumero());
                    oos.writeObject(p.getNom());
                    oos.writeObject(p.getDescripcio());
                    oos.writeObject(p.getHora());
                    oos.writeObject(p.getLatitud());
                    oos.writeObject(p.getLongitud());
                    oos.writeObject(p.getElevació());
                    oos.writeObject(p.getPhotoURL());
                    oos.writeObject(p.getPhotoTitol());
                    oos.writeObject(p.getRuta());  
                }
                
                oos.close();
                
            }else {
                
                System.out.println("Ara mateix nomès s'accepten els codis 1 i 2.");
                
            }
            
            ois.close();
            socket.close();
            
            
        }
        //System.out.println("Shutting down Socket server!!");
        //close the ServerSocket object
        //server.close();
        //con.close();
    }
    
    
    
    
    public static List<InfoRuta> getListInfoRuta(){
        
        List<InfoRuta> ret = new ArrayList<InfoRuta>();
        Statement stmt;
        ResultSet rs;
                
        try {  
            
            stmt = con.createStatement();  
            rs = stmt.executeQuery("SELECT r.*, f.titol as foto_titol, f.url as foto_url FROM ruta r JOIN foto f on (r.foto = f.url)");  
            while(rs.next()){
                int id = rs.getInt("id");
                String titol = rs.getString("titol");
                String descMarkDown = rs.getString("descMarkDown");
                int desnivell = rs.getInt("desnivell");
                int alcadaMax = rs.getInt("alcadaMax");
                int alcadaMin = rs.getInt("alcadaMin");
                int distanciaKm = rs.getInt("distanciaKm");
                int tempsAprox = rs.getInt("tempsAprox");
                boolean circular = rs.getBoolean("circular");
                int dificultat5 = rs.getInt("dificultat5");

                String gpxURL = rs.getString("gpxFileUrl");
                String photoURL = rs.getString("foto_url");
                String photoTitol = rs.getString("foto_titol");
                ret.add(new InfoRuta(id, titol, descMarkDown, desnivell, alcadaMax, alcadaMin, distanciaKm, tempsAprox, circular, dificultat5, gpxURL, photoURL, photoTitol));
            }
            rs.close();
        
        } catch (SQLException ex) {
            Logger.getLogger(JavaServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.out.println("Retorno: " + ret.toString());
        
        return ret;
    }
    
    
    public static InfoRuta getInfoRuta(Integer idRuta){
        
        InfoRuta ret = null;
        ResultSet rs;
                
        try {  
            
            PreparedStatement ps = con.prepareStatement("SELECT r.*, f.titol as foto_titol, f.url as foto_url FROM ruta r JOIN foto f on (r.foto = f.url) WHERE r.id = ?");  
            ps.setInt(1, idRuta);
            rs = ps.executeQuery();
            
            while(rs.next()){
                int id = rs.getInt("id");
                String titol = rs.getString("titol");
                String descMarkDown = rs.getString("descMarkDown");
                int desnivell = rs.getInt("desnivell");
                int alcadaMax = rs.getInt("alcadaMax");
                int alcadaMin = rs.getInt("alcadaMin");
                int distanciaKm = rs.getInt("distanciaKm");
                int tempsAprox = rs.getInt("tempsAprox");
                boolean circular = rs.getBoolean("circular");
                int dificultat5 = rs.getInt("dificultat5");

                String gpxURL = rs.getString("gpxFileUrl");
                String photoURL = rs.getString("foto_url");
                String photoTitol = rs.getString("foto_titol");
                ret = new InfoRuta(id, titol, descMarkDown, desnivell, alcadaMax, alcadaMin, distanciaKm, tempsAprox, circular, dificultat5, gpxURL, photoURL, photoTitol);
            }
            rs.close();
        
        } catch (SQLException ex) {
            Logger.getLogger(JavaServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Retorno: " + ret);
        return ret;
        
    }
    
    public static List<Punt> getListPunts(Integer idRuta){
        
        List<Punt> ret = new ArrayList<Punt>();
        ResultSet rs;
                
        try {
            
            PreparedStatement ps = con.prepareStatement("SELECT p.*, f.titol as foto_titol, f.url as foto_url FROM punt p JOIN foto f on (p.foto = f.url) WHERE p.ruta = ?");  
            ps.setInt(1, idRuta);
            rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("numero");
                String nom = rs.getString("nom");
                String descripcio = rs.getString("descripcio");
                int hora = rs.getInt("hora");
                double latitud = rs.getDouble("latitud");
                double longitud = rs.getDouble("longitud");
                int elevacio = rs.getInt("elevacio");

                String photoURL = rs.getString("foto_url");
                String photoTitol = rs.getString("foto_titol");
                int ruta = rs.getInt("ruta");
                ret.add(new Punt(id, nom, descripcio, hora, latitud, longitud, elevacio, photoURL, photoTitol, ruta));
            }
            rs.close();
        
        } catch (SQLException ex) {
            Logger.getLogger(JavaServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Dins de getListPUnts Retorno: " + ret);
        return ret;
    }
    
}
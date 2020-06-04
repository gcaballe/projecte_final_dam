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
import cat.cabapek.*;


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
            System.out.println("No s'ha trobat el arxiu config.properties.");
        } catch (IOException ex) {
            System.out.println("Ha petat amb IOException al obrir config.properties");
        }
        
        try {  
            Class.forName(prop.getProperty("db.driver"));
            con=DriverManager.getConnection(prop.getProperty("db.url"),prop.getProperty("db.username"),prop.getProperty("db.password"));
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Error al conectar a la BD." + ex.getMessage());
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
                    oos.writeObject(ir.getCategoria().getId());
                    oos.writeObject(ir.getCategoria().getNom());
                }
                oos.close();
            }else if (codi == 2){
                
                //get id_ruta
                Integer id_ruta = (Integer) ois.readObject();
                
                System.out.println("Un client m'ha demanat infoRuta(id_ruta="+id_ruta+")");
                
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
                oos.writeObject(ir.getCategoria().getId());
                oos.writeObject(ir.getCategoria().getNom());
                    
                //getArrayPunts
                List<Punt> arr_punts = getListPunts(id_ruta);
                
                int num_punts = arr_punts.size();
                //envio num_punts
                oos.writeObject(num_punts);
                System.out.println("Procedeixo a enviar "+num_punts+" punts.");
            
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
            rs = stmt.executeQuery("SELECT r.*, f.titol as foto_titol, f.url as foto_url, c.id as cat_id, c.nom as cat_nom FROM ruta r JOIN foto f on (r.foto = f.url) JOIN categoria c on (r.categoria = c.id)");  
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
                
                Integer idCat = rs.getInt("cat_id");
                String nomCat = rs.getString("cat_nom");
                
                Categoria c = new Categoria(idCat, nomCat);
                ret.add(new InfoRuta(id, titol, descMarkDown, desnivell, alcadaMax, alcadaMin, distanciaKm, tempsAprox, circular, dificultat5, gpxURL, photoURL, photoTitol, c));
            }
            rs.close();
        
        } catch (SQLException ex) {
            System.out.println("Error de SQL: "+ ex.getMessage());
        }
        System.out.println("He fet query a la BD (getListInfoRutes): " + ret.toString());
        
        return ret;
    }
    
    
    public static InfoRuta getInfoRuta(Integer idRuta){
        
        InfoRuta ret = null;
        ResultSet rs;
                
        try {  
            
            PreparedStatement ps = con.prepareStatement("SELECT r.*, f.titol as foto_titol, f.url as foto_url, c.id as cat_id, c.nom as cat_nom FROM ruta r JOIN foto f on (r.foto = f.url) JOIN categoria c on (r.categoria = c.id) WHERE r.id = ?");  
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
                
                Integer idCat = rs.getInt("cat_id");
                String nomCat = rs.getString("cat_nom");
                
                Categoria c = new Categoria(idCat, nomCat);
                ret = new InfoRuta(id, titol, descMarkDown, desnivell, alcadaMax, alcadaMin, distanciaKm, tempsAprox, circular, dificultat5, gpxURL, photoURL, photoTitol, c);
            }
            rs.close();
        
        } catch (SQLException ex) {
            System.out.println("Error de SQL: "+ ex.getMessage());
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
            System.out.println("Error de SQL: "+ ex.getMessage());
        }
        System.out.println("Dins de getListPunts Retorno: " + ret);
        return ret;
    }
    
}
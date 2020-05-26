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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author guillem
 */
public class JavaServer {

    private static ServerSocket server;
    private static int port = 9876;
    private static Socket socket = null;
    private static Connection con;
    
    public static void main(String args[]) {

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
        
        try {
            server = new ServerSocket(port);
            while(true){
                
                System.out.println("esperant request...");
                socket = server.accept();
                
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                
                
                int codi = ois.readInt();
                System.out.println("He rebut codi="+codi);
                
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeInt(codi + 1);
                
                
                ois.close();
                oos.close();
                            
                socket.close();
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                 /*   
                
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                System.out.println("step1");
                int z = 420;
                oos.writeInt(z);
                System.out.println("step3");
                
                
                oos.close();
                
                */
                
/*
                switch(codi){
                    case 1:
                        //enviarRutes();
                        ObjectOutputStream oos;
                        List<InfoRuta> ll_ir = getListInfoRuta();
                        oos = new ObjectOutputStream(socket.getOutputStream());
                        int num_ll = ll_ir.size();
                        oos.writeInt(num_ll);
                        oos.close();
                        System.out.println("break");
                        
                        break;
                    case 2:
                        int idRuta = (int) ois.readInt();
                        enviarInfoRuta(idRuta);
                        break;
                    default:
                        System.out.println("Codi ha de ser 1 o 2.");
                        break;
                }
*/
                
                //socket.close();
            }
        //System.out.println("Shutting down Socket server!!");
        //server.close();
        } catch (IOException ex) {
            Logger.getLogger(JavaServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void enviarRutes(){
        
        ObjectOutputStream oos;
        List<InfoRuta> ll_ir = getListInfoRuta();
        try {
            oos = new ObjectOutputStream(socket.getOutputStream());
            int num_ll = ll_ir.size();
            oos.writeInt(num_ll);
            System.out.println("step 1. num_ll="+num_ll);
            //oos.close();
            
            //oos = new ObjectOutputStream(socket.getOutputStream());
            for(int i = 0; i < num_ll; i++){
                System.out.println("Envio InfoRuta nº: " + i);
                oos.writeObject(ll_ir.get(i));
            }
            oos.close();
        } catch (IOException ex) {
            Logger.getLogger(JavaServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public static void enviarInfoRuta(int idRuta){
        
        System.out.println("aqui enviaria infoRuta de "+idRuta);
        
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
            con.close();
        
        } catch (SQLException ex) {
            Logger.getLogger(JavaServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.out.println("Retorno: " + ret.toString());
        
        return ret;
    }
    
    
}

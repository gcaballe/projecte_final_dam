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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author guillem
 */
public class JavaClient {

    private static int port = 9876;
        
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException{
        
        InetAddress host = null;
        try {
            host = InetAddress.getLocalHost();
        } catch (UnknownHostException ex) {
            System.out.println("Host desconegut.");
            return;
        }
        Socket socket = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try {
            
            System.out.println("Engego el client.");
            socket = new Socket(host.getHostName(), port);
            
            oos = new ObjectOutputStream(socket.getOutputStream());
            int codi = 1;
            oos.writeInt(codi);
            System.out.println("JA he enviat i=" + codi);
            
            
            
            ois = new ObjectInputStream(socket.getInputStream());
            int response = ois.readInt();
            System.out.println("response: " + response);
            
            
            
            
            ois.close();
            oos.close();
            
//socket.close();
            
            //oos.writeInt(codi);
            
            /*
            
            
            
            
            */
            
            
            
            
            
            
            //ois.close();
            
            //System.out.println("size="+size);
            //socket.close();
            
        } catch (UnknownHostException ex) {
            Logger.getLogger(JavaClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JavaClient.class.getName()).log(Level.SEVERE, null, ex);
        }
            /*
            List<InfoRuta> ll_ir = new ArrayList<InfoRuta>();
            
            try {
            host = InetAddress.getLocalHost();
            socket = new Socket(host.getHostName(), port);
            System.out.println("Conectat al servidor.");
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            int c = 1;
            System.out.println("Envio codi="+c);
            oos.writeInt(c);

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            int size = ois.readInt();
            System.out.println("size="+size);
            
            for(int i = 0; i < size; i++){
            System.out.println("Envio InfoRuta nº: " + size);
            InfoRuta ir = (InfoRuta) ois.readObject();
            ll_ir.add(ir);
            }
            
            oos.close();
            ois.close();
            } catch (IOException ex) {
            ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            }
            
            System.out.println("RESULTAT: "+ll_ir.toString());
            socket.close();
            */
        
    }

        
    /*
    Per enviar infoRUta, primer envio codi = 1, despres size rutes, i despres loop de objectes
    
    public static List<InfoRuta> getListInfoRuta(){

        List<InfoRuta> ll_ir = new ArrayList<InfoRuta>();
       
        try {
            host = InetAddress.getLocalHost();
            socket = new Socket(host.getHostName(), port);
            System.out.println("Conectat al servidor.");
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            int c = 1;
            System.out.println("Envio codi="+c);
            oos.writeInt(c);
            oos.close();
            System.out.println("tanco OOS");
            
            System.out.println("creo OIS");
            int size = ois.readInt();
            System.out.println("size="+size);
            
            for(int i = 0; i < size; i++){
                System.out.println("Envio InfoRuta nº: " + size);
                InfoRuta ir = (InfoRuta) ois.readObject();
                ll_ir.add(ir);
            }
            
            ois.close();
            
        } catch (IOException ex) {
            Logger.getLogger(JavaClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JavaClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return ll_ir;
    }*/
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.cabapek;

/**
 *
 * @author guillem
 */
public class Punt {
    
    private int numero;
    private String nom;
    private String descripcio;
    private int hora;
    private double latitud;
    private double longitud;
    private int elevació;
    private String photoURL;
    private String photoTitol;
    private int ruta;

    public Punt(int numero, String nom, String descripcio, int hora, double latitud, double longitud, int elevació, String photoURL, String photoTitol, int ruta) {
        this.numero = numero;
        this.nom = nom;
        this.descripcio = descripcio;
        this.hora = hora;
        this.latitud = latitud;
        this.longitud = longitud;
        this.elevació = elevació;
        this.photoURL = photoURL;
        this.photoTitol = photoTitol;
        this.ruta = ruta;
    }

    @Override
    public String toString() {
        return "Punt{" + "numero=" + numero + ", nom=" + nom + ", descripcio=" + descripcio + ", hora=" + hora + ", latitud=" + latitud + ", longitud=" + longitud + ", elevaci\u00f3=" + elevació + ", photoURL=" + photoURL + ", photoTitol=" + photoTitol + ", ruta=" + ruta + '}';
    }

    
    
    
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public int getElevació() {
        return elevació;
    }

    public void setElevació(int elevació) {
        this.elevació = elevació;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getPhotoTitol() {
        return photoTitol;
    }

    public void setPhotoTitol(String photoTitol) {
        this.photoTitol = photoTitol;
    }

    public int getRuta() {
        return ruta;
    }

    public void setRuta(int ruta) {
        this.ruta = ruta;
    }
    
    
    
}

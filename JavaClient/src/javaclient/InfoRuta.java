/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaclient;

import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author guillem
 */
public class InfoRuta {
    
    private int id;
    private String titol;
    private String descMarkDown;
    private int desnivell;
    private int alçadaMax;
    private int alcadaMin;
    private float distanciaKm;
    private int tempsAprox;
    private boolean circular;
    private int dificultat;
    private String gpxURL;
    private String photoURL;
    private String photoTitol;

    public InfoRuta(int id, String titol, String descMarkDown, int desnivell, int alçadaMax, int alcadaMin, float distanciaKm, int tempsAprox, boolean circular, int dificultat, String gpxURL, String photoURL, String photoTitol) {
        this.id = id;
        this.titol = titol;
        this.descMarkDown = descMarkDown;
        this.desnivell = desnivell;
        this.alçadaMax = alçadaMax;
        this.alcadaMin = alcadaMin;
        this.distanciaKm = distanciaKm;
        this.tempsAprox = tempsAprox;
        this.circular = circular;
        this.dificultat = dificultat;
        this.gpxURL = gpxURL;
        this.photoURL = photoURL;
        this.photoTitol = photoTitol;
    }    

    @Override
    public String toString() {
        return "InfoRuta{" + "id=" + id + ", titol=" + titol + ", descMarkDown=" + descMarkDown.substring(0,15) + "..." + ", desnivell=" + desnivell + ", al\u00e7adaMax=" + alçadaMax + ", alcadaMin=" + alcadaMin + ", distanciaKm=" + distanciaKm + ", tempsAprox=" + tempsAprox + ", circular=" + circular + ", dificultat=" + dificultat + ", gpxURL=" + gpxURL + ", photoURL=" + photoURL + ", photoTitol=" + photoTitol + '}';
    }
    
    
    //getters setters
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitol() {
        return titol;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public String getDescMarkDown() {
        return descMarkDown;
    }

    public void setDescMarkDown(String descMarkDown) {
        this.descMarkDown = descMarkDown;
    }

    public int getDesnivell() {
        return desnivell;
    }

    public void setDesnivell(int desnivell) {
        this.desnivell = desnivell;
    }

    public int getAlçadaMax() {
        return alçadaMax;
    }

    public void setAlçadaMax(int alçadaMax) {
        this.alçadaMax = alçadaMax;
    }

    public int getAlcadaMin() {
        return alcadaMin;
    }

    public void setAlcadaMin(int alcadaMin) {
        this.alcadaMin = alcadaMin;
    }

    public float getDistanciaKm() {
        return distanciaKm;
    }

    public void setDistanciaKm(float distanciaKm) {
        this.distanciaKm = distanciaKm;
    }

    public int getTempsAprox() {
        return tempsAprox;
    }

    public void setTempsAprox(int tempsAprox) {
        this.tempsAprox = tempsAprox;
    }

    public boolean isCircular() {
        return circular;
    }

    public void setCircular(boolean circular) {
        this.circular = circular;
    }

    public int getDificultat() {
        return dificultat;
    }

    public void setDificultat(int dificultat) {
        this.dificultat = dificultat;
    }

    public String getGpxURL() {
        return gpxURL;
    }

    public void setGpxURL(String gpxURL) {
        this.gpxURL = gpxURL;
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
    
    
    
    
}

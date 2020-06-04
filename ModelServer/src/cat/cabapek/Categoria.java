/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.cabapek;

import java.util.Objects;

/**
 *
 * @author guillem
 */
public class Categoria {
    
    private int id;
    
    private String nom;

    public Categoria(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    @Override
    public String toString() {
        return getNom();
    }

    @Override
    public boolean equals(Object arg0) {
        if(arg0.getClass().equals(Categoria.class)){
            return this.getNom().equals(((Categoria)arg0).getNom());
        }else return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.nom);
        return hash;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    
    
}

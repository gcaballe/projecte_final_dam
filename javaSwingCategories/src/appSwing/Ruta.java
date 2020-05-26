/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appSwing;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

/**
 *
 * @author guillem
 */
public class Ruta {
    
    @Id
    @GeneratedValue(generator = "myTableGen")
    @TableGenerator(
        name = "myTableGen", 
        table = "taula_autoincrement", 
        pkColumnName = "nom_taula", 
        valueColumnName = "ruta", 
        pkColumnValue = "valor", 
        initialValue = 0, 
        allocationSize = 1)
    private int id;
    
    @Column(name="nom", nullable=false, length=128)
    private String nom;

    public Ruta(int id, String nom) {
        this.id = id;
        this.nom = nom;
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

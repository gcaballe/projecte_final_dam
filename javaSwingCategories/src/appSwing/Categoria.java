package appSwing;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Query;
import javax.persistence.TableGenerator;

@Entity
public class Categoria {
    
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Categoria){
            return this.getId() == ((Categoria) obj).getId();
        }
        return false;
    }

    @Id
    @GeneratedValue(generator = "myTableGen")
    @TableGenerator(
        name = "myTableGen", 
        table = "taula_autoincrement", 
        pkColumnName = "nom_taula", 
        valueColumnName = "valor", 
        pkColumnValue = "categoria", 
        initialValue = 0, 
        allocationSize = 1)
    private int id;
    
    @Column(name="nom", nullable=false, length=128)
    private String nom;
    
    @ManyToOne(optional=true, fetch=FetchType.LAZY)
    @JoinColumn(name="pare", nullable=true)
    private Categoria pare;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pare")
    private List<Categoria> filles;
    
    @ElementCollection(fetch=FetchType.EAGER)
    @CollectionTable(name = "ruta", joinColumns = @JoinColumn(name = "categoria"))
    @Column(name = "titol")
    private List<String> rutes;
    
    

    
    
    public static List<Categoria> getAllCategories() {
        List<Categoria> arr_cat;
        arr_cat = AppSwing.em.createQuery("SELECT c FROM Categoria c").getResultList(); 
        return arr_cat;
    }
    
    public static Categoria getCategoriaPerNom(String paramNom){
        
        Query q = (Query) AppSwing.em.createQuery("SELECT c FROM Categoria c WHERE c.nom=:nomCat");
        q.setParameter("nomCat", paramNom);
        Categoria c = (Categoria) q.getSingleResult();
        return c;
    }
    
    public void afegirFill(Categoria c) {
        if (c == null) {
            System.out.println("Has intentat afegirFill(null)");
            return;
        }
        if (this.getFilles() == null){
            this.filles = new ArrayList<Categoria>();
            filles.add(c);
            if(c.getPare() != this) c.setPare(this);
        }else if (!this.getFilles().contains(c)) {
            filles.add(c);
            if (c.getPare() != this ) c.setPare(this);
        }
    }
 
    public void eliminarFill(Categoria c) {
        if(getFilles() != null) getFilles().remove(c);
        if(c.getPare() != null && c.getPare() != this) c.setPare(null);
    }    
    
    public Categoria(){};
    
    public Categoria(int id, String nom){
        setId(id);
        setNom(nom);
    }
    
    public Categoria(int id, String nom, Categoria pare, List<String> rutes) {
        setId(id);
        setNom(nom);
        setPare(pare);
        setRutes(rutes);
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

    public Categoria getPare() {
        return pare;
    }

    public void setPare(Categoria p) {
        
        if(this.getPare() != null){
            //si ja tenia pare
            this.getPare().eliminarFill(this);            
            this.pare = p;

            if(p != null && !p.getFilles().contains(this)){ // el contains ja el fa el afegirFill
                p.afegirFill(this);
            }
            
        }else{
            //si no tenia pare
            if(p == null){
                this.pare = p;
            }else{
                this.pare = p; 
                if(p.getFilles() != null && !p.getFilles().contains(this)){ // el contains ja el fa el afegirFill
                    p.afegirFill(this);
                }
            }
        }
    }

    public void setRutes(List<String> rutes) {
        this.rutes = rutes;
    }
    
    public void setFilles(List<Categoria> filles) {
        this.filles = filles;
    }
    
    public List<String> getRutes() {
        return rutes;
    }

    public List<Categoria> getFilles() {
        return filles;
    }


    @Override
    public String toString(){
        return getNom();
    }   
}    
    
    
    


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appSwing;

/**
 *
 * @author guillem
 */
public class TestClass {
    
    public static void main(String[] args) {
        
        
        Categoria pare = new Categoria(1,"Pare", null, null);
        
        
        Categoria fill = new Categoria(2,"Fill", null, null);
        
        
        pare.afegirFill(fill);
        
    }
    
    
}

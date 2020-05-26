/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appSwing;

import java.util.List;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;

public class JavaSwingCategories {

   
    private static JTree tree;
    public static DefaultTreeModel model;
    
    public static void setModelActual() {
        
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Categories");
        
        List <Categoria> ll_cat = Categoria.getAllCategories();
        
        for(Categoria c: ll_cat){
            
            if(c.getPare() == null){
                //si no te pare    
                DefaultMutableTreeNode categoria = new DefaultMutableTreeNode(c); //.getNom()
                root.add(categoria);
                List<Categoria> filles = c.getFilles();
                crearFills(categoria,c);
            }
        }
        
        TreeNode rootNode = root;
        TreeModel treeModel = new DefaultTreeModel(rootNode);
        
        model = (DefaultTreeModel)treeModel;
    }
    
    private static void crearFills(DefaultMutableTreeNode node_pare, Categoria c){
        for(Categoria f : c.getFilles()){
            DefaultMutableTreeNode node_fill = new DefaultMutableTreeNode(f); //aixo agafa f.toString()
            if(!f.getFilles().isEmpty()){ //si es not empty
                crearFills(node_fill,f);
            }
            node_pare.add(node_fill);
        }
    }
    
  
}

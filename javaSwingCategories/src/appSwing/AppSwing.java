/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appSwing;

import java.awt.GridLayout;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.text.Position;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;
import java.util.Properties;

public class AppSwing extends javax.swing.JFrame {
    
    Categoria categoriaSelected;
    DefaultMutableTreeNode node;
    
    public static EntityManagerFactory emf = null;
    public static EntityManager em = null;
    
    public static void main(String args[]) {

        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream("resources/config.properties"));
        } catch (FileNotFoundException ex) {
            System.out.println("No s'ha trobat 'resourcers/config.properties'.");
            return;
        } catch (IOException ex) {
            System.out.println("IOException al agafar arxiu de propietats.");
            return;
        }
        
        String up = prop.getProperty("persistence-unit");
        
        HashMap<String,String> propietats = new HashMap();
        
        propietats.put("javax.persistence.jdbc.url", prop.getProperty("javax.persistence.jdbc.url"));
        propietats.put("javax.persistence.jdbc.user", prop.getProperty("javax.persistence.jdbc.user"));
        propietats.put("javax.persistence.jdbc.password", prop.getProperty("javax.persistence.jdbc.password"));
        propietats.put("javax.persistence.jdbc.driver", prop.getProperty("javax.persistence.jdbc.driver"));
        propietats.put("hibernate.hbm2ddl.auto", prop.getProperty("hibernate.hbm2ddl.auto"));
        propietats.put("hibernate.dialect", prop.getProperty("hibernate.dialect"));
        propietats.put("hibernate.show_sql", prop.getProperty("hibernate.show_sql"));
        
        emf = Persistence.createEntityManagerFactory(up,propietats);
        em = emf.createEntityManager();
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AppSwing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AppSwing().setVisible(true);
            }
        });
        
    }
    
    
    
    public AppSwing() {
        
        JavaSwingCategories.setModelActual();
        initComponents();
        
         jTree1.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {

                node = (DefaultMutableTreeNode) jTree1.getLastSelectedPathComponent();
                if (node == null || node.isRoot()) return;
                
                Categoria nodeInfo = (Categoria)node.getUserObject();
                
                categoriaSelected = nodeInfo;
                
                //omplir llista de rutes
                list1.clear();
                if(categoriaSelected != null && categoriaSelected.getRutes() != null){
                    for(String s : categoriaSelected.getRutes()){
                        System.out.println(s);
                        list1.add(s);
                    }
                }
            }
        });
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        addButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        modifyButton = new javax.swing.JButton();
        list1 = new java.awt.List();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTree1.setModel(JavaSwingCategories.model);
        jScrollPane1.setViewportView(jTree1);

        addButton.setText("Afegir una categoria");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        deleteButton.setText("Esborrar una categoria");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        modifyButton.setText("Modificar una categoria");
        modifyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(111, 111, 111)
                        .addComponent(list1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(modifyButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(94, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(list1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addButton)
                    .addComponent(modifyButton)
                    .addComponent(deleteButton))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        list1.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        
        if(node == null){
            JOptionPane.showMessageDialog(rootPane, "Has de seleccionar una categoria del Tree.");
            return;
        }
        if(node.isRoot()){
            JOptionPane.showMessageDialog(rootPane, "No pots esborrar el node root.");
            return;
        }

        if(node.getChildCount() != 0){
            
            JOptionPane.showMessageDialog(rootPane, "No es pot borrar la categoria " + categoriaSelected.getNom() + " per que té nodes fills.\n"
                    + categoriaSelected.getFilles().toString());
            
        }else if (categoriaSelected.getRutes() != null && categoriaSelected.getRutes().size() > 0){
            //si tenen rutes asociades
            JOptionPane.showMessageDialog(rootPane, "No es pot borrar la categoria " + categoriaSelected.getNom() + " per que té rutes associades.\n"
                    + categoriaSelected.getRutes().toString());
        }else{
            int reply = JOptionPane.showConfirmDialog(
            this, "Segur que vols borrar " + categoriaSelected.getNom()+"?","Delete",JOptionPane.YES_NO_OPTION);
            
            if(reply == JOptionPane.YES_OPTION){
                TreePath path;
                Categoria c = em.find(Categoria.class, categoriaSelected.getId());
                
                if(c.getPare() != null) c.getPare().eliminarFill(c);
                
                
                categoriaSelected = null;
                em.getTransaction().begin();
                em.remove(c);
                em.getTransaction().commit();
                
                path = jTree1.getNextMatch(c.getNom(), 0, Position.Bias.Forward);
                MutableTreeNode mNode = (MutableTreeNode)path.getLastPathComponent();
                JavaSwingCategories.model.removeNodeFromParent(mNode);
                JavaSwingCategories.model.reload();
            }else{
                return;
            }
        }
        
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void modifyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifyButtonActionPerformed

        if(node == null){
            JOptionPane.showMessageDialog(rootPane, "Has de seleccionar una categoria del Tree.");
            return;
        }
        if(node.isRoot()){
            JOptionPane.showMessageDialog(rootPane, "No pots modificar el node root.");
            return;
        }
        String name = JOptionPane.showInputDialog(this,"Quin nom vols posar a '"+categoriaSelected.getNom()+"?", null);
        
        if(name == null){
            return;
        }
        
        if(!name.equals("")){

            try{
                em.getTransaction().begin();
                categoriaSelected.setNom(name);
                em.getTransaction().commit();
            
            }catch(PersistenceException ex){
                JOptionPane.showMessageDialog(rootPane, "Error al modificar nom de categoria.  ");
                return;
            }
            
            JavaSwingCategories.model.reload();
            
        }else{
            JOptionPane.showMessageDialog(rootPane, "El nou nom no pot ser buit.");
            return;
        }
        
    }//GEN-LAST:event_modifyButtonActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        
        Categoria cat_pare;
        
        if(node == null || node.isRoot()){
            cat_pare = null;
        }else{
            cat_pare = categoriaSelected;
            System.out.println("cat_pare="+cat_pare);
        }
         
        JPanel fields = new JPanel(new GridLayout(2, 1));
        JLabel lblNom = new JLabel("Nom: ");
        fields.add(lblNom);
        JTextField field = new JTextField(10);
        fields.add(field);
      
        ArrayList<Categoria> categories = (ArrayList<Categoria>)Categoria.getAllCategories();
        String[] array = new String[categories.size()+1];
        
        array[0] = "";
        for(int i = 1; i <= categories.size(); i++){
            array[i] = categories.get(i-1).getNom();
        }
        
        JLabel lblPare = new JLabel("Pare: ");
        fields.add(lblPare);
        JComboBox<String> comboBox = new JComboBox<String>(array);
        
        if(categoriaSelected == null) comboBox.setSelectedIndex(0);
        else comboBox.setSelectedIndex(categories.indexOf(categoriaSelected) + 1); //+1 pel espai en blanc
        
        fields.add(comboBox);
        
        int result = JOptionPane.showConfirmDialog(null, fields, "Categoria ", JOptionPane.OK_CANCEL_OPTION); //, JOptionPane.QUESTION_MESSAGE
        String fieldNom = field.getText();
        
        if(result == JOptionPane.OK_OPTION){
            if(fieldNom == null || fieldNom.equals("") ){
                JOptionPane.showMessageDialog(this, "No has entrat nom.");
            }else{
                if(comboBox.getSelectedIndex() == 0) cat_pare = null;
                else cat_pare = Categoria.getCategoriaPerNom((String)comboBox.getSelectedItem());

                Categoria nova_cat;
                try{
                    em.getTransaction().begin();
                    nova_cat = new Categoria();
                    nova_cat.setNom(fieldNom);
                    nova_cat.setPare(cat_pare);
                    em.persist(nova_cat);
                    em.getTransaction().commit();
                   
                }catch(PersistenceException ex){
                    JOptionPane.showMessageDialog(rootPane, "Error al inserir categoria.  "+ex.getMessage());
                    return;
                }
                
                    TreePath path;
                    DefaultMutableTreeNode mNew = new DefaultMutableTreeNode(nova_cat);
                    
                    if(cat_pare != null) path = jTree1.getNextMatch(cat_pare.getNom(), 0, Position.Bias.Forward);    
                    else path = jTree1.getNextMatch("Categories", 0, Position.Bias.Forward); 
                    DefaultMutableTreeNode mNode = (DefaultMutableTreeNode)path.getLastPathComponent();
                    mNode.add(mNew);
                   
                    JavaSwingCategories.model.reload();
               
            }
        
        }
    }//GEN-LAST:event_addButtonActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTree jTree1;
    private java.awt.List list1;
    private javax.swing.JButton modifyButton;
    // End of variables declaration//GEN-END:variables
}

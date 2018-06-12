/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetAntoine.ihm;

import projetAntoine.entites.CEntitees;
import projetAntoine.persistance.CPersistance;

/**
 *
 * @author Antoine Liégard, liegard.antoine35@gmail.com
 */
public class JMainFrame extends javax.swing.JFrame {

    CEntitees entitee;
    CPersistance persistance;
    int posX = 150;
    int posY = 15;

    /**
     * Creates new form NewMDIApplication
     *
     * @param entitee
     * @param persistance
     */
    public JMainFrame(CEntitees entitee, CPersistance persistance) {
        this.entitee = entitee;
        this.persistance = persistance;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPane = new javax.swing.JDesktopPane();
        CreateEtuButton = new javax.swing.JButton();
        EditEtuButton = new javax.swing.JButton();
        EditEtuButton1 = new javax.swing.JButton();
        CreateEtuButton1 = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        saveAsMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        helpMenu = new javax.swing.JMenu();
        contentMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        desktopPane.setPreferredSize(new java.awt.Dimension(800, 600));

        CreateEtuButton.setText("Créer étudiant");
        CreateEtuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreateEtuButtonActionPerformed(evt);
            }
        });
        desktopPane.add(CreateEtuButton);
        CreateEtuButton.setBounds(10, 20, 120, 25);

        EditEtuButton.setText("Modifier étudiant");
        EditEtuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditEtuButtonActionPerformed(evt);
            }
        });
        desktopPane.add(EditEtuButton);
        EditEtuButton.setBounds(10, 50, 120, 25);

        EditEtuButton1.setText("Modifier étudiant");
        EditEtuButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditEtuButton1ActionPerformed(evt);
            }
        });
        desktopPane.add(EditEtuButton1);
        EditEtuButton1.setBounds(10, 50, 120, 25);

        CreateEtuButton1.setText("Créer formation");
        CreateEtuButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreateEtuButton1ActionPerformed(evt);
            }
        });
        desktopPane.add(CreateEtuButton1);
        CreateEtuButton1.setBounds(10, 100, 120, 25);

        fileMenu.setMnemonic('f');
        fileMenu.setText("File");

        openMenuItem.setMnemonic('o');
        openMenuItem.setText("Open");
        fileMenu.add(openMenuItem);

        saveMenuItem.setMnemonic('s');
        saveMenuItem.setText("Save");
        fileMenu.add(saveMenuItem);

        saveAsMenuItem.setMnemonic('a');
        saveAsMenuItem.setText("Save As ...");
        saveAsMenuItem.setDisplayedMnemonicIndex(5);
        fileMenu.add(saveAsMenuItem);

        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        editMenu.setMnemonic('e');
        editMenu.setText("Edit");
        menuBar.add(editMenu);

        helpMenu.setMnemonic('h');
        helpMenu.setText("Help");

        contentMenuItem.setMnemonic('c');
        contentMenuItem.setText("Contents");
        helpMenu.add(contentMenuItem);

        aboutMenuItem.setMnemonic('a');
        aboutMenuItem.setText("About");
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(desktopPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(desktopPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void CreateEtuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreateEtuButtonActionPerformed

        JPanelInsertEtudiant panelInsert = new JPanelInsertEtudiant(entitee, persistance);
        panelInsert.setBounds(posX, posY, panelInsert.getPreferredSize().height, panelInsert.getPreferredSize().width);
        panelInsert.setVisible(true);
        desktopPane.add(panelInsert);

    }//GEN-LAST:event_CreateEtuButtonActionPerformed

    private void EditEtuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditEtuButtonActionPerformed

        JPanelEditEtudiant panel = new JPanelEditEtudiant(entitee, persistance);
        panel.setBounds(posX, posY, panel.getPreferredSize().height, panel.getPreferredSize().width);
        panel.setVisible(true);
        desktopPane.add(panel);

    }//GEN-LAST:event_EditEtuButtonActionPerformed

    private void EditEtuButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditEtuButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EditEtuButton1ActionPerformed

    private void CreateEtuButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreateEtuButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CreateEtuButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new JMainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CreateEtuButton;
    private javax.swing.JButton CreateEtuButton1;
    private javax.swing.JButton EditEtuButton;
    private javax.swing.JButton EditEtuButton1;
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenuItem contentMenuItem;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JMenuItem saveAsMenuItem;
    private javax.swing.JMenuItem saveMenuItem;
    // End of variables declaration//GEN-END:variables

}

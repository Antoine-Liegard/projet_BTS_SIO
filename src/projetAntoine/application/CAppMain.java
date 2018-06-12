/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetAntoine.application;

import projetAntoine.entites.CEntitees;
import projetAntoine.ihm.JMainFrame;
import projetAntoine.persistance.CPersistance;

/**
 *
 * @author Antoine Liégard, liegard.antoine35@gmail.com
 */
public class CAppMain {
    JMainFrame mainFrame;
    CEntitees entitee;
    CPersistance persistance;
    

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        
        CAppMain app = new CAppMain();
        app.entitee= new CEntitees();
        app.persistance= new CPersistance();
        
        app.entitee.initEntitée(app.persistance);

        
        
        app.mainFrame = new JMainFrame(app.entitee, app.persistance);
        app.mainFrame.setVisible(true);
        
    }
    
}

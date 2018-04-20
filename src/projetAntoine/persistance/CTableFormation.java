/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetAntoine.persistance;

/**
 *
 * @author Antoine Liégard, liegard.antoine35@gmail.com
 */

import projetAntoine.entites.CFormation;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;


public class CTableFormation {
    protected CBDD bdd;
    
    
    public void setBdd(CBDD bdd) {
        this.bdd = bdd;
    }
    
    
    //nom de méthode explicite, ne fonctionne pas à cause des contraintes
    int supprimerTable() {
        String req = "DROP TABLE tableformation";
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion KO");
        }
        return res;
    }
    
    CFormation convertir_result_Formation(ResultSet result) {

        try {
            int idFormation = result.getInt("idEtudiant");
            String libelleDiplome = result.getString("libelleDiplome");
            String optionDiplome = result.getString("optionDiplome");
            

            return new CFormation(idFormation, libelleDiplome,optionDiplome);
        } catch (SQLException ex) {
            Logger.getLogger(CTableEtudiants.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}

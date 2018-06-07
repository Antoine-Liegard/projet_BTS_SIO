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
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import projetAntoine.entites.CListeFormations;

public class CTableFormation {

    protected CBDD bdd;

    // constructeur avec bdd
    public CTableFormation(CBDD bdd) {
        this.bdd = bdd;
    }

    // constructeur vide
    public CTableFormation() {

    }

    public void setBdd(CBDD bdd) {
        this.bdd = bdd;
    }

    //nom de méthode explicite, ne fonctionne pas à cause des contraintes (foreign key)
    void supprimerTable() {
        String req = "DROP TABLE tableformation";
        if (bdd.connecter() == true) {
            bdd.executerRequeteUpdate(req);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion KO");
        }
    }

    //retourne une formation par rapport à un résultat de requête
    CFormation convertirResultFormation(ResultSet result) {

        try {
            int idFormation = result.getInt("idFormation");
            String libelleDiplome = result.getString("libelleDiplome");
            String optionDiplome = result.getString("optionDiplome");

            return new CFormation(idFormation, libelleDiplome, optionDiplome);
        } catch (SQLException ex) {
            Logger.getLogger(CTableEtudiants.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    // recherche et retourne une classe CListeFormations par rapport à toutes les entrées de la base
    public CListeFormations lireFormations() {

        if (bdd.connecter() == true) {
            CListeFormations listeFormation = new CListeFormations();
            ResultSet result = bdd.executerRequeteQuery("select * from tableformations");
            try {
                while (result.next()) {
                    CFormation formation = convertirResultFormation(result);
                    listeFormation.ajouterFormation(formation);
                }
            } catch (SQLException ex) {
                Logger.getLogger(CBDD.class.getName()).log(Level.SEVERE, null, ex);
            }
            bdd.deconnecter();
            return listeFormation;
        } else {
            System.out.println("Connexion KO");
        }
        return null;
    }

    // recherche et retourne une classe formation dans la base de donné par rapport à son ID
    public CFormation lireFormation(int idForm) {
        CFormation formation = null;
        if (bdd.connecter() == true) {
            System.out.println("Connexion OK");
            ResultSet result = bdd.executerRequeteQuery("select * from tableformations  WHERE `tableformations`.`idFormation` = " + idForm);
            try {
                if (result.next()) {
                    formation = convertirResultFormation(result);
                }
            } catch (SQLException ex) {
                Logger.getLogger(CBDD.class.getName()).log(Level.SEVERE, null, ex);
            }
            bdd.deconnecter();
        } else {
            System.out.println("Connexion KO");
        }
        return formation;
    }

    // insere une formation dans la base de donnée a partir d'un classe, retourne 1 si éxecuté
    public int insererFormation(CFormation formation) {
        int res = 0;
        if (bdd.connecter() == true) {
            String req = "INSERT INTO `tableformations` (`libelleDiplome`, `optionDiplome`) VALUES (?,?);";

            try {

                PreparedStatement pstmt = bdd.conn.prepareStatement(req);

                pstmt.setString(1, formation.getLibelleDiplome());
                pstmt.setString(2, formation.getOptionDiplome());

                pstmt.execute();
                res = 1;
            } catch (SQLException ex) {
                Logger.getLogger(CBDD.class.getName()).log(Level.SEVERE, null, ex);
                res = 0;
            }

            bdd.deconnecter();
        } else {
            System.out.println("Connexion KO");
        }
        return res;
    }

    // TODO tester
    public int updateFormation(CFormation formation) {
        int res = 0;
        if (bdd.connecter() == true) {

            String req = "UPDATE tableformations"
                    + " SET `libelleDiplome` = ? , `optionDiplome` = ? "
                    + "WHERE `tableformations`.`idFormation` = " + formation.getIdFormation() + ";";
            try {

                PreparedStatement pstmt = bdd.conn.prepareStatement(req);

                pstmt.setString(1, formation.getLibelleDiplome());
                if (formation.getOptionDiplome().equals("") || formation.getOptionDiplome().equals("null") || formation.getOptionDiplome().equals("NULL")) {
                    pstmt.setString(2, null);
                } else {
                    pstmt.setString(2, formation.getOptionDiplome());
                }

                System.out.println(pstmt);
                pstmt.execute();
                res = 1;
            } catch (SQLException ex) {
                Logger.getLogger(CBDD.class.getName()).log(Level.SEVERE, null, ex);
                res = 0;
            }

            bdd.deconnecter();
        } else {
            System.out.println("Connexion KO");
        }
        return res;
    }

    // methode test
    public static void main(String[] args) {
        CBDD bdd = new CBDD(new CParametresBDD("parametresBdd.properties"));
        CTableFormation test = new CTableFormation();
        test.setBdd(bdd);

//        //test insertion
//        CFormation formation = new CFormation("BTS SNIR", "");
//        test.insererFormation(formation);
        CListeFormations listeFormation = test.lireFormations();
//        //test modification

//        listeFormation.selectionnerFormation(2).setOptionDiplome("");
//        CFormation formation = listeFormation.selectionnerFormation(2);
//        test.updateFormation(formation);
        listeFormation.toString();

    }
}

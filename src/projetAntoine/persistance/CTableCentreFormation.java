/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetAntoine.persistance;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import projetAntoine.entites.CListeCentreFormation;
import projetAntoine.entites.CCentreFormation;

/**
 *
 * @author Antoine Liégard, liegard.antoine35@gmail.com
 */
public class CTableCentreFormation {

    public CTableCentreFormation(CBDD bdd) {
        this.bdd = bdd;
    }
    
    public CTableCentreFormation() {
    }

    protected CBDD bdd;

    public void setBdd(CBDD bdd) {
        this.bdd = bdd;
    }

    //nom de méthode explicite, ne fonctionne pas à cause des contraintes (foreign key)
    int supprimerTable() {
        String req = "DROP TABLE tablecentresformation";
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion KO");
        }
        return res;
    }

    //retourne une formation par rapport à un résultat de requête
    CCentreFormation convertir_result_CentreFormation(ResultSet result) {

        try {
            int idCentreFormation = result.getInt("idCentreFormation");
            String raisonSociale = result.getString("raisonSociale");
            String commune = result.getString("commune");
            String codePostal = result.getString("codePostal");
            String numeroVoie = result.getString("numeroVoie");
            String typeVoie = result.getString("typeVoie");
            String nomVoie = result.getString("nomVoie");
            String mail = result.getString("mail1");
            String mail2 = result.getString("mail2");
            String telphone = result.getString("telephone1");
            String telephone2 = result.getString("telephone2");

            return new CCentreFormation(idCentreFormation, raisonSociale, commune, codePostal, numeroVoie, typeVoie, nomVoie, mail, mail2, telphone, telephone2);

        } catch (SQLException ex) {
            Logger.getLogger(CTableEtudiants.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    // recherche et retourne une classe CListeFormations par rapport à toutes les entrées de la base
    CListeCentreFormation lireCentresFormations() {

        if (bdd.connecter() == true) {
            CListeCentreFormation listeCentreFormation = new CListeCentreFormation();
            ResultSet result = bdd.executerRequeteQuery("select * from tablecentresformation");
            try {
                while (result.next()) {
                    CCentreFormation centreFormation = convertir_result_CentreFormation(result);
                    listeCentreFormation.ajouterFormation(centreFormation);
                }
            } catch (SQLException ex) {
                Logger.getLogger(CBDD.class.getName()).log(Level.SEVERE, null, ex);
            }
            bdd.deconnecter();
            return listeCentreFormation;
        } else {
            System.out.println("Connexion KO");
        }
        return null;
    }

    // recherche et retourne une classe CCentreFormation dans la base de donné par rapport à son ID
    CCentreFormation lireCentreFormation(int idCentreForm) {
        CCentreFormation centreFormation = null;
        if (bdd.connecter() == true) {
            System.out.println("Connexion OK");
            ResultSet result = bdd.executerRequeteQuery("select * from tablecentresformation WHERE `tablecentresformation`.`idCentreFormation` = " + idCentreForm);
            try {
                if (result.next()) {
                    centreFormation = convertir_result_CentreFormation(result);
                }
            } catch (SQLException ex) {
                Logger.getLogger(CBDD.class.getName()).log(Level.SEVERE, null, ex);
            }
            bdd.deconnecter();
        } else {
            System.out.println("Connexion KO");
        }
        return centreFormation;
    }

    // insere une formation dans la base de donnée a partir d'un classe, retourne 1 si éxecuté
    int insererCentreFormation(CCentreFormation centreFormation) {
        int res = 0;
        if (bdd.connecter() == true) {
            String req = "INSERT INTO `tablecentresformation` (`raisonSociale`, `commune`, "
                    + "`codePostal`, `numeroVoie`, `typeVoie`, `nomVoie`, `telephone1`, "
                    + "`telephone2`, `mail1`, `mail2`) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

            try {

                PreparedStatement pstmt = bdd.conn.prepareStatement(req);

                pstmt.setString(1, centreFormation.getRaisonSociale());
                pstmt.setString(2, centreFormation.getCommune());
                pstmt.setString(3, centreFormation.getCodePostal());
                pstmt.setString(4, centreFormation.getNumeroVoie());
                pstmt.setString(5, centreFormation.getTypeVoie());
                pstmt.setString(6, centreFormation.getNomVoie());
                pstmt.setString(7, centreFormation.getTelphone());
                pstmt.setString(8, centreFormation.getTelephone2());
                pstmt.setString(9, centreFormation.getMail());
                pstmt.setString(10, centreFormation.getMail2());

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
    int updateCentreFormation(CCentreFormation centreFormation) {
        int res = 0;
        if (bdd.connecter() == true) {

            String req = "UPDATE tablecentresformation"
                    + " SET `raisonSociale`=?, `commune`=?, "
                    + "`codePostal`=?, `numeroVoie`=?, `typeVoie`=?, `nomVoie`=?, `telephone1`=?, "
                    + "`telephone2`=?, `mail1`=?, `mail2`=?"
                    + "WHERE `tablecentresformation`.`idCentreFormation` = " + centreFormation.getIdCentreFormation() + ";";
            try {

                PreparedStatement pstmt = bdd.conn.prepareStatement(req);

                pstmt.setString(1, centreFormation.getRaisonSociale());
                pstmt.setString(2, centreFormation.getCommune());
                pstmt.setString(3, centreFormation.getCodePostal());
                if ("".equals(centreFormation.getNumeroVoie())) {
                    pstmt.setNull(4, java.sql.Types.VARCHAR);
                } else {
                    pstmt.setString(4, centreFormation.getNumeroVoie());
                }
                pstmt.setString(5, centreFormation.getTypeVoie());
                pstmt.setString(6, centreFormation.getNomVoie());
                pstmt.setString(7, centreFormation.getTelphone());
                if ("".equals(centreFormation.getTelephone2())) {
                    pstmt.setNull(8, java.sql.Types.VARCHAR);
                } else {
                    pstmt.setString(8, centreFormation.getTelephone2());
                }
                pstmt.setString(9, centreFormation.getMail());
                if ("".equals(centreFormation.getMail2())) {
                    pstmt.setNull(10, java.sql.Types.VARCHAR);
                } else {
                    pstmt.setString(10, centreFormation.getMail2());
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
        CTableCentreFormation test = new CTableCentreFormation();
        test.setBdd(bdd);

//        //test insertion
//        CCentreFormation centreFormation = new CCentreFormation("raisonSociale", "commune", 
//                "codePostal", "", "typeVoie", "nomVoie", "mail", "mail2", "telphone", "telephone2");
//        test.insererCentreFormation(centreFormation);


        CListeCentreFormation listeCentreFormation = test.lireCentresFormations();
        listeCentreFormation.toString();
//        //test modification
//        CCentreFormation centreFormation = listeCentreFormation.selectionnerCentreFormation(2);
//        centreFormation.setRaisonSociale("Greta Saint Malo");
//        test.updateCentreFormation(centreFormation);
//        listeCentreFormation.toString();


//§
    

    }
}

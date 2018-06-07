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
import projetAntoine.entites.CCentreFormation;
import projetAntoine.entites.CFormation;
import projetAntoine.entites.CListeCentreFormation;
import projetAntoine.entites.CListeFormations;
import projetAntoine.entites.CSessionFormation;
import projetAntoine.entites.CListeSessionFormation;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;

public class CTableSessionFormation {

    protected CBDD bdd;
    protected CListeFormations listeFormation;
    protected CListeCentreFormation listeCentreFormation;

    // constructeur vide
    public CTableSessionFormation() {
    }

    //constructeur
    public CTableSessionFormation(CBDD bdd) {
        this.bdd = bdd;

        CTableFormation tableFormation = new CTableFormation(bdd);
        this.listeFormation = tableFormation.lireFormations();

        CTableCentreFormation tableCentreFormation = new CTableCentreFormation(bdd);
        this.listeCentreFormation = tableCentreFormation.lireCentresFormations();
    }

    public void setBdd(CBDD bdd) {
        this.bdd = bdd;
    }

    //nom de méthode explicite ne fonctionne pas à cause des contraintes
    int supprimerTable() {
        String req = "DROP TABLE tablesessionsformations";
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion KO");
        }
        return res;
    }

    CSessionFormation convertirResultSessionFormation(ResultSet result) {

        try {
            int idSessionFormation = result.getInt("idSessionFormation");
            int idFormation = result.getInt("idFormation");
            int idCentreFormation = result.getInt("idFormation");
            Date debutSession = result.getDate("debutSession");
            Date finSession = result.getDate("finSession");
            GregorianCalendar gDebutSession = new GregorianCalendar();
            gDebutSession.setTime(debutSession);
            GregorianCalendar gFinSession = new GregorianCalendar();
            gFinSession.setTime(finSession);
            CFormation formation = listeFormation.selectionnerFormation(idFormation);
            CCentreFormation centreFormation = listeCentreFormation.selectionnerCentreFormation(idCentreFormation);

            return new CSessionFormation(idSessionFormation, formation, centreFormation, gDebutSession, gFinSession);
        } catch (SQLException ex) {
            Logger.getLogger(CTableEtudiants.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public CListeSessionFormation lireSessionsFormations() {

        if (bdd.connecter() == true) {
            CListeSessionFormation listeSession = new CListeSessionFormation();
            ResultSet result = bdd.executerRequeteQuery("select * from tablesessionsformations");
            try {
                while (result.next()) {
                    CSessionFormation session = convertirResultSessionFormation(result);
                    listeSession.ajouterSession(session);
                }
            } catch (SQLException ex) {
                Logger.getLogger(CBDD.class.getName()).log(Level.SEVERE, null, ex);
            }
            bdd.deconnecter();
            return listeSession;
        } else {
            System.out.println("Connexion KO");
        }
        return null;
    }

    public CSessionFormation lireSessionFormation(int idSession) {
        CSessionFormation session = null;
        if (bdd.connecter() == true) {
            System.out.println("Connexion OK");
            ResultSet result = bdd.executerRequeteQuery("select * from tablesessionsformations  WHERE `tablesessionsformations`.`idSessionFormation` = " + idSession);
            try {
                if (result.next()) {
                    session = convertirResultSessionFormation(result);
                }
            } catch (SQLException ex) {
                Logger.getLogger(CBDD.class.getName()).log(Level.SEVERE, null, ex);
            }
            bdd.deconnecter();
        } else {
            System.out.println("Connexion KO");
        }
        return session;
    }

    // inserer une nouvelle session dans la base, retourne 1 si éxecuté
    int inserrerSession(CSessionFormation session) {
        int res = 0;
        if (bdd.connecter() == true) {

            String req = "INSERT INTO `tablesessionsformations` "
                    + "( `idFormation`, `idCentreFormation`, `debutSession`,`finSession`)"
                    + "VALUES (?,?,?,?);";

            try {

                PreparedStatement pstmt = bdd.conn.prepareStatement(req);
                pstmt.setInt(1, session.getFormation().getIdFormation());
                pstmt.setInt(2, session.getCentreFormation().getIdCentreFormation());
                String debutSession = bdd.formaterDate(session.getDebutSession());
                pstmt.setString(3, debutSession);
                String finSession = bdd.formaterDate(session.getFinSession());
                pstmt.setString(4, finSession);

                System.out.println(pstmt);
                pstmt.execute();
            } catch (SQLException ex) {
                Logger.getLogger(CBDD.class.getName()).log(Level.SEVERE, null, ex);
                res = 1;
            }

            bdd.deconnecter();
        } else {
            System.out.println("Connexion KO");
        }
        return res;
    }

    // met a jour une session dans la base avec un prepared statement, retourne 1 si éxecuté
    public int mettreAJourSession(CSessionFormation session) {
        int res = 0;
        if (bdd.connecter() == true) {

            String req = "UPDATE tablesessionsformations "
                    + "SET `idFormation` = ? , `idCentreFormation` = ? , "
                    + "`debutSession` = ? , `finSession` = ?  WHERE"
                    + " `tablesessionsformations`.`idSessionFormation` =" + session.getIdSessionFormation() + ";";
            try {
                PreparedStatement pstmt = bdd.conn.prepareStatement(req);
                pstmt.setInt(1, session.getFormation().getIdFormation());
                pstmt.setInt(2, session.getCentreFormation().getIdCentreFormation());

                String debutSession = bdd.formaterDate(session.getDebutSession());
                pstmt.setString(3, debutSession);

                String finSession = bdd.formaterDate(session.getFinSession());
                pstmt.setString(4, finSession);

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

    // supprime une session par rapport à son ID dans la classe ( besoin de voir comment faire avec clef étrangère
    public int supprimerSession(CSessionFormation session) {
        int res = -1;
        if (bdd.connecter() == true) {

            String req = "DELETE FROM tablesessionsformations WHERE `tablesessionsformations`.`id` = " + session.getIdSessionFormation();
            res = bdd.executerRequeteUpdate(req);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion KO");
        }
        return res;
    }

    // supprime session par rapport à son ID dans la base de donnée
    public int supprimerSession(int id) {
        int res = -1;
        if (bdd.connecter() == true) {
            String req = "DELETE FROM tablesessionsformations WHERE `tablesessionsformations`.`id` = " + id;
            res = bdd.executerRequeteUpdate(req);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion KO");
        }
        return res;
    }

    // methode test
    public static void main(String[] args) {
        CBDD bdd = new CBDD(new CParametresBDD("parametresBdd.properties"));
        CTableSessionFormation tableSession = new CTableSessionFormation();
        CTableCentreFormation tableCentre = new CTableCentreFormation();
        CTableFormation tableFormation = new CTableFormation();

        // Création de l'association simple entre tables et CBDD
        tableSession.setBdd(bdd);
        tableFormation.setBdd(bdd);
        tableCentre.setBdd(bdd);

        tableSession.listeCentreFormation = tableCentre.lireCentresFormations();
        tableSession.listeFormation = tableFormation.lireFormations();

//        // test insertion session
//        CSessionFormation session = new CSessionFormation(0, tableSession.listeFormation.selectionnerFormation(1), tableSession.listeCentreFormation.selectionnerCentreFormation(1), new GregorianCalendar(), new GregorianCalendar());
//        tableSession.inserrerSession(session);
        CListeSessionFormation listeSession = tableSession.lireSessionsFormations();

        GregorianCalendar finSession = new GregorianCalendar(2018, 12, 24);
        System.out.println(bdd.formaterDate(finSession));

        //test modification session
        listeSession.selectionnerSession(1).setFinSession(finSession);

        tableSession.mettreAJourSession(listeSession.selectionnerSession(1));
        listeSession = tableSession.lireSessionsFormations();

        listeSession.toString();
    }
}

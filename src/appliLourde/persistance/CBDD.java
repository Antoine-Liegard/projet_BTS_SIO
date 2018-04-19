package appliLourde.persistance;

/**
 *
 * @author Antoine Liégard, liegard.antoine35@gmail.com
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CBDD {
    

    protected CParametresBDD parametresStockageBDD = null;
    protected Connection conn = null;
    protected Statement stmt = null;

    public CBDD(CParametresBDD parametresStockageBDD) {
        this.parametresStockageBDD = parametresStockageBDD;
        try {
            Class.forName(parametresStockageBDD.getDriver()); // Chargement du driver
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    boolean connecter() {
        try {
            conn = DriverManager.getConnection(
                    parametresStockageBDD.getProtocole() + "//"
                    + parametresStockageBDD.getIp() + "/"
                    + parametresStockageBDD.getNomBase()
                    +
//                    pour désactiver SSL et les messages d'erreurs
                    "?useSSL=false"
                    ,
                    parametresStockageBDD.getUtilisateur(),
                    parametresStockageBDD.getMotDePasse()
            );
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CBDD.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    boolean deconnecter() {
        try {
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CBDD.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    int executerRequeteUpdate(String requete) {
        try {
            stmt = conn.createStatement();
            return stmt.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(CBDD.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }

    }
    
    ResultSet executerRequeteQuery(String requete) {
        try {
            stmt = conn.createStatement();
            return stmt.executeQuery(requete);
        } catch (SQLException ex) {
            Logger.getLogger(CBDD.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
         
    }
    // permet de formater une date pour avoir le format requis par SQL
    public String formaterDate(GregorianCalendar gc) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        fmt.setCalendar(gc);
        return fmt.format(gc.getTime());
    }
    
//  Test
//    public static void main(String[] args) {
//        ArrayList<CEtudiant> listeEtudiants = new ArrayList();
//        
//        
//        CBDD bdd = new CBDD(new CParametresBDD("parametresBdd.properties"));
//        if (bdd.connecter() == true) {
//            System.out.println("Connexion OK");
//            ResultSet rs = bdd.executerRequeteQuery("select * from tableEtudiants");
//            try {
//
//                while (rs.next()) {
//                    int id = rs.getInt("id");
//                    String nom = rs.getString("nom");
//                    String prenom = rs.getString("prenom");
//                    Date date = rs.getDate("dateNaissance");
//                    GregorianCalendar gc = new GregorianCalendar();
//                    gc.setTime(date);
//                    CEtudiant etudiant = new CEtudiant(id, nom, prenom, gc);
//                    listeEtudiants.add(etudiant);
//                }
//            } catch (SQLException ex) {
//                Logger.getLogger(CBDD.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            bdd.deconnecter();
//        } else {
//            System.out.println("Connexion KO");
//        }
//        for (CEtudiant etudiant : listeEtudiants) {
//            System.out.println("" + etudiant.toString());
//        }
//    }

}

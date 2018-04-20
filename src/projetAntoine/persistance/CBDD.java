package projetAntoine.persistance;

/**
 *
 * @author Antoine Liégard, liegard.antoine35@gmail.com
 */
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
                    + //                    pour désactiver SSL et les messages d'erreurs
                    "?useSSL=false",
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

    //nom de méthode explicite
    // a coder
    int creerBDD() {
        String req = "requete a créer";
        int res = -1;
        if (this.connecter() == true) {
            res = this.executerRequeteUpdate(req);
            this.deconnecter();
        } else {
            System.out.println("Connexion KO");
        }
        return res;
    }

    // permet de formater une date pour avoir le format requis par SQL
    public String formaterDate(GregorianCalendar gc) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        fmt.setCalendar(gc);
        return fmt.format(gc.getTime());
    }

//  Test
    public static void main(String[] args) {
//        CBDD bdd = new CBDD(new CParametresBDD("parametresBdd.properties"));
//        bdd.creerBDD();
    }
}

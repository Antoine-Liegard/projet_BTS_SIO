package appliLourde.persistance;

/**
 *
 * @author Antoine Liégard, liegard.antoine35@gmail.com
 */
import appliLourde.entites.CEtudiant;
import appliLourde.entites.CListeEtudiants;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;

public class CTableEtudiants {

    protected CBDD bdd;

    public void setBdd(CBDD bdd) {
        this.bdd = bdd;
    }

    // permet de formater une date pour avoir le format requis par SQL
    public String formaterDate(GregorianCalendar gc) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        fmt.setCalendar(gc);
        return fmt.format(gc.getTime());
    }

    //nom de méthode explicite
    int supprimerTable() {
        String req = "DROP TABLE tableetudiants";
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion KO");
        }
        return res;
    }

    //nom de méthode explicite
    // réfléchir si intéressant de garder ou plutôt avoir crerBDD() dans loa classe CBDD
    int creerTable() {
        String req
                = "CREATE TABLE IF NOT EXISTS `tableetudiants` ("
                + "`idEtudiant` int(11) NOT NULL AUTO_INCREMENT,"
                + "`idSessionFormation` int(11) DEFAULT NULL,"
                + "`nom` varchar(20) NOT NULL,"
                + "`prenom` varchar(20) NOT NULL,"
                + "`dateNaissance` date NOT NULL,"
                + "`numeroSS` varchar(15) NOT NULL,"
                + "`commune` varchar(30) NOT NULL,"
                + "`codePostal` varchar(10) NOT NULL,"
                + "`numeroVoie` varchar(10) DEFAULT NULL,"
                + "`typeVoie` varchar(20) NOT NULL,"
                + "`nomVoie` varchar(50) NOT NULL,"
                + "`mail` varchar(45) NOT NULL,"
                + "`telephone1` varchar(12) NOT NULL,"
                + "`telephone2` varchar(12) DEFAULT NULL,"
                + "PRIMARY KEY (`idEtudiant`),"
                + "KEY `fk_etudiant_sessionFormation` (`idSessionFormation`)"
                + ") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;";
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion KO");
        }
        return res;
    }

    CEtudiant convertir_result_Etudiant(ResultSet result) {

        try {
            int idEtudiant = result.getInt("idEtudiant");
            int idSessionFormation = result.getInt("idSessionFormation");
            String nom = result.getString("nom");
            String prenom = result.getString("prenom");
            Date date = result.getDate("dateNaissance");
            String numeroSS = result.getString("numeroSS");
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTime(date);
            String commune = result.getString("commune");
            String codePostal = result.getString("codePostal");
            String numeroVoie = result.getString("numeroVoie");
            String nomVoie = result.getString("nomVoie");
            String typeVoie = result.getString("typeVoie");
            String mail = result.getString("mail");
            String telephone1 = result.getString("telephone1");
            String telephone2 = result.getString("telephone2");

            return new CEtudiant(idEtudiant, idSessionFormation, nom, prenom, gc, numeroSS, commune, codePostal, numeroVoie, nomVoie, typeVoie, mail, telephone1, telephone2);
        } catch (SQLException ex) {
            Logger.getLogger(CTableEtudiants.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    CListeEtudiants lireEtudiants() {

        if (bdd.connecter() == true) {
            CListeEtudiants listeEtudiants = new CListeEtudiants();
            ResultSet result = bdd.executerRequeteQuery("select * from tableetudiants");
            try {
                while (result.next()) {
                    CEtudiant etudiant = convertir_result_Etudiant(result);
                    listeEtudiants.ajouterEtudiant(etudiant);
                }
            } catch (SQLException ex) {
                Logger.getLogger(CBDD.class.getName()).log(Level.SEVERE, null, ex);
            }
            bdd.deconnecter();
            return listeEtudiants;
        } else {
            System.out.println("Connexion KO");
        }
        return null;
    }

    CEtudiant lireEtudiant(int idEtu) {
        CEtudiant etudiant = null;
        if (bdd.connecter() == true) {
            System.out.println("Connexion OK");
            ResultSet result = bdd.executerRequeteQuery("select * from tableetudiants  WHERE `tableetudiants`.`id` = " + idEtu);
            try {
                if (result.next()) {
                    etudiant = convertir_result_Etudiant(result);
                }
            } catch (SQLException ex) {
                Logger.getLogger(CBDD.class.getName()).log(Level.SEVERE, null, ex);
            }
            bdd.deconnecter();
        } else {
            System.out.println("Connexion KO");
        }
        return etudiant;
    }

    int insererEtudiant(CEtudiant etudiant) {
        int res = 0;
        if (bdd.connecter() == true) {
            String dateNaissance = formaterDate(etudiant.getDateNaissance());
            String req = "INSERT INTO `tableetudiants` (`idSessionFormation`, "
                    + "`nom`, `prenom`, `dateNaissance`,`numeroSS`, `commune`, "
                    + ""
                    + "`codePostal`, `numeroVoie`, "
                    + "`typeVoie`, `nomVoie`, `mail`, `telephone1`, `telephone2`) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);";

            try {
                
                PreparedStatement pstmt = bdd.conn.prepareStatement(req);
                if (etudiant.getIdSessionFormation() <= 0) {
                    pstmt.setNull(1, java.sql.Types.INTEGER);
                } else {
                    pstmt.setInt(1, etudiant.getIdSessionFormation());
                }
                pstmt.setString(2, etudiant.getNom());
                pstmt.setString(3, etudiant.getPrenom());
                pstmt.setString(4, dateNaissance);
                pstmt.setString(5, etudiant.getNumeroSS());
                pstmt.setString(6, etudiant.getCommune());
                pstmt.setString(7, etudiant.getCodePostal());
                
                if ("".equals(etudiant.getNumeroVoie())) {
                    pstmt.setNull(8, java.sql.Types.VARCHAR);
                } else {
                    pstmt.setString(8, etudiant.getNumeroVoie());
                }
                pstmt.setString(9, etudiant.getTypeVoie());
                pstmt.setString(10, etudiant.getNomVoie());
                pstmt.setString(11, etudiant.getMail());
                pstmt.setString(12, etudiant.getTelephone1());

                if ("".equals(etudiant.getTelephone2())) {
                    pstmt.setNull(13, java.sql.Types.VARCHAR);
                } else {
                    pstmt.setString(13, etudiant.getTelephone2());
                }
                pstmt.execute();
            } catch (SQLException ex) {
                Logger.getLogger(CBDD.class.getName()).log(Level.SEVERE, null, ex);
                res=1;
            }

            bdd.deconnecter();
        } else {
            System.out.println("Connexion KO");
        }
        return res;
    }

    int mettreAJourEtudiant(CEtudiant etudiant) {
        int res = 0;
        if (bdd.connecter() == true) {
            String dateNaissance = formaterDate(etudiant.getDateNaissance());
            
            String req = "UPDATE tableetudiants "
                    + "SET `IdSessionFormation` = ? , `nom` = ? , `prenom` = ? , `dateNaissance` = ? , `numeroSS` = ? , `commune` = ? , `codePostal` = ? , `numeroVoie` = ? , `typeVoie` = ? , `nomVoie` = ? , `mail` = ? , `telephone1` = ? , `telephone2` = ? WHERE `tableetudiants`.`idEtudiant` =" + etudiant.getIdEtudiant() + ";";
            try {
                
                PreparedStatement pstmt = bdd.conn.prepareStatement(req);
                if (etudiant.getIdSessionFormation() <= 0) {
                    pstmt.setNull(1, java.sql.Types.INTEGER);
                } else {
                    pstmt.setInt(1, etudiant.getIdSessionFormation());
                }
                pstmt.setString(2, etudiant.getNom());
                pstmt.setString(3, etudiant.getPrenom());
                pstmt.setString(4, dateNaissance);
                pstmt.setString(5, etudiant.getNumeroSS());
                pstmt.setString(6, etudiant.getCommune());
                pstmt.setString(7, etudiant.getCodePostal());
                
                if ("".equals(etudiant.getNumeroVoie())) {
                    pstmt.setNull(8, java.sql.Types.VARCHAR);
                } else {
                    pstmt.setString(8, etudiant.getNumeroVoie());
                }
                pstmt.setString(9, etudiant.getTypeVoie());
                pstmt.setString(10, etudiant.getNomVoie());
                pstmt.setString(11, etudiant.getMail());
                pstmt.setString(12, etudiant.getTelephone1());

                if ("".equals(etudiant.getTelephone2())) {
                    pstmt.setNull(13, java.sql.Types.VARCHAR);
                } else {
                    pstmt.setString(13, etudiant.getTelephone2());
                }
                System.out.println(pstmt);
                pstmt.execute();
                res=1;
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

    // nom de méthode explicite
    int supprimerTousLesEtudiants() {
        int res = -1;
        if (bdd.connecter() == true) {
            String req = "DELETE FROM tableetudiants ";
            res = bdd.executerRequeteUpdate(req);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion KO");
        }
        return res;
    }

    // supprime étudiant par rapport à son ID de classe
    int supprimerEtudiant(CEtudiant etudiant) {
        int res = -1;
        if (bdd.connecter() == true) {

            String req = "DELETE FROM tableetudiants WHERE `tableetudiants`.`id` = " + etudiant.getIdEtudiant();
            res = bdd.executerRequeteUpdate(req);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion KO");
        }
        return res;
    }

    // TODO réfléchir si intéressant de garder
    int supprimerEtudiant(int id) {
        int res = -1;
        if (bdd.connecter() == true) {
            String req = "DELETE FROM tableetudiants WHERE `tableetudiants`.`id` = " + id;
            res = bdd.executerRequeteUpdate(req);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion KO");
        }
        return res;
    }

    public static void main(String[] args) {
        CBDD bdd = new CBDD(new CParametresBDD("parametresBdd.properties"));
        CTableEtudiants tableEtudiants = new CTableEtudiants();
        tableEtudiants.setBdd(bdd);  // Création de l'association simple entre TableEtudiants et CBDD

//        for (int i = 0; i < 5; i++) {
//            int rand = (int) (Math.random() * 100);
//            tableEtudiants.insererEtudiant(new CEtudiant(
//                    -1,
//                    -1,
//                    "Nom" + rand,
//                    "Prénom" + rand,
//                    new GregorianCalendar(),
//                    "196114634948493",
//                    "Rennes",
//                    "35000",
//                    "1",
//                    "Rue",
//                    "Bô jeu",
//                    "test.test@gmail.com",
//                    "0102030405",
//                    "0203040506"));
//        }
        
//        CEtudiant etudiant = new CEtudiant(
//                    49,
//                    -1,
//                    "Chevalier",
//                    "Alexis",
//                    new GregorianCalendar(),
//                    "196114634948493",
//                    "Rennes",
//                    "35000",
//                    "1",
//                    "Rue",
//                    "Bô jeu",
//                    "test.test@gmail.com",
//                    "0102030405",
//                    "0203040506");
//        tableEtudiants.mettreAJourEtudiant(etudiant);

        CListeEtudiants listeEtudiants = tableEtudiants.lireEtudiants();
        listeEtudiants.toString();

////        test
//        tableEtudiants.supprimerTousLesEtudiants();
    }
}

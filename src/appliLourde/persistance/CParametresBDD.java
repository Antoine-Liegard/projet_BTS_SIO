package appliLourde.persistance;

/**
 *
 * @author Antoine Liégard, liegard.antoine35@gmail.com
 */

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CParametresBDD {

    protected String ip;
    protected int port;
    protected String driver;
    
    protected String protocole;

    public String getProtocole() {
        return protocole;
    }

    public void setProtocole(String protocole) {
        this.protocole = protocole;
    }

    protected String nomBase;
    protected String utilisateur;
    protected String motDePasse;

    public CParametresBDD(String fichierProperties) {
        chargerFichierProperties(fichierProperties);
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(String utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getNomBase() {
        return nomBase;
    }

    public void setNomBase(String nomBase) {
        this.nomBase = nomBase;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void chargerFichierProperties(String fichierProperties) {
        try (InputStream in = new FileInputStream(fichierProperties)) {
            Properties prop = new Properties();
            prop.load(in);
            this.ip = prop.getProperty("ip");
            this.port = Integer.parseInt(prop.getProperty("port"));
            this.driver = prop.getProperty("driver");
            this.protocole = prop.getProperty("protocole");
            this.nomBase = prop.getProperty("nomBase");
            this.utilisateur = prop.getProperty("utilisateur");
            this.motDePasse = prop.getProperty("motDePasse");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "" + ip + "   " + port + "   " + driver + "   "+ protocole + "   " + nomBase + "   "
                + utilisateur + " " + motDePasse;
    }

    public static void main(String[] args) {
        CParametresBDD parametresStockageBDD
                = new CParametresBDD("parametresBdd.properties");
        System.out.println(parametresStockageBDD.toString());
    }
}

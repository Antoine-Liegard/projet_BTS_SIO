package appliLourde.entites;

/**
 *
 * @author Antoine Liégard, liegard.antoine35@gmail.com
 */
import java.util.GregorianCalendar;

public class CEtudiant {

    protected int idEtudiant;
    protected Integer idSessionFormation;
    protected String nom;
    protected String prenom;
    protected GregorianCalendar dateNaissance;
    protected String numeroSS;
    protected String commune;
    protected String codePostal;
    protected String numeroVoie;
    protected String typeVoie;
    protected String nomVoie;
    protected String mail;
    protected String telephone1;
    protected String telephone2;

    //constructeur complet 
    public CEtudiant(int idEtudiant, int idSessionFormation, String nom, String prenom, GregorianCalendar dateNaissance, String numeroSS, String commune, String codePostal, String numeroVoie, String typeVoie, String nomVoie, String mail, String telephone1, String telephone2) {
        this.idEtudiant = idEtudiant;
        if (idSessionFormation > 0) {
            this.idSessionFormation = idSessionFormation;
        } else {
            this.idSessionFormation = 0;
        }

        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.commune = commune;
        this.codePostal = codePostal;
        this.numeroVoie = numeroVoie;
        this.typeVoie = typeVoie;
        this.nomVoie = nomVoie;
        this.mail = mail;
        this.telephone1 = telephone1;
        this.telephone2 = telephone2;
        this.numeroSS = numeroSS;
    }

    String gregorianCalendarToString(GregorianCalendar gc) {
        return "" + gc.get(GregorianCalendar.YEAR)
                + '/'
                + gc.get(GregorianCalendar.MONTH) + '/'
                + gc.get(GregorianCalendar.DAY_OF_MONTH) + ' '
                + gc.get(GregorianCalendar.HOUR) + ':'
                + gc.get(GregorianCalendar.MINUTE) + ':'
                + gc.get(GregorianCalendar.SECOND);
    }

    // toString retournant une chaine avec l'ID, le nom, le prénom et la date de naissance
    @Override
    public String toString() {
        return "" + idEtudiant + " " + nom + " " + prenom + " " + gregorianCalendarToString(dateNaissance) + " " +idSessionFormation + " " +telephone2;
    }

    // getter et setter, générés automatiquement
    public int getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudiant(int idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    public int getIdSessionFormation() {
        return idSessionFormation;
    }

    public void setIdSessionFormation(int idSessionFormation) {
        this.idSessionFormation = idSessionFormation;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public GregorianCalendar getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(GregorianCalendar dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getNumeroVoie() {
        return numeroVoie;
    }

    public void setNumeroVoie(String numeroVoie) {
        this.numeroVoie = numeroVoie;
    }

    public String getTypeVoie() {
        return typeVoie;
    }

    public void setTypeVoie(String typeVoie) {
        this.typeVoie = typeVoie;
    }

    public String getNomVoie() {
        return nomVoie;
    }

    public void setNomVoie(String nomVoie) {
        this.nomVoie = nomVoie;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTelephone1() {
        return telephone1;
    }

    public void setTelephone1(String telephone1) {
        this.telephone1 = telephone1;
    }

    public String getTelephone2() {
        return telephone2;
    }

    public void setTelephone2(String telephone2) {
        this.telephone2 = telephone2;
    }

    public String getNumeroSS() {
        return numeroSS;
    }

    public void setNumeroSS(String numeroSS) {
        this.numeroSS = numeroSS;
    }

}

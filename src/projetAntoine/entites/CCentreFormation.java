/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetAntoine.entites;

/**
 *
 * @author Antoine Liégard, liegard.antoine35@gmail.com
 */
public class CCentreFormation {

    protected int idCentreFormation;
    protected String raisonSociale;
    protected String commune;
    protected String codePostal;
    protected String numeroVoie;
    protected String typeVoie;
    protected String nomVoie;
    protected String mail;
    protected String mail2;
    protected String telphone;
    protected String telephone2;

    // constructeurs, l'un complet et l'autre sans id
    public CCentreFormation(int idCentreFormation, String raisonSociale, String commune, String codePostal, String numeroVoie, String typeVoie, String nomVoie, String mail, String mail2, String telphone, String telephone2) {
        this.idCentreFormation = idCentreFormation;
        this.raisonSociale = raisonSociale;
        this.commune = commune;
        this.codePostal = codePostal;
        this.numeroVoie = numeroVoie;
        this.typeVoie = typeVoie;
        this.nomVoie = nomVoie;
        this.mail = mail;
        this.mail2 = mail2;
        this.telphone = telphone;
        this.telephone2 = telephone2;
    }

    public CCentreFormation(String raisonSociale, String commune, String codePostal, String numeroVoie, String typeVoie, String nomVoie, String mail, String mail2, String telphone, String telephone2) {
        this.raisonSociale = raisonSociale;
        this.commune = commune;
        this.codePostal = codePostal;
        this.numeroVoie = numeroVoie;
        this.typeVoie = typeVoie;
        this.nomVoie = nomVoie;
        this.mail = mail;
        this.mail2 = mail2;
        this.telphone = telphone;
        this.telephone2 = telephone2;
    }

    // toString retournant une chaine avec l'ID, le nom, le prénom et la date de naissance
    @Override
    public String toString() {
        return "" + idCentreFormation + " " + raisonSociale;
    }

    //Getter and Setters
    public int getIdCentreFormation() {
        return idCentreFormation;
    }

    public void setIdCentreFormation(int idCentreFormation) {
        this.idCentreFormation = idCentreFormation;
    }

    public String getMail2() {
        return mail2;
    }

    public void setMail2(String mail2) {
        this.mail2 = mail2;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getTelephone2() {
        return telephone2;
    }

    public void setTelephone2(String telephone2) {
        this.telephone2 = telephone2;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNomVoie() {
        return nomVoie;
    }

    public void setNomVoie(String nomVoie) {
        this.nomVoie = nomVoie;
    }

    public String getTypeVoie() {
        return typeVoie;
    }

    public void setTypeVoie(String typeVoie) {
        this.typeVoie = typeVoie;
    }

    public String getNumeroVoie() {
        return numeroVoie;
    }

    public void setNumeroVoie(String numeroVoie) {
        this.numeroVoie = numeroVoie;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    public static void main(String[] args) {
        CCentreFormation test = new CCentreFormation("raisonSociale", "commune", "codePostal", "numeroVoie", "typeVoie", "nomVoie", "mail", "mail2", "telphone", "telephone2");
        System.out.println(test.toString());
    }

}

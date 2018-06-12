/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetAntoine.persistance;

import projetAntoine.entites.CEntitees;

/**
 *
 * @author Antoine Liégard, liegard.antoine35@gmail.com
 */
public class CPersistance {

    protected CBDD bdd;
    protected CTableCentreFormation tableCentreFormation;
    protected CTableEtudiants tableEtudiants;
    protected CTableFormation tableFormation;
    protected CTableSessionFormation tableSessionFormation;

    
    // constructeur prennant en paramètre un onjet CBDD
    public CPersistance(CBDD bdd) {
        this.bdd = bdd;
        this.tableCentreFormation = new CTableCentreFormation(bdd);
        this.tableEtudiants = new CTableEtudiants(bdd);
        this.tableFormation = new CTableFormation(bdd);
        this.tableSessionFormation = new CTableSessionFormation(bdd);
    }
    
    // constructeur vide initialisant avec les paramètres du fichier properties
    public CPersistance() {
        this.bdd = new CBDD(new CParametresBDD("parametresBdd.properties"));
        this.tableCentreFormation = new CTableCentreFormation(bdd);
        this.tableEtudiants = new CTableEtudiants(bdd);
        this.tableFormation = new CTableFormation(bdd);
        this.tableSessionFormation = new CTableSessionFormation(bdd);
    }

    public CBDD getBdd() {
        return bdd;
    }

    public void setBdd(CBDD bdd) {
        this.bdd = bdd;
    }

    public CTableCentreFormation getTableCentreFormation() {
        return tableCentreFormation;
    }

    public void setTableCentreFormation(CTableCentreFormation tableCentreFormation) {
        this.tableCentreFormation = tableCentreFormation;
    }

    public CTableEtudiants getTableEtudiants() {
        return tableEtudiants;
    }

    public void setTableEtudiants(CTableEtudiants tableEtudiants) {
        this.tableEtudiants = tableEtudiants;
    }

    public CTableFormation getTableFormation() {
        return tableFormation;
    }

    public void setTableFormation(CTableFormation tableFormation) {
        this.tableFormation = tableFormation;
    }

    public CTableSessionFormation getTableSessionFormation() {
        return tableSessionFormation;
    }

    public void setTableSessionFormation(CTableSessionFormation tableSessionFormation) {
        this.tableSessionFormation = tableSessionFormation;
    }
    

    
    
    
    public static void main(String[] args) {
        CBDD bdd = new CBDD(new CParametresBDD("parametresBdd.properties"));
        CPersistance persistance = new CPersistance(bdd);
    }

}

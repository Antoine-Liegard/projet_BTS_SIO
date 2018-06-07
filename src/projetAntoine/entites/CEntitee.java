/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetAntoine.entites;

import projetAntoine.persistance.CPersistance;

/**
 *
 * @author Antoine Liégard, liegard.antoine35@gmail.com
 */
public class CEntitee {

    protected CListeCentreFormation listeCentreFormation;
    protected CListeEtudiants listeEtudiants;
    protected CListeFormations listeFormations;
    protected CListeSessionFormation listeSessionFormation;

    public CEntitee() {
    }

    
    public void initEntitée(CPersistance persistance){
        this.listeCentreFormation= persistance.getTableCentreFormation().lireCentresFormations();       
        this.listeFormations= persistance.getTableFormation().lireFormations();
        this.listeSessionFormation= persistance.getTableSessionFormation().lireSessionsFormations();
        persistance.getTableEtudiants().setListeSessionFormation(listeSessionFormation);
        this.listeEtudiants= persistance.getTableEtudiants().lireEtudiants();
    }

    public CListeCentreFormation getListeCentreFormation() {
        return listeCentreFormation;
    }

    public void setListeCentreFormation(CListeCentreFormation listeCentreFormation) {
        this.listeCentreFormation = listeCentreFormation;
    }

    public CListeEtudiants getListeEtudiants() {
        return listeEtudiants;
    }

    public void setListeEtudiants(CListeEtudiants listeEtudiants) {
        this.listeEtudiants = listeEtudiants;
    }

    public CListeFormations getListeFormations() {
        return listeFormations;
    }

    public void setListeFormations(CListeFormations listeFormations) {
        this.listeFormations = listeFormations;
    }

    public CListeSessionFormation getListeSessionFormation() {
        return listeSessionFormation;
    }

    public void setListeSessionFormation(CListeSessionFormation listeSessionFormation) {
        this.listeSessionFormation = listeSessionFormation;
    }

    
    public static void main(String[] args) {
        
    }
}

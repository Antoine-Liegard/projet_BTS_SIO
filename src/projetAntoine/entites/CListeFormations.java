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
import java.util.ArrayList;

public class CListeFormations {

    public ArrayList<CFormation> listeFormations = new ArrayList();

    public void ajouterFormation(CFormation formation) {
        this.listeFormations.add(formation);
    }

    CFormation selectionnerFormation(int id) {
        for (CFormation formation : this.listeFormations) {
            if (formation.idFormation == id) {
                return formation;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String res = "";
        for (CFormation formation : this.listeFormations) {
            System.out.println(formation.toString());
            res += formation.toString() + "|";
        }
        return res;
    }
}

package appliLourde.entites;

/**
 *
 * @author Antoine Liégard, liegard.antoine35@gmail.com
 */

import java.util.ArrayList;

public class CListeEtudiants {

    public ArrayList<CEtudiant> listeEtudiant = new ArrayList();

    public void ajouterEtudiant(CEtudiant etudiant) {
        this.listeEtudiant.add(etudiant);
    }

    CEtudiant selectionnerPersonne(int id) {
        for (CEtudiant listeEtudiants : this.listeEtudiant) {
            if (listeEtudiants.idEtudiant == id) {
                return listeEtudiants;
            }
        }
        return null;
    }
    
    @Override
    public String toString() {
        for (CEtudiant etudiant : this.listeEtudiant) {
            System.out.println(etudiant.toString());
        }
        return null;
    }

}

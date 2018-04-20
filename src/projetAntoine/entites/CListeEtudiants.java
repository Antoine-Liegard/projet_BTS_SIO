package projetAntoine.entites;

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
        for (CEtudiant etudiant : this.listeEtudiant) {
            if (etudiant.idEtudiant == id) {
                return etudiant;
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

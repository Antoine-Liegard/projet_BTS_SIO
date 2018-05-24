package projetAntoine.entites;

/**
 *
 * @author Antoine Liégard, liegard.antoine35@gmail.com
 */
import java.util.ArrayList;

public class CListeCentreFormation {

    public ArrayList<CCentreFormation> listeCentreFormation = new ArrayList();

    public void ajouterFormation(CCentreFormation centreFormation) {
        this.listeCentreFormation.add(centreFormation);
    }

    // retourne un centre formation par rapport à son ID de table
    public CCentreFormation selectionnerCentreFormation(int id) {
        for (CCentreFormation centreFormation : this.listeCentreFormation) {
            if (centreFormation.idCentreFormation == id) {
                return centreFormation;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String res = "";
        for (CCentreFormation centreFormation : this.listeCentreFormation) {
            System.out.println(centreFormation.toString());
            res += centreFormation.toString() + "/n";
        }
        return res;
    }

    public static void main(String[] args) {
        CCentreFormation centreFormation1 = new CCentreFormation("raisonSociale", "commune", "codePostal", "numeroVoie", "typeVoie", "nomVoie", "mail", "mail2", "telphone", "telephone2");
        CCentreFormation centreFormation2 = new CCentreFormation("raisonSociale", "commune", "codePostal", "numeroVoie", "typeVoie", "nomVoie", "mail", "mail2", "telphone", "telephone2");

        CListeCentreFormation test = new CListeCentreFormation();
        test.ajouterFormation(centreFormation1);
        test.ajouterFormation(centreFormation2);
        test.toString();
    }
}

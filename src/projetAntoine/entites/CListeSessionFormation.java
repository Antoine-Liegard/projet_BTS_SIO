/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetAntoine.entites;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 *
 * @author Antoine Liégard, liegard.antoine35@gmail.com
 */
public class CListeSessionFormation {
    
    public ArrayList<CSessionFormation> listeSessionFormation = new ArrayList();
    
    public void ajouterSession(CSessionFormation session) {
        this.listeSessionFormation.add(session);
    }
    
    public CSessionFormation selectionnerSession(int id) {
        for (CSessionFormation session : this.listeSessionFormation) {
            if (session.idSessionFormation == id) {
                return session;
            }
        }
        return null;
    }
    
    @Override
    public String toString() {
        for (CSessionFormation session : this.listeSessionFormation) {
            System.out.println(session.toString());
        }
        return null;
    }
    
    // test
    public static void main(String[] args) {
        CListeSessionFormation testListe = new CListeSessionFormation();
        CFormation formation = new CFormation("BTS SIO", "SLAM");
        CCentreFormation centreFormation = new CCentreFormation("raisonSociale", "commune", 
                "codePostal", "", "typeVoie", "nomVoie", "mail", "mail2", "telphone", "telephone2");
        GregorianCalendar debutSession = new GregorianCalendar(2017, 9, 12);
        GregorianCalendar finSession = new GregorianCalendar(2018, 6, 24);
        CSessionFormation sessionFormation = new CSessionFormation(0, formation, centreFormation, debutSession, finSession);
        testListe.ajouterSession(sessionFormation);
        
        testListe.toString();
    }
}

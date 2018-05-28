/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetAntoine.entites;

import java.util.GregorianCalendar;

/**
 *
 * @author Antoine Liégard, liegard.antoine35@gmail.com
 */
public class CSessionFormation {

    protected int idSessionFormation;
    protected CFormation formation;
    protected CCentreFormation centreFormation;
    protected GregorianCalendar debutSession;
    protected GregorianCalendar finSession;

    // constructeur complet  
    public CSessionFormation(int idSessionFormation, CFormation formation, CCentreFormation centreFormation, GregorianCalendar debutSession, GregorianCalendar finSession) {
        if (idSessionFormation > 0) {
            this.idSessionFormation = idSessionFormation;
        } else {
            this.idSessionFormation = 0;
        }

        this.formation = formation;
        this.centreFormation = centreFormation;
        this.debutSession = debutSession;
        this.finSession = finSession;
    }

    // constructeur sans dates
    public CSessionFormation(int idSessionFormation, CFormation formation, CCentreFormation centreFormation) {
        if (idSessionFormation > 0) {
            this.idSessionFormation = idSessionFormation;
        } else {
            this.idSessionFormation = 0;
        }
        this.formation = formation;
        this.centreFormation = centreFormation;
    }

    // methode de formatage de la date
    String gregorianCalendarToString(GregorianCalendar gc) {
        return "" + gc.get(GregorianCalendar.YEAR)
                + '/'
                + gc.get(GregorianCalendar.MONTH) + '/'
                + gc.get(GregorianCalendar.DAY_OF_MONTH) + ' '
                + gc.get(GregorianCalendar.HOUR) + ':'
                + gc.get(GregorianCalendar.MINUTE) + ':'
                + gc.get(GregorianCalendar.SECOND);
    }

    // toString retournant une chaine avec le diplome, le centre de formation et les dates de debut et fin
    @Override
    public String toString() {
        return formation.getLibelleDiplome() + " " + centreFormation.getRaisonSociale() + " " + gregorianCalendarToString(debutSession) + " " + gregorianCalendarToString(finSession);
    }

    public int getIdSessionFormation() {
        return idSessionFormation;
    }

    public void setIdSessionFormation(int idSessionFormation) {
        this.idSessionFormation = idSessionFormation;
    }

    public CFormation getFormation() {
        return formation;
    }

    public void setFormation(CFormation formation) {
        this.formation = formation;
    }

    public CCentreFormation getCentreFormation() {
        return centreFormation;
    }

    public void setCentreFormation(CCentreFormation centreFormation) {
        this.centreFormation = centreFormation;
    }

    public GregorianCalendar getDebutSession() {
        return debutSession;
    }

    public void setDebutSession(GregorianCalendar debutSession) {
        this.debutSession = debutSession;
    }

    public GregorianCalendar getFinSession() {
        return finSession;
    }

    public void setFinSession(GregorianCalendar finSession) {
        this.finSession = finSession;
    }

    
    
    // test
    
    public static void main(String[] args) {
        
    }
}

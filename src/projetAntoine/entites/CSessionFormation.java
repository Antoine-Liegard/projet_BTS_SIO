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

    // methode de formatage de la date
    String gregorianCalendarToString(GregorianCalendar gc) {

        String message = "";
        if (gc.get(GregorianCalendar.DAY_OF_MONTH) < 10) {
            message += "0" + Integer.toString(gc.get(GregorianCalendar.DAY_OF_MONTH)) + '/';
        } else {
            message += Integer.toString(gc.get(GregorianCalendar.DAY_OF_MONTH)) + '/';

        }
        
        if (gc.get(GregorianCalendar.MONTH) < 9) {
            message += "0" + Integer.toString(gc.get(GregorianCalendar.MONTH)+1) + '/';
        } else {
            message += Integer.toString(gc.get(GregorianCalendar.MONTH)+1) + '/';

        }

            message += gc.get(GregorianCalendar.YEAR);


        return message;
    }

    // toString retournant une chaine avec le diplome, le centre de formation et les dates de debut et fin
    @Override
    public String toString() {
        String optionDiplome = "";
        if (formation.getOptionDiplome() != null) {
            optionDiplome += " " + formation.getOptionDiplome();
        }
        return Integer.toString(idSessionFormation) + " " + formation.getLibelleDiplome() + optionDiplome + " " + centreFormation.getRaisonSociale() + dateToString();
    }

    public String dateToString() {
        return " du " + gregorianCalendarToString(debutSession) + " au " + gregorianCalendarToString(finSession);
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

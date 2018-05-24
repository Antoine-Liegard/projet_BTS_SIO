package projetAntoine.entites;

/**
 *
 * @author Antoine Liégard, liegard.antoine35@gmail.com
 */
public class CFormation {

    protected int idFormation;
    protected String libelleDiplome;
    protected String optionDiplome;

    // constructeurs, l'un complet et l'autre sans id
    public CFormation(int idFormation, String libelleDiplome, String optionDiplome) {
        this.idFormation = idFormation;
        this.libelleDiplome = libelleDiplome;
        this.optionDiplome = optionDiplome;
    }
    
    public CFormation(String libelleDiplome, String optionDiplome) {
        this.libelleDiplome = libelleDiplome;
        this.optionDiplome = optionDiplome;
    }
    
    // toString
        @Override
    public String toString() {
        return libelleDiplome + " " + optionDiplome;
    }
    
    //Getter and Setters
    public int getIdFormation() {
        return idFormation;
    }

    public void setIdFormation(int idFormation) {
        this.idFormation = idFormation;
    }

    public String getLibelleDiplome() {
        return libelleDiplome;
    }

    public void setLibelleDiplome(String libelleDiplome) {
        this.libelleDiplome = libelleDiplome;
    }

    public String getOptionDiplome() {
        return optionDiplome;
    }

    public void setOptionDiplome(String optionDiplome) {
        this.optionDiplome = optionDiplome;
    }



}

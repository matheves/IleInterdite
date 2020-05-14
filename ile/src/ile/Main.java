package ile;

import java.util.ArrayList;

/**
 *
 * @author matheves
 */
public class Main {

    public static void main(String[] args) {
        Controleur control = new Controleur();
        control.getVuePlat().addObservateur(control);
        control.getJoueur1().addObservateur(control);
        control.getJoueur2().addObservateur(control);
        control.getJoueur3().addObservateur(control);
        control.getJoueur4().addObservateur(control);
    }
    
}

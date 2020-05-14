package ile;

import javax.swing.JPanel;

/**
 *
 * @author laurillau
 */

public class Observe extends JPanel {
    private Observateur observateur;
    private JPanel panel;
    
    public void addObservateur(Observateur o) {
        this.observateur = o;
    }
    
    public void notifierObservateur(Message m) {
        if (observateur != null) {
            observateur.traiterMessage(m);
        }
    }
}

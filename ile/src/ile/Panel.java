package ile;

import java.awt.*;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author matheves
 */
public class Panel extends JPanel {
    private Tuile tuile;
    private ArrayList<JPanel> panels;
    
    Panel(Tuile tuile) {
        super(new BorderLayout());
        this.tuile = tuile;
        JPanel p = new JPanel(new GridLayout(1,4));
        this.add(p,BorderLayout.NORTH);
        panels = new ArrayList<>();
        for(int i = 0; i < 4 ; i++) {
            panels.add(new JPanel());
            p.add(getPanels().get(i));
        }
    }

    public Tuile getTuile() {
        return tuile;
    }

    public void setTuile(Tuile tuile) {
        this.tuile = tuile;
    }

    public ArrayList<JPanel> getPanels() {
        return panels;
    }
    
    
    
    
    
}

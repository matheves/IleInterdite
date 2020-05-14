package ile;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.swing.*;
import java.awt.Graphics;

public class vuePlateau extends JPanel {

    private JFrame window;
    private JPanel mainPanel;
    private JPanel grillePanel;
    private ArrayList<JPanel> panelCels;

    public vuePlateau(Grille g/*,ArrayList<Aventurier> aventuriers*/) {
        this.window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(1080, 720);
        window.setTitle("Plateau");
        mainPanel = new JPanel(new BorderLayout());
        window.add(mainPanel);
        grillePanel = new JPanel(new GridLayout(6, 6));
        window.add(grillePanel, BorderLayout.CENTER);
        panelCels = new ArrayList<>();
        for (int i = 1; i < 37; i++) {
            JLabel lab = new JLabel(g.getCases().get(i).getNom(), SwingConstants.CENTER);
            JPanel panelCel = new JPanel();
            panelCels.add(panelCel);
            if (i == 3 || i == 4 || (i >= 8 && i <= 11) || (i >= 13 && i <= 24) || (i >= 26 && i <= 29) || i == 33 || i == 34) {
                panelCel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                if (g.getCases().get(i).getEtat() == Etat.SECHE) {
                    panelCel.setBackground(Color.ORANGE);
                } else if (g.getCases().get(i).getEtat() == Etat.INONDEE) {
                    panelCel.setBackground(Color.CYAN);
                } else {
                    panelCel.setBackground(Color.BLUE);
                }
                panelCel.addMouseListener(
                        new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                    }
                }
                );
            }
            panelCel.add(lab);
            grillePanel.add(panelCel);
            
            
        }
        window.setVisible(true);
    }

    public void selectionnerUneCases(HashMap<Integer, Tuile> cases) {
        Set<Map.Entry<Integer, Tuile>> set = cases.entrySet();
        Iterator<Map.Entry<Integer, Tuile>> it = set.iterator();
        Tuile t = new Tuile();
        while (it.hasNext()) {
            Map.Entry<Integer, Tuile> val = it.next();
            t = val.getValue();
            /*colorCases(t.getNum());*/
            int i = t.getNum();
            JPanel panelSelec = panelCels.get(i-1); 
            if (i == 3 || i == 4 || (i >= 8 && i <= 11) || (i >= 13 && i <= 24) || (i >= 26 && i <= 29) || i == 33 || i == 34) {
                panelSelec.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
                repaint();
            }
        }
    }
}


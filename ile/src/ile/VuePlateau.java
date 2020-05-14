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

public class VuePlateau extends Observe {

    private JFrame window;
    private JPanel mainPanel;
    private JPanel grillePanel;
    private ArrayList<Panel> panelCels;
    private ArrayList<Panel> casesJoueurs;
    private ArrayList<Panel> deplacementsPossibles;

    public VuePlateau(Grille g) {
        deplacementsPossibles = new ArrayList<>();
        casesJoueurs = new ArrayList<>();
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
            Panel panelCel = new Panel(g.getCases().get(i));
            panelCels.add(panelCel);
            for(int j = 0; j < g.getCases().get(i).getNbJoueursCase().size();j++) {
                Pion p = g.getCases().get(i).getNbJoueursCase().get(j).getPion();
                Color c;
                switch (p.getCouleur()) {
                    case "BLEU":
                        c = Color.BLUE;
                        break;
                    case "GRIS":
                        c = Color.GRAY;
                        break;
                    case "JAUNE":
                        c = Color.YELLOW;
                        break;
                    case "NOIR":
                        c = Color.BLACK;
                        break;
                    case "ROUGE":
                        c = Color.RED;
                        break;
                    default:
                        c = Color.GREEN;
                        break;
                }
                panelCel.getPanels().get(j).setBackground(c);
                casesJoueurs.add(panelCel);
            }
            if (i == 3 || i == 4 || (i >= 8 && i <= 11) || (i >= 13 && i <= 24) || (i >= 26 && i <= 29) || i == 33 || i == 34) {
                panelCel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                if (g.getCases().get(i).getEtat().getEtat() == "SECHE") {
                    panelCel.setBackground(Color.ORANGE);
                    panelCel.setForeground(Color.ORANGE);
                } else if (g.getCases().get(i).getEtat().getEtat() == "INONDEE") {
                    panelCel.setBackground(Color.CYAN);
                    panelCel.setForeground(Color.CYAN);
                } else {
                    panelCel.setBackground(Color.BLUE);
                    panelCel.setForeground(Color.BLUE);
                }
                panelCel.addMouseListener(
                        new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        Message m = new Message();
                        m.t = panelCel.getTuile();
                        m.type = TypesMessages.TYPE_TUILE;
                        notifierObservateur(m);
                        
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

    public void selectionnerUneCase(HashMap<Integer, Tuile> cases) {
        this.getDeplacementsPossibles().clear();
        Set<Map.Entry<Integer, Tuile>> set = cases.entrySet();
        Iterator<Map.Entry<Integer, Tuile>> it = set.iterator();
        Tuile t = new Tuile();
        while (it.hasNext()) {
            Map.Entry<Integer, Tuile> val = it.next();
            t = val.getValue();
            int i = t.getNum();
            if (i == 3 || i == 4 || (i >= 8 && i <= 11) || (i >= 13 && i <= 24) || (i >= 26 && i <= 29) || i == 33 || i == 34) {
                Panel panelSelec = panelCels.get(i-1); 
                this.getDeplacementsPossibles().add(panelSelec);
            }
        }
    }
    
    public void update(Grille g){
        for (int i = 0; i < 35; i++) {
            if (i == 3 || i == 4 || (i >= 8 && i <= 11) || (i >= 13 && i <= 24) || (i >= 26 && i <= 29) || i == 33 || i == 34) {
                for(int j = 0; j < 4;j++) {
                    panelCels.get(i-1).getPanels().get(j).setBackground(Color.WHITE);
                }
            }            
        }
        for (int i = 1; i < 35; i++) {
            for(int j = 0; j < g.getCases().get(i).getNbJoueursCase().size();j++) {
                Pion p = g.getCases().get(i).getNbJoueursCase().get(j).getPion();
                Color c;
                switch (p.getCouleur()) {
                    case "BLEU":
                        c = Color.BLUE;
                        break;
                    case "GRIS":
                        c = Color.GRAY;
                        break;
                    case "JAUNE":
                        c = Color.YELLOW;
                        break;
                    case "NOIR":
                        c = Color.BLACK;
                        break;
                    case "ROUGE":
                        c = Color.RED;
                        break;
                    default:
                        c = Color.GREEN;
                        break;
                }
                panelCels.get(i-1).getPanels().get(j).setBackground(c);
                
            }
             if (i == 3 || i == 4 || (i >= 8 && i <= 11) || (i >= 13 && i <= 24) || (i >= 26 && i <= 29) || i == 33 || i == 34) {
                panelCels.get(i-1).setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                if (g.getCases().get(i).getEtat().getEtat() == "SECHE") {
                    panelCels.get(i-1).setBackground(Color.ORANGE);
                    panelCels.get(i-1).setForeground(Color.ORANGE);
                } else if (g.getCases().get(i).getEtat().getEtat() == "INONDEE") {
                    panelCels.get(i-1).setBackground(Color.CYAN);
                    panelCels.get(i-1).setForeground(Color.CYAN);
                } else {
                    panelCels.get(i-1).setBackground(Color.BLUE);
                    panelCels.get(i-1).setForeground(Color.BLUE);
                }
            }
        }
        for (Panel p : this.getDeplacementsPossibles()){
            p.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
        }
        validate();
        repaint();
    }

    public ArrayList<Panel> getDeplacementsPossibles() {
        return deplacementsPossibles;
    }
    
    
}
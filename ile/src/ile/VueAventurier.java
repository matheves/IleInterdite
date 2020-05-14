package ile;
 
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.border.MatteBorder;
 
public class VueAventurier extends Observe {
 
    private JPanel mainPanel;
    private JPanel panelNord;
    private JPanel panelCartes;
    private JPanel panelChoixAction;
    private JPanel panelActionPerso;
    private JPanel panelActionCartes;
    private JPanel panelChoixJoueurs;
    private JPanel panelSouth;
    private CardLayout cardLayout = new CardLayout();
    private ArrayList<Aventurier> joueurs;
    private ArrayList<PanelCarte> cartes;
    private JLabel infoPerso;
    private JButton choixActionsPerso;
    private JButton choixActionCartes;
    private JButton terminerTour;
    private JButton deplacer;
    private JButton assecher;
    private JButton actionSpe;
    private JButton terminerActions;
    private JButton defausser;
    private JButton donner;
    private JButton utiliser;
    private JButton joueur1;
    private JButton joueur2;
    private JButton joueur3;
    private JButton retourMenuPrec1;
    private JButton retourMenuPrec2;
    private JButton retourMenuPrec3;
 
    VueAventurier(String nomJoueur, String typeAventurier, Color couleur, Aventurier aventurier, ArrayList<Aventurier> joueur) {
        mainPanel = new JPanel(new BorderLayout());
        joueurs = new ArrayList<>();
        for(Aventurier a : joueur){
            joueurs.add(a);
        }
       
        //Panel du haut
        panelNord = new JPanel();
        mainPanel.add(panelNord, BorderLayout.NORTH);
        panelNord.setBackground(couleur);
        infoPerso = new JLabel(nomJoueur + "(" + typeAventurier + ")");
        infoPerso.setForeground(Color.WHITE);
        panelNord.add(infoPerso);
        infoPerso.setHorizontalAlignment(panelNord.getWidth() / 2);
        infoPerso.setVerticalAlignment(panelNord.getHeight() / 2);
 
        //Panel Centre
        cartes = new ArrayList<>();
        panelCartes = new JPanel(new GridLayout(1, 6));
        mainPanel.add(panelCartes, BorderLayout.CENTER);
        cartes.add(new PanelCarte("Calice", 0));
        cartes.add(new PanelCarte("Cristal", 0));
        cartes.add(new PanelCarte("Pierre", 0));
        cartes.add(new PanelCarte("Zéphyr", 0));
        cartes.add(new PanelCarte("Hélicoptère", 0));
        cartes.add(new PanelCarte("Sac De Sable", 0));
        Integer cal = 0;
        Integer cris = 0;
        Integer pierre = 0;
        Integer zeph = 0;
        Integer helico = 0;
        Integer sac = 0;
        for (Carte c : aventurier.getMainCarte()) {
            System.out.println("Comptage des cartes");
            if (c instanceof CarteActionSpeciale) {
                if (c.getType() == TypeAcSpe.SACDESABLE) {
                    System.out.println("sac de sable");
                    sac += 1;
                } else {
                    System.out.println("hélico");
                    helico += 1;
                }
            } else if (c instanceof CarteTresor) {
                if (c.getTresor() == Tresor.PIERRE) {
                    System.out.println("pierre");
                    pierre += 1;
                } else if (c.getTresor() == Tresor.ZEPHYR) {
                    System.out.println("zephyr");
                    zeph += 1;
                } else if (c.getTresor() == Tresor.CRISTAL) {
                    System.out.println("cristal");
                    cris += 1;
                } else {
                    System.out.println("calice");
                    cal += 1;
                }
            }
        }
        cartes.get(0).setN(cal);
        cartes.get(0).setNombre(new JLabel(cal.toString()));
        cartes.get(1).setN(cris);
        cartes.get(1).setNombre(new JLabel(cris.toString()));
        cartes.get(2).setN(pierre);
        cartes.get(2).setNombre(new JLabel(pierre.toString()));
        cartes.get(3).setN(zeph);
        cartes.get(3).setNombre(new JLabel(zeph.toString()));
        cartes.get(4).setN(helico);
        cartes.get(4).setNombre(new JLabel(helico.toString()));
        cartes.get(5).setN(sac);
        cartes.get(5).setNombre(new JLabel(sac.toString()));
        for (PanelCarte p : cartes) {
            System.out.println("ajout carte");
            p.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            p.add(p.getNom());
            p.add(p.getNombre());
            panelCartes.add(p);
            p.addMouseListener(
                        new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (p.isClique()) {
                            p.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                            p.setClique(false);
                        } else {
                            p.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
                            p.setClique(true);
                        }
                        Message m = new Message();
                        m.type=TypesMessages.TYPE_CARTE;
                        if (p.getType().equals("Pierre")){
                            CarteTresor c = new CarteTresor(Tresor.PIERRE);
                            m.carte=c;
                            notifierObservateur(m);
                        }
                        else if(p.getType().equals("Calice")){
                            CarteTresor c = new CarteTresor(Tresor.CALICE);
                            m.carte=c;
                            notifierObservateur(m);
                        }
                        else if(p.getType().equals("Cristal")){
                            CarteTresor c = new CarteTresor(Tresor.CRISTAL);
                            m.carte=c;
                            notifierObservateur(m);
                        }
                        else if(p.getType().equals("Zéphyr")){
                            CarteTresor c = new CarteTresor(Tresor.ZEPHYR);
                            m.carte=c;
                            notifierObservateur(m);
                        }
                        else if(p.getType().equals("Hélicoptère")){
                            CarteActionSpeciale c = new CarteActionSpeciale(TypeAcSpe.HELICOPTERE);
                            m.carte=c;
                            notifierObservateur(m);
                        }
                        else if(p.getType().equals("Sac De Sable")){
                            CarteActionSpeciale c = new CarteActionSpeciale(TypeAcSpe.SACDESABLE);  
                            m.carte=c;
                            notifierObservateur(m);
                        }
                       
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
       
 
        //Panel sud choix action perso
        panelSouth = new JPanel(cardLayout);
     
        panelActionPerso = new JPanel(new GridLayout(1, 5));
        deplacer = new JButton("Deplacer");
        deplacer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Message m = new Message();
                m.s = "deplacer";
                m.type = TypesMessages.TYPE_ACTION;
                m.nomAventurier = typeAventurier;
                System.out.println(m.s + " vueAventurier");
                notifierObservateur(m);
            }
        });
        assecher = new JButton("Assecher");
        assecher.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Message m = new Message();
                m.s = "assecher";
                m.type = TypesMessages.TYPE_ACTION;
                m.nomAventurier = typeAventurier;
                notifierObservateur(m);
            }
        });
        actionSpe = new JButton("Action spé");
        actionSpe.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Message m = new Message();
                m.type = TypesMessages.TYPE_ACTION;
                m.s = "actionSpeciale";
                notifierObservateur(m);
            }
        });
        terminerActions = new JButton("Terminé act");
        terminerActions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Message m = new Message();
                m.type = TypesMessages.TYPE_FINAC;
                notifierObservateur(m);
            }
        });
        retourMenuPrec1 = new JButton("retour");
        retourMenuPrec1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelSouth, "4");
                validate();
                repaint();
            }
        });
        panelActionPerso.add(deplacer);
        panelActionPerso.add(assecher);
        panelActionPerso.add(actionSpe);
        panelActionPerso.add(terminerActions);
        panelActionPerso.add(retourMenuPrec1);
        panelSouth.add(panelActionPerso, "1");
 
        //Panel choix action carte
        panelActionCartes = new JPanel(new GridLayout(1, 4));
        defausser = new JButton("Défausser");
        defausser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Message m = new Message();
                m.type = TypesMessages.TYPE_ACTION;
                m.s = "defausser";
                notifierObservateur(m);
            }
        });
        donner = new JButton("Donner");
        donner.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelSouth, "3");
                validate();
                repaint();
                Message m = new Message();
                m.type = TypesMessages.TYPE_ACTION;
                m.s = "donner";
                notifierObservateur(m); 
            }
        });
        utiliser = new JButton("Utiliser");
        utiliser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Message m = new Message();
                m.type = TypesMessages.TYPE_ACTION;
                m.s = "utiliser";
                notifierObservateur(m);
            }
        });
        retourMenuPrec2 = new JButton("Retour");
        retourMenuPrec2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelSouth, "4");
                validate();
                repaint();
            }
        });
        panelActionCartes.add(defausser);
        panelActionCartes.add(donner);
        panelActionCartes.add(utiliser);
        panelActionCartes.add(retourMenuPrec2);
        panelSouth.add(panelActionCartes, "2");
       
        //Panel choix joueurs
        for (Aventurier a : joueurs) {
            System.out.println(a.getPion().toString());
        }
        System.out.println("");
        joueurs.remove(aventurier);
        panelChoixJoueurs = new JPanel(new GridLayout(1, 4));
        joueur1 = new JButton(joueurs.get(0).getClass().getSimpleName());
        joueur1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                Message m = new Message();
                m.type = TypesMessages.TYPE_JOUEUR;
                m.aventurier=joueurs.get(0);
                notifierObservateur(m);
                
            }
        });
        
        joueur2 = new JButton(joueurs.get(1).getClass().getSimpleName());
        joueur2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Message m = new Message();
                m.type = TypesMessages.TYPE_JOUEUR;
                m.aventurier=joueurs.get(1);
                notifierObservateur(m);
                
            }
        });
        joueur3 = new JButton(joueurs.get(2).getClass().getSimpleName());
        joueur3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Message m = new Message();
                m.type = TypesMessages.TYPE_JOUEUR;
                m.aventurier = joueurs.get(2);
                notifierObservateur(m);
                
            }
        });
        retourMenuPrec3 = new JButton("Retour");
        
        retourMenuPrec3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelSouth, "2");
                validate();
                repaint();
                
            }
        });
        panelChoixJoueurs.add(joueur1);
        panelChoixJoueurs.add(joueur2);
        panelChoixJoueurs.add(joueur3);
        panelChoixJoueurs.add(retourMenuPrec3);
        panelSouth.add(panelChoixJoueurs, "3");
 
        //Panel sud Choix Action
        panelChoixAction = new JPanel(new GridLayout(1, 3));
        mainPanel.add(panelChoixAction, BorderLayout.SOUTH);
        choixActionsPerso = new JButton("Actions perso");
        choixActionsPerso.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelSouth, "1");
                validate();
                repaint();
            }
        });
        choixActionCartes = new JButton("Actions carte");
        choixActionCartes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelSouth, "2");
                validate();
                repaint();
            }
        });
        terminerTour = new JButton("terminer tour");
        terminerTour.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Message m = new Message();
                m.type = TypesMessages.TYPE_ANNULER;
                notifierObservateur(m);
            }
        });
        panelChoixAction.add(choixActionsPerso);
        panelChoixAction.add(choixActionCartes);
        panelChoixAction.add(terminerTour);
        panelSouth.add(panelChoixAction,"4");
       
        cardLayout.show(panelSouth,"4");
        mainPanel.add(panelSouth, BorderLayout.SOUTH);
       
       
    }
 
    public JPanel getMainPanel() {
        return mainPanel;
    }
   
}
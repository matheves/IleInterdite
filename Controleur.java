package ile;

import ile.Grille;
import java.awt.Color;
import java.util.*;

public class Controleur implements Observateur {

    private static VuePlateau vuePlat;
    private static VueAventurier joueur1, joueur2, joueur3, joueur4;
    private static ArrayList<Aventurier> joueurs;
    private ArrayList<CarteTresor> pileTresor;
    private ArrayList<CarteInondation> pileInondation;
    private static Grille grille;
    private int niveauEau = 0;
    private String etat;

    private int i = 0;
    private Aventurier avActuel;
    private int numact = 3;
    private int ingeAss = 0;
    private boolean acSpe = false;
    private static PiocheCartes pioche = new PiocheCartes();

    Controleur() {
        grille = new Grille();
        joueurs = new ArrayList<>();
        initTuile(grille);
        createListAventurier(grille, joueurs);
        avActuel = joueurs.get(i);
        vuePlat = new VuePlateau(grille);
        joueur1 = new VueAventurier("Yannis", "Explorateur", Color.GREEN);
        joueur2 = new VueAventurier("Tom", "Ingenieur", Color.RED);
        joueur3 = new VueAventurier("Arthur", "Pilote", Color.BLUE);
        joueur4 = new VueAventurier("Samuel", "Messager", Color.GRAY);
    }

    public static void createListAventurier(Grille g, ArrayList<Aventurier> res) {
        Explorateur explo = new Explorateur(new Pion("VERT"), g.getCases().get(17), pioche);
        g.getCases().get(17).getNbJoueursCase().add(explo);
        res.add(explo);
        Ingenieur inge = new Ingenieur(new Pion("ROUGE"), g.getCases().get(4), pioche);
        g.getCases().get(4).getNbJoueursCase().add(inge);
        res.add(inge);
        Pilote pilote = new Pilote(new Pion("BLEU"), g.getCases().get(16), pioche);
        g.getCases().get(16).getNbJoueursCase().add(pilote);
        res.add(pilote);
        Messager messager = new Messager(new Pion("GRIS"), g.getCases().get(14), pioche);
        g.getCases().get(14).getNbJoueursCase().add(messager);
        res.add(messager);
    }

    //<editor-fold defaultstate="collapsed" desc="initTuile">
    public static void initTuile(Grille g) {
        Tuile t1 = new Tuile(new Etat("SOMBREE"), 1, 1);
        g.getCases().put(1, t1);
        Tuile t2 = new Tuile(new Etat("SOMBREE"), 2, 1);
        g.getCases().put(2, t2);
        Tuile t3 = new Tuile(3, "Le Pont Des Abimes", 3, 1, new Etat("SECHE"));
        g.getCases().put(3, t3);
        Tuile t4 = new Tuile(4, "La Porte De Bronze", 4, 1, new Etat("INONDEE"));
        g.getCases().put(4, t4);
        Tuile t5 = new Tuile(new Etat("SOMBREE"), 5, 1);
        g.getCases().put(5, t5);
        Tuile t6 = new Tuile(new Etat("SOMBREE"), 6, 1);
        g.getCases().put(6, t6);
        Tuile t7 = new Tuile(new Etat("SOMBREE"), 1, 2);
        g.getCases().put(7, t7);
        Tuile t8 = new Tuile(8, "La Caverne Des Ombres", 2, 2, new Etat("SECHE"));
        g.getCases().put(8, t8);
        Tuile t9 = new Tuile(9, "La Porte De Fer", 3, 2, new Etat("SECHE"));
        g.getCases().put(9, t9);
        Tuile t10 = new Tuile(10, "La Porte d'Or", 4, 2, new Etat("INONDEE"));
        g.getCases().put(10, t10);
        Tuile t11 = new Tuile(11, "Les falaises De L'Oubli", 5, 2, new Etat("SECHE"));
        g.getCases().put(11, t11);
        Tuile t12 = new Tuile(new Etat("SOMBREE"), 6, 2);
        g.getCases().put(12, t12);
        Tuile t13 = new Tuile(13, "Le Palais De Corail", 1, 3, new Etat("SECHE"));
        g.getCases().put(13, t13);
        Tuile t14 = new Tuile(14, "La Porte d'Argent", 2, 3, new Etat("SECHE"));
        g.getCases().put(14, t14);
        Tuile t15 = new Tuile(15, "Les Dunes De L'Illusion", 3, 3, new Etat("SOMBREE"));
        g.getCases().put(15, t15);
        Tuile t16 = new Tuile(16, "Heliport", 4, 3, new Etat("SECHE"));
        g.getCases().put(16, t16);
        Tuile t17 = new Tuile(17, "La Porte De Cuivre", 5, 3, new Etat("SECHE"));
        g.getCases().put(17, t17);
        Tuile t18 = new Tuile(18, "Le Jardin Des Hurlements", 6, 3, new Etat("SECHE"));
        g.getCases().put(18, t18);
        Tuile t19 = new Tuile(19, "La Foret Pourpre", 1, 4, new Etat("SECHE"));
        g.getCases().put(19, t19);
        Tuile t20 = new Tuile(20, "Le Lagon Perdu", 2, 4, new Etat("INONDEE"));
        g.getCases().put(20, t20);
        Tuile t21 = new Tuile(21, "Le Marais Brumeux", 3, 4, new Etat("SOMBREE"));
        g.getCases().put(21, t21);
        Tuile t22 = new Tuile(22, "Observatoire", 4, 4, new Etat("INONDEE"));
        g.getCases().put(22, t22);
        Tuile t23 = new Tuile(23, "Le Rocher Fantome", 5, 4, new Etat("SOMBREE"));
        g.getCases().put(23, t23);
        Tuile t24 = new Tuile(24, "La Caverne Du Brasier", 6, 4, new Etat("INONDEE"));
        g.getCases().put(24, t24);
        Tuile t25 = new Tuile(new Etat("SOMBREE"), 1, 5);
        g.getCases().put(25, t25);
        Tuile t26 = new Tuile(26, "Le Temple Du Soleil", 2, 5, new Etat("SECHE"));
        g.getCases().put(26, t26);
        Tuile t27 = new Tuile(27, "Le Temple De La Lune", 3, 5, new Etat("SOMBREE"));
        g.getCases().put(27, t27);
        Tuile t28 = new Tuile(28, "Le Palais Des Marees", 4, 5, new Etat("SECHE"));
        g.getCases().put(28, t28);
        Tuile t29 = new Tuile(29, "Le Val Du Crepuscule", 5, 5, new Etat("SECHE"));
        g.getCases().put(29, t29);
        Tuile t30 = new Tuile(new Etat("SOMBREE"), 6, 5);
        g.getCases().put(30, t30);
        Tuile t31 = new Tuile(new Etat("SOMBREE"), 1, 6);
        g.getCases().put(31, t31);
        Tuile t32 = new Tuile(new Etat("SOMBREE"), 2, 6);
        g.getCases().put(32, t32);
        Tuile t33 = new Tuile(33, "La Tour Du Guet", 3, 6, new Etat("SECHE"));
        g.getCases().put(33, t33);
        Tuile t34 = new Tuile(34, "Le Jardin Des Murmures", 4, 6, new Etat("INONDEE"));
        g.getCases().put(34, t34);
        Tuile t35 = new Tuile(new Etat("SOMBREE"), 5, 6);
        g.getCases().put(35, t35);
        Tuile t36 = new Tuile(new Etat("SOMBREE"), 6, 6);
        g.getCases().put(36, t36);
    }
//</editor-fold>

    public void traiterMessage(Message message) {
        switch (message.type) {
            case TYPE_ACTION:
                etat = message.s;
                /*
                for(int i = 0; i < joueurs.size(); i++) {
                    if(message.nomAventurier.equals(joueurs.get(i).getClass().getSimpleName())) {
                        vuePlat.selectionnerUneCase(joueurs.get(i).getCasesPossibles(grille));
                    }
                }
                 */
                break;

            case TYPE_TUILE:
                if (etat == "deplacer" && numact > 0) {
                    if (avActuel.deplacer(message.t, grille)) {
                        numact = numact - 1;
                        if (avActuel.getClass() == Ingenieur.class && ingeAss == 1) {
                            numact = numact - 1;
                            ingeAss = 0;

                        }
                    }

                } else if (etat == "assecher" && numact > 0) {
                    if (avActuel.getClass() == Ingenieur.class) {
                        if (avActuel.assecher(message.t, grille)) {
                            if (ingeAss == 1) {
                                ingeAss = 0;
                                numact = numact - 1;
                            } else if (ingeAss == 0) {
                                ingeAss = 1;
                            }
                        }

                    } else {
                        if (avActuel.assecher(message.t, grille)) {
                            numact = numact - 1;
                        }
                    };

                } //Changer le nombre d'actions pour prendre en compte le traitement
                else if (etat == "actionSpeciale" && numact > 0) {
                    if (avActuel instanceof Pilote && acSpe == false) {
                        avActuel.teleportation(message.t, grille);
                        numact = numact - 1;
                        acSpe = true;

                    }
                } else {
                    System.out.println("plus d'action");
                }
                break;
            case TYPE_ANNULER:

                System.out.println("terminer");
                System.out.println("changement joueur");
                numact = 3;
                acSpe = false;
                if (i < joueurs.size() - 1) {
                    System.out.println(joueurs.size());
                    i = i + 1;
                    System.out.println(i);
                    avActuel = joueurs.get(i);
                    if (avActuel.getClass() == Navigateur.class) {
                        numact = 4;
                    }
                } else {
                    i = 0;
                    niveauEau = niveauEau + 1;
                    avActuel = joueurs.get(i);
                    System.out.println(i);
                    if (avActuel.getClass() == Navigateur.class) {
                        numact = 4;
                    }
                }
                break;

        }
        vuePlat.update(grille);
        vuePlat.repaint();
        joueur1.repaint();
        joueur2.repaint();
        joueur3.repaint();
        joueur4.repaint();
        System.out.println("Case actuelle de l'aventurier : " + avActuel.getCasePos().getNom() + "\n\n");

    }

    public static VueAventurier getJoueur1() {
        return joueur1;
    }

    public static void setJoueur1(VueAventurier joueur1) {
        Controleur.joueur1 = joueur1;
    }

    public static VueAventurier getJoueur2() {
        return joueur2;
    }

    public static void setJoueur2(VueAventurier joueur2) {
        Controleur.joueur2 = joueur2;
    }

    public static VueAventurier getJoueur3() {
        return joueur3;
    }

    public static void setJoueur3(VueAventurier joueur3) {
        Controleur.joueur3 = joueur3;
    }

    public static VueAventurier getJoueur4() {
        return joueur4;
    }

    public static void setJoueur4(VueAventurier joueur4) {
        Controleur.joueur4 = joueur4;
    }

    public static ArrayList<Aventurier> getJoueurs() {
        return joueurs;
    }

    public static void setJoueurs(ArrayList<Aventurier> joueurs) {
        Controleur.joueurs = joueurs;
    }

    public static Grille getGrille() {
        return grille;
    }

    public static void setGrille(Grille grille) {
        Controleur.grille = grille;
    }

    public static VuePlateau getVuePlat() {
        return vuePlat;
    }

    public static void setVuePlat(VuePlateau vuePlat) {
        Controleur.vuePlat = vuePlat;
    }

}

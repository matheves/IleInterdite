package ile;

import java.awt.Color;
import java.util.*;

public class Controleur implements Observateur {

    //Génération du plateau
    private static VuePlateau vuePlat;
    private static VueAventurier joueur1, joueur2, joueur3, joueur4;
    private static VueNiveauEau nivEau;
    private static VueJeu jeu;
    private ArrayList<VueAventurier> vuesAventuriers;
    private static ArrayList<Aventurier> joueurs;
    private static HashMap<Tresor, Boolean> tresors;
    private static Grille grille;
    private static int niveauEau = 1;
    private String etat;
    //Joueurs
    private Plongeur plongeur;
    private Ingenieur ingenieur;
    private Explorateur explorateur;
    private Messager messager;
    private Pilote pilote;
    private Navigateur navigateur;
    //Variables du plateau
    private int x, w = 0;
    private int numact = 3;
    private int ingeAss = 0;
    private boolean acSpe = false;
    private static Aventurier avActuel;
    private static Aventurier avSelec;
    private static PiocheCartes pioche = new PiocheCartes();
    private static PiocheInondation piocheInondation;
    private Defausse defausseCartes = new Defausse();
    private Defausse defausseCartesInondation = new Defausse();
    private Carte carteSelec;

    Controleur() {
        grille = new Grille();
        joueurs = new ArrayList<>();
        vuesAventuriers = new ArrayList<>();
        tresors = new HashMap<>();
        initListTresors(tresors);
        initTuile(grille);
        plongeur = new Plongeur(new Pion("NOIR"), grille.getCases().get(17), pioche);
        grille.getCases().get(17).getNbJoueursCase().add(plongeur);
        joueurs.add(plongeur);
        ingenieur = new Ingenieur(new Pion("ROUGE"), grille.getCases().get(4), pioche);
        grille.getCases().get(4).getNbJoueursCase().add(ingenieur);
        joueurs.add(ingenieur);
        pilote = new Pilote(new Pion("BLEU"), grille.getCases().get(16), pioche);
        grille.getCases().get(16).getNbJoueursCase().add(pilote);
        joueurs.add(pilote);
        pilote.addCarte(new CarteTresor(Tresor.CALICE));
        pilote.addCarte(new CarteTresor(Tresor.CALICE));
        pilote.addCarte(new CarteTresor(Tresor.CALICE));
        pilote.addCarte(new CarteTresor(Tresor.CALICE));
        messager = new Messager(new Pion("GRIS"), grille.getCases().get(14), pioche);
        grille.getCases().get(14).getNbJoueursCase().add(messager);
        joueurs.add(messager);
        avActuel = joueurs.get(x);
        vuePlat = new VuePlateau(grille);
        joueur1 = new VueAventurier("Yannis", "Plongeur", Color.BLACK, plongeur, joueurs);
        vuesAventuriers.add(joueur1);
        joueur2 = new VueAventurier("Tom", "Ingenieur", Color.RED, ingenieur, joueurs);
        vuesAventuriers.add(joueur2);
        joueur3 = new VueAventurier("Arthur", "Pilote", Color.BLUE, pilote, joueurs);
        vuesAventuriers.add(joueur3);
        joueur4 = new VueAventurier("Samuel", "Messager", Color.GRAY, messager, joueurs);
        vuesAventuriers.add(joueur4);
        nivEau = new VueNiveauEau();
        nivEau.setNiveauEau(1);
        jeu = new VueJeu(vuePlat, nivEau, vuesAventuriers);
        vuePlat.getEtat().setText("C'est à " + joueurs.get(0).getClass().getSimpleName() + " de jouer.");
        activerBtn();
    }

    public static void createListAventurier(Grille g, ArrayList<Aventurier> res) {
        Plongeur plongeur = new Plongeur(new Pion("NOIR"), g.getCases().get(17), pioche);
        g.getCases().get(17).getNbJoueursCase().add(plongeur);
        res.add(plongeur);
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

    public static void initListTresors(HashMap<Tresor, Boolean> tresors) {
        tresors.put(Tresor.CALICE, Boolean.FALSE);
        tresors.put(Tresor.ZEPHYR, Boolean.FALSE);
        tresors.put(Tresor.PIERRE, Boolean.FALSE);
        tresors.put(Tresor.CRISTAL, Boolean.FALSE);
    }

    //<editor-fold defaultstate="collapsed" desc="initTuile">
    public static void initTuile(Grille g) {
        Tuile t1 = new Tuile(new Etat("null"), 1, 1);
        g.getCases().put(1, t1);
        Tuile t2 = new Tuile(new Etat("null"), 2, 1);
        g.getCases().put(2, t2);
        Tuile t3 = new Tuile(3, "Le Pont Des Abimes", 3, 1, new Etat("SECHE"));
        g.getCases().put(3, t3);
        Tuile t4 = new Tuile(4, "La Porte De Bronze", 4, 1, new Etat("INONDEE"));
        g.getCases().put(4, t4);
        Tuile t5 = new Tuile(new Etat("null"), 5, 1);
        g.getCases().put(5, t5);
        Tuile t6 = new Tuile(new Etat("null"), 6, 1);
        g.getCases().put(6, t6);
        Tuile t7 = new Tuile(new Etat("null"), 1, 2);
        g.getCases().put(7, t7);
        Tuile t8 = new Tuile(8, "La Carverne Des Ombres", 2, 2, new Etat("SECHE"));
        g.getCases().put(8, t8);
        Tuile t9 = new Tuile(9, "La Porte De Fer", 3, 2, new Etat("SECHE"));
        g.getCases().put(9, t9);
        Tuile t10 = new Tuile(10, "La Porte dOr", 4, 2, new Etat("INONDEE"));
        g.getCases().put(10, t10);
        Tuile t11 = new Tuile(11, "Les Falaises De LOubli", 5, 2, new Etat("SECHE"));
        g.getCases().put(11, t11);
        Tuile t12 = new Tuile(new Etat("null"), 6, 2);
        g.getCases().put(12, t12);
        Tuile t13 = new Tuile(13, "Le Palais De Corail", 1, 3, new Etat("SECHE"));
        g.getCases().put(13, t13);
        Tuile t14 = new Tuile(14, "La Porte dArgent", 2, 3, new Etat("SECHE"));
        g.getCases().put(14, t14);
        Tuile t15 = new Tuile(15, "Les Dunes De LIllusion", 3, 3, new Etat("SOMBREE"));
        g.getCases().put(15, t15);
        Tuile t16 = new Tuile(16, "Heliport", 4, 3, new Etat("SECHE"));
        g.getCases().put(16, t16);
        Tuile t17 = new Tuile(17, "La Porte De Cuivre", 5, 3, new Etat("INONDEE"));
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
        Tuile t24 = new Tuile(24, "La Carverne Du Brasier", 6, 4, new Etat("INONDEE"));
        g.getCases().put(24, t24);
        Tuile t25 = new Tuile(new Etat("null"), 1, 5);
        g.getCases().put(25, t25);
        Tuile t26 = new Tuile(26, "Le Temple Du Soleil", 2, 5, new Etat("SECHE"));
        g.getCases().put(26, t26);
        Tuile t27 = new Tuile(27, "Le Temple De La Lune", 3, 5, new Etat("SOMBREE"));
        g.getCases().put(27, t27);
        Tuile t28 = new Tuile(28, "Le Palais Des Marees", 4, 5, new Etat("SECHE"));
        g.getCases().put(28, t28);
        Tuile t29 = new Tuile(29, "Le Val Du Crepuscule", 5, 5, new Etat("SECHE"));
        g.getCases().put(29, t29);
        Tuile t30 = new Tuile(new Etat("null"), 6, 5);
        g.getCases().put(30, t30);
        Tuile t31 = new Tuile(new Etat("null"), 1, 6);
        g.getCases().put(31, t31);
        Tuile t32 = new Tuile(new Etat("null"), 2, 6);
        g.getCases().put(32, t32);
        Tuile t33 = new Tuile(33, "La Tour Du Guet", 3, 6, new Etat("SECHE"));
        g.getCases().put(33, t33);
        Tuile t34 = new Tuile(34, "Le Jardin Des Murmures", 4, 6, new Etat("INONDEE"));
        g.getCases().put(34, t34);
        Tuile t35 = new Tuile(new Etat("null"), 5, 6);
        g.getCases().put(35, t35);
        Tuile t36 = new Tuile(new Etat("null"), 6, 6);
        g.getCases().put(36, t36);
        piocheInondation = new PiocheInondation(grille);
    }
//</editor-fold>

    public void traiterMessage(Message message) {
        switch (message.type) {
            case TYPE_ACTION:
                // <editor-fold defaultstate="collapsed" desc="Switch 1 TYPE_ACTION">
                vuePlat.update(grille);
                etat = message.s;

                if (etat.equals("deplacer") && numact != 0) {
                    for (int k = 0; k < joueurs.size(); k++) {
                        if (message.nomAventurier.equals(joueurs.get(k).getClass().getSimpleName())) {
                            vuePlat.selectionnerUneCase(joueurs.get(k).getCasesPossibles(grille));
                        }
                    }

                } else if (etat.equals("assecher") && numact != 0) {
                    for (int k = 0; k < joueurs.size(); k++) {
                        if (message.nomAventurier.equals(joueurs.get(k).getClass().getSimpleName())) {
                            if (joueurs.get(k).getClass().getSimpleName().equals("Explorateur")) {
                                vuePlat.selectionnerUneCase(grille.getCasesAssechementExplorateur((joueurs.get(k)).getPosition()));

                            } else {
                                vuePlat.selectionnerUneCase(grille.getCasesAssechement((joueurs.get(k)).getPosition()));
                            }
                        }
                    }

                } else if (etat.equals("actionSpeciale") && numact != 0) {
                    if (avActuel instanceof Pilote && acSpe == false) {
                        vuePlat.selectionnerUneCase(grille.getCases());
                    }
                } else if (numact == 0) {
                    actFinit();
                }

                vuePlat.repaint();
                break;
// </editor-fold>
//
            case TYPE_TUILE:
                // <editor-fold defaultstate="collapsed" desc="Switch 2 TYPE_TUILE">
                if ("deplacer" == (etat) && numact > 0 && avActuel.getMainCarte().size() < 6) {
                    if (avActuel.deplacer(message.t, grille)) {
                        numact = numact - 1;
                        vuePlat.getEtat().setText("Il vous reste " + numact + " action(s).");

                        if (avActuel.getClass() == Ingenieur.class && ingeAss == 1) {
                            numact = numact - 1;
                            vuePlat.getEtat().setText("Il vous reste " + numact + " action(s).");
                            ingeAss = 0;
                        }
                    }

                } else if ("assecher" == (etat) && numact > 0 && avActuel.getMainCarte().size() < 6) {
                    if (avActuel.getClass() == Ingenieur.class) {
                        if (avActuel.assecher(message.t, grille)) {
                            if (ingeAss == 1) {
                                ingeAss = 0;
                                numact = numact - 1;
                                vuePlat.getEtat().setText("Il vous reste " + numact + " action(s).");

                            } else if (ingeAss == 0) {
                                ingeAss = 1;
                            }
                        }

                    } else {
                        if (avActuel.assecher(message.t, grille)) {
                            numact = numact - 1;
                            vuePlat.getEtat().setText("Il vous reste " + numact + " action(s).");
                        }
                    }

                    //Changer le nombre d'actions pour prendre en compte le traitement
                } else if ("actionSpeciale" == (etat) && numact > 0 && avActuel.getMainCarte().size() < 6) {
                    if (avActuel instanceof Pilote && acSpe == false) {
                        actSpeFinit();
                        avActuel.teleportation(message.t, grille);
                        numact = numact - 1;
                        vuePlat.getEtat().setText("Il vous reste " + numact + " action(s).");
                        acSpe = true;
                    }

                } else if ("sandbag" == (etat)) {
                    if (message.t.getEtat().etat == ("INONDEE")) {
                        Set<Map.Entry<Integer, Tuile>> set = grille.getCases().entrySet();
                        Iterator<Map.Entry<Integer, Tuile>> it = set.iterator();
                        Tuile tu = new Tuile();

                        while (it.hasNext()) {
                            Map.Entry<Integer, Tuile> val = it.next();
                            tu = val.getValue();

                            if (tu.getNom() == message.t.getNom()) {
                                tu.setEtat(new Etat("SECHE"));
                                System.out.println(tu.getNom() + " | " + tu.getEtat());
                            }
                        }

                        int j = 0;
                        boolean def = false;

                        while (j < avActuel.getMainCarte().size() && def == false) {
                            if (avActuel.getMainCarte().get(j).getType() == TypeAcSpe.SACDESABLE) {
                                defausseCartes.addCarte(avActuel.getMainCarte().get(j));
                                avActuel.getMainCarte().remove(j);
                                def = true;
                            }
                            j = j + 1;
                        }

                        vuePlat.validate();
                        vuePlat.repaint();
                        etat = null;

                    } else {
                        System.out.println("cette case ne peut pas être sechée");
                    }

                    if (avActuel.getMainCarte().size() < 6) {
                        activerBtn();
                    }

                } else if (etat == ("helicoptere")) {
                    // avActuel se déplace sur la tuile message.t
                    if (message.t.getEtat().etat == ("SOMBREE")) {
                        System.out.println("Tuile sombrée.");

                    } else {

                        int j = 0;
                        boolean def = false;

                        while (j < avActuel.getMainCarte().size() && def == false) {
                            if (avActuel.getMainCarte().get(j).getType() == TypeAcSpe.HELICOPTERE) {
                                defausseCartes.addCarte(avActuel.getMainCarte().get(j));
                                avActuel.getMainCarte().remove(j);
                                ArrayList<Tuile> resAl = new ArrayList<>(grille.getCases().values());

                                for (int i = 0; i < resAl.size(); i++) {
                                    if (message.t == resAl.get(i)) {
                                        avActuel.getCasePos().getNbJoueursCase().remove(avActuel);
                                        avActuel.setCasePos(message.t);
                                        avActuel.getCasePos().getNbJoueursCase().add(avActuel);
                                    }
                                }
                                def = true;
                            }
                            j = j + 1;
                        }

                        vuePlat.validate();
                        vuePlat.repaint();
                        etat = null;
                    }

                    if (avActuel.getMainCarte().size() < 6) {
                        activerBtn();
                    }

                } else {
                    System.out.println("plus d'action");
                }

                vuePlat.update(grille);
                break;
// </editor-fold>
//
            case TYPE_ANNULER:
                //<editor-fold defaultstate="collapsed" desc="Switch 3 TYPE_ANNULER">
                System.out.println("cartes inondations");
                /* 1-2 = 2 cartes
                3-5 = 3 cartes
                6-7 = 4 cartes
                8-9 = 5 cartes
                10 = fin de la partie */

                //Début traitement de niveau
                if (niveauEau < 3) {
                    for (int i = 0; i < 2; i++) {
                        if (!piocheInondation.getP().isEmpty() || !defausseCartesInondation.getD().isEmpty()) {
                            if (piocheInondation.getP().isEmpty()) {
                                piocheInondation.remplir(defausseCartesInondation);
                            }

                            if (piocheInondation.getP().get(0).getTuile().getEtat().etat == ("SECHE")) {
                                System.out.println("la case etait seche");
                                Set<Map.Entry<Integer, Tuile>> set = grille.getCases().entrySet();
                                Iterator<Map.Entry<Integer, Tuile>> it = set.iterator();
                                Tuile t = new Tuile();

                                while (it.hasNext()) {
                                    Map.Entry<Integer, Tuile> val = it.next();
                                    t = val.getValue();

                                    if (t.getNom() == piocheInondation.getP().get(0).getTuile().getNom()) {
                                        t.setEtat(new Etat("INONDEE"));
                                        System.out.println(t.getNom() + " " + t.getEtat().etat);
                                    }
                                }

                            } else if (piocheInondation.getP().get(0).getTuile().getEtat().etat == ("INONDEE")) {
                                System.out.println("la case etait inondée");
                                Set<Map.Entry<Integer, Tuile>> set = grille.getCases().entrySet();
                                Iterator<Map.Entry<Integer, Tuile>> it = set.iterator();
                                Tuile t = new Tuile();

                                while (it.hasNext()) {
                                    Map.Entry<Integer, Tuile> val = it.next();
                                    t = val.getValue();

                                    if (t.getNom() == piocheInondation.getP().get(0).getTuile().getNom()) {
                                        t.setEtat(new Etat("SOMBREE"));
                                        System.out.println(t.getNom() + " " + t.getEtat().etat);
                                    }
                                }
                            }

                            if (piocheInondation.getP().get(0).getTuile().getEtat().etat == ("INONDEE")) {
                                defausseCartesInondation.addCarte(piocheInondation.getP().get(0));
                            }
                            piocheInondation.getP().remove(0);
                            vuePlat.update(grille);
                        }
                    }

                    //Changement de traitement de niveau
                } else if (niveauEau < 6) {
                    for (int i = 0; i < 3; i++) {
                        if (!piocheInondation.getP().isEmpty() || !defausseCartesInondation.getD().isEmpty()) {
                            if (piocheInondation.getP().isEmpty()) {
                                piocheInondation.remplir(defausseCartesInondation);
                            }

                            if (piocheInondation.getP().get(0).getTuile().getEtat().etat == ("SECHE")) {
                                System.out.println("la case etait seche");
                                Set<Map.Entry<Integer, Tuile>> set = grille.getCases().entrySet();
                                Iterator<Map.Entry<Integer, Tuile>> it = set.iterator();
                                Tuile t = new Tuile();

                                while (it.hasNext()) {
                                    Map.Entry<Integer, Tuile> val = it.next();
                                    t = val.getValue();

                                    if (t.getNom() == piocheInondation.getP().get(0).getTuile().getNom()) {
                                        t.setEtat(new Etat("INONDEE"));
                                        System.out.println(t.getNom() + " " + t.getEtat().etat);
                                    }
                                }

                            } else if (piocheInondation.getP().get(0).getTuile().getEtat().etat == ("INONDEE")) {
                                System.out.println("la case etait inondée");
                                Set<Map.Entry<Integer, Tuile>> set = grille.getCases().entrySet();
                                Iterator<Map.Entry<Integer, Tuile>> it = set.iterator();
                                Tuile t = new Tuile();

                                while (it.hasNext()) {
                                    Map.Entry<Integer, Tuile> val = it.next();
                                    t = val.getValue();

                                    if (t.getNom() == (piocheInondation.getP().get(0).getTuile().getNom())) {
                                        t.setEtat(new Etat("SOMBREE"));
                                        System.out.println(t.getNom() + " " + t.getEtat().etat);
                                    }
                                }

                            } else {
                                System.out.println("la case etait sombrée");
                            }
                            if (piocheInondation.getP().get(0).getTuile().getEtat().etat == ("INONDEE")) {
                                defausseCartesInondation.addCarte(piocheInondation.getP().get(0));
                            }

                            piocheInondation.getP().remove(0);
                            vuePlat.update(grille);
                        }
                    }

                    //Changement de traitement de niveau
                } else if (niveauEau < 8) {
                    for (int i = 0; i < 4; i++) {
                        if (!piocheInondation.getP().isEmpty() || !defausseCartesInondation.getD().isEmpty()) {
                            if (piocheInondation.getP().isEmpty()) {
                                piocheInondation.remplir(defausseCartesInondation);
                            }

                            if (piocheInondation.getP().get(0).getTuile().getEtat().etat == ("SECHE")) {
                                System.out.println("la case etait seche");
                                Set<Map.Entry<Integer, Tuile>> set = grille.getCases().entrySet();
                                Iterator<Map.Entry<Integer, Tuile>> it = set.iterator();
                                Tuile t = new Tuile();

                                while (it.hasNext()) {
                                    Map.Entry<Integer, Tuile> val = it.next();
                                    t = val.getValue();

                                    if (t.getNom() == (piocheInondation.getP().get(0).getTuile().getNom())) {
                                        t.setEtat(new Etat("INONDEE"));
                                        System.out.println(t.getNom() + " " + t.getEtat().etat);
                                    }
                                }

                            } else if (piocheInondation.getP().get(0).getTuile().getEtat().etat == ("INONDEE")) {
                                System.out.println("la case etait inondée");
                                Set<Map.Entry<Integer, Tuile>> set = grille.getCases().entrySet();
                                Iterator<Map.Entry<Integer, Tuile>> it = set.iterator();
                                Tuile t = new Tuile();

                                while (it.hasNext()) {
                                    Map.Entry<Integer, Tuile> val = it.next();
                                    t = val.getValue();

                                    if (t.getNom() == (piocheInondation.getP().get(0).getTuile().getNom())) {
                                        t.setEtat(new Etat("SOMBREE"));
                                        System.out.println(t.getNom() + " " + t.getEtat().etat);
                                    }
                                }

                            } else {
                                System.out.println("la case etait sombrée");
                            }

                            if (piocheInondation.getP().get(0).getTuile().getEtat().etat == ("INONDEE")) {
                                defausseCartesInondation.addCarte(piocheInondation.getP().get(0));
                            }
                            piocheInondation.getP().remove(0);
                            vuePlat.update(grille);
                        }
                    }

                    //Changement de traitement de niveau
                } else if (niveauEau < 10) {
                    for (int i = 0; i < 5; i++) {
                        if (!piocheInondation.getP().isEmpty() || !defausseCartesInondation.getD().isEmpty()) {
                            if (piocheInondation.getP().isEmpty()) {
                                piocheInondation.remplir(defausseCartesInondation);
                            }

                            if (piocheInondation.getP().get(0).getTuile().getEtat().etat == ("SECHE")) {
                                System.out.println("la case etait seche");
                                Set<Map.Entry<Integer, Tuile>> set = grille.getCases().entrySet();
                                Iterator<Map.Entry<Integer, Tuile>> it = set.iterator();
                                Tuile t = new Tuile();

                                while (it.hasNext()) {
                                    Map.Entry<Integer, Tuile> val = it.next();
                                    t = val.getValue();

                                    if (t.getNom() == (piocheInondation.getP().get(0).getTuile().getNom())) {
                                        t.setEtat(new Etat("INONDEE"));
                                        System.out.println(t.getNom() + " " + t.getEtat().etat);
                                    }
                                }

                            } else if (piocheInondation.getP().get(0).getTuile().getEtat().etat == ("INONDEE")) {
                                System.out.println("la case etait inondée");
                                Set<Map.Entry<Integer, Tuile>> set = grille.getCases().entrySet();
                                Iterator<Map.Entry<Integer, Tuile>> it = set.iterator();
                                Tuile t = new Tuile();

                                while (it.hasNext()) {
                                    Map.Entry<Integer, Tuile> val = it.next();
                                    t = val.getValue();

                                    if (t.getNom() == (piocheInondation.getP().get(0).getTuile().getNom())) {
                                        t.setEtat(new Etat("SOMBREE"));
                                        System.out.println(t.getNom() + " " + t.getEtat().etat);
                                    }
                                }

                            } else {
                                System.out.println("la case etait sombrée");
                            }

                            if (piocheInondation.getP().get(0).getTuile().getEtat().etat == ("INONDEE")) {
                                defausseCartesInondation.addCarte(piocheInondation.getP().get(0));
                            }

                            piocheInondation.getP().remove(0);
                            vuePlat.update(grille);
                        }
                    }

                    //Changement de traitement de niveau d'eau => Si niveau d'eau > 10, on perd
                } else {
                    System.out.println("fin de la partie");
                    vuePlat.getEtat().setText("Vous avez perdu !");
                    vuePlat.setVisible(false);
                    break;
                }

                //Vérification que la partie n'est pas perdue
                System.out.println("est perdu : " + estPerdu());
                if (estPerdu() == true) {
                    System.out.println("bah t'as perdu mec");
                    vuePlat.getEtat().setText(("Vous avez perdu !"));
                    desactiverBtn();
                    break;
                } else {
                    System.out.println("Bah t'as pas encore perdu, gg mec");
                }

                //Vérification que personne n'a coulé
                for (int i = 0; i < joueurs.size(); i++) {
                    if (joueurs.get(i).getCasePos().getEtat().etat == "SOMBREE") {
                        System.out.println(joueurs.get(i).getClass().toString() + " est sombree");
                        boolean depEf = false;
                        Set<Map.Entry<Integer, Tuile>> set = grille.getCasesAdjacente(joueurs.get(i).getCasePos()).entrySet();
                        Iterator<Map.Entry<Integer, Tuile>> it = set.iterator();
                        Tuile t = new Tuile();

                        while (it.hasNext() && depEf == false) {
                            Map.Entry<Integer, Tuile> val = it.next();
                            t = val.getValue();

                            if (t.getEtat().etat.equals("INONDEE") || t.getEtat().etat.equals("SECHE")) {
                                joueurs.get(i).deplacer(t, grille);
                                depEf = true;
                                System.out.println("nouvelle pos" + joueurs.get(i).getCasePos().getNom());
                            }
                        }
                    }
                }

                System.out.println("\n" + estEntoure() + "\n");
                vuePlat.repaint();
                vuePlat.update(grille);
                System.out.println("changement joueur");

                //On repasse le nombre d'actions possibles à 3 => changement de joueur
                numact = 3;
                //On repasse la vérification de l'action spéciale à false
                acSpe = false;

                if (x < joueurs.size() - 1) {
                    System.out.println(joueurs.size());
                    x = x + 1;
                    System.out.println(x);
                    avActuel = joueurs.get(x);
                    vuePlat.getEtat().setText("C'est à " + joueurs.get(x).getClass().getSimpleName() + " de jouer.");
                    if (avActuel instanceof Navigateur) {
                        numact = 4;
                    }

                } else {
                    x = 0;
                    if (niveauEau < 10) {
                        niveauEau = niveauEau + 1;
                    }
                    nivEau.setNiveauEau(niveauEau);
                    avActuel = joueurs.get(x);
                    System.out.println(x);
                    System.out.println("niveau=" + niveauEau);
                    if (avActuel instanceof Navigateur) {
                        numact = 4;
                    }
                }

                desactiverBtn();
                w = ((w + 1) % 4);
                activerBtn();
                if (avActuel.getMainCarte().size() > 5) {
                    tropDeCartes();
                }

                break;
//</editor-fold>
//
            case TYPE_JOUEUR:
                // <editor-fold defaultstate="collapsed" desc="Switch 4 TYPE_JOUEUR">
                avSelec = message.aventurier;
                System.out.println("avSelect modifier");
                break;
// </editor-fold>
//
            case TYPE_CARTE:
                // <editor-fold defaultstate="collapsed" desc="Switch 5 TYPE_CARTE">
                if (etat == "defausser") {
                    System.out.println("etat defausse");
                    int k = 0;
                    boolean defC = false;
                    while (k < avActuel.getMainCarte().size() && defC == false) {
                        if (message.carte.getClass() == avActuel.getMainCarte().get(k).getClass() && message.carte instanceof CarteTresor) {
                            if (avActuel.getMainCarte().get(k).getTresor() == message.carte.getTresor()) {
                                defausseCartes.addCarte(avActuel.getMainCarte().get(k));
                                avActuel.getMainCarte().remove(k);
                                defC = true;

                            } else {
                                k = k + 1;
                            }

                        } else if (message.carte.getClass() == avActuel.getMainCarte().get(k).getClass() && message.carte instanceof CarteActionSpeciale) {
                            if (avActuel.getMainCarte().get(k).getType() == message.carte.getType()) {
                                if (message.carte.getType() == TypeAcSpe.HELICOPTERE) {

                                    if (estGagne() == true) {
                                        System.out.println("on passe dans le true");
                                        vuePlat.getEtat().setText("Partie gagnée !");

                                    } else {
                                        System.out.println("on passe dans le false");
                                    }
                                }
                                defausseCartes.addCarte(avActuel.getMainCarte().get(k));
                                avActuel.getMainCarte().remove(k);
                                defC = true;

                            } else {
                                k = k + 1;
                            }

                        } else {
                            k = k + 1;
                        }
                    }
                    if (defC) {
                        System.out.println("carte defaussée");
                        if (avActuel.getMainCarte().size() < 6) {
                            activerBtn();
                        }
                    }

                } else if (etat == "donner") {
                    if (message.carte instanceof CarteTresor) {
                        if (avActuel instanceof Messager) {

                            int j = 0;
                            boolean def = false;
                            while (j < avActuel.getMainCarte().size() && def == false) {
                                if (avActuel.getMainCarte().get(j) instanceof CarteTresor && avActuel.getMainCarte().get(j).getTresor() == (message.carte.getTresor())) {
                                    System.out.println("carte trouvée");
                                    avActuel.getMainCarte().remove(j);
                                    def = true;

                                }
                                j = j + 1;
                            }
                            if (def) {
                                avSelec.addCarte(message.carte);
                                System.out.println("échange effectué");
                            } else {
                                System.out.println("echange annulé vous n'avez pas la carte");
                            }

                        } else if (avSelec.getCasePos().equals(avActuel.getCasePos())) {

                            int j = 0;
                            boolean def = false;
                            while (j < avActuel.getMainCarte().size() && def == false) {
                                if (avActuel.getMainCarte().get(j) instanceof CarteTresor && avActuel.getMainCarte().get(j).getTresor() == (message.carte.getTresor())) {
                                    System.out.println("carte trouvée");
                                    avActuel.getMainCarte().remove(j);
                                    def = true;
                                } else {
                                    System.out.println("else");
                                }
                                j = j + 1;

                            }
                            System.out.println("fin du while ");
                            if (def) {
                                avSelec.addCarte(message.carte);
                                System.out.println("echange effectue");
                            } else {
                                System.out.println("echange annulé vous n'avez pas la carte");
                            }
                        } else {
                            System.out.println(" les joueur ne sont pas sur la meme case");
                        }
                    } else {
                        System.out.println("on en peut qu'echanger des tresors");
                    }
                    if (avActuel.getMainCarte().size() < 6) {
                        activerBtn();
                    }

                } else if (etat == "utiliser") {
                    if (message.carte.getType() == TypeAcSpe.SACDESABLE) {
                        carteSelec = message.carte;
                        etat = "sandbag";

                    } else if (message.carte.getType() == TypeAcSpe.HELICOPTERE) {
                        carteSelec = message.carte;
                        etat = "helicoptere";
                    } else {
                        if (ramassable()) {
                            carteSelec = message.carte;
                            System.out.println("je rentre dans le if");
                            tresors.put(carteSelec.getTresor(), Boolean.TRUE);
                            int i = 0;
                            ArrayList<Carte> indices = new ArrayList<>();
                            for (int j = 0; j < avActuel.getMainCarte().size(); j++) {
                                System.out.println(avActuel.getMainCarte().get(j).getTresor() + " == " + carteSelec.getTresor());
                                if (avActuel.getMainCarte().get(j) instanceof CarteTresor && avActuel.getMainCarte().get(j).getTresor() == carteSelec.getTresor() && i < 4) {
                                    System.out.println("supression carte : " + carteSelec.getTresor().toString());
                                    indices.add(avActuel.getMainCarte().get(j));
                                    i = i + 1;
                                }
                            }
                            for (Carte k : indices) {
                                System.out.println("supression");
                                avActuel.getMainCarte().remove(k);
                            }
                            indices.clear();
                            vuePlat.getTresor(carteSelec.getTresor());
                        }
                    }
                }

                break;
// </editor-fold>
//
            //TYPE_FINAC = Type utilisé à la FIN d'une ACtion

            case TYPE_FINAC:
                // <editor-fold defaultstate="collapsed" desc="Switch 6 TYPE_FINAC">
                if (avActuel.getMainCarte().size() < 6) {
                    desactiverFinAct();
                    numact = 0;
                    if (pioche.getM().size() > 2) {
                        pioche.piocher(avActuel);
                        System.out.println("pioche2");

                    } else {
                        pioche.remplir(defausseCartes);
                    }

                    for (int i = 0; i < avActuel.getMainCarte().size(); i++) {

                        if (avActuel.getMainCarte().get(i) instanceof CarteActionSpeciale
                                && avActuel.getMainCarte().get(i).getType() == TypeAcSpe.MONTEEDESEAUX) {
                            if (niveauEau < 10) {
                                niveauEau = niveauEau + 1;
                            }
                            nivEau.setNiveauEau(niveauEau);
                            System.out.println("vous avez pioché une carte monté des eaux pas deso");
                            System.out.println("niveau=" + niveauEau);
                            defausseCartes.addCarte(avActuel.getMainCarte().get(i));
                            avActuel.getMainCarte().remove(i);
                        }
                    }
                }
// </editor-fold>
//
        }

        //Rafraichissement de la grille + affichage d'infos
        vuePlat.repaint();
        joueur1.update(joueurs.get(0));
        joueur2.update(joueurs.get(1));
        joueur3.update(joueurs.get(2));
        joueur4.update(joueurs.get(3));
        nivEau.repaint();
        System.out.println("Case actuelle de l'aventurier : " + avActuel.getCasePos().getNom() + "\n\n");
    }
    //Autres fonctions

    public static boolean estEntoure() {
        boolean res = false;
        int mort = joueurs.size();
        System.out.println("mort == " + mort);
        for (int i = 0; i < joueurs.size(); i++) {
            res = false;
            ArrayList<Tuile> gAl = new ArrayList<>(joueurs.get(i).getCasesPossibles(grille).values());
            for (int u = 0; u < gAl.size(); u++) {
                if (gAl.get(u).getEtat().etat == "INONDEE" || gAl.get(u).getEtat().etat == "SECHE") {
                    res = true;
                }
            }
            if (res) {
                mort = mort - 1;
            }
        }
        System.out.println("Mort : " + mort);
        return (mort != 0);
    }

    //Permet de savoir si une partie est finie [Traite l'héliport sombré et le niveau d'eau trop haut]
    public static boolean estPerdu() {
        Set<Map.Entry<Integer, Tuile>> set = grille.getCases().entrySet();
        Iterator<Map.Entry<Integer, Tuile>> it = set.iterator();
        boolean chk = true;
        boolean chkTres = true;
        boolean chkTresF = true;
        Tuile t = new Tuile();
        while (it.hasNext()) {
            Map.Entry<Integer, Tuile> val = it.next();
            t = val.getValue();
            //Vérifie si l'héliport a sombré
            if ("Heliport" == (t.getNom()) && "SOMBREE" == (t.getEtat().etat)) {
                return true;
            }
        }
        //Renvoie true si 2 tuiles d'un même trésor sombrent sans qu'on l'ait récupéré
        // <editor-fold defaultstate="collapsed" desc="Test trésor non coulé">
        ArrayList<Tuile> tAl = new ArrayList<>(grille.getCases().values());
        for (Tuile tTre : tAl) {
            //Ici on va vérifier qu'il reste une tuile non SOMBREE
            if (tTre.getEtat().etat == "INONDEE" || t.getEtat().etat == "SECHEE") {
                chk = false;
            }
            //Pierre
            if (tresors.get(Tresor.PIERRE) == false) {
                if (tTre.getNom() == "Le Temple Du Soleil" && tTre.getEtat().etat == "SOMBREE") {
                    chkTres = false;
                } else {
                    chkTres = true;
                }
                if (tTre.getNom() == "Le Temple De La Lune" && tTre.getEtat().etat == "SOMBREE") {
                    chkTresF = false;
                } else {
                    chkTresF = true;
                }
            }
            //Zephyr
            if (tresors.get(Tresor.ZEPHYR) == false) {
                if (tTre.getNom() == "Le Jardin Des Hurlements" && tTre.getEtat().etat == "SOMBREE") {
                    chkTres = false;
                } else {
                    chkTres = true;
                }
                if (tTre.getNom() == "Le Jardin Des Murmures" && tTre.getEtat().etat == "SOMBREE") {
                    chkTresF = false;
                } else {
                    chkTresF = true;
                }
            }
            //Calice
            if (tresors.get(Tresor.CALICE) == false) {
                if (tTre.getNom() == "Le Palais De Corail" && tTre.getEtat().etat == "SOMBREE") {
                    chkTres = false;
                } else {
                    chkTres = true;
                }
                if (tTre.getNom() == "Le Palais Des Marees" && tTre.getEtat().etat == "SOMBREE") {
                    chkTresF = false;
                } else {
                    chkTresF = true;
                }
            }
            //Cristal
            if (tresors.get(Tresor.CRISTAL) == false) {
                if (tTre.getNom() == "La Caverne Des Ombres" && tTre.getEtat().etat == "SOMBREE") {
                    chkTres = false;
                } else {
                    chkTres = true;
                }
                if (tTre.getNom() == "La Caverne Du Brasier" && tTre.getEtat().etat == "SOMBREE") {
                    chkTresF = false;
                } else {
                    chkTresF = true;
                }
            }
        }
// </editor-fold>
//
        System.out.println(chkTres + " chkTres");
        System.out.println(chkTresF + " chkTresF");
        //Retourne vrai si le niveau d'eau est supérieur à 10
        if (chk == true) {
            return true;
        }

        if (niveauEau >= 10) {
            return true;
        }

        if (chkTres == false && chkTresF == false) {
            System.out.println("c'est très oui");
            return true;
        }

        System.out.println("ENTOURE " + estEntoure());
        if (estEntoure()) {
            System.out.println("ENTOURE A");
            return true;
        }
        return false;
    }

    public static boolean estGagne() {
        //Verif que les tresors sont récupérés
        boolean chk = true;
        if (tresors.get(Tresor.PIERRE) == true
                && tresors.get(Tresor.ZEPHYR) == true
                && tresors.get(Tresor.CALICE) == true
                && tresors.get(Tresor.CRISTAL) == true) {
            for (Aventurier av : joueurs) {
                if (av.getCasePos().getNom() != "Heliport") {
                    chk = false;
                }
            }
            return chk;
        }
        return false;
    }
//

    public static boolean ramassable() {
        int j = 0;
        if (avActuel.getCasePos().getNom() == ("Le Palais Des Marees")
                || avActuel.getCasePos().getNom() == ("Le Palais De Corail")) {
            System.out.println("ramassable");
            for (int i = 0; i < avActuel.getMainCarte().size(); i++) {
                if (avActuel.getMainCarte().get(i).getTresor() == Tresor.CALICE) {
                    System.out.println("carte calice");
                    j = j + 1;
                }
            }
            return j > 3;

        } else if (avActuel.getCasePos().getNom() == ("La Caverne Du Brasier")
                || avActuel.getCasePos().getNom() == ("La Caverne Des Ombres")) {

            for (int i = 0; i < avActuel.getMainCarte().size(); i++) {
                if (avActuel.getMainCarte().get(i).getTresor() == Tresor.CRISTAL) {
                    j = j + 1;
                }
            }
            return j > 3;

        } else if (avActuel.getCasePos().getNom() == ("Le Temple Du Soleil")
                || avActuel.getCasePos().getNom() == ("Le Temple De La Lune")) {
            for (int i = 0; i < avActuel.getMainCarte().size(); i++) {
                if (avActuel.getMainCarte().get(i).getTresor() == Tresor.PIERRE) {
                    j = j + 1;
                }
            }
            return j > 3;

        } else if (avActuel.getCasePos().getNom() == ("Le Jardin Des Murmures")
                || avActuel.getCasePos().getNom() == ("Le Jardin Des Hurlements")) {
            for (int i = 0; i < avActuel.getMainCarte().size(); i++) {
                if (avActuel.getMainCarte().get(i).getTresor() == Tresor.ZEPHYR) {
                    j = j + 1;
                }
            }
            return j > 3;

        } else {
            return false;
        }
    }

    //Désactivation des boutons des aventuriers qui ne jouent pas
    public void activerBtn() {
        vuesAventuriers.get(w).getChoixActionsPerso().setEnabled(true);
        vuesAventuriers.get(w).getChoixActionCartes().setEnabled(true);
        if (!joueurs.get(w).getClass().getSimpleName().equals("Pilote")) {
            actSpeFinit();
        }
    }

    public void desactiverBtn() {
        vuesAventuriers.get(w).getChoixActionsPerso().setEnabled(false);
        vuesAventuriers.get(w).getChoixActionCartes().setEnabled(false);
        vuesAventuriers.get(w).getTerminerTour().setEnabled(false);
    }

    public void desactiverFinAct() {
        vuesAventuriers.get(w).defaultPanel();
        vuesAventuriers.get(w).getChoixActionsPerso().setEnabled(false);
        vuesAventuriers.get(w).getTerminerTour().setEnabled(true);
    }

    public void actFinit() {
        vuesAventuriers.get(w).getDeplacer().setEnabled(false);
        vuesAventuriers.get(w).getAssecher().setEnabled(false);
        vuesAventuriers.get(w).getActionSpe().setEnabled(false);
        vuesAventuriers.get(w).getRetourMenuPrec1().setEnabled(false);
    }

    public void actSpeFinit() {
        vuesAventuriers.get(w).getActionSpe().setEnabled(false);
    }

    public void tropDeCartes() {
        vuesAventuriers.get(w).getChoixActionsPerso().setEnabled(false);
    }

// <editor-fold defaultstate="collapsed" desc="Getters&Setters">
    public static ArrayList<Aventurier> getJoueurs() {
        return joueurs;
    }

    public static VueAventurier getJoueur1() {
        return joueur1;
    }

    public static VueAventurier getJoueur2() {
        return joueur2;
    }

    public static VueAventurier getJoueur3() {
        return joueur3;
    }

    public static VueAventurier getJoueur4() {
        return joueur4;
    }

    public static Grille getGrille() {
        return grille;
    }

    public static VuePlateau getVuePlat() {
        return vuePlat;
    }

    public static void setJoueur1(VueAventurier joueur1) {
        Controleur.joueur1 = joueur1;
    }

    public static void setJoueurs(ArrayList<Aventurier> joueurs) {
        Controleur.joueurs = joueurs;
    }

    public static void setJoueur2(VueAventurier joueur2) {
        Controleur.joueur2 = joueur2;
    }

    public static void setJoueur3(VueAventurier joueur3) {
        Controleur.joueur3 = joueur3;
    }

    public static void setJoueur4(VueAventurier joueur4) {
        Controleur.joueur4 = joueur4;
    }

    public static void setGrille(Grille grille) {
        Controleur.grille = grille;
    }

    public static void setVuePlat(VuePlateau vuePlat) {
        Controleur.vuePlat = vuePlat;
    }

// </editor-fold>
//
}

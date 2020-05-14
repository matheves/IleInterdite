package ile;

import ile.Grille;
import java.util.*;

public class Controleur {

	vueAventurier vueAventurier;
	vuePlateau vuePlateau;
	private HashMap<Pion, Aventurier> joueur;
	private ArrayList<CarteTresor> pileTresor;
	private ArrayList<CarteInondation> pileInondation;
	Grille grille;
	private int niveauEau;
        
        //<editor-fold defaultstate="collapsed" desc="tuiles">
        public static void initTuile(Grille g) {
            Tuile t1 = new Tuile(Etat.SOMBREE,1,1);
            g.getCases().put(1, t1);
            Tuile t2 = new Tuile(Etat.SOMBREE,2,1);
            g.getCases().put(2, t2);
            Tuile t3 = new Tuile(3,"Le Pont Des Abimes",3,1,Etat.SECHE);
            g.getCases().put(3, t3);
            Tuile t4 = new Tuile(4,"La Porte De Bronze",4,1,Etat.SECHE);
            g.getCases().put(4, t4);
            Tuile t5 = new Tuile(Etat.SOMBREE,5,1);
            g.getCases().put(5, t5);
            Tuile t6 = new Tuile(Etat.SOMBREE,6,1);
            g.getCases().put(6, t6);
            Tuile t7 = new Tuile(Etat.SOMBREE,1,2);
            g.getCases().put(7, t7);
            Tuile t8 = new Tuile(8,"La Caverne Des Ombres",2,2,Etat.SECHE);
            g.getCases().put(8, t8);
            Tuile t9 = new Tuile(9,"La Porte De Fer",3,2,Etat.SECHE);
            g.getCases().put(9, t9);
            Tuile t10 = new Tuile(10,"La Porte d'Or",4,2,Etat.SECHE);
            g.getCases().put(10, t10);
            Tuile t11 = new Tuile(11,"Les falaises De L'Oubli",5,2,Etat.SECHE);
            g.getCases().put(11, t11);
            Tuile t12 = new Tuile(Etat.SOMBREE,6,2);
            g.getCases().put(12, t12);
            Tuile t13 = new Tuile(13,"Le Palais De Corail",1,3,Etat.SECHE);
            g.getCases().put(13, t13);
            Tuile t14 = new Tuile(14,"La Porte d'Argent",2,3,Etat.SECHE);
            g.getCases().put(14, t14);
            Tuile t15 = new Tuile(15,"Les Dunes De L'Illusion",3,3,Etat.SECHE);
            g.getCases().put(15, t15);
            Tuile t16 = new Tuile(16,"Heliport",4,3,Etat.SECHE);
            g.getCases().put(16, t16);
            Tuile t17 = new Tuile(17,"La Porte De Cuivre",5,3,Etat.SECHE);
            g.getCases().put(17, t17);
            Tuile t18 = new Tuile(18,"Le Jardin Des Hurlements",6,3,Etat.SECHE);
            g.getCases().put(18, t18);
            Tuile t19 = new Tuile(19,"La Foret Pourpre",1,4,Etat.SECHE);
            g.getCases().put(19, t19);
            Tuile t20 = new Tuile(20,"Le Lagon Perdu",2,4,Etat.SECHE);
            g.getCases().put(20, t20);
            Tuile t21 = new Tuile(21,"Le Marais Brumeux",3,4,Etat.SECHE);
            g.getCases().put(21, t21);
            Tuile t22 = new Tuile(22,"Observatoire",4,4,Etat.SECHE);
            g.getCases().put(22, t22);
            Tuile t23 = new Tuile(23,"Le Rocher Fantome",5,4,Etat.SECHE);
            g.getCases().put(23, t23);
            Tuile t24 = new Tuile(24,"La Caverne Du Brasier",6,4,Etat.SECHE);
            g.getCases().put(24, t24);
            Tuile t25 = new Tuile(Etat.SOMBREE,1,5);
            g.getCases().put(25, t25);
            Tuile t26 = new Tuile(26,"Le Temple Du Soleil",2,5,Etat.SECHE);
            g.getCases().put(26, t26);
            Tuile t27 = new Tuile(27,"Le Temple De La Lune",3,5,Etat.SECHE);
            g.getCases().put(27, t27);
            Tuile t28 = new Tuile(28,"Le Palais Des Marees",4,5,Etat.SECHE);
            g.getCases().put(28, t28);
            Tuile t29 = new Tuile(29,"Le Val Du Crepuscule",5,5,Etat.SECHE);
            g.getCases().put(29, t29);
            Tuile t30 = new Tuile(Etat.SOMBREE,6,5);
            g.getCases().put(30, t30);
            Tuile t31 = new Tuile(Etat.SOMBREE,1,6);
            g.getCases().put(31, t31);
            Tuile t32 = new Tuile(Etat.SOMBREE,2,6);
            g.getCases().put(32, t32);
            Tuile t33 = new Tuile(33,"La Tour Du Guet",3,6,Etat.SECHE);
            g.getCases().put(33, t33);
            Tuile t34 = new Tuile(34,"Le Jardin Des Murmures",4,6,Etat.SECHE);
            g.getCases().put(34, t34);
            Tuile t35 = new Tuile(Etat.SOMBREE,5,6);
            g.getCases().put(35, t35);
            Tuile t36 = new Tuile(Etat.SOMBREE,6,6);
            g.getCases().put(36, t36);
        }
//</editor-fold>
        
        public static void main(String[] args) {
            Grille g = new Grille();
            initTuile(g);
            vuePlateau vuePlat = new vuePlateau(g);
            
        }
        
        

}
package game;

import java.util.ArrayList;

/**
 * Interface IBuilding initialisant les différentes signatures de méthodes nécessaire 
 * pour les batiments (Building) et machines (Machine)
 * @author Jaouen MARIE
 * @version 1.0
 */
public interface IBuilding {

	/**
	 * Méthode abstraite permettant de récuperer le nombre de points de victoire de la carte batiment/machine
	 * @return points de victoire
	 */
	abstract int getVictoryPoint();

	/**
	 * Méthode abstraite getter retournant la valeur du chemin de l'image de la carte en mode chantier
	 * @return chemin de l'image
	 */
	abstract String getPathChantier();

	/**
	 * Méthode abstraite getter retournant la valeur du chemin de l'image de la carte en mode fini
	 * @return chemin de l'image
	 */
	abstract String getPathFini();

	/**
	 * Méthode abstraite permettant de récupérer les construteurs sur le batiment/machine pour sa construction
	 * @return liste des constructeurs
	 */
	abstract ArrayList<Builder> getBuildersOnSite();

	/**
	 * Méthode abstraite permettant d'ajouter un construteur à un batiment/machine pour réaliser sa construction
	 * @param b constructeur
	 */
	abstract void addBuilder(Builder b);

	/**
	 * Méthode abstraite permettant d'enlever tout les constructeurs, sur la construction du batiment/machine, 
	 * quand celle-ci est fini
	 */
	abstract void removeBuilders();

	/**
	 * Méthode abstraite getter permettant de savoir si le batiment/machine a fini d'être construit
	 * @return vrai si construction fini, false sinon
	 */
	abstract boolean getFinished();

	/**
	 * Méthode abstraite permettant de regarder si la construction est censé être fini. Elle va regarder 
	 * les valeurs des différents paramètres des constructeurs et les comparer avec la demande 
	 * du batiment/machine
	 * @param p joueur à qui l'on va rajouter les écus si le chantier est bien fini
	 */
	abstract void checkIsFinished(Player p);

	/**
	 * Méthode abstraite retournant une chaîne de caractère structurant les différentes données d'un(e) batiment/machine, 
	 * permettant son affichage dans un terminal
	 * @return chaîne de caractère des données d'un(e) batiment/machine
	 */
	abstract String toString();

}
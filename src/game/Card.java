package game;

import java.io.Serializable;

/**
 * Classe abstraite Card représentant les données nécessaire que possède chaque carte du jeu
 * @author Jaouen MARIE
 * @version 1.0
 */
public abstract class Card  implements Serializable{

	/** Nom de la carte */
	protected String name;
	/** Nombre de pierre que possède la carte */
	protected int stone;
	/** Nombre de bois que possède la carte */
	protected int wood;
	/** Nombre de connaissance que possède la carte */
	protected int knowledge;
	/** Nombre de tuile que possède la carte */
	protected int tile;

	/**
	 * Constructeur de la classe Card permettant l'initialisation des différents paramètres 
	 * de la carte de jeu
	 * @param name nom du batiment
	 * @param stone nombre de pierre demandé pour sa construction
	 * @param wood nombre de bois demandé pour sa construction
	 * @param knowledge nombre de points de connaissance demandé pour sa construction
	 * @param tile nombre de tuile demandé pour sa construction
	 */
	public Card(String name, int stone, int wood, int knowledge, int tile) {
		if(name != null) this.name = name;
		else throw new IllegalArgumentException("Error - Card - name can't be null");
		if(stone >= 0) this.stone = stone;
		else throw new IllegalArgumentException("Error - Card - stone need to be upper or equal than 0");
		if(wood >= 0) this.wood = wood;
		else throw new IllegalArgumentException("Error - Card - wood need to be upper or equal than 0");
		if(knowledge >= 0) this.knowledge = knowledge;
		else throw new IllegalArgumentException("Error - Card - knowledge need to be upper or equal than 0");
		if(tile >= 0) this.tile = tile;
		else throw new IllegalArgumentException("Error - Card - tile need to be upper or equal than 0");
	}

	/**
	 * Méthode getter permettant de récupérer le nom de la carte
	 * @return nom de la carte
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Méthode getter permettant de récupérer le nombre de pierre que possède la carte
	 * @return nombre de pierre
	 */
	public int getStone() {
		return this.stone;
	}

	/**
	 * Méthode getter permettant de récupérer le nombre de bois que possède la carte
	 * @return nombre de bois
	 */
	public int getWood() {
		return this.wood;
	}

	/**
	 * Méthode getter permettant de récupérer le nombre de connaissance que possède la carte
	 * @return nombre de connaissance
	 */
	public int getKnowledge() {
		return this.knowledge;
	}

	/**
	 * Méthode getter permettant de récupérer le nombre de tuile que possède la carte
	 * @return nombre de tuile
	 */
	public int getTile() {
		return this.tile;
	}

	/**
	 * Méthode abstraite retournant une chaîne de caractère structurant les différentes données de la carte, 
	 * permettant son affichage dans un terminal
	 * @return chaîne de caractère des données de la carte
	 */
	public abstract String toString();

}
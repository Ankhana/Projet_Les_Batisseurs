package game;

import java.io.Serializable;

/**
 * Classe abstraite Player représentant les données nécessaire que possède chaque joueur du jeu
 * @author Jaouen MARIE
 * @version 1.0
 */
public abstract class Player  implements Serializable{

	/** Nom du joueur */
	protected String name;
	/** Nombre d'écus que possède le joueur */
	protected int nbEcus;
	/** Deck de carte du joueur */
	protected Deck playerDeck;
	/** Deck du jeu en cours */
	protected Deck gameDeck;
	/** Deck du jeu en cours */
	protected Game game;
	/** Nombre d'action restant */
	protected int remainingTurn;

	/**
	 * Constructeur de la classe Player permettant l'initialisation des différents paramètres 
	 * du joueur
	 * @param name nom du joueur
	 * @param name nom du joueur
	 * @param nbEcus nombre d'écus pour commencer du joueur
	 * @param game parti de jeu
	 */
	public Player(String name, int nbEcus, Game game) {
		if(name != null) this.name = name;
		else throw new IllegalArgumentException("Error - Player - passed parameter name can't be null");
		if(nbEcus >= 0) this.nbEcus = nbEcus;
		else throw new IllegalArgumentException("Error - Player - passed parameter nbEcus need to be upper or equal than 0");
		if(game != null) this.game = game;
		else throw new IllegalArgumentException("Error - Player - passed parameter Game can't be null");
		this.gameDeck = game.getDeck();
		this.playerDeck = new Deck();
		this.remainingTurn = 3;
	}

	/**
	 * Méthode abstraite permettant au joueur de réaliser son tour en faisant plusieurs actions
	 */
	public abstract void play();

	/**
	 * Méthode getter permettant de récupérer le nom du joueur
	 * @return nom du joueur
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Méthode setter permettant de changer le nom du joueur
	 * @param name nom du joueur
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Méthode getter permettant de récupérer le nombre d'écus que possède le joueur
	 * @return nombre d'écus du joueur
	 */
	public int getNbEcus() {
		return this.nbEcus;
	}

	/**
	 * Méthode permettant d'ajouter ou d'enlever des écus au joueur
	 * @param number nombre d'écus à ajouter/enlever au joueur
	 */
	public void modifyNbEcus(int number) {
		this.nbEcus += number;
	}

	/**
	 * Méthode getter permettant de récuperer le deck de carte du joueur
	 * @return deck de carte du joueur
	 */
	public Deck getPlayerDeck() {
		return this.playerDeck;
	}

	/**
	 * Methode getter permettant de récupérer le nombre d'actions restantes
	 * @return nombre d'actions
	 */
	public int getRemainingTurn() {
		return this.remainingTurn;
	}

	/**
	 * Méthode permettant d'enlever des actions gratuites au joueur
	 * @param i nombre d'actions à enlever
	 */
	public void removeRemainingTurn(int i) {
		this.remainingTurn -= i;
	}

	/**
	 * Méthode retournant une chaîne de caractère structurant les différentes données du joueur, 
	 * permettant son affichage dans un terminal
	 * @return chaîne de caractère des données du joueur
	 */
	public String toString() {
		String ret = this.name + "\n"
				+ "------------------------------------------------------------------------------------------------------------------------\n"
				+ "Nombre d'écus : " + this.nbEcus + "\n"
				+ "Nombre de tours gratuits restants : " + this.remainingTurn + "\n\n"
				+ this.playerDeck.toString()
				+ "\n------------------------------------------------------------------------------------------------------------------------\n"
				;
		return ret;
	}

}
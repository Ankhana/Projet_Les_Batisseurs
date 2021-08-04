package game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import util.RWFile;

/**
 * Classe Deck représentant un jeu de plusieurs batiments et constructeurs. Ce deck de cartes permettra de 
 * représenter le deck du plateau mais aussi les decks des joueurs
 * @author Jaouen MARIE
 * @version 1.0
 */
public class Deck  implements Serializable{

	/** Liste des batiments */
	private ArrayList<Card> buildings;
	/** Liste des constructeurs */
	private ArrayList<Builder> builders;

	/**
	 * Constructeur de la classe Card permettant l'initialisation des différents paramètres 
	 * du deck en laissant les listes vides (pour les joueurs)
	 */
	public Deck() {
		this.buildings = new ArrayList<Card>();
		this.builders = new ArrayList<Builder>();
	}

	/**
	 * Constructeur de la classe Card permettant l'initialisation des différents paramètres 
	 * de du deck en remplissant les différentes listes en fonction de plusieurs fichiers 
	 * passés en paramètre, en rajoutant la possibilité si le fichier des machines est 
	 * séparé de celui des batiments (pour la partie)
	 * @param buildersFilePath chemin du fichier des constructeurs
	 * @param buildingsFilePath chemin du fichier des batiments
	 * @param machinesFilePath chemin du fichier des machines
	 */
	public Deck(String buildersFilePath, String buildingsFilePath, String machinesFilePath) {
		this.builders = new ArrayList<Builder>();
		this.buildings = new ArrayList<Card>();
		this.initializeDeck(buildersFilePath, buildingsFilePath, machinesFilePath);
		this.shuffleCards();
	}

	/**
	 * Méthode permettant l'initialisation des listes avec les chemins des différents fichiers
	 * @param buildersFilePath chemin du fichier des constructeurs
	 * @param buildingsFilePath chemin du fichier des batiments
	 * @param machinesFilePath chemin du fichier des machines
	 */
	private void initializeDeck(String buildersFilePath, String buildingsFilePath, String machinesFilePath) {
		ArrayList<String> builder = RWFile.readFile(buildersFilePath);
		int i = 1;
		for (String s : builder) {
			String[] elem = s.split(";");
			String path = "./data/images/cartes/ouvriers/" + i + ".png";
			this.builders.add(new Builder(elem[0], Integer.parseInt(elem[2]), Integer.parseInt(elem[3]), Integer.parseInt(elem[4]), Integer.parseInt(elem[5]), Integer.parseInt(elem[1]), path));
			i++;
		}

		ArrayList<String> buildings = RWFile.readFile(buildingsFilePath);
		i = 1;
		for (String s : buildings) {
			String[] elem = s.split(";");
			String path_chantier = "./data/images/cartes/batiments/chantiers/" + i + ".png";
			String path_fini = "./data/images/cartes/batiments/finis/" + i + ".png";
			this.buildings.add(new Building(elem[0], Integer.parseInt(elem[3]), Integer.parseInt(elem[4]), Integer.parseInt(elem[5]), Integer.parseInt(elem[6]), Integer.parseInt(elem[2]), Integer.parseInt(elem[1]), path_chantier, path_fini));
			i++;
		}

		ArrayList<String> machine = RWFile.readFile(machinesFilePath);
		i = 1;
		for (String s : machine) {
			String[] elem = s.split(";");
			String path_chantier = "./data/images/cartes/machines/chantiers/" + i + ".png";
			String path_fini = "./data/images/cartes/machines/finis/" + i + ".png";
			this.buildings.add(new Machine(elem[0], Integer.parseInt(elem[6]), Integer.parseInt(elem[7]), Integer.parseInt(elem[8]), Integer.parseInt(elem[9]), Integer.parseInt(elem[5]), Integer.parseInt(elem[1]), Integer.parseInt(elem[2]), Integer.parseInt(elem[3]), Integer.parseInt(elem[4]), path_chantier, path_fini));
			i++;
		}
	}

	/**
	 * Méthode permettant le mélange des cartes dans la liste de façon aléatoire (pour la partie)
	 */
	private void shuffleCards() {
		Collections.shuffle(this.builders);
		Collections.shuffle(this.buildings);
	}

	/**
	 * Méthode permettant de récupérer l'objet Building dans l'arraylist à un index donné
	 * @param cardNumber index du batiment à récupérer
	 */
	public Card getBuildingCard(int cardNumber) {
		Card ret = null;
		if(cardNumber >= 0 && cardNumber < this.buildings.size()) {
			ret = this.buildings.get(cardNumber);
		}else throw new IllegalArgumentException("Error - Deck - cardNumber need to be between 0 (included) and Building Arraylist size");
		return ret;
	}

	/**
	 * Méthode permettant de récupérer l'objet Builder dans l'arraylist à un index donné
	 * @param cardNumber index du constructeur à récupérer
	 */
	public Builder getBuilderCard(int cardNumber) {
		Builder ret = null;
		if(cardNumber >= 0 && cardNumber < this.builders.size()) {
			ret = this.builders.get(cardNumber);
		}else throw new IllegalArgumentException("Error - Deck - cardNumber need to be between 0 (included) and Builder Arraylist size");
		return ret;
	}

	/**
	 * Méthode permettant de récupérer le nombre de points de victoire actuelle du deck
	 * @return points de victoire
	 */
	public int getVictoryPoint() {
		int ret = 0;
		for (Card c : buildings) {
			if(((IBuilding) c).getFinished()) ret += ((IBuilding) c).getVictoryPoint();
		}
		return ret;
	}

	/**
	 * Méthode permettant de récupérer la taille de l'ArrayList des constructeurs
	 * @return taille de l'Arraylist des constructeur
	 */
	public int getBuildersSize() {
		return this.builders.size();
	}

	/**
	 * Méthode permettant de récupérer la taille de l'ArrayList des constructions
	 * @return taille de l'Arraylist des constructions
	 */
	public int getBuildingsSize() {
		return this.buildings.size();
	}

	/**
	 * Méthode permettant de rajouter un Builder à la l'arraylist des constructeurs du deck
	 * @param b Builder à rajouter à l'arraylist
	 */
	public void addBuilder(Builder b) {
		if(b != null) {
			this.builders.add(b);
		}else throw new IllegalArgumentException("Error - Deck - passed parameter Builder can't be null");
    }

	/**
	 * Méthode permettant de rajouter un Building à la l'arraylist des batiments du deck
	 * @param b Building à rajouter à l'arraylist
	 */
	public void addBuilding(IBuilding b) {
		if(b != null) {
			this.buildings.add((Card) b);
		}else throw new IllegalArgumentException("Error - Deck - passed parameter Building can't be null");
    }

	/**
	 * Méthode permettant d'enlever un construteur du deck
	 * @param b constructeur à enlever du deck
	 */
	public void removeBuilder(Builder b) {
		if(b != null) {
			this.builders.remove(b);
		}else throw new IllegalArgumentException("Error - Deck - passed parameter Builder can't be null");
    }

	/**
	 * Méthode permettant de rajouter un Building à la l'arraylist des batiments du deck
	 * @param b Building à rajouter à l'arraylist
	 */
	public void removeBuilding(IBuilding b) {
		if(b != null) {
			this.buildings.remove((Card) b);
		}else throw new IllegalArgumentException("Error - Deck - passed parameter Building can't be null");
    }


	/**
	 * Méthode permettant de placer un constructeur sur un batiment pour faire la construction de celui-ci
	 * @param cardNumber index du batiment à construire
	 * @param builderNumber index du constructeur à placer
	 */
	public void putBuilderOnBuildingSite(int cardNumber, int builderNumber) {
		if(this.builders.get(builderNumber).getOnBuildingSite() == false) {
			if(!((IBuilding) this.buildings.get(cardNumber)).getFinished()) {
				((IBuilding) this.buildings.get(cardNumber)).addBuilder(this.builders.get(builderNumber));
				this.builders.get(builderNumber).setOnBuildingSite(true);
			}else System.out.println("The construction of this building is finished");
		}else System.out.println("the builder is already on another site");
	}

	/**
	 * Méthode retournant une string de l'affichage des cartes du deck de la partie de jeu
	 * @return affichage des cartes du deck de la partie de jeu
	 */
	public String printGameDeck() {
		String ret = "LES OUVRIERS DISPONIBLES :\n"
			+ "  	Nom	     |	Cout	|	Pierre	|	Bois	|	Savoir	|	Tuile	|	Construit	|\n"
		;
		for(int i = 0; i < 5; i++) {
			ret += i + "/	" + this.builders.get(i).toString() + "\n";
		}

		ret += "\n\nLES BÂTIMENTS À CONSTRUIRE :\n"
			+ "  	Nom			   |	Pierre	|	Bois	|	Savoir	|	Tuile	|	Points	|	Ecus	|	Construction	|\n"
		;
		for(int i = 0; i < 5; i++) {
			ret += i + "/	" + this.buildings.get(i).toString() + "\n";
		}
		return ret;
	}

	/**
	 * Méthode retournant une chaîne de caractère structurant les différentes données du deck de cartes, 
	 * permettant son affichage dans un terminal
	 * @return chaîne de caractère des données du deck de cartes
	 */
	public String toString() {
		String ret = "VOS OUVRIERS :\n"
			+ "  	Nom	     |	Cout	|	Pierre	|	Bois	|	Savoir	|	Tuile	|	Construit	|\n"
		;
		int i = 0;
		for (Builder b : builders) {
			ret += i + "/	" + b.toString() + "\n";
			i++;
		}

		ret += "\n\nVOS BÂTIMENTS :\n"
		+ "  	Nom			   |	Pierre	|	Bois	|	Savoir	|	Tuile	|	Points	|	Ecus	|	Construction	|	Ouvriers\n"
		;
		i = 0;
		for (Card c : buildings) {
			ret += i + "/	" + c.toString()  + "\n";
			i++;
		}

		return ret;
	}

}

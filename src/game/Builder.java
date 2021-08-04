package game;

/**
 * Classe Builder représentant une carte de jeu constructeur, carte de jeu étant attribué à des 
 * batiments pour les construire par le biais de leurs différents points de compétences.
 * @author Jaouen MARIE
 * @version 1.0
 */
public class Builder extends Card {

	/** Batiment sur lequel est le constructeur (null si sur aucun) */
	private boolean onBuildingSite;
	/** Coût d'utilisation du constructeur pour la construction de batiments */
	private int cost;
	/** Chemin de l'image de la carte */
	private String path;

	/**
	 * Constructeur de la classe Builder permettant l'initialisation des différents paramètres 
	 * de la carte de jeu
	 * @param name nom du constructeur
	 * @param stone point de compétences en roche
	 * @param wood point de compétences en bois
	 * @param knowledge point de compétences en connaissance
	 * @param tile point de compétences en tuile
	 * @param cost coût d'utlisation du constructeur
	 * @param path chemin de l'image de la carte
	 */
	public Builder(String name, int stone, int wood, int knowledge, int tile, int cost, String path) {
		super(name, stone, wood, knowledge, tile);
		if(cost >= 0) this.cost = cost;
		else throw new IllegalArgumentException("Error - Builder - cost need to be upper or equal than 0");
		if(path != null) this.path = path;
		else throw new IllegalArgumentException("Error - Builder - path can't be null");
		this.onBuildingSite = false;
	}

	/**
	 * Méthode getter permettant de savoir sur quelle batiment est le constructeur
	 * @return batiment sur lequel est le constructeur
	 */
	public boolean getOnBuildingSite() {
		return this.onBuildingSite;
	}

	/**
	 * Méthode setter permettant d'assigner le construteur à la construction d'un batiment
	 * @param b batiment auquel doit être assigné le constructeur
	 */
	public void setOnBuildingSite(boolean b) {
		this.onBuildingSite = b;
	}

	/**
	 * Méthode getter retournant la valeur de coût du constructeur
	 * @return coût du constructeur
	 */
	public int getCost() {
		return this.cost;
	}

	/**
	 * Méthode getter retournant la valeur du chemin de l'image de la carte
	 * @return chemin de l'image
	 */
	public String getPath() {
		return this.path;
	}

	/**
	 * Méthode retournant une chaîne de caractère structurant les différentes données de la carte constructeur, 
	 * permettant son affichage dans un terminal
	 * @return chaîne de caractère des données du constructeur
	 */
	public String toString() {
		String padding = "";
		for(int i = 0; i < 13-this.name.length();i++) padding += " ";

		String ret = this.name + padding + "|	"
				+ this.cost + "	|	"
				+ this.stone + "	|	"
				+ this.wood + "	|	"
				+ this.knowledge + "	|	"
				+ this.tile + "	|	"
				;
		if(!this.onBuildingSite) ret += "non		|	";
		else ret += "oui		|	";
		return ret;
	}

}

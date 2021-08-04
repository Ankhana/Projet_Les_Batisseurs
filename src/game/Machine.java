package game;

//import de la classe arraylist pour une meilleur gestion des tableaux
import java.util.ArrayList;

/**
 * Classe Machine représentant une carte de jeu d'un machine, carte de jeu permettant de gagner 
 * des points de victoire après avoir fini sa construction ainsi que d'aider les constructeurs 
 * pour de futur construction de batiment
 * @author Jaouen MARIE
 * @version 1.0
 */
public class Machine extends Card implements IBuilding {

	/** Liste des constructeurs (Builder) permettant la construction de se batiment */
	private ArrayList<Builder> buildersOnSite;
	/** Nombre de pierre qu'offrira la carte */
	private int stoneOffered;
	/** Nombre de bois qu'offrira la carte */
	private int woodOffered;
	/** Nombre de connaissance qu'offrira la carte */
	private int knowledgeOffered;
	/** Nombre de tuile qu'offrira la carte */
	private int tileOffered;
	/** Nombre de points de victoire (couronne) que rapporte la construction de cette machine */
	private int victoryPoint;
	/** Boolean permettant de savoir si la machine est fini ou non d'être construit */
	private boolean finished;
	/** chemin de l'image de la carte en mode chantier */
	private String path_chantier;
	/** chemin de l'image de la carte en mode fini */
	private String path_fini;

	/**
	 * Constructeur de la classe Building permettant l'initialisation des différents paramètres 
	 * de la carte de jeu
	 * @param name nom de la machine
	 * @param stone nombre de pierre demandé pour sa construction
	 * @param wood nombre de bois demandé pour sa construction
	 * @param knowledge nombre de points de connaissance demandé pour sa construction
	 * @param tile nombre de tuile demandé pour sa construction
	 * @param victoryPoint nombre de points de victoire que rapporte la machine pour sa construction
	 * @param stoneOffered nombre de pierre qu'offrira la carte
	 * @param woodOffered nombre de bois qu'offrira la carte
	 * @param knowledgeOffered nombre de connaissance qu'offrira la carte
	 * @param tileOffered nombre de tuile qu'offrira la carte
	 * @param path_chantier chemin de l'image de la carte en mode chantier
	 * @param path_fini chemin de l'image de la carte en mode fini
	 */
	public Machine(String name, int stone, int wood, int knowledge, int tile, int victoryPoint, int stoneOffered, int woodOffered, int knowledgeOffered, int tileOffered, String path_chantier, String path_fini) {
		super(name, stone, wood, knowledge, tile);
		if(victoryPoint >= 0) this.victoryPoint = victoryPoint;
		else throw new IllegalArgumentException("Error - Machine - victoryPoint need to be upper or equal than 0");
		if(stoneOffered >= 0) this.stoneOffered = stoneOffered;
		else throw new IllegalArgumentException("Error - Machine - stoneOffered need to be upper or equal than 0");
		if(woodOffered >= 0) this.woodOffered = woodOffered;
		else throw new IllegalArgumentException("Error - Machine - woodOffered need to be upper or equal than 0");
		if(knowledgeOffered >= 0) this.knowledgeOffered = knowledgeOffered;
		else throw new IllegalArgumentException("Error - Machine - knowledgeOffered need to be upper or equal than 0");
		if(tileOffered >= 0) this.tileOffered = tileOffered;
		else throw new IllegalArgumentException("Error - Machine - tileOffered need to be upper or equal than 0");
		if(path_chantier != null) this.path_chantier = path_chantier;
		else throw new IllegalArgumentException("Error - Machine - path_chantier can't be null");
		if(path_fini != null) this.path_fini = path_fini;
		else throw new IllegalArgumentException("Error - Machine - path_fini can't be null");
		this.finished = false;
		this.buildersOnSite = new ArrayList<Builder>();
	}

	/**
	 * Méthode getter permettant de récupérer le nombre de pierre qu'offrira la carte
	 * @return nombre de pierre qu'offrira la carte
	 */
	public int getStoneOffered() {
		return this.stoneOffered;
	}

	/**
	 * Méthode getter permettant de récupérer le nombre de bois qu'offrira la carte
	 * @return nombre de bois qu'offrira la carte
	 */
	public int getWoodOffered() {
		return this.woodOffered;
	}

	/**
	 * Méthode getter permettant de récupérer le nombre de connaissance qu'offrira la carte
	 * @return nombre de connaissance qu'offrira la carte
	 */
	public int getKnowledgeOffered() {
		return this.knowledgeOffered;
	}

	/**
	 * Méthode getter permettant de récupérer le nombre de tuile qu'offrira la carte
	 * @return nombre de tuile qu'offrira la carte
	 */
	public int getTileOffered() {
		return this.tileOffered;
	}

	/**
	 * Méthode permettant de récuperer le nombre de points de victoire de la carte batiment/machine
	 * @return points de victoire
	 */
	public int getVictoryPoint() { return this.victoryPoint; }

	/**
	 * Méthode permettant de récupérer les construteurs sur le batiment/machine pour sa construction
	 * @return liste des constructeurs
	 */
	public ArrayList<Builder> getBuildersOnSite() { return this.buildersOnSite; }

	/**
	 * Méthode getter retournant la valeur du chemin de l'image de la carte en mode chantier
	 * @return chemin de l'image
	 */
	public String getPathChantier() {
		return this.path_chantier;
	}

	/**
	 * Méthode getter retournant la valeur du chemin de l'image de la carte en mode fini
	 * @return chemin de l'image
	 */
	public String getPathFini() {
		return this.path_fini;
	}

	/**
	 * Méthode permettant d'ajouter un construteur à un batiment/machine pour réaliser sa construction
	 * @param b constructeur
	 */
	public void addBuilder(Builder b) {
		if(b != null) {
			this.buildersOnSite.add(b);
		}
		else throw new IllegalArgumentException("Error - Machine - passed parameter Builder can't be null");
	}

	/**
	 * Méthode permettant d'enlever tout les constructeurs, sur la construction du batiment/machine,
	 * quand celle-ci est fini
	 */
	public void removeBuilders() { this.buildersOnSite.clear(); }

	/**
	 * Méthode getter permettant de savoir si le batiment/machine a fini d'être construit
	 * @return vrai si construction fini, false sinon
	 */
	public boolean getFinished() { return this.finished; }

	/**
	 * Méthode permettant de regarder si la construction est censé être fini. Elle va regarder
	 * les valeurs des différents paramètres des constructeurs et les comparer avec la demande
	 * du batiment/machine
	 * @param p joueur à qui l'on va rajouter les écus si le chantier est bien fini
	 */
	public void checkIsFinished(Player p) {
		int allStone = 0;
		int allWoods = 0;
		int allKnowledge = 0;
		int allTile = 0;

		if(this.buildersOnSite.size() > 0) {
			for (Builder b : this.buildersOnSite) {
				allStone += b.getStone();
				allWoods += b.getWood();
				allKnowledge += b.getKnowledge();
				allTile += b.getTile();
			}
		}

		if(allStone >= this.stone && allWoods >= this.wood && allKnowledge >= this.knowledge && allTile >= this.tile) {
			if(!this.finished) this.finished = true;
			this.removeBuilders();
		}
	}


	/**
	 * Méthode retournant une chaîne de caractère structurant les différentes données de la carte machine, 
	 * permettant son affichage dans un terminal
	 * @return chaîne de caractère des données de la machine
	 */
	public String toString() {
		String padding = "";
		for(int i = 0; i < 27-this.name.length();i++) padding += " ";

		String ret = this.name + padding + "|	"
				+ this.stone + "," + this.stoneOffered + "	|	"
				+ this.wood + "," + this.woodOffered + "	|	"
				+ this.knowledge + "," + this.knowledgeOffered + "	|	"
				+ this.tile + "," + this.tileOffered + "	|	"
				+ this.victoryPoint + "	|		|	"
				;

		if(this.finished) ret += "finished	|	";
		else if(this.buildersOnSite.size() > 0) ret += "in progress	|	";
		else ret += "not started	|	";
		
		if(this.buildersOnSite.size() > 0) {
			for (Builder b : this.buildersOnSite) {
				ret += b.getName() + ", ";
			}
		}
		return ret;
	}

}

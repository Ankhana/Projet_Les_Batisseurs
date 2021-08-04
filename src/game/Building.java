package game;

//import de la classe arraylist pour une meilleur gestion des tableaux
import java.util.ArrayList;

/**
 * Classe Buildig représentant une carte de jeu d'un batiment, carte de jeu permettant de gagner 
 * des points de victoire après avoir fini sa construction
 * @author Jaouen MARIE
 * @version 1.0
 */
public class Building extends Card implements IBuilding {

	/** Liste des constructeurs (Builder) permettant la construction de se batiment */
	private ArrayList<Builder> buildersOnSite;
	/** Nombre d'écus rapporté par la construction de se batiment */
	private int ecus;
	/** Nombre de points de victoire (couronne) que rapporte la construction de ce batiment */
	private int victoryPoint;
	/** Boolean permettant de savoir si le batiment est fini ou non d'être construit */
	private boolean finished;
	/** chemin de l'image de la carte en mode chantier */
	private String path_chantier;
	/** chemin de l'image de la carte en mode fini */
	private String path_fini;

	/**
	 * Constructeur de la classe Building permettant l'initialisation des différents paramètres 
	 * de la carte de jeu
	 * @param name nom du batiment
	 * @param stone nombre de pierre demandé pour sa construction
	 * @param wood nombre de bois demandé pour sa construction
	 * @param knowledge nombre de points de connaissance demandé pour sa construction
	 * @param tile nombre de tuile demandé pour sa construction
	 * @param victoryPoint nombre de points de victoire que rapporte le batiment pour sa construction
	 * @param ecus nombre d'écus que rapporte le batiment pour sa construction
	 * @param path_chantier chemin de l'image de la carte en mode chantier
	 * @param path_fini chemin de l'image de la carte en mode fini
	 */
	public Building(String name, int stone, int wood, int knowledge, int tile, int victoryPoint, int ecus, String path_chantier, String path_fini) {
		super(name, stone, wood, knowledge, tile);
		if(victoryPoint >= 0) this.victoryPoint = victoryPoint;
		else throw new IllegalArgumentException("Error - Building - victoryPoint need to be upper or equal than 0");
		if(ecus >= 0) this.ecus = ecus;
		else throw new IllegalArgumentException("Error - Building - ecus need to be upper or equal than 0");
		if(path_chantier != null) this.path_chantier = path_chantier;
		else throw new IllegalArgumentException("Error - Building - path_chantier can't be null");
		if(path_fini != null) this.path_fini = path_fini;
		else throw new IllegalArgumentException("Error - Building - path_fini can't be null");
		this.buildersOnSite = new ArrayList<Builder>();
		this.finished = false;
	}

	/**
	 * Méthode getter permettant de récupérer le nombre d'écus que rapporte la construction du batiment
	 * @return nombre d'écus
	 */
	public int getEcus() {
		return this.ecus;
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
		if(!this.finished) {
			if(b != null) {
				this.buildersOnSite.add(b);
			}
			else throw new IllegalArgumentException("Error - Building - passed parameter Builder can't be null");
		}else System.out.println("The construction of this building is finished");
	}

	/**
	 * Méthode permettant d'enlever tout les constructeurs, sur la construction du batiment/machine,
	 * quand celle-ci est fini
	 */
	public void removeBuilders() { 
		for (Builder builder : buildersOnSite) {
			builder.setOnBuildingSite(false);
		}
		this.buildersOnSite.clear(); 
	}

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
			this.finished = true;
			p.modifyNbEcus(this.ecus);
			this.removeBuilders();
		}
	}

	/**
	 * Méthode retournant une chaîne de caractère structurant les différentes données de la carte batiment, 
	 * permettant son affichage dans un terminal
	 * @return chaîne de caractère des données du batiment
	 */
	public String toString() {
		String padding = "";
		for(int i = 0; i < 27-this.name.length();i++) padding += " ";

		String ret = this.name + padding + "|	"
				+ this.stone + "	|	"
				+ this.wood + "	|	"
				+ this.knowledge + "	|	"
				+ this.tile + "	|	"
				+ this.victoryPoint + "	|	 "
				+ this.ecus + "	|	"
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
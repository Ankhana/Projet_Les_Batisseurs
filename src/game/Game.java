package game;

import java.io.Serializable;
import util.InputReader;
import util.SaveReloadGame;
import view.Frame;

/**
 * Classe Game représentant la partie entière du jeu. Elle fera la gestion de la configuration de 
 * la partie et des différents tours de jeu des joueurs.
 * @author Jaouen MARIE
 * @version 1.0
 */
public class Game  implements Serializable{

	/** Joueur numéro un */
	private Player player1;
	/** Joueur numéro deux */
	private Player player2;
	/** Joueur numéro trois */
	private Player player3;
	/** Joueur numéro quatre */
	private Player player4;
	/** Deck de carte du plateau de jeu */
	private Deck gameDeck;
	/** Mode de jeu (nombre de joueur, humain ou automatique) */
	private Mode mode;
	/** Joueur pour lequel c'est son tour de jouer */
	private Player currentPlayer;
	/** Frame de l'interface graphique */
	private Frame frame;

	/**
	 * Constructeur de la classe Game permettant l'initialisation des différents paramètres 
	 * de la partie
	 */
	public Game() {
		System.out.println("\u001B[35m" + "1) TEXTUEL");
		System.out.println("2) GRAPHIQUE");
		System.out.println("3) QUITTER" + "\u001B[0m");
		System.out.println("\u001B[94m" + "Quel mode d'interface souhaitez-vous utiliser ?" + "\u001B[0m");
		int modeInput = InputReader.readIntUserInput();
		switch (modeInput) {
			case 1:
				this.gameDeck = new Deck("./data/builders.txt","./data/buildings.txt","./data/machines.txt");
				this.menu();
				break;
			case 2:
				this.gameDeck = new Deck("./data/builders.txt","./data/buildings.txt","./data/machines.txt");
				frame = new Frame(this);
				break;
			case 3:
				System.exit(0);
				break;
			default:

		}
	}

	/**
	 * Constructeur de la classe Game permettant l'initialisation des différents paramètres 
	 * de la partie, utilisé majoritairement pour les tests
	 * @param mode mode de jeu
	 * @param p1 joueur 1
	 * @param p2 joueur 2
	 * @param p3 joueur 3
	 * @param p4 joueur 4
	 */
	public Game(Mode mode, Player p1, Player p2, Player p3, Player p4) {
		this.gameDeck = new Deck("./data/builders.txt","./data/buildings.txt","./data/machines.txt");
		this.mode = mode;
		this.player1 = p1;
		this.player2 = p2;
		this.player3 = p3;
		this.player4 = p4;
		this.currentPlayer = p1;
	}

	/**
	 * Méthode permettant d'initialiser les différents paramètres d'une nouvelle partie de jeu
	 */
	public void startGame() {
		System.out.println("\u001B[94m" + "Quel mode de jeu souhaiter vous (de AA à HHHH avec A = IA et H = Humain, le mode par défaut est HHHH)" + "\u001B[0m");
		String modeInput = InputReader.readStringUserInput();
		if(modeInput.equalsIgnoreCase("AA")) this.mode = Mode.AA;
		else if(modeInput.equalsIgnoreCase("AH")) this.mode = Mode.AH;
		else if(modeInput.equalsIgnoreCase("HH")) this.mode = Mode.HH;
		else if(modeInput.equalsIgnoreCase("AAA")) this.mode = Mode.AAA;
		else if(modeInput.equalsIgnoreCase("AAH")) this.mode = Mode.AAH;
		else if(modeInput.equalsIgnoreCase("AHH")) this.mode = Mode.AHH;
		else if(modeInput.equalsIgnoreCase("HHH")) this.mode = Mode.HHH;
		else if(modeInput.equalsIgnoreCase("AAAA")) this.mode = Mode.AAAA;
		else if(modeInput.equalsIgnoreCase("AAAH")) this.mode = Mode.AAAH;
		else if(modeInput.equalsIgnoreCase("AAHH")) this.mode = Mode.AAHH;
		else if(modeInput.equalsIgnoreCase("AHHH")) this.mode = Mode.AHHH;
		else  this.mode = Mode.HHHH;
		
		this.gameDeck = new Deck("./data/builders.txt","./data/buildings.txt","./data/machines.txt");
		
		this.initializePlayer();

		this.start();
	}

	/**
	 * Méthode permettant de lancer la partie de jeu
	 */
	public void start() {
		boolean finished = false;
		Player lastPlayer = player2;
		if(this.mode == Mode.AAA || this.mode == Mode.AAH || this.mode == Mode.AHH || this.mode == Mode.HHH) lastPlayer = this.player3;
		if(this.mode == Mode.AAAA || this.mode == Mode.AAAH || this.mode == Mode.AAHH || this.mode == Mode.AHHH || this.mode == Mode.HHHH) lastPlayer = this.player4;


		while (!finished || this.currentPlayer != lastPlayer) {
			this.currentPlayer.play();
			if(this.currentPlayer.getPlayerDeck().getVictoryPoint() >= 17) finished = true;
			this.changeCurrentPlayer();
		}
		this.endGame();
	}

	/**
	 * Méthode permettant de mettre fin à la partie et d'afficher le gagnant
	 */
	public void endGame() {
		int vctPt1 = this.player1.getPlayerDeck().getVictoryPoint() + this.player1.getNbEcus()/10;
		int vctPt2 = this.player2.getPlayerDeck().getVictoryPoint() + this.player2.getNbEcus()/10;
		Player winner;

		if(vctPt1 > vctPt2) winner = player1;
		else if (vctPt1 == vctPt2) {
			if(this.player1.getPlayerDeck().getVictoryPoint() > this.player2.getPlayerDeck().getVictoryPoint()) winner = this.player1;
			else if (this.player1.getPlayerDeck().getVictoryPoint() == this.player2.getPlayerDeck().getVictoryPoint()) {
				if(this.player1.getNbEcus() > this.player2.getNbEcus()) winner = player1;
				else winner = player2;
			}else winner = player2;
		}else winner = player2;

		if(this.mode == Mode.AAA || this.mode == Mode.AAH || this.mode == Mode.AHH || this.mode == Mode.HHH) {
			int vctPt3 = this.player3.getPlayerDeck().getVictoryPoint() + this.player3.getNbEcus()/10;
			int vctPtW = winner.getPlayerDeck().getVictoryPoint() + winner.getNbEcus()/10;
			
			if(vctPt3 > vctPtW) winner = player3;
			else if (vctPt3 == vctPtW) {
				if(this.player3.getPlayerDeck().getVictoryPoint() > winner.getPlayerDeck().getVictoryPoint()) winner = this.player3;
				else if (this.player3.getPlayerDeck().getVictoryPoint() == winner.getPlayerDeck().getVictoryPoint()) {
					if(this.player3.getNbEcus() > winner.getNbEcus()) winner = player3;
				}
			}
		}
		if(this.mode == Mode.AAAA || this.mode == Mode.AAAH || this.mode == Mode.AAHH || this.mode == Mode.AHHH || this.mode == Mode.HHHH) {
			int vctPt4 = this.player4.getPlayerDeck().getVictoryPoint() + this.player4.getNbEcus()/10;

			int vctPtW = winner.getPlayerDeck().getVictoryPoint() + winner.getNbEcus()/10;
			
			if(vctPt4 > vctPtW) winner = player4;
			else if (vctPt4 == vctPtW) {
				if(this.player4.getPlayerDeck().getVictoryPoint() > winner.getPlayerDeck().getVictoryPoint()) winner = this.player4;
				else if (this.player4.getPlayerDeck().getVictoryPoint() == winner.getPlayerDeck().getVictoryPoint()) {
					if(this.player4.getNbEcus() > winner.getNbEcus()) winner = player4;
				}
			}
		}

		int vctPtW = winner.getPlayerDeck().getVictoryPoint() + winner.getNbEcus()/10;
		System.out.println("\n\n\n========================================================================================================================================================================================");
		System.out.println("Le premier Bâtisseur du Royaume est " + winner.getName() + " avec " + vctPtW + " points de victoire !");
		System.out.println("========================================================================================================================================================================================\n\n\n");
		this.menu();

	}

	/**
	 * Méthode permettant d'afficher le menu du jeu
	 */
	private void menu() {
		System.out.println("\u001B[36m");
		System.out.println(" +-+-+-+ +-+-+-+-+-+-+-+-+-+-+ +-+ +-+-+-+-+-+-+-+-+-+");
		System.out.println(" |L|e|s| |B|a|t|i|s|s|e|u|r|s| |:| |M|o|y|e|n|-|A|g|e|");
		System.out.println(" +-+-+-+ +-+-+-+-+-+-+-+-+-+-+ +-+ +-+-+-+-+-+-+-+-+-+");
		System.out.println("\u001B[0m");
		System.out.println("Bienvenue sur le jeu Les Bâtisseurs : Moyen-Âge");
		System.out.println("");
		
		boolean menu = false;

		while(!menu) {
			System.out.println("\u001B[35m" + "1) JOUER");
			System.out.println("2) CHARGER");
			System.out.println("3) REGLES");
			System.out.println("4) QUITTER" + "\u001B[0m");

			System.out.println("\u001B[94m" + "Votre choix ?" + "\u001B[0m");
			int input = InputReader.readIntUserInput();

			switch (input) {
				case 1:
					this.startGame();
					menu = true;
					break;
				case 2:
					this.charger();
					menu = true;
					break;
				case 3:
					regles();
					break;
				case 4:
					System.exit(0);
					break;
				default:
					System.err.println("Veuillez choisir l'un des choix proposé");
					menu = false;
					break;
			}

		}
	}

	/**
	 * Méthode permettant de charger la sauvegarde d'une partie
	 */
	public void charger() {
		Game g = SaveReloadGame.fileReaderO("./data/save.txt");
		this.player1 = g.getPlayer1();
		this.player2 = g.getPlayer2();
		this.player3 = g.getPlayer3();
		this.player4 = g.getPlayer4();
		this.gameDeck = g.getDeck();
		this.mode = g.getMode();
		this.currentPlayer = g.getCurrentPlayer();
		System.out.println("\n/!\\ PARTIE CHARGÉ !\n");
		this.start();
	}

	/**
	 * Méthode permettant d'afficher les règles du jeu
	 */
	private static void regles() {
		String regles = "\n\n****** RÈGLES DU JEU LES BATISSEURS : MOYEN-ÂGE ******\n\n\n"
				+ "But du jeu\n-----------------------------------------------\n"
				+ "Les joueurs doivent accumuler le plus de points de victoire en construisant des bâtiments.\n\n\n"
				+ "Affichage pendant la partie\n-----------------------------------------------\n"
				+ "1. Les informations du joueur courant (nom, nombre d'écus, nombre de tour, deck)\n"
				+ "2. Le deck de la partie\n"
				+ "3. Les différentes options qui sont possibles\n\n"
				+ "Tour de jeu\n-----------------------------------------------\n"
				+ "Lors de son tour de jeu, un joueur dispose de 3 actions gratuites auxquelles il peut, s’il le souhaite, ajouter une ou plusieurs actions payantes. Une action payante coûte 5 Écus. Avec ses actions un joueur peut :\n"
				+ "1. Prendre un ou plusieurs Écus\n"
				+ "2. Ouvrir un Chantier\n"
				+ "3. Recruter un Ouvrier\n"
				+ "4. Envoyer travailler un Ouvrier\n"
				+ "5. Finir votre tour\n"
				+ "6. Options\n\n"
				+ "Prendre des écus - coût = 1 / 2 / 3 actions\n"
				+ "• Pour 1 action, il peut prendre 1 Écu,\n"
				+ "• Pour 2 actions, il peut prendre 3 Écus,\n"
				+ "• Pour 3 actions, il peut prendre 6 Écus.\n\n"
				+ "Ouvrir un Chantier - coût = 1 action\n"
				+ "• Permet de récupérer un batiment que vous allez pouvoir construire plus tard\n\n"
				+ "Recruter un Ouvrier - coût = 1 action\n"
				+ "• Permet de récupérer un ouvrier que vous allez pouvoir utiliser pour construire des batiments plus tard\n\n"
				+ "Envoyer travailler un Ouvrier - coût = variable\n"
				+ "• Le joueur pose un de ses Ouvriers sur Bâtiment en Chantier, de façon à ce que les ressources produites par l'ouvrier répondent au besoin du bâtiment. Une fois un Ouvrier engagé sur un Chantier, il ne peut plus être déplacé tant que le Chantier n’est pas terminé.\n"
				+ "• Pendant un tour de jeu, Envoyer travailler un Ouvrier sur un Chantier, quel que soit le nombre d’Ouvriers déjà présents sur ce Chantier, ne coûte qu’une action.\n"
				+ "• Cependant, Envoyer travailler un deuxième Ouvrier sur le même Chantier au cours du même tour coûte 2 actions !\n"
				+ "• Envoyer travailler un troisième Ouvrier, au cours du même tour et toujours sur le même Chantier, coûte 3 actions, et ainsi de suite.\n"
				+ "• Par contre, Envoyer travailler un Ouvrier sur un Chantier puis, Envoyer travailler un Ouvrier (un autre ou le même si le Bâtiment sur lequel il se trouvait est terminé) sur un autre Chantier pendant le même tour ne coûte que 2 actions, soit 1 par Ouvrier\n\n"
				+ "Finir votre tour - coût = 0 action\n"
				+ "• Permet de mettre fin à son tour\n\n"
				+ "Options\n"
				+ "• Permet de sauvegarder ou de quitter la partie\n\n\n"
				+ "Terminer un batiment\n-----------------------------------------------\n"
				+ "Vous rapporte des écus et des points de victoire si c'est un batiment normal et, des points de victoire et un nouvel ouvrier si c'est une machine (ce transforme en ouvrier en offrant des ressources).\n\n\n"
				+ "Fin de partie\n-----------------------------------------------\n"
				+ "Lorsqu’un joueur, à la fin de son tour, atteint ou dépasse 17 points de victoire (en comptant les Bâtiments et les"
				+ "Machines mais pas les Écus), il déclenche la fin de partie. Seuls les joueurs qui n’ont pas encore joué lors de ce"
				+ "tour peuvent encore le faire, de façon à ce que tout le monde ait joué autant de tours. Ainsi, si c’est le premier"
				+ "joueur qui déclenche la fin de partie, tous les autres joueurs doivent encore jouer une fois. A l’opposé, si c’est"
				+ "le dernier joueur qui déclenche la fin de partie, le jeu s’arrête à la fin de son tour.\n\n"
				+ "Chaque joueur additionne alors les points de victoire des Bâtiments et des Machines terminés, auxquels il ajoute 1"
				+ "point par tranche complète de 10 Écus encore en sa possession. Celui qui a le plus de points de victoire devient le"
				+ "Premier Bâtisseur du Royaume et est déclaré vainqueur ! En cas d’égalité, c’est le joueur avec le plus de points de"
				+ "victoire grâce à ses bâtiments qui est le vainqueur. S’il y a encore égalité, c’est celui qui a le plus de pièces qui l’emporte.\n\n"
		;
		System.out.println(regles);
	}

	/**
	 * Méthode permettant la sauvegarde de la partie
	 */
	public void saveGame() {
		SaveReloadGame.fileWriterO("./data/save.txt", this);
		System.out.println("\n/!\\ PARTIE SAUVEGARDÉ !\n");
	}

	/**
	 * Méthode permettant d'initialiser les différents joueurs en fonction du mode de jeu choisi
	 */
	private void initializePlayer() {
		switch (this.mode) {
			case AA:
				System.out.println("\u001B[94m" + "Nom du joueur 1 :" + "\u001B[0m");
				this.player1 = new AutoPlayer(InputReader.readStringUserInput(), 15, this, Difficulty.EASY);
				System.out.println("\u001B[94m" + "Nom du joueur 2 :" + "\u001B[0m");
				this.player2 = new AutoPlayer(InputReader.readStringUserInput(), 15, this, Difficulty.EASY);
				break;
			case AH:
				System.out.println("\u001B[94m" + "Nom du joueur 1 :" + "\u001B[0m");
				this.player1 = new AutoPlayer(InputReader.readStringUserInput(), 15, this, Difficulty.EASY);
				System.out.println("\u001B[94m" + "Nom du joueur 2 :" + "\u001B[0m");
				this.player2 = new HumanPlayer(InputReader.readStringUserInput(), 15, this);
				break;
			case HH:
				System.out.println("\u001B[94m" + "Nom du joueur 1 :" + "\u001B[0m");
				this.player1 = new HumanPlayer(InputReader.readStringUserInput(), 15, this);
				System.out.println("\u001B[94m" + "Nom du joueur 2 :" + "\u001B[0m");
				this.player2 = new HumanPlayer(InputReader.readStringUserInput(), 15, this);
				break;
			case AAA:
				System.out.println("\u001B[94m" + "Nom du joueur 1 :" + "\u001B[0m");
				this.player1 = new AutoPlayer(InputReader.readStringUserInput(), 15, this, Difficulty.EASY);
				System.out.println("\u001B[94m" + "Nom du joueur 2 :" + "\u001B[0m");
				this.player2 = new AutoPlayer(InputReader.readStringUserInput(), 15, this, Difficulty.EASY);
				System.out.println("\u001B[94m" + "Nom du joueur 3 :" + "\u001B[0m");
				this.player3 = new AutoPlayer(InputReader.readStringUserInput(), 15, this, Difficulty.EASY);
				break;
			case AAH:
				System.out.println("\u001B[94m" + "Nom du joueur 1 :" + "\u001B[0m");
				this.player1 = new AutoPlayer(InputReader.readStringUserInput(), 15, this, Difficulty.EASY);
				System.out.println("\u001B[94m" + "Nom du joueur 2 :" + "\u001B[0m");
				this.player2 = new AutoPlayer(InputReader.readStringUserInput(), 15, this, Difficulty.EASY);
				System.out.println("\u001B[94m" + "Nom du joueur 3 :" + "\u001B[0m");
				this.player3 = new HumanPlayer(InputReader.readStringUserInput(), 15, this);
				break;
			case AHH:
				System.out.println("\u001B[94m" + "Nom du joueur 1 :" + "\u001B[0m");
				this.player1 = new AutoPlayer(InputReader.readStringUserInput(), 15, this, Difficulty.EASY);
				System.out.println("\u001B[94m" + "Nom du joueur 2 :" + "\u001B[0m");
				this.player2 = new HumanPlayer(InputReader.readStringUserInput(), 15, this);
				System.out.println("\u001B[94m" + "Nom du joueur 3 :" + "\u001B[0m");
				this.player3 = new HumanPlayer(InputReader.readStringUserInput(), 15, this);
				break;
			case HHH:
				System.out.println("\u001B[94m" + "Nom du joueur 1 :" + "\u001B[0m");
				this.player1 = new HumanPlayer(InputReader.readStringUserInput(), 15, this);
				System.out.println("\u001B[94m" + "Nom du joueur 2 :" + "\u001B[0m");
				this.player2 = new HumanPlayer(InputReader.readStringUserInput(), 15, this);
				System.out.println("\u001B[94m" + "Nom du joueur 3 :" + "\u001B[0m");
				this.player3 = new HumanPlayer(InputReader.readStringUserInput(), 15, this);
				break;
			case AAAA:
				System.out.println("\u001B[94m" + "Nom du joueur 1 :" + "\u001B[0m");
				this.player1 = new AutoPlayer(InputReader.readStringUserInput(), 15, this, Difficulty.EASY);
				System.out.println("\u001B[94m" + "Nom du joueur 2 :" + "\u001B[0m");
				this.player2 = new AutoPlayer(InputReader.readStringUserInput(), 15, this, Difficulty.EASY);
				System.out.println("\u001B[94m" + "Nom du joueur 3 :" + "\u001B[0m");
				this.player3 = new AutoPlayer(InputReader.readStringUserInput(), 15, this, Difficulty.EASY);
				System.out.println("\u001B[94m" + "Nom du joueur 4 :" + "\u001B[0m");
				this.player4 = new AutoPlayer(InputReader.readStringUserInput(), 15, this, Difficulty.EASY);
				break;
			case AAAH:
				System.out.println("\u001B[94m" + "Nom du joueur 1 :" + "\u001B[0m");
				this.player1 = new AutoPlayer(InputReader.readStringUserInput(), 15, this, Difficulty.EASY);
				System.out.println("\u001B[94m" + "Nom du joueur 2 :" + "\u001B[0m");
				this.player2 = new AutoPlayer(InputReader.readStringUserInput(), 15, this, Difficulty.EASY);
				System.out.println("\u001B[94m" + "Nom du joueur 3 :" + "\u001B[0m");
				this.player3 = new AutoPlayer(InputReader.readStringUserInput(), 15, this, Difficulty.EASY);
				System.out.println("\u001B[94m" + "Nom du joueur 4 :" + "\u001B[0m");
				this.player4 = new HumanPlayer(InputReader.readStringUserInput(), 15, this);
				break;
			case AAHH:
				System.out.println("\u001B[94m" + "Nom du joueur 1 :" + "\u001B[0m");
				this.player1 = new AutoPlayer(InputReader.readStringUserInput(), 15, this, Difficulty.EASY);
				System.out.println("\u001B[94m" + "Nom du joueur 2 :" + "\u001B[0m");
				this.player2 = new AutoPlayer(InputReader.readStringUserInput(), 15, this, Difficulty.EASY);
				System.out.println("\u001B[94m" + "Nom du joueur 3 :" + "\u001B[0m");
				this.player3 = new HumanPlayer(InputReader.readStringUserInput(), 15, this);
				System.out.println("\u001B[94m" + "Nom du joueur 4 :" + "\u001B[0m");
				this.player4 = new HumanPlayer(InputReader.readStringUserInput(), 15, this);
				break;
			case AHHH:
				System.out.println("\u001B[94m" + "Nom du joueur 1 :" + "\u001B[0m");
				this.player1 = new AutoPlayer(InputReader.readStringUserInput(), 15, this, Difficulty.EASY);
				System.out.println("\u001B[94m" + "Nom du joueur 2 :" + "\u001B[0m");
				this.player2 = new HumanPlayer(InputReader.readStringUserInput(), 15, this);
				System.out.println("\u001B[94m" + "Nom du joueur 3 :" + "\u001B[0m");
				this.player3 = new HumanPlayer(InputReader.readStringUserInput(), 15, this);
				System.out.println("\u001B[94m" + "Nom du joueur 4 :" + "\u001B[0m");
				this.player4 = new HumanPlayer(InputReader.readStringUserInput(), 15, this);
				break;
			case HHHH:
				System.out.println("\u001B[94m" + "Nom du joueur 1 :" + "\u001B[0m");
				this.player1 = new HumanPlayer(InputReader.readStringUserInput(), 15, this);
				System.out.println("\u001B[94m" + "Nom du joueur 2 :" + "\u001B[0m");
				this.player2 = new HumanPlayer(InputReader.readStringUserInput(), 15, this);
				System.out.println("\u001B[94m" + "Nom du joueur 3 :" + "\u001B[0m");
				this.player3 = new HumanPlayer(InputReader.readStringUserInput(), 15, this);
				System.out.println("\u001B[94m" + "Nom du joueur 4 :" + "\u001B[0m");
				this.player4 = new HumanPlayer(InputReader.readStringUserInput(), 15, this);
				break;
			default:
				System.err.println("Error - Game - Choosen mode is not possible");
				break;
		}
		this.currentPlayer = player1;
	}

	/**
	 * Méthode permettant d'initialiser les différents joueurs en fonction du mode de jeu choisi
	 * @param mode mode de jeu
	 * @param name1 nom du joueur 1
	 * @param name2 nom du joueur 2
	 * @param name3 nom du joueur 3
	 * @param name4 nom du joueur 4
	 */
	public void initializeGame(Mode mode, String name1, String name2, String name3, String name4) {
		this.mode = mode;
		switch (this.mode) {
			case AA:
							this.player1 = new AutoPlayer(name1, 15, this, Difficulty.EASY);
							this.player2 = new AutoPlayer(name2, 15, this, Difficulty.EASY);
				break;
			case AH:
							this.player1 = new AutoPlayer(name1, 15, this, Difficulty.EASY);
							this.player2 = new HumanPlayer(name2, 15, this);
				break;
			case HH:
							this.player1 = new HumanPlayer(name1, 15, this);
							this.player2 = new HumanPlayer(name2, 15, this);
				break;
			case AAA:
							this.player1 = new AutoPlayer(name1, 15, this, Difficulty.EASY);
							this.player2 = new AutoPlayer(name2, 15, this, Difficulty.EASY);
							this.player3 = new AutoPlayer(name3, 15, this, Difficulty.EASY);
				break;
			case AAH:
							this.player1 = new AutoPlayer(name1, 15, this, Difficulty.EASY);
							this.player2 = new AutoPlayer(name2, 15, this, Difficulty.EASY);
							this.player3 = new HumanPlayer(name3, 15, this);
				break;
			case AHH:
							this.player1 = new AutoPlayer(name1, 15, this, Difficulty.EASY);
							this.player2 = new HumanPlayer(name2, 15, this);
							this.player3 = new HumanPlayer(name3, 15, this);
				break;
			case HHH:
							this.player1 = new HumanPlayer(name1, 15, this);
							this.player2 = new HumanPlayer(name2, 15, this);
							this.player3 = new HumanPlayer(name3, 15, this);
				break;
			case AAAA:
							this.player1 = new AutoPlayer(name1, 15, this, Difficulty.EASY);
							this.player2 = new AutoPlayer(name2, 15, this, Difficulty.EASY);
							this.player3 = new AutoPlayer(name3, 15, this, Difficulty.EASY);
							this.player4 = new AutoPlayer(name4, 15, this, Difficulty.EASY);
				break;
			case AAAH:
							this.player1 = new AutoPlayer(name1, 15, this, Difficulty.EASY);
							this.player2 = new AutoPlayer(name2, 15, this, Difficulty.EASY);
							this.player3 = new AutoPlayer(name3, 15, this, Difficulty.EASY);
							this.player4 = new HumanPlayer(name4, 15, this);
				break;
			case AAHH:
							this.player1 = new AutoPlayer(name1, 15, this, Difficulty.EASY);
							this.player2 = new AutoPlayer(name2, 15, this, Difficulty.EASY);
							this.player3 = new HumanPlayer(name3, 15, this);
							this.player4 = new HumanPlayer(name4, 15, this);
				break;
			case AHHH:
							this.player1 = new AutoPlayer(name1, 15, this, Difficulty.EASY);
							this.player2 = new HumanPlayer(name2, 15, this);
							this.player3 = new HumanPlayer(name3, 15, this);
							this.player4 = new HumanPlayer(name4, 15, this);
				break;
			case HHHH:
							this.player1 = new HumanPlayer(name1, 15, this);
							this.player2 = new HumanPlayer(name2, 15, this);
							this.player3 = new HumanPlayer(name3, 15, this);
							this.player4 = new HumanPlayer(name4, 15, this);
				break;
			default:
				System.err.println("Error - Game - Choosen mode is not possible");
				break;
		}
		this.currentPlayer = player1;
	}
	

	/**
	 * Méthode permettant le changement de joueur pour faire le tour des différents 
	 * joueur dans l'ordre
	 */
	public void changeCurrentPlayer() {
		if(this.currentPlayer == this.player1) this.currentPlayer = this.player2;
		else if(this.currentPlayer == this.player2 && (this.mode == Mode.AAA || this.mode == Mode.AAH || this.mode == Mode.AHH || this.mode == Mode.HHH || this.mode == Mode.AAAA || this.mode == Mode.AAAH || this.mode == Mode.AAHH || this.mode == Mode.AHHH || this.mode == Mode.HHHH)) this.currentPlayer = this.player3;
		else if(this.currentPlayer == this.player3 && (this.mode == Mode.AAAA || this.mode == Mode.AAAH || this.mode == Mode.AAHH || this.mode == Mode.AHHH || this.mode == Mode.HHHH)) this.currentPlayer = this.player4;
		else this.currentPlayer = this.player1;
	}

	/**
	 * Méthode getter permettant de récupérer le deck de cartes de la partie en cours
	 * @return deck de cartes
	 */
	public Deck getDeck() {
		return this.gameDeck;
	}

	/**
	 * Méthode getter permettant de récupérer l'objet Player du joueur 1
	 * @return joueur 1
	 */
	public Player getPlayer1() {
		return this.player1;
	}

	/**
	 * Méthode getter permettant de récupérer l'objet Player du joueur 2
	 * @return joueur 2
	 */
	public Player getPlayer2() {
		return this.player2;
	}

	/**
	 * Méthode getter permettant de récupérer l'objet Player du joueur 3
	 * @return joueur 3
	 */
	public Player getPlayer3() {
		return this.player3;
	}

	/**
	 * Méthode getter permettant de récupérer l'objet Player du joueur 4
	 * @return joueur 4
	 */
	public Player getPlayer4() {
		return this.player4;
	}

	/**
	 * Méthode getter permettant de récupérer l'objet Player du joueur courant
	 * @return joueur courant
	 */
	public Player getCurrentPlayer() {
		return this.currentPlayer;
	}

	/**
	 * Méthode getter permettant de récupérer le mode de jeu sélectionné pour la partie
	 * @return mode de jeu
	 */
	public Mode getMode() {
		return this.mode;
	}
}

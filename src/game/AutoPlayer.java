package game;

import java.util.Random;

/**
 * Classe AutoPlayer représentant un joueur automatique. Elle utilisera plusieurs niveau d'intelligence 
 * permettant d'adapter le niveaux de jeu du bot
 * @author Jaouen MARIE
 * @version 1.0
 */
public class AutoPlayer extends Player {

	/** Difficulté du joueur bot */
	private Difficulty difficulty;

	/**
	 * Constructeur de la classe AutoPlayer permettant l'initialisation des paramètres du joueur automatique
	 * @param name le nom du bot
	 * @param difficulty le niveau de difficulté de l'IA
	 */
	public AutoPlayer(String name, int nbEcus, Game game, Difficulty difficulty) {
		super(name, nbEcus, game);
		if (difficulty != null) this.difficulty = difficulty;
		else throw new IllegalArgumentException("Error - AutoPlayer - passed parameter difficulty can't be null");
	}

	/**
	 * Méthode permettant au joueur de réaliser son tour en faisant plusieurs actions
	 */
	public void play() {
		while(this.remainingTurn > 0) {
			System.out.println("\n\n\n========================================================================================================================================================================================\n\n");
			System.out.println(this.toString() + "\n\n");
			System.out.println(this.gameDeck.printGameDeck() + "\n");

			Random r = new Random();
			int choice = r.nextInt(5);

			int nbActionUsed = 0;
			int choice_four = 0;

			switch (choice) {
				case 1:
					boolean done1 = false;
					while(!done1) {
						done1 = true;
						int input = r.nextInt(3)+1;
						if(input == 1)this.nbEcus += 1;
						else if(input == 2)this.nbEcus += 3;
						else if(input == 3)this.nbEcus += 6;
						else done1 = false;
						nbActionUsed = input;
					}
					break;
				case 2:
					boolean done2 = false;
					while(!done2) {
						done2 = true;
						int input = r.nextInt(5);
						if(input == 0) {
							this.playerDeck.addBuilding((IBuilding) this.gameDeck.getBuildingCard(0));
							this.gameDeck.removeBuilding((IBuilding) this.gameDeck.getBuildingCard(0));
						}
						else if(input == 1){
							this.playerDeck.addBuilding((IBuilding) this.gameDeck.getBuildingCard(1));
							this.gameDeck.removeBuilding((IBuilding) this.gameDeck.getBuildingCard(1));
						}
						else if(input == 2) {
							this.playerDeck.addBuilding((IBuilding) this.gameDeck.getBuildingCard(2));
							this.gameDeck.removeBuilding((IBuilding) this.gameDeck.getBuildingCard(2));
						}
						else if(input == 3) {
							this.playerDeck.addBuilding((IBuilding) this.gameDeck.getBuildingCard(3));
							this.gameDeck.removeBuilding((IBuilding) this.gameDeck.getBuildingCard(3));
						}
						else if(input == 4) {
							this.playerDeck.addBuilding((IBuilding) this.gameDeck.getBuildingCard(4));
							this.gameDeck.removeBuilding((IBuilding) this.gameDeck.getBuildingCard(4));
						}
						else done2 = false;
						nbActionUsed = 1;
					}
					break;
				case 3:
					boolean done3 = false;
					while(!done3) {
						done3 = true;
						int input = r.nextInt(5);
						if(input == 0) {
							this.playerDeck.addBuilder(this.gameDeck.getBuilderCard(0));
							this.gameDeck.removeBuilder(this.gameDeck.getBuilderCard(0));
						}
						else if(input == 1) {
							this.playerDeck.addBuilder(this.gameDeck.getBuilderCard(1));
							this.gameDeck.removeBuilder(this.gameDeck.getBuilderCard(1));
						}
						else if(input == 2) {
							this.playerDeck.addBuilder(this.gameDeck.getBuilderCard(2));
							this.gameDeck.removeBuilder(this.gameDeck.getBuilderCard(2));
						}
						else if(input == 3) {
							this.playerDeck.addBuilder(this.gameDeck.getBuilderCard(3));
							this.gameDeck.removeBuilder(this.gameDeck.getBuilderCard(3));
						}
						else if(input == 4) {
							this.playerDeck.addBuilder(this.gameDeck.getBuilderCard(4));
							this.gameDeck.removeBuilder(this.gameDeck.getBuilderCard(4));
						}
						else done3 = false;
						nbActionUsed = 1;
					}
					break;
				case 4:
					boolean done4 = false;
					while(!done4) {
						int input1 = r.nextInt(this.playerDeck.getBuildersSize());
						int input2 = r.nextInt(this.playerDeck.getBuildingsSize());
						if(!((IBuilding) this.playerDeck.getBuildingCard(input2)).getFinished()) {
							this.playerDeck.putBuilderOnBuildingSite(input2, input1);
							((IBuilding)this.playerDeck.getBuildingCard(input2)).checkIsFinished(this);
							choice_four++;
							nbActionUsed = choice_four;
							this.nbEcus -= this.playerDeck.getBuilderCard(input1).getCost();
							done4 = true;
						}else {
							System.out.println("The construction of this building is finished");
							nbActionUsed = 0;
						}
					}
					break;
				default:
					System.err.println("Please select a valid option");
			}
			this.remainingTurn -= nbActionUsed;
			
			if(this.remainingTurn < 0) {
				this.nbEcus -= (this.remainingTurn*-5);
				this.remainingTurn = 0;
			}
		}
		this.remainingTurn = 3;
	}

	/**
	 * Méthode getter renvoyant la difficulté qu'utilise l'IA du bot
	 * @return difficulté du bot
	 */
	public Difficulty getDifficulty() {
		return this.difficulty;
	}

	/**
	 * Méthode setter permettant de mettre à jour la difficulté du bot
	 * @param d nouvelle difficulté souhaité pour le bot
	 */
	public void setDifficulty(Difficulty d) {
		if(d != null) this.difficulty = d;
		else throw new IllegalArgumentException("Error - AutoPlayer - passed parameter difficulty can't be null");
	}

}
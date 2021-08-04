package game;

import util.InputReader;

/**
 * Classe HumanPlayer représentant un joueur humain
 * @author Jaouen MARIE
 * @version 1.0
 */
public class HumanPlayer extends Player {

	/**
	 * Constructeur de la classe HumanPlayer permettant l'initialisation des différents paramètres 
	 * du joueur humain
	 * @param name nom du joueur
	 * @param nbEcus nombre d'écus pour commencer du joueur
	 * @param game parti de jeu
	 */
	public HumanPlayer(String name, int nbEcus, Game game) {
		super(name, nbEcus, game);
	}

	/**
	 * Méthode permettant au joueur de réaliser son tour en faisant plusieurs actions
	 */
	public void play() {
		boolean finished = false;
		while(!finished) {
			System.out.println("\n\n\n========================================================================================================================================================================================\n\n");
			System.out.println(this.toString() + "\n\n");
			System.out.println(this.gameDeck.printGameDeck() + "\n");
			System.out.println("\u001B[35m" + "1) Prendre des écus");
			System.out.println("2) Prendre un batiment");
			System.out.println("3) Prendre un constructeur");
			System.out.println("4) Mettre un construteur sur un chantier");
			System.out.println("5) Finir mon tour");
			System.out.println("6) Options" + "\u001B[0m");
			System.out.print("\u001B[94m" + "Votre choix ?\n" + "\u001B[0m");
			int choice = InputReader.readIntUserInput();

			int nbActionUsed = 0;
			int choice_four = 0;

			switch (choice) {
				case 1:
					boolean done1 = false;
					while(!done1) {
						done1 = true;
						System.out.println("Nombre d'action à dépenser (1 = 1 écu, 2 = 3 écus, 3 = 6 écus) :");
						int input = InputReader.readIntUserInput();
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
						System.out.println("Batiment que vous souhaiter prendre :");
						int input = InputReader.readIntUserInput();
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
						System.out.println("Ouvrier à recruter : ");
						int input = InputReader.readIntUserInput();
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
						System.out.println("Ouvrier à mettre sur un chantier : ");
						int input1 = InputReader.readIntUserInput();
						System.out.println("Chantier sur lequel mettre l'ouvrier : ");
						int input2 = InputReader.readIntUserInput();
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
				case 5:
					finished = true;
					break;
				case 6:
					System.out.println("\u001B[35m" + "1/ Save game");
					System.out.println("2/ Exit game");
					System.out.print("\u001B[94m" + "Votre choix ?\n" + "\u001B[0m");
					int input = InputReader.readIntUserInput();
					if(input == 1) this.game.saveGame();
					if(input == 2) System.exit(0);
					break;
				default:
					System.err.println("Please select a valid option");
					finished = false;
			}
			this.remainingTurn -= nbActionUsed;
			
			if(this.remainingTurn < 0) {
				this.nbEcus -= (this.remainingTurn*-5);
				this.remainingTurn = 0;
			}
		}
		this.remainingTurn = 3;
	}

}

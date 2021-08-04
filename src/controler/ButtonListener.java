package controler;

import view.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe ButtonListener qui est le listener de tout les boutons du jeu
 * @author Jaouen MARIE
 * @version 1.0
 */
public class ButtonListener implements ActionListener{
    private Frame frame;

    public ButtonListener(Frame f) {
        frame = f;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Jouer")) frame.optionView();
        if(e.getActionCommand().equals("Charger")) frame.chargerView();
        if(e.getActionCommand().equals("Règles du jeu")) frame.reglesView();
        if(e.getActionCommand().equals("Quitter")) System.exit(0);

        if(e.getActionCommand().equals("Prendre des écus")) frame.getGameView().getEcus();
        if(e.getActionCommand().equals("Prendre un batiment")) frame.getGameView().getBuilding();
        if(e.getActionCommand().equals("Prendre un ouvrier")) frame.getGameView().getBuilder();
        if(e.getActionCommand().equals("Mettre un ouvrier sur un chantier")) frame.getGameView().putBuilder();
        if(e.getActionCommand().equals("Finir votre tour")) frame.getGameView().setFinished(true);
        if(e.getActionCommand().equals("Options")) frame.getGameView().options();

        if(e.getActionCommand().equals("Suivant")) frame.getRulesView().suivante();
        if(e.getActionCommand().equals("Precedent")) frame.getRulesView().precedente();
        if(e.getActionCommand().equals("Retour")) frame.menuView();

        if(e.getActionCommand().equals("Lancer")) {
            frame.getOptionsPanel().initializeParam();
            frame.gameView();
        }

        
    }
    
}

package util;

import game.*;
import java.io.*;

/**
 * Classe SaveReloadGame qui est un utilitaire permettant de lire et écrire les données de la partie dans un fichier
 * @author Jaouen MARIE
 * @version 1.0
 */
public class SaveReloadGame {
    
    /**
     * Méthode permettant d'écrire les données de la partie sérialisé dans un fichier dont le chemin est passé en paramètre
     * @param fileName chemin du fichier
     * @param g partie à sérialiser
     */
    public static void fileWriterO(String fileName, Game g){
        try {
            FileOutputStream f = new FileOutputStream(fileName); 
            ObjectOutputStream o = new ObjectOutputStream(f); 
            o.writeObject(g); 
            o.close(); 
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Méthode permettant de lire les données de la partie sérialisé dans un fichier dont le chemin est passé en paramètre
     * @param fileName chemin du fichier
     * @return partie après extraction des données
     */
    public static Game fileReaderO(String fileName) {
        Game ret = null;
        try {
            FileInputStream f = new FileInputStream(fileName);
            ObjectInputStream o = new ObjectInputStream(f);
            ret = (Game) o.readObject();
            o.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }catch (ClassNotFoundException ex){
            System.out.println(ex.getMessage());
        }
        return ret;
    }


}

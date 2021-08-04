package util;

import java.util.ArrayList;
import java.io.FileReader;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Classe RWFile qui est un utilitaire permettant de lire et écrire dans des fichiers
 * @author Jaouen MARIE
 * @version 1.0
 */
public class RWFile {
    /**
     * File read method. Line-by-line reading loop of the data contained in 
     * the file whose name is passed as a parameter. Returns an ArrayList 
     * whose elements are the character lines read from the file
     * @param fileName file path
     * @return ArrayList whose elements are the character lines read from the file
     */
    public static ArrayList < String > readFile(String fileName) {
        ArrayList < String > liste = new ArrayList < String > ();
        try {
            Scanner in = new Scanner(new FileReader(fileName));
            while ( in .hasNextLine()) {
                liste.add(in.nextLine());
            } in.close();
        } catch (FileNotFoundException e) {
            System.out.println("readFile - Fichier non trouve : " + fileName);
        }
        return liste;
    }

    /**
     * File writing method. Line-by-line writing loop of the content of 
     * the Arraylist passed as a parameter in the file whose name is passed as a parameter
     * @param liste content of the Arraylist passed as a parameter
     * @param fileName file path
     */
    public static void writeFile(ArrayList < String > liste, String fileName) {
        try {
            PrintWriter out = new PrintWriter(fileName);
            for (String ligne: liste) out.println(ligne);
            out.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}

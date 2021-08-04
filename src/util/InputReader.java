package util;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Classe InputReader qui est un utilitaire permettant de lire l'entrée d'un utilisateur par le biais d'une console
 * @author Jaouen MARIE
 * @version 1.0
 */
public class InputReader {

    /**
     * Méthode permettant de lire l'entrée d'un entier dans la console
     * @return entier rentré
     */
    public static String readStringUserInput() {
        String ret = "";
        Scanner scan = new Scanner(System.in);
        System.out.print("\u001B[92m" + "$ " + "\u001B[0m");
			try {
				ret = scan.next();
			} catch (Exception e) {
				e.printStackTrace();
			}
        return ret;
    }

    /**
     * Méthode permettant de lire l'entrée d'une string dans la console
     * @return string rentrée
     */
    public static int readIntUserInput() {
        int ret = -1;
        Scanner scan = new Scanner(System.in);
        System.out.print("\u001B[92m" + "$ " + "\u001B[0m");
			try {
				ret = scan.nextInt();
			} catch (InputMismatchException e) {
				e.printStackTrace();
			}catch (NoSuchElementException e) {
				e.printStackTrace();
			}
        return ret;
    }
}

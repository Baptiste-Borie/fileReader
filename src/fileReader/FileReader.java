package fileReader;

import fileReader.concreteFileReader.*;
import java.io.File;

/**
 * Classe principale pour la gestion des fichiers.
 * Elle permet de lire le contenu des fichiers texte et CSV,
 * d'inverser les lignes, de créer des palindromes, et de comparer deux
 * fichiers.
 * 
 * @author Baptiste Borie
 */
public class FileReader {
    private File file;
    private File file2;
    private boolean isSimpleResult = true;

    /**
     * Constructeur par défaut qui initialise le lecteur avec un fichier texte par
     * défaut.
     */
    public FileReader() {
        this.file = new File("assets/test.txt");
        if (!this.file.exists()) {
            System.err.println("Erreur : Le fichier par défaut assets/test.txt n'existe pas.");
            this.file = null;
        }
    }

    /**
     * Constructeur qui initialise le lecteur avec le fichier spécifié.
     * 
     * @param file Chemin du fichier à lire.
     */
    public FileReader(String file) {
        this.file = new File(file);
    }

    /**
     * Constructeur qui initialise le lecteur avec deux fichiers.
     * 
     * @param file  Chemin du premier fichier à lire.
     * @param file2 Chemin du deuxième fichier à lire.
     */
    public FileReader(String file, String file2) {
        this.file = new File(file);
        this.file2 = new File(file2);
    }

    /**
     * Obtient le premier fichier.
     * 
     * @return Le premier fichier.
     */
    public File getFile() {
        return this.file;
    }

    /**
     * Obtient le deuxième fichier.
     * 
     * @return Le deuxième fichier, ou null si non spécifié.
     */
    public File getFile2() {
        return this.file2;
    }

    /**
     * Vérifie si le résultat est simple.
     * 
     * @return true si le résultat est simple, sinon false.
     */
    public boolean getIsSimpleResult() {
        return this.isSimpleResult;
    }

    /**
     * Définit le premier fichier.
     * 
     * @param file Le fichier à définir.
     */
    public void setFile(File file) {
        this.file = file;
    }

    /**
     * Définit le deuxième fichier.
     * 
     * @param file Le fichier à définir.
     */
    public void setFile2(File file) {
        this.file2 = file;
    }

    /**
     * Définit si le résultat doit être simple.
     * 
     * @param simpleRes true pour un résultat simple, sinon false.
     */
    public void setIsSimpleResult(boolean simpleRes) {
        this.isSimpleResult = simpleRes;
    }

    /**
     * Crée un lecteur de fichiers basé sur le type de fichier.
     * 
     * @param file Le fichier à lire.
     * @return Une instance d'AbstractFileReader correspondant au type de fichier.
     * @throws IllegalArgumentException Si le type de fichier n'est pas pris en
     *                                  charge.
     */
    public AbstractFileReader simplerReaderConstructor(File file) {
        try {
            if (file.getPath().endsWith(".txt")) {
                return new FileReaderTxt(file); // Renvoie un lecteur de fichiers texte
            } else if (file.getPath().endsWith(".csv")) {
                return new FileReaderCsv(file); // Renvoie un lecteur de fichiers CSV
            } else {
                throw new IllegalArgumentException("Le type de fichier n'est ni csv ni txt");
            }

        } catch (IllegalArgumentException e) {
            System.err.println("Erreur : " + e.getMessage());
            return null;
        }
    }

    /**
     * Si un ou zéro fichier donnée en entrée affiche le contenu, les lignes
     * inversées,
     * et le palindrome du fichier. Sinon compare deux fichiers.
     */
    public void affiche() {
        AbstractFileReader fileReader = simplerReaderConstructor(this.file);
        if (this.file2 == null) {
            System.out.println("----- Contenu -----");
            System.out.println(fileReader.read());
            System.out.println("----- Lignes inverses -----");
            System.out.println(fileReader.readReverseLine());
            System.out.println("----- Palindrome -----");
            System.out.println(fileReader.readPalindrome());

        } else {
            AbstractFileReader fileReader2 = simplerReaderConstructor(this.file2);
            System.out.println("----- Comparaison -----");
            System.out.println("Fichier 1 : " + fileReader.getFilePath());
            System.out.println("Fichier 2 : " + fileReader2.getFilePath());
            System.out.println(fileReader.compare(fileReader2));
        }
    }

    /**
     * Méthode principale pour exécuter le programme.
     * 
     * @param args Arguments de la ligne de commande pour spécifier les fichiers.
     * @throws IllegalArgumentException Si le nombre de fichier donnée en paramètre
     *                                  et supérieur à 2.
     */
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            System.out.println("argument : " + args[i]);
        }

        FileReader main = null;
        if (args.length == 0) {
            main = new FileReader();
        } else if (args.length == 1) {
            main = new FileReader(args[0]);
        } else if (args.length == 2) {
            main = new FileReader(args[0], args[1]);
        } else {
            throw new IllegalArgumentException(
                    "Trop de fichiers données en paramètres. Vous pouvez donnez 0, 1 ou 2 fichiers en paramètres");
        }
        if (main != null) {
            main.affiche();
        }
    }
}

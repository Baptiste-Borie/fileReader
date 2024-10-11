package fileReader;

import java.io.FileInputStream;

/**
 * Interface définissant les opérations de base pour un lecteur de fichiers.
 * Elle inclut des méthodes pour lire, comparer, et manipuler le contenu d'un
 * fichier, ainsi que pour gérer l'ouverture et la fermeture des flux de
 * fichiers.
 * 
 * @author Baptiste Borie
 */
public interface FileReaderInterface {

    /**
     * Lit le contenu du fichier.
     * 
     * @return Le contenu du fichier sous forme de chaîne de caractères.
     */
    public String read();

    /**
     * Lit le contenu du fichier avec les lignes inversées.
     * 
     * @return Le contenu du fichier avec l'ordre des lignes inversé.
     */
    public String readReverseLine();

    /**
     * Lit le fichier en inversant les mots de chaque ligne pour créer un
     * palindrome.
     * 
     * @return Le contenu du fichier avec les mots inversés en palindrome.
     */
    public String readPalindrome();

    /**
     * Obtient le chemin du fichier.
     * 
     * @return Le chemin du fichier.
     */
    public String getFilePath();

    /**
     * Ouvre le fichier et retourne un FileInputStream.
     * 
     * @param path Le chemin du fichier à ouvrir.
     * @return Un FileInputStream pour accéder au fichier.
     */
    public FileInputStream open(String path);

    /**
     * Ferme le flux d'entrée du fichier.
     * 
     * @param inputStream Le flux d'entrée à fermer.
     */
    public void close(FileInputStream inputStream);

    /**
     * Compare le contenu du fichier actuel avec un autre fichier.
     * 
     * @param file Instance d'AbstractFileReader représentant l'autre fichier à
     *             comparer.
     * @return Une chaîne de caractères décrivant le résultat de la comparaison.
     */
    public String compare(AbstractFileReader file);
}

package fileReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;

/**
 * Classe abstraite représentant un lecteur de fichiers. Elle fournit des
 * méthodes
 * communes pour ouvrir, fermer et récupérer le chemin d'un fichier, ainsi que
 * des méthodes abstraites pour lire le fichier de différentes manières.
 * 
 * @author Baptiste Borie
 */
public abstract class AbstractFileReader implements FileReaderInterface {
    /**
     * Chemin du fichier à lire.
     */
    private String path;

    /**
     * Constructeur prenant le chemin d'accès d'un fichier sous forme de chaîne.
     *
     * @param path Chemin d'accès du fichier à lire.
     */
    public AbstractFileReader(String path) {
        this.path = path;
    }

    /**
     * Constructeur prenant un objet File représentant le fichier.
     *
     * @param file Fichier à lire.
     */
    public AbstractFileReader(File file) {
        this.path = file.getPath();
    }

    /**
     * Récupère le chemin du fichier à lire.
     *
     * @return Le chemin du fichier.
     */
    public String getFilePath() {
        return path;
    }

    /**
     * Ouvre un fichier en utilisant son chemin et retourne un FileInputStream.
     *
     * @param path Le chemin du fichier à ouvrir.
     * @return Un FileInputStream pour le fichier ou null en cas d'échec.
     */
    @Override
    public FileInputStream open(String path) {
        try {
            return new FileInputStream(path);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Ferme le FileInputStream après utilisation.
     *
     * @param inputStream Le flux d'entrée à fermer.
     */
    @Override
    public void close(FileInputStream inputStream) {
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode abstraite pour lire le contenu d'un fichier.
     *
     * @return Le contenu du fichier sous forme de chaîne de caractères.
     */
    @Override
    public abstract String read();

    /**
     * Méthode abstraite pour lire le contenu d'un fichier avec les lignes
     * inversées.
     *
     * @return Le contenu du fichier avec l'ordre des lignes inversé.
     */
    @Override
    public abstract String readReverseLine();

    /**
     * Méthode abstraite pour lire le fichier en inversant les mots de chaque ligne
     * (palindrome).
     *
     * @return Le contenu du fichier avec les mots inversés en palindrome.
     */
    @Override
    public abstract String readPalindrome();

    /**
     * Méthode abstraite pour comparer le contenu de deux fichiers.
     *
     * @param file L'autre fichier à comparer.
     * @return Résultat de la comparaison sous forme de chaîne de caractères.
     */
    @Override
    public abstract String compare(AbstractFileReader file);

    /**
     * Retourne une représentation sous forme de chaîne de l'objet.
     *
     * @return Chaîne représentant l'objet avec le chemin du fichier.
     */
    @Override
    public String toString() {
        return "AbstractFileReader with path " + this.getFilePath();
    }
}

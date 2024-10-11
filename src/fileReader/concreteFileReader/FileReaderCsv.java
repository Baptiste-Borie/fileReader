package fileReader.concreteFileReader;

import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;
import fileReader.*;

/**
 * La classe FileReaderCsv implémente un lecteur de fichiers CSV,
 * héritant des
 * fonctionnalités de la classe abstraite AbstractFileReader. Elle
 * permet de
 * lire, écrire, inverser et comparer le contenu des fichiers CSV ou
 * TXT.
 * 
 * @author Baptiste Borie
 */
public class FileReaderCsv extends AbstractFileReader {

    /**
     * Délimiteurs potentiels pour séparer les colonnes dans un fichier CSV.
     * Le premier délimiteur est la virgule, le second est le point-virgule.
     */
    private final static String[] delimiter = { ",", ";" };

    /**
     * Constructeur prenant un chemin vers le fichier à lire.
     *
     * @param path Chemin du fichier CSV.
     */
    public FileReaderCsv(String path) {
        super(path);
    }

    /**
     * Constructeur prenant un objet File représentant le fichier à lire.
     *
     * @param file Fichier CSV.
     */
    public FileReaderCsv(File file) {
        super(file);
    }

    /**
     * Méthode pour lire le contenu du fichier CSV. La méthode détecte
     * automatiquement le délimiteur (virgule ou point-virgule) utilisé dans le
     * fichier et retourne les données en supprimant ce délimiteur.
     *
     * @return Le contenu du fichier sous forme de chaîne de caractères ou null si
     *         le fichier ne peut pas être lu.
     */
    @Override
    public String read() {
        FileInputStream inputStream = open(getFilePath());
        if (inputStream == null) {
            return null;
        }

        StringBuilder content = new StringBuilder();
        try {
            int c;
            while ((c = inputStream.read()) != -1) {
                content.append((char) c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(inputStream);
        }

        String fileContent = content.toString();

        String detectedDelimiter = delimiter[0]; // Par défaut, on suppose que c'est une virgule
        if (fileContent.contains(delimiter[1])) {
            detectedDelimiter = delimiter[1]; // Si on trouve un point-virgule, on l'utilise
        }

        // On découpe le contenu en lignes, puis en colonnes
        String[] lines = fileContent.split("\n");
        StringBuilder result = new StringBuilder();
        for (String line : lines) {
            String[] columns = line.split(detectedDelimiter);
            for (String column : columns) {
                result.append(column).append("");
            }
            result.append("\n");
        }

        return result.toString();
    }

    /**
     * Méthode pour lire le contenu du fichier CSV avec les lignes inversées.
     *
     * @return Contenu du fichier avec l'ordre des lignes inversé.
     */
    @Override
    public String readReverseLine() {
        String content = this.read();

        String[] lines = content.toString().split("\n");

        // Inverser les lignes
        StringBuilder reversedContent = new StringBuilder();
        for (int i = lines.length - 1; i >= 0; i--) {
            reversedContent.append(lines[i]).append("\n");
        }
        return reversedContent.toString();
    }

    /**
     * Méthode pour lire le contenu du fichier CSV avec les mots de chaque ligne
     * inversés en palindrome.
     *
     * @return Contenu du fichier avec les mots inversés en palindrome.
     */
    @Override
    public String readPalindrome() {
        String content = this.read();

        StringBuilder palindromeContent = new StringBuilder();
        String[] words = content.toString().split("\\s+");
        for (int i = words.length - 1; i >= 0; i--) {
            StringBuilder reversedWord = new StringBuilder(words[i]).reverse();
            palindromeContent.append(reversedWord).append(" ");
        }
        return palindromeContent.toString();
    }

    /**
     * Méthode pour comparer le contenu de ce fichier CSV avec un autre fichier
     * CSV.
     *
     * @param fileReader Instance de AbstractFileReader représentant un autre
     *                   fichier à comparer.
     * @return Résultat de la comparaison des deux fichiers : nombre de lignes et
     *         indication si les fichiers sont identiques ou non.
     */
    @Override
    public String compare(AbstractFileReader fileReader) {
        String content = this.read();

        String content2 = fileReader.read();

        int linesFile1 = content.split("\n").length;
        int linesFile2 = content2.split("\n").length;

        return "Fichier 1 : " + linesFile1 + " lignes\n" +
                "Fichier 2 : " + linesFile2 + " lignes. " +
                (content.equals(content2) ? "\n Les fichiers sont identiques." : "\nLes fichiers sont différents.");
    }
}

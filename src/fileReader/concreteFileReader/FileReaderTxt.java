package fileReader.concreteFileReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;
import fileReader.*;

/**
 * 
 * La classe FileReaderTxt implémente un lecteur de fichiers texte
 * (TXT),
 * héritant des fonctionnalités de la classe abstraite
 * AbstractFileReader.
 * Elle permet de lire, inverser, et comparer le contenu des fichiers
 * TXT.
 * 
 * @author Baptiste Borie
 */
public class FileReaderTxt extends AbstractFileReader {

    /**
     * Constructeur prenant un chemin vers le fichier à lire.
     *
     * @param path Chemin du fichier texte.
     */
    public FileReaderTxt(String path) {
        super(path);
    }

    /**
     * Constructeur prenant un objet File représentant le fichier à lire.
     *
     * @param file Fichier texte.
     */
    public FileReaderTxt(File file) {
        super(file);
    }

    /**
     * Méthode pour lire le contenu du fichier texte. Le fichier est lu
     * caractère par caractère et le contenu est retourné sous forme de chaîne
     * de caractères.
     *
     * @return Le contenu du fichier sous forme de chaîne de caractères ou null
     *         si le fichier ne peut pas être lu.
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

        return content.toString();
    }

    /**
     * Méthode pour lire le contenu du fichier texte avec les lignes inversées.
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
     * Méthode pour lire le contenu du fichier texte avec les mots de chaque ligne
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
     * Méthode pour comparer le contenu de ce fichier texte avec un autre fichier
     * texte.
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

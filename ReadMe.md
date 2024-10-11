# Fonctionnement du lecteur de fichier

Ce programme est un lecteur de fichier Txt ou CSV.

Dans le cas d'un fichier CSV le lecteur ignorera les séparateurs "," ou ";".

Ce programme permet de lire un fichier à l'endroit. De le lire en inversant les lignes (Premiere devient dernière, seconde avant-dernière, ...). Et enfin de lire avec les mots de chaque ligne inversés en palindrome.

# Dossier assets

Afin de faciliter l'utilisation du programme il y a dans le dossier assets 4 fichiers différents :

- `test.txt` contient : `Hello World ! Second phrase.`
- `test2.txt` contient : `Je suis différent`
- `testCSV.csv` contient : `Hello World !; Second phrase.;`
- `testComa.csv` contient :

```
Un autre texte, mais cette fois avec des virgules, pour servir de separateur, d'ailleurs csv veut dire "coma separated value".
```

# Résultat affiché

Le programme accepte 0,1 ou 2 fichiers données en entrée. Pour donné un fichier en entrée il suffit de suivre la commande et d'indiquer son chemin.

- 0 fichier en entrée : Le fichier `test.text` est lancé par défaut.

- 1 fichier en entrée :
  Le programme va afficher le contenu du fichier. Puis son contenu avec les lignes inversé et enfin ou chaque mot est renversé en palindrome.

- 2 fichier en entrée : Le programme va rappelé les deux fichiers choisis. Puis il va affiché le nombre de lignes de chaque fichier et enfin les comparer et renvoyé si il sont identiques ou non.

# Commande ant :

- Pour (juste) compiler : `ant compile`

- Pour générer la javadoc : `ant javadoc`

- Pour nettoyer les dossiers `build` et `doc` : `ant clean`

- Pour lancer le projet sans fichier en entrée : `ant run` ou `ant`

- Pour lancer le projet avec un fichier en entrée : `ant run -Darg1=assets/testComma.csv`

- Pour lancer le projet avec 2 fichier en entrée : `ant run -Darg1=assets/testComma.csv -Darg2=assets/test.txt   `

# Javadoc

Pour générer la javadoc, il faut utiliser la commande `ant javadoc`. Cette
commande génère la javadoc dans le dossier `doc`. Il suffit après de lancer le fichier `index.html` pour naviguer dans la documentation du projet.

# Lancement

La commande `ant run` permet de générer les dossier `build`&`doc` qui contiennet respectivement les classes et la javadoc. Elle permet également de compiler et de lancer le projet. La commande `ant compile` permet seulement de compiler sans lancer le projet.

# Si ant ne fonctionne pas/ n'est pas installé :

```
javac -d build src/fileReader/*.java src/fileReader/**/*.java

java -cp build fileReader.FileReader
java -cp build fileReader.FileReader assets/testComa.csv
java -cp build fileReader.FileReader assets/testComa.csv assets/test.txt

```

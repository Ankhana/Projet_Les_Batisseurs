## +-+-+-+ +-+-+-+-+-+-+-+-+-+-+ +-+ +-+-+-+-+-+-+-+-+-+
## |L|e|s| |B|a|t|i|s|s|e|u|r|s| |:| |M|o|y|e|n|-|A|g|e|
## +-+-+-+ +-+-+-+-+-+-+-+-+-+-+ +-+ +-+-+-+-+-+-+-+-+-+              


#### ============= Description =============

Nous avons une demande de Sébastien LEFÈVRE, Enseignant-chercheur en informatique qui aime les jeux-vidéos. Le client, par nostalgie, souhaite pouvoir jouer au jeu Les Bâtisseurs : Moyen-Âge sur son ordinateur (ou tablette, smartphone…). 

J'ai développé ici le jeu Les Batisseurs : Moyen-Âge qui est un jeu de carte de construction. Le but est de gagner le plus de points en construisant des batiments et en accumulant des écus.

Le site de présentation du jeu : [site](https://studiobombyx.com/jeu/les-batisseurs/)

Le lien vers le PDF des règles du jeu : [règles](https://studiobombyx.com/assets/LES-BATISSEURS_MOYEN-AGE_rulebook_FR.pdf)

#### ============= Limitations =============

Je n'ai pas pu développer une réelle intelligence de jeu, l'interface graphique du plateau de jeu dû à un manque de temps.

#### ============= Commandes =============

 - Compilation (depuis le répertoire ws) : javac -cp ../src/lib/hamcrest-core-1.3.jar:../src/lib/junit-4.12.jar -d ../class ../src/*/*.java ../src/*.java
 
 - Utilisation du build.xml pour la compilation et création du jar (à lancer à la racine) : ant
 
 - Lancement du jar : java -jar Batisseurs_v1.0.jar

/!\ Le fichier jar doit être au même niveau que le dossier data pour fonctionner

#### ============= Arborescence =============

code
├── class
│   ├── controler
│   ├── game
│   ├── util
│   └── view
├── data
│   ├── images
│   │   ├── cartes
│   │   │   ├── batiments
│   │   │   │   ├── chantiers
│   │   │   │   └── finis
│   │   │   ├── machines
│   │   │   │   ├── chantiers
│   │   │   │   └── finis
│   │   │   └── ouvriers
│   │   │       ├── apprentis
│   │   │       ├── compagnons
│   │   │       ├── maîtres
│   │   │       └── manœuves
│   │   └── regles
│   └── musiques
├── doc
│   ├── controler
│   ├── game
│   ├── jquery
│   │   ├── external
│   │   │   └── jquery
│   │   ├── images
│   │   ├── jszip
│   │   │   └── dist
│   │   └── jszip-utils
│   │       └── dist
│   ├── resources
│   ├── util
│   └── view
├── src
│   ├── controler
│   ├── game
│   ├── lib
│   ├── test
│   ├── util
│   └── view
└── ws

43 dossiers et 324 fichiers

Le dossier class contient les codes compilé.
Le dossier data contient toutes les ressources nécessaires au bon fonctionnement du jeu.
Le dossier doc contient lui toute la Javadoc.
Le dossier src contient tous les fichiers sources du code du jeu.

#### ============= Contenu du Manifest =============

Main-Class: GameLauncher

(le fichier Manifest.txt est créer automatique par le fichier buld.xml)

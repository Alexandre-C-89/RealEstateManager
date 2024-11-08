#Real Estate Manager

##Description : 
"Real Estate Manager est une application Android permettant aux agents immobiliers de gérer des propriétés immobilières, incluant l'écran de détail d'un bien, l'ajout,la modification et la suppression de biens. Il sera notament possible si l'agent le souhaite de localiser les biens autour de ça position."

#Fonctionnalités Principales

- Gestion des propriétés (ajout, modification, suppression)
- Conversion de devises (Dollar vers Euro)
- Indication du statut (vendu/disponible) des biens
- Validation et formatage de la date
- Stockage et récupération de données dans une base de données

#Technologies Utilisées

Langage : Kotlin 

Architecture : MVVM

Libraries :
- Jetpack Compose pour l’interface utilisateur
- Hilt pour l’injection de dépendances
- Room pour la gestion de la base de données
- Coroutines pour la gestion de tâches asynchrones
- Flow pour la gestion de données réactives
- APIs : Google Maps ou l’API de localisation

#Installation et Pré-requis
outils nécessaires pour exécuter le projet :

Android Studio Arctic Fox ou version ultérieure

minSdk = 24
targetSdk = 34

Cloner le projet :
- Dans le terminal taper la commande suivante :
git clone https://github.com/Alexandre-C-89/RealEstateManager.git


#Structure du Projet

app/ : Code source de l’application

data/ : Gestion des entités, DAO et Repository pour Room.

designsystem/ : Composants utilisé au sein des différents écrans

domain/ : Contient les différents model de données utilisé dans l'application ainsi que les repository

feature/ : Les différents écrans nécessaire à l'affiche des différentes interfaces utilisateur

navigation/ : La navigation entre les différents écrans nécessaire pour l'utilisateur

util/ : Différentes fonctions utilisé dans l'application


#Captures d'écrans

A venir


Utilisation de l’Application

Expliquer comment naviguer dans l’application (par exemple, cliquer sur un bien pour voir les détails, etc.).

Ajouter des précisions sur les validations des données, comme la validation du format de date.

Auteur

M. Clémençot Alexandre
pseudo GitHub : Alexandre-C-89

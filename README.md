# Real Estate Manager

## Description : 
"Real Estate Manager est une application Android permettant aux agents immobiliers de gérer des propriétés immobilières, incluant l'écran de détail d'un bien, l'ajout,la modification et la suppression de biens. Il sera notament possible si l'agent le souhaite de localiser les biens autour de ça position."

# Fonctionnalités Principales

- Gestion des propriétés (ajout, modification, suppression)
- Conversion de devises (Dollar vers Euro)
- Indication du statut (vendu/disponible) des biens
- Validation et formatage de la date
- Stockage et récupération de données dans une base de données

# Technologies Utilisées

Langage : Kotlin 

Architecture : MVVM

Libraries :
- Jetpack Compose pour l’interface utilisateur
- Hilt pour l’injection de dépendances
- Room pour la gestion de la base de données
- Coroutines pour la gestion de tâches asynchrones
- Flow pour la gestion de données réactives
- APIs : Google Maps ou l’API de localisation

# Installation et Pré-requis
outils nécessaires pour exécuter le projet :

Android Studio Arctic Fox ou version ultérieure

minSdk = 24
targetSdk = 34

Cloner le projet :
- Dans le terminal taper la commande suivante :
git clone https://github.com/Alexandre-C-89/RealEstateManager.git


# Structure du Projet

app/ : Code source de l’application

data/ : Gestion des entités, DAO et Repository pour Room.

designsystem/ : Composants utilisé au sein des différents écrans

domain/ : Contient les différents model de données utilisé dans l'application ainsi que les repository

feature/ : Les différents écrans nécessaire à l'affiche des différentes interfaces utilisateur

navigation/ : La navigation entre les différents écrans nécessaire pour l'utilisateur

util/ : Différentes fonctions utilisé dans l'application


# Captures d'écrans

Écran d'accueil (Home) :

![home](https://github.com/user-attachments/assets/0533a984-97c7-4b76-8d90-fa12f10c28b7)

Écran d'informations :

![details](https://github.com/user-attachments/assets/73b32b58-83ba-46be-9bef-def98f853ccb)

Écran de modification :

![modify](https://github.com/user-attachments/assets/7be13de7-3bbf-4df4-bfd5-35215d18e5e6)

Écran de création :

![create](https://github.com/user-attachments/assets/1b57b5c0-b953-4eb2-a9f6-efa4de59983c)

# Auteur :

M. Clémençot Alexandre
pseudo GitHub : Alexandre-C-89
Linkedin : https://www.linkedin.com/in/alexandre-clemencot/

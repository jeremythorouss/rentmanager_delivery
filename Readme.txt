Copie de jérémy Thorouss
Majeure MIN 
groupe MIN2

comment le lancer:
"tomcat7:run" dans le répertoire du fichier pom.xml

Tache réalisées
Onglet utilisateur: 
visualisation de la liste 
ajouter un utilisateur
modifier utilisateur: le nom de l'utilisateur modifier se trouve en majuscule
supprimer un utilisateur
voir les details de l'utilisateur, les voitures réservées et quand

Onglet vehicle: 
visualisation de la liste 
ajouter un vehicle
modifier vehicle: vehicle
supprimer un vehicle
voir les details de la voiture, les clients qui l'ont réservées et quand

onglet réservation 
visualisation de la liste : avec non pas les ids mais les noms prénom, constructeur et model des réservation
ajouter un réservation
modifier réservation
supprimer une réservation



framework spring implémenté

exigences réalisées supplémentaires
- un client n'ayant pas 18ans ne peut pas être créé:
 retourne une erreur dans le cmd mais ne fait pas planter l'application et ne créé pas le client
 - le nom et le prénom d'un client doivent faire au moins 3 caractères	
 retourne une erreur dans le cmd mais ne fait pas planter l'application et ne créé pas le client
 - une voiture doit avoir un modèle et un constructeur, son nombre de place doit être compris entre 2 et 9
 Un message apparait a l'écran pour le modele et le constructeur, 
pour le nombre de places, refuse de créer le véhicule
 
 
Taches non réalisées
je n'ai pas trouvé pertinent de mettre un onglet details dans la réservation
 par manque de temps:
- une voiture ne peux pas être réservé 2 fois le même jour: tentée mais pas réussies
- une voiture ne peux pas être réservé plus de 7 jours de suite par le même utilisateur
- une voiture ne peux pas être réservé 30 jours de suite sans pause
- si un client ou un véhicule est supprimé, alors il faut supprimer les réservations associées

Lancement du fichier:

.il faut aller dans le dossier C:\Users\votre user\ et enlever les fichiers "RentManagerDatabase"
.ensuite il faut lancer le script "fill database" par java, cela permettra de génerer une base de donnée de façon saine
.ceci afin d'éviter des erreurs de formattage de base de données en passant d'un utilisateur a l'autre
.si les fichiers "RentManagerDatabase" n'existent pas et qu'on lance quand meme le programme par "tomcat7:run", celui ci affiche erreur table vehicule introuvabke
.il va créer les fichiers mais ceci seront vide sans structure


Il est bien sur possible d'ajouter manuellement les clients/voitures/réservation après initialisation 

Difficultées rencontrées tout au long du codage
-	difficultée au niveau du nommage des différents paramètres, que ce soit 
	véhicule,vehicle, ...
	lastname, firstname, name...
-	difficultée au niveau des servelettes et de leur organisation: j'ai choisi de séparer les servelettes en fonctions qu'elles sont les servelettes générales de listing
et les servelettes liées à une fonction précise: create, delete, details, update
-	 plus dur a été bien sur de coder les servelette de l'onglet user
en effet, il s'agit de faire en sorte que tout marche sur un onglet
l'onglet cars n'est pas le plus difficile ensuite
Ce qui a été difficile a été de coder l'onglet rent et l'association entre les 2 autres onglets, le fait qu'un onglet ne puisse prendre que des infos déterminées par d'autres listes.
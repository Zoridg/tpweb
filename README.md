		README

----- Configuration de départ -----

La connexion à la base de données se fait au lancement du contexte grâce à un Listener. Il faut donc modifier l'url de la
base de données, le login et le mot de passe et le remplacer par vos identifiants dans le fichier MyContexteListener.java
Après cela :
mvn clean package compile tomcat7:run

Au niveau du code, vous verrez sûrement des incohérences car il est encore en work in progress (nous voulons optimiser le code
afin de prendre en compte les clés primaires et secondaires et agirent beaucoup plus facilement dessus lors d'un delete ou d'un
update, notamment pour des tuples de clés primaires référençant des clés secondaires).

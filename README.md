# MobileArtifacts

This project will hold all the application needed for the backend of
our mobile project

## Installing the database

The database is hosted in a docker container. To install it
proceed as follow

1) Install docker (https://www.docker.com/)
2) Pull la dernière image de mysql : docker pull mysql:latest
3) Créer le container avec cette commande : docker run --name mobile2018 -e MYSQL_ROOT_PASSWORD=mobile2018 -p 3307:3306 -d mysql:latest


la base de données sera bien crée et écoutera sur le port 3307 avec comme user "root"
et mot de passe "mobile2018"

pour la stoper exécuter un
- docker stop mobile2018

pour la redémarrer faire
- docker start mobile2018
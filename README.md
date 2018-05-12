# MobileArtifacts

This project will hold the application needed for the backend of the mobile project

## Installing the database

The database is hosted in a docker container. To install it
proceed as follow

1) Install docker (https://www.docker.com/)
2) Pull the last mysql snapshot : docker pull mysql:latest
3) Create the docker container : docker run --name mobile2018 -e MYSQL_ROOT_PASSWORD=mobile2018 -p 3307:3306 -d mysql:latest


la base de données sera bien crée et écoutera sur le port 3307 avec comme user "root"
et mot de passe "mobile2018"

Une fois connecté à la base de données avec le client de votre choix, crééz un nouveau schéma appelé 'mobile' 

pour la stopper, exécuter un
- docker stop mobile2018

pour la redémarrer, faire
- docker start mobile2018

Le lancement des tests nécessite de disposer du navigateur Google Chrome à jour (version 106 ou 107).
Les OS pris en charge sont Windows, Mac version M1, Mac classique et Linux.

Les webdrivers ont déjà téléchargés et ajoutés au projet pour vous faire gagner du temps.

Commencez par vérifier la version de Chrome dont vous disposez, dans "à propos de Chrome".
Si c'est la version 106, il n'y a pas de manipulation supplémentaires à faire.
Si c'est la version 107, alors il faudra modifier le fichier FluentWebTest.java sous src/test/java/basicTest, à la ligne 17. Il faudra alors décommander la ligne 18 et commenter la ligne 17.

Ceci va permettre de sélectionner le bon driver en fonction de la version de chrome installée sur votre machine.

Il est recommandé d'exécuter au moins une fois le fichier chromedriver correspondant à votre version de chrome et à votre OS, pour éviter les problèmes de droit d'exécution sur le fichier.
Pour cela, localisez le bon fichier sous src/drivers/chrome. Choisir le dossier 106 ou 107 selon votre version de Chrome, puis choisir le dossier correspondant à votre OS. Le fichier exécutable se trouve ici. Double cliquez dessus et exécutez le avec le terminal afin de lui donner le droit d'exécution sur votre machine

Vous êtes à présent prêt à exécuter les tests.

Les tests se trouvent dans le fichier TopMoviesTests.java sous src/test/java/basicTest.
Chaque question a été traitée dans une fonction de test (question 2 et 3 réunies en une seule fonction).
Il suffira alors de faire un clic droit sur la fonction de test que vous souhaitez exécuter et la jouer.
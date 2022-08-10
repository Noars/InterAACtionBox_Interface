# interaactionBoxOS-AFSR

Lien vers la dernière release:
https://github.com/AFSR/interaactionBoxOS-AFSR/releases/tag/0.0.1

# Google Chrome Portable

Version actuellement utilisé -> 104.0.5112.81

Si on change de version, pour pouvoir envoie le tout en ligne sur git il faut :
* Aller dans GoogleChromePortable/App/Chrome-bin/104.0.5112.81 (Peut changer selon la version de votre Google Chrome Portable)
* Compresser le fichier chrome.dll sous le nom "chromeDll.zip"
* Et supprimer le fichier chrome.dll
* Ensuite compresser le dossier "Data" sous le nom "Data.zip",  celui-ci se trouve dans le dossier "GoogleChromePortable"
* Pour finir supprimer le dossier "Data"

On fait cela car git n'accepte pas l'envoie de fichier supérieur à 100mo ! <br>
Et aussi, la génération d'un exe via innosetup plnate du à un trop grand nombre de fichier.

# Python

Version actuellement utilisé -> 3.10
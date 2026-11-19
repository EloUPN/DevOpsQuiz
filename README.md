# DevOpsQuiz - L3 MIAGE
Application Java console avec CI/CD Jenkins, JUnit Platform Console Standalone et Docker, sans Maven.

## Pré-requis
Ubuntu / WSL Ubuntu, JDK 21 (ou JDK >= 17), Git, curl, Docker; Jenkins pour le pipeline.

## Installation
```bash
chmod +x scripts/*.sh
./scripts/setup-junit.sh
./scripts/build.sh
./scripts/test.sh
java -cp build/classes fr.miage.devopsquiz.Main
```

## Conteneur Docker
```bash
docker build -t devopsquiz:local .
docker volume create devopsquiz-scores
docker run --rm -it -v devopsquiz-scores:/data devopsquiz:local
docker run --rm devopsquiz:local --healthcheck
```

## Jenkins
Créer une tâche Pipeline « Pipeline script from SCM », utiliser Git, branches main/develop, script path Jenkinsfile.
Pour la démonstration, build/tests sur toutes les branches; déploiement seulement sur main.
Le conteneur devopsquiz-prod est volontairement créé sans démarrage : le quiz est interactif.
Démarrer une partie avec `docker run --rm -it -v devopsquiz-scores:/data devopsquiz:latest`.

## Vérification CI Jenkins
Test du déclenchement automatique du pipeline après un git push.

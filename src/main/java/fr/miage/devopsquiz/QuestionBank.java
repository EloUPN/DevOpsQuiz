package fr.miage.devopsquiz;
import java.util.*;

public final class QuestionBank {
    private QuestionBank() {}
    private static Question q(String c, String t, char a, String... options) {
        return new Question(c, t, options, a);
    }
    public static List<Question> all() {
        return List.of(
            q("Linux", "Afficher le dossier courant ?", 'B', "ls", "pwd", "cd", "cat"),
            q("Linux", "Lister les fichiers ?", 'A', "ls", "mv", "rm", "grep"),
            q("Linux", "Afficher un fichier texte ?", 'C', "mkdir", "chmod", "cat", "touch"),
            q("Linux", "Changer de répertoire ?", 'D', "pwd", "ps", "find", "cd"),
            q("Linux", "Filtrer des lignes de texte ?", 'A', "grep", "cp", "kill", "echo"),
            q("Linux", "Créer un répertoire ?", 'B', "tail", "mkdir", "head", "sort"),
            q("Linux", "Afficher les processus ?", 'C', "curl", "wc", "ps", "date"),
            q("Linux", "Modifier les permissions ?", 'D', "ls", "mv", "find", "chmod"),
            q("Linux", "Afficher les dernières lignes ?", 'A', "tail", "diff", "who", "sed"),
            q("Linux", "Supprimer un fichier ?", 'B', "pwd", "rm", "mkdir", "date"),
            q("Git", "Créer un dépôt local ?", 'A', "git init", "git clone --bare", "git log", "git show"),
            q("Git", "Voir l'état des fichiers ?", 'B', "git tag", "git status", "git fetch", "git reset"),
            q("Git", "Préparer des fichiers au commit ?", 'C', "git pull", "git log", "git add", "git diff"),
            q("Git", "Créer un commit ?", 'D', "git stash", "git status", "git clean", "git commit"),
            q("Git", "Créer et rejoindre une branche ?", 'A', "git switch -c", "git diff", "git push -d", "git archive"),
            q("Git", "Télécharger les commits distants ?", 'B', "git stash", "git fetch", "git tag -d", "git rm"),
            q("Git", "Afficher l'historique ?", 'C', "git add", "git push", "git log", "git init"),
            q("Git", "Publier sa branche ?", 'D', "git reset", "git diff", "git status", "git push"),
            q("Git", "Voir les différences ?", 'A', "git diff", "git tag", "git init", "git clone"),
            q("Git", "Créer un tag ?", 'B', "git status", "git tag", "git grep", "git restore"),
            q("Jenkins", "Un Jenkinsfile décrit ?", 'A', "Un pipeline", "Un réseau Docker", "Une branche", "Un paquet Java"),
            q("Jenkins", "Une étape Jenkins est souvent nommée ?", 'B', "row", "stage", "table", "volume"),
            q("Jenkins", "Exécuter une commande shell ?", 'C', "echoStage", "jar", "sh", "push"),
            q("Jenkins", "Récupérer le code Git dans un pipeline ?", 'D', "archive", "build", "input", "checkout scm"),
            q("Jenkins", "Déclencher après push GitHub ?", 'A', "Webhook", "chmod", "docker exec", "javac"),
            q("Jenkins", "Rapporter les résultats JUnit ?", 'B', "cat", "junit", "git", "sleep"),
            q("Jenkins", "Rôle de l'agent ?", 'C', "Dessiner l'UI", "Trier les logs", "Exécuter le travail", "Créer GitHub"),
            q("Jenkins", "Un build rouge signifie ?", 'D', "Succès", "Tag créé", "Pause", "Échec"),
            q("Jenkins", "Où voir les sorties du build ?", 'A', "Console Output", "Settings BIOS", "Docker Hub", "Gitignore"),
            q("Jenkins", "Une condition de déploiement est ?", 'B', "Build cassé", "Tests réussis", "Branche vide", "Log supprimé"),
            q("Docker", "Un Dockerfile sert à ?", 'A', "Définir l'image", "Créer une branche", "Calculer un score", "Exécuter Git"),
            q("Docker", "Construire une image ?", 'B', "docker ps", "docker build", "docker stop", "docker logs"),
            q("Docker", "Lister les conteneurs actifs ?", 'C', "docker images", "docker run", "docker ps", "docker tag"),
            q("Docker", "Voir les logs ?", 'D', "docker pull", "docker cp", "docker build", "docker logs"),
            q("Docker", "Persister les données ?", 'A', "Volume", "Layer", "Tag", "Commit Git"),
            q("Docker", "Démarrer un nouveau conteneur ?", 'B', "docker ps", "docker run", "docker image", "docker info"),
            q("Docker", "Supprimer un conteneur arrêté ?", 'C', "docker log", "docker build", "docker rm", "docker push"),
            q("Docker", "Image versus conteneur ?", 'D', "Identiques", "Image = processus", "Conteneur = code Git", "Image = modèle, conteneur = instance"),
            q("Docker", "Voir les images locales ?", 'A', "docker images", "docker ps", "docker logs", "docker stop"),
            q("Docker", "Arrêter un conteneur ?", 'B', "docker exec", "docker stop", "docker build", "docker commit"),
            q("DevOps", "CI veut dire ?", 'A', "Intégration continue", "Conteneur isolé", "Code interne", "Interface commune"),
            q("DevOps", "CD renvoie ici à ?", 'B', "Compilation difficile", "Livraison / déploiement continu", "Code distribué", "Données"),
            q("DevOps", "Un test automatisé aide à ?", 'C', "Effacer Git", "Éviter Linux", "Détecter les régressions", "Remplacer le code"),
            q("DevOps", "Un rollback sert à ?", 'D', "Supprimer l'équipe", "Cacher les logs", "Couper les tests", "Revenir à une version"),
            q("DevOps", "Un pipeline est ?", 'A', "Une suite d'étapes automatisées", "Une base SQL", "Un langage", "Un câble"),
            q("DevOps", "Le versionnement permet ?", 'B', "Plus de bugs", "Traçabilité", "Moins de code", "Pas de tests"),
            q("DevOps", "La supervision sert à ?", 'C', "Compiler", "Déboguer Git", "Observer l'application", "Créer des tags"),
            q("DevOps", "Le principe fail fast consiste à ?", 'D', "Ignorer les erreurs", "Déployer cassé", "Tout tester à la fin", "Détecter tôt les erreurs"),
            q("DevOps", "Infrastructure as Code ?", 'A', "Infrastructure décrite en fichiers", "Infrastructure manuelle", "Pas de Git", "Sans automatisation"),
            q("DevOps", "Quelle pratique favorise la qualité ?", 'B', "Push direct sans tests", "Revues de code", "Secrets en clair", "Logs supprimés")
        );
    }
}

<html>
<head>
    <title>Application de gestion des demandes</title>
</head>
<body>
    <h2>Bienvenue dans l'Application de gestion des demandes!</h2>
    <p>Voici les différentes fonctionnalités disponibles dans l'application :</p>
    
    <h3>Fonctionnalités :</h3>
    <ul>
        <li><strong>Créer une demande :</strong> Utilisez <code>POST webapi/demandes</code> pour créer une nouvelle demande.</li>
        <li><strong>Obtenir toutes les demandes :</strong> Utilisez <code>GET webapi/demandes</code> pour récupérer la liste de toutes les demandes.</li>
        <li><strong>Obtenir une demande par ID :</strong> Utilisez <code>GET webapi/demandes/{id}</code> pour obtenir une demande spécifique par son ID.</li>
        <li><strong>Attribuer un resolver et changer le statut à "en cours" :</strong> Utilisez <code>PUT /demandes/{id}/resolver</code> pour assigner un resolver et mettre la demande "en cours". <br> 
            <strong>Important :</strong> Lors de cette requête, vous devez envoyer l'ID de l'utilisateur qui résout la demande dans le corps de la requête.
            
        </li>
        <li><strong>Marquer la demande comme terminée :</strong> Utilisez <code>PUT /demandes/{id}/terminer</code> pour marquer une demande comme "terminée".</li>
    </ul>
    
    <h3>Lien :</h3>
    <ul>
        <li><a href="webapi/demandes">Voir toutes les demandes, ajoutez /{id} pour consulter une demande en particulier</a></li>
        
    </ul>

</body>
</html>


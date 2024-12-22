<html>
<head>
    <title>Application de gestion des demandes</title>
</head>
<body>
    <h2>Bienvenue dans l'Application de gestion des demandes!</h2>
    <p>Voici les diff�rentes fonctionnalit�s disponibles dans l'application :</p>
    
    <h3>Fonctionnalit�s :</h3>
    <ul>
        <li><strong>Cr�er une demande :</strong> Utilisez <code>POST webapi/demandes</code> pour cr�er une nouvelle demande.</li>
        <li><strong>Obtenir toutes les demandes :</strong> Utilisez <code>GET webapi/demandes</code> pour r�cup�rer la liste de toutes les demandes.</li>
        <li><strong>Obtenir une demande par ID :</strong> Utilisez <code>GET webapi/demandes/{id}</code> pour obtenir une demande sp�cifique par son ID.</li>
        <li><strong>Attribuer un resolver et changer le statut � "en cours" :</strong> Utilisez <code>PUT /demandes/{id}/resolver</code> pour assigner un resolver et mettre la demande "en cours". <br> 
            <strong>Important :</strong> Lors de cette requ�te, vous devez envoyer l'ID de l'utilisateur qui r�sout la demande dans le corps de la requ�te.
            
        </li>
        <li><strong>Marquer la demande comme termin�e :</strong> Utilisez <code>PUT /demandes/{id}/terminer</code> pour marquer une demande comme "termin�e".</li>
    </ul>
    
    <h3>Lien :</h3>
    <ul>
        <li><a href="webapi/demandes">Voir toutes les demandes, ajoutez /{id} pour consulter une demande en particulier</a></li>
        
    </ul>

</body>
</html>


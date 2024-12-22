package fr.insa.helperapp.demandManagementREST;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.*;

@Path("demandes")
public class DemandesService {

    private static List<Demande> demandes = new ArrayList<>();
    private static int currentId = 1;

    // Ajouter une nouvelle demande
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Demande createDemande(Demande demande) {
        demande.setId(currentId++);
        demandes.add(demande);
        return demande;
    }

    // Modifier une demande en ajoutant un resolver et en passant le statut à "en cours"
    @PUT
    @Path("{id}/resolver")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Demande resolveDemande(@PathParam("id") int id, String resolverId) {
        for (Demande demande : demandes) {
            if (demande.getId() == id) {
                demande.setResolver(resolverId);
                demande.setStatus("en cours");
                return demande;
            }
        }
        throw new NotFoundException("Demande non trouvée");
    }

    // Mettre à jour une demande pour marquer le statut à "terminé"
    @PUT
    @Path("{id}/terminer")
    @Produces(MediaType.APPLICATION_JSON)
    public Demande terminerDemande(@PathParam("id") int id) {
        for (Demande demande : demandes) {
            if (demande.getId() == id) {
                demande.setStatus("terminé");
                return demande;
            }
        }
        throw new NotFoundException("Demande non trouvée");
    }

    // Obtenir toutes les demandes
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Demande> getDemandes() {
        return demandes;
    }

    // Obtenir une demande par son id
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Demande getDemande(@PathParam("id") int id) {
        for (Demande demande : demandes) {
            if (demande.getId() == id) {
                return demande;
            }
        }
        throw new NotFoundException("Demande non trouvée");
    }
}


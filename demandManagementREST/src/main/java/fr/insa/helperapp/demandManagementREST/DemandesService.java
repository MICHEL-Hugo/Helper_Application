package fr.insa.helperapp.demandManagementREST;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;


import java.io.File;
import java.io.IOException;
import java.util.*;

@Path("demandes")
public class DemandesService {

    private static final String FILE_PATH = "demandes.json";  
    private static List<Demande> demandes = new ArrayList<>();
    private static int currentId = 1;
    

    static {
        loadDemandesFromFile(); 
    }

    private static void loadDemandesFromFile() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            File file = new File(FILE_PATH);
            if (file.exists() && file.length() > 0) {  
                demandes = mapper.readValue(file, new TypeReference<List<Demande>>() {});

                if (!demandes.isEmpty()) {
                    currentId = demandes.get(demandes.size() - 1).getId() + 1;
                }
            } else if (file.exists()) {
                
                demandes = new ArrayList<>();
            } else {
                
                demandes = new ArrayList<>();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveDemandesToFile() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(FILE_PATH), demandes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Demande createDemande(Demande demande) {
        Demande nouvelleDemande = new Demande(currentId++, demande.getName(), demande.getDescription(), demande.getAuthor());
        demandes.add(nouvelleDemande);
        saveDemandesToFile();
        return nouvelleDemande;
    }

    @PUT
    @Path("{id}/resolver")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Demande resolveDemande(@PathParam("id") int id, String resolverId) {
        for (Demande demande : demandes) {
            if (demande.getId() == id) {
                demande.setResolver(resolverId);
                demande.setStatus("en cours");
                saveDemandesToFile();  
                return demande;
            }
        }
        throw new NotFoundException("Demande non trouvée");
    }

    @PUT
    @Path("{id}/terminer")
    @Produces(MediaType.APPLICATION_JSON)
    public Demande terminerDemande(@PathParam("id") int id) {
        for (Demande demande : demandes) {
            if (demande.getId() == id) {
                demande.setStatus("terminé");
                saveDemandesToFile();  
                return demande;
            }
        }
        throw new NotFoundException("Demande non trouvée");
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Demande> getDemandes() {
        return demandes;
    }
    @GET
    @Path("/en-attente")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Demande> getDemandesEnAttente() {
        List<Demande> demandesEnAttente = new ArrayList<>();
        for (Demande demande : demandes) {
            if ("en attente".equals(demande.getStatus())) {
                demandesEnAttente.add(demande);
            }
        }
        return demandesEnAttente;
    }


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
    
    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteDemande(@PathParam("id") int id) {
        for (Demande demande : demandes) {
            if (demande.getId() == id) {
                demandes.remove(demande); 
                saveDemandesToFile(); 
                return "{\"message\": \"Demande avec le titre '" + demande.getName() + "' a bien été supprimée.\"}";
            }
        }
        throw new NotFoundException("Demande non trouvée");
    }


}

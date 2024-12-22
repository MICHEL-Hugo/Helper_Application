package fr.insa.helperapp.whoIsTheResolverMS.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import fr.insa.helperapp.whoIsTheResolverMS.model.Aggregate;
import fr.insa.helperapp.whoIsTheResolverMS.model.Resolver;
import fr.insa.helperapp.whoIsTheResolverMS.repository.ResolverRepository;

@RestController
@RequestMapping("/api/resolvers")
public class ResolverResource {

    @Autowired
    private ResolverRepository resolverRepository;

    @Autowired
    private RestTemplate restTemplate;

    // Récupérer un résolveur par ID
    public Resolver getUserById(@PathVariable int id) {
        return resolverRepository.getResolverById(id);
    }

    // Récupérer tous les résolveurs
    @GetMapping
    public List<Resolver> getAllResolvers() {
        return resolverRepository.getAllResolvers();
    }

    // Créer un nouveau résolveur
    @PostMapping
    public String createResolver(@RequestBody Aggregate newResolving) {
        Resolver resolver = new Resolver();
        resolver.setId(newResolving.getId());
        resolver.setResolver(newResolving.getResolver());
        
        // Insérer dans la base de données
        int rowsAffected = resolverRepository.createResolver(resolver);

        // Préparer les données pour un autre microservice si nécessaire
        String jsonPayload = String.format("""
                {
                    "status": "%s"
                }
            """, newResolving.getStatus());

        // Créer les en-têtes HTTP pour le JSON
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Créer la requête avec le JSON et les en-têtes
        HttpEntity<String> request = new HttpEntity<>(jsonPayload, headers);

        // Effectuer la requête PATCH vers le microservice statusDemandMS
        restTemplate.put("http://statusDemandMS/api/status/" + newResolving.getId(), request);

        return rowsAffected > 0 ? "Resolver created successfully" : "Error creating resolver";
    }

    // Mettre à jour un résolveur
    @PutMapping("/{id}")
    public String updateResolver(@PathVariable int id, @RequestBody Resolver updatedResolver) {
        updatedResolver.setId(id);
        int rowsAffected = resolverRepository.updateResolver(updatedResolver);
        return rowsAffected > 0 ? "Resolver updated successfully" : "Error updating resolver";
    }

    // Supprimer un résolveur
    @DeleteMapping("/{id}")
    public String deleteResolver(@PathVariable int id) {
        int rowsAffected = resolverRepository.deleteResolver(id);
        return rowsAffected > 0 ? "Resolver deleted successfully" : "Error deleting resolver";
    }
}


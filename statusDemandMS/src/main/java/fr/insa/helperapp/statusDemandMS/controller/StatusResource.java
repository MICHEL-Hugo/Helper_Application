package fr.insa.helperapp.statusDemandMS.controller;

//import fr.insa.helperapp.catalogDemandMS.model.demandModel;

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

import fr.insa.helperapp.statusDemandMS.model.Status;
import fr.insa.helperapp.statusDemandMS.model.AggregatedResponse;
import fr.insa.helperapp.statusDemandMS.repository.StatusRepository;

@RestController
@RequestMapping("/api/status")

public class StatusResource {

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{id}")
    //public Status getStatusById(@PathVariable int id) {
        //return statusRepository.getStatusById(id);

    public AggregatedResponse getStatusById(@PathVariable int id) {
        // Récupérer le statut localement
        Status status = statusRepository.getStatusById(id);

        // Appeler l'autre microservice
        Map<String, Object> externalData = restTemplate.getForObject("http://catalogDemandMS/api/demand/" + id, Map.class);

        // Construire la réponse agrégée
        AggregatedResponse response = new AggregatedResponse();
        response.setId(status.getId());
        response.setStatus(status.getStatus());
        response.setName((String) externalData.get("name"));
        response.setDescription((String) externalData.get("description"));
        response.setAuthor((String) externalData.get("author"));

        return response;
    }

    @GetMapping
    public List<Status> getAllStatuses() {
        return statusRepository.getAllStatuses();
    }

    @PostMapping
    public String createStatus(@RequestBody AggregatedResponse newDemand) {
    	Status newStatus = new Status();
    	newStatus.setId(newDemand.getId());
    	newStatus.setStatus(newDemand.getStatus());
        int rowsAffected = statusRepository.createStatus(newStatus);
        
        // 3. Préparer les données pour le microservice catalogDemandMS
        String jsonPayload = String.format("""
                {
                    "name": "%s",
                    "description": "%s",
                    "author": "%s"
                }
            """, newDemand.getName(), newDemand.getDescription(), newDemand.getAuthor());
        
        // Créez les en-têtes HTTP pour le JSON
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        // Créez la requête avec le JSON et les en-têtes
        HttpEntity<String> request = new HttpEntity<>(jsonPayload, headers);
        
        // Effectuez la requête POST pour envoyer les données vers catalogDemandMS
        restTemplate.postForObject("http://catalogDemandMS/api/demand", request, Void.class);
        
        return rowsAffected > 0 ? "Status created successfully" : "Error creating status";
    }

    @PutMapping("/{id}")
    public String updateStatus(@PathVariable int id, @RequestBody Status updatedStatus) {
        updatedStatus.setId(id);
        int rowsAffected = statusRepository.updateStatus(updatedStatus);
        return rowsAffected > 0 ? "Status updated successfully" : "Error updating status";
    }

    @DeleteMapping("/{id}")
    public String deleteStatus(@PathVariable int id) {
        int rowsAffected = statusRepository.deleteStatus(id);
        return rowsAffected > 0 ? "Status deleted successfully" : "Error deleting status";
    }
}

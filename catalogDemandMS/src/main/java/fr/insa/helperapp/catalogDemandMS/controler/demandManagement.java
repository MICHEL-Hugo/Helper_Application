package fr.insa.helperapp.catalogDemandMS.controler;

import fr.insa.helperapp.catalogDemandMS.model.demandModel;
import fr.insa.helperapp.catalogDemandMS.Services.demandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/demand")
public class demandManagement {

    @Autowired
    private demandService demandService;
    
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public List<demandModel> getAllRequests() {
        System.out.println("GET /demand has been called");

        List<demandModel> demands = demandService.getAllRequests();

        return demands;
    }

    @GetMapping("/{id}")
    public demandModel getRequestById(@PathVariable int id) {
        return demandService.getRequestById(id);
    }

    @PostMapping
    public void addRequest(@RequestBody demandModel demand) {
        demandService.addRequest(demand);
    }

    @PutMapping("/{id}")
    public void updateRequest(@PathVariable int id, @RequestBody demandModel demand) {
        demand.setId(id);
        demandService.updateRequest(demand);
    }

    @DeleteMapping("/{id}")
    public void deleteRequest(@PathVariable int id) {
        demandService.deleteRequest(id);
    }
}

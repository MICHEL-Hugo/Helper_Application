package fr.insa.helperapp.statusDemandMS.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import fr.insa.helperapp.statusDemandMS.model.Status;

import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
@Repository
public class StatusRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Créer un statut
    public int createStatus(Status status) {
        String sql = "INSERT INTO status_table (id, status_var) VALUES (?, ?)";
        return jdbcTemplate.update(sql, status.getId(), status.getStatus());
    }

    // Trouver un statut par ID
    public Status getStatusById(int id) {
        String sql = "SELECT * FROM status_table WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
            Status status = new Status();
            status.setId(rs.getInt("id"));
            status.setStatus(rs.getString("status_var"));
            return status;
        });
    }

    // Récupérer tous les statuts
    public List<Status> getAllStatuses() {
        String sql = "SELECT * FROM status_table";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Status status = new Status();
            status.setId(rs.getInt("id"));
            status.setStatus(rs.getString("status_var"));
            return status;
        });
    }

    // Mettre à jour un statut
    public int updateStatus(Status status) {
        String sql = "UPDATE status_table SET status_var = ? WHERE id = ?";
        return jdbcTemplate.update(sql, status.getStatus(), status.getId());
    }

    // Supprimer un statut
    public int deleteStatus(int id) {
        String sql = "DELETE FROM status_table WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}

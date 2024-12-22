package fr.insa.helperapp.whoIsTheResolverMS.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import fr.insa.helperapp.whoIsTheResolverMS.model.Resolver;  // Import du modèle Resolver

import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
@Repository
public class ResolverRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Créer un résolveur
    public int createResolver(Resolver resolver) {
        String sql = "INSERT INTO resolver_table (id, resolver) VALUES (?, ?)";
        return jdbcTemplate.update(sql, resolver.getId(), resolver.getResolver());
    }

    // Trouver un résolveur par ID
    public Resolver getResolverById(int id) {
        String sql = "SELECT * FROM resolver_table WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
            Resolver resolver = new Resolver();
            resolver.setId(rs.getInt("id"));
            resolver.setResolver(rs.getString("resolver"));
            return resolver;
        });
    }

    // Récupérer tous les résolveurs
    public List<Resolver> getAllResolvers() {
        String sql = "SELECT * FROM resolver_table";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Resolver resolver = new Resolver();
            resolver.setId(rs.getInt("id"));
            resolver.setResolver(rs.getString("resolver"));
            return resolver;
        });
    }

    // Mettre à jour un résolveur
    public int updateResolver(Resolver resolver) {
        String sql = "UPDATE resolver_table SET resolver = ? WHERE id = ?";
        return jdbcTemplate.update(sql, resolver.getResolver(), resolver.getId());
    }

    // Supprimer un résolveur
    public int deleteResolver(int id) {
        String sql = "DELETE FROM resolver_table WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}


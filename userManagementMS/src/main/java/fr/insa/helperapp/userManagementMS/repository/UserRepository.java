package fr.insa.helperapp.userManagementMS.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import fr.insa.helperapp.userManagementMS.model.User;

import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Créer un utilisateur
    public int createUser(User user) {
        String sql = "INSERT INTO user (id, username, email) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, user.getId(), user.getUsername(), user.getEmail());
    }

    // Trouver un utilisateur par ID
    public User getUserById(int id) {
        String sql = "SELECT * FROM user WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setEmail(rs.getString("email"));
            return user;
        });
    }

    // Récupérer tous les utilisateurs
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM user";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setEmail(rs.getString("email"));
            return user;
        });
    }

    // Mettre à jour un utilisateur
    public int updateUser(User user) {
        String sql = "UPDATE user SET username = ?, email = ? WHERE id = ?";
        return jdbcTemplate.update(sql, user.getUsername(), user.getEmail(), user.getId());
    }

    // Supprimer un utilisateur
    public int deleteUser(int id) {
        String sql = "DELETE FROM user WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}

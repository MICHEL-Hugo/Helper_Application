package fr.insa.helperapp.catalogDemandMS.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import fr.insa.helperapp.catalogDemandMS.model.demandModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class demandService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final class DemandRowMapper implements RowMapper<demandModel> {
        @Override
        public demandModel mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new demandModel(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getString("description")
            );
        }
    }

    public List<demandModel> getAllRequests() {
        String sql = "SELECT * FROM demand";
        return jdbcTemplate.query(sql, new DemandRowMapper());
    }

    public demandModel getRequestById(Long id) {
        String sql = "SELECT * FROM demand WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new DemandRowMapper(), id);
    }


    public void addRequest(demandModel demand) {
        String sql = "INSERT INTO demand (name, description) VALUES (?, ?)";
        jdbcTemplate.update(sql, demand.getName(), demand.getDescription());
    }

    public void updateRequest(demandModel demand) {
        String sql = "UPDATE demand SET name = ?, description = ?,WHERE id = ?";
        jdbcTemplate.update(sql, demand.getName(), demand.getDescription(), demand.getId());
    }

    public void deleteRequest(Long id) {
        String sql = "DELETE FROM demand WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
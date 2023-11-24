package pdp.service_with_NamedParameter_JDBC;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import pdp.Todo;

import java.util.List;
import java.util.Map;

public class Todo_Service_NamedParameter_JDBC {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Todo_Service_NamedParameter_JDBC(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void save(Todo todo) {
        var sql = "insert into todo(title,priority) values (:title,:priority);";
        namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(todo));
    }

    public void update(Todo todo) {
        var sql = "update todo set title = :title , priority = :priority where id = :id;";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("title", todo.getTitle())
                .addValue("priority", todo.getPriority())
                .addValue("id", todo.getId());
        namedParameterJdbcTemplate.update(sql, parameterSource);
    }

    public void delete(Integer id) {
        var sql = "delete from todo where id = :id;";
        namedParameterJdbcTemplate.update(sql, Map.of("id", id));
    }

    public Todo get(Integer id) {
        var sql = "select * from todo where id = :id;";
        return namedParameterJdbcTemplate.queryForObject(sql, Map.of("id", id), BeanPropertyRowMapper.newInstance(Todo.class));
    }

    public List<Todo> getAll() {
        var sql = "select * from todo order by createdat desc   ";
        return namedParameterJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Todo.class));
    }
}

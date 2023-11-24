package pdp.service_with_JDBCTemplate;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import pdp.Todo;

import java.util.List;

public class Todo_Service_with_JDBCTemplate {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public Todo_Service_with_JDBCTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
    }

    public void save_withSimpleJdbcInsert(Todo todo) {
        simpleJdbcInsert.usingColumns("title", "priority").execute(new BeanPropertySqlParameterSource(todo));
    }

    public void save(Todo todo) {
        var sql = "insert into todo (title,priority) values (?,?)";
        jdbcTemplate.update(sql, todo.getTitle(), todo.getPriority());
    }

    public void update(Todo todo) {
        var sql = "update todo set title = ? , priority = ? where id = ?";
        jdbcTemplate.update(sql, todo.getTitle(), todo.getPriority(), todo.getId());
    }

    public void delete(Integer id) {
        var sql = "delete from todo where id = ?";
        jdbcTemplate.update(sql, id);
    }

    public List<Todo> getAll() {
        var sql = "select * from todo order by createdat desc ";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Todo.class));
    }

    public Todo get(Integer id) {
        var sql = "select * from todo where id = ?";
        return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Todo.class), id);
    }
}

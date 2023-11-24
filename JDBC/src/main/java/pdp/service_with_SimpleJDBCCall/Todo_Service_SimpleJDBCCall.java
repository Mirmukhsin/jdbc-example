package pdp.service_with_SimpleJDBCCall;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.postgresql.util.PGobject;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import pdp.Todo;

import java.util.List;
import java.util.Objects;


public class Todo_Service_SimpleJDBCCall {
    private final SimpleJdbcCall simpleJdbcCall;

    public Todo_Service_SimpleJDBCCall(SimpleJdbcCall simpleJdbcCall) {
        this.simpleJdbcCall = simpleJdbcCall.withSchemaName("spring_jdbc");
    }

    public void save(Todo todo) {
        simpleJdbcCall.withFunctionName("save").execute(todo.getTitle(), todo.getPriority());
    }

    public void update(Todo todo) {
        simpleJdbcCall.withFunctionName("update").execute(todo.getTitle(), todo.getPriority(), todo.getId());
    }

    public void delete(Integer id) {
        simpleJdbcCall.withFunctionName("delete").execute(id);
    }

    public Todo find(Integer id) throws JsonProcessingException {
        String json = String.valueOf(simpleJdbcCall.withFunctionName("find").executeObject(PGobject.class, id));
        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
        Todo todo = mapper.readValue(json, Todo.class);
        if (Objects.equals(todo.getId(), id)) {
            return todo;
        } else {
            return null;
        }
    }

    public List<Todo> findAll() throws JsonProcessingException {
        String jsonList = String.valueOf(simpleJdbcCall.withFunctionName("find_all").execute("1"));
        jsonList = jsonList.substring(jsonList.indexOf('['), jsonList.indexOf(']') + 1);
        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
        return mapper.readValue(jsonList, new TypeReference<>() {});
    }
}

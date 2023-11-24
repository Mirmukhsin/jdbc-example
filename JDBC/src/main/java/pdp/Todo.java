package pdp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Todo {
    private Integer id;
    private String title;
    private String priority;
    private LocalDateTime createdat;
}

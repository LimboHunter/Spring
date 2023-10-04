package pizzas;

import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table
public class Pizza {

    @Id
    private long id;

    private Date createdAt = new Date();

    @NotNull
    @Size(min = 5, message = "Name must be at least 5 letters long")
    private String name;

    @NotNull
    @Size(min = 1, message = "Choose at least one element")
    private List<Ingredient> ingredients;

}

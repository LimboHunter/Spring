package pizzas.web.api;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import pizzas.Pizza;
import pizzas.PizzaOrder;
import pizzas.data.PizzaRepository;

import java.util.Optional;

@RestController
@RequestMapping(path = "api/pizzas",
produces = "application/json")
@CrossOrigin(origins = "http://pizzacloud:8080")
public class PizzaController {

    private PizzaRepository pizzaRepo;

    public PizzaController(PizzaRepository pizzaRepo){
        this.pizzaRepo = pizzaRepo;
    }

    @GetMapping(params = "recent")
    public Iterable<Pizza> recentPizzas() {
        PageRequest page = PageRequest.of(
                0, 12, Sort.by("createdAt").descending());
        return pizzaRepo.findAll(page).getContent();
    }
    @GetMapping("/{id}")
    public Optional<Pizza> pizzaById(@PathVariable("id") Long id){
        return pizzaRepo.findById(id);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Pizza postPizza(@RequestBody Pizza pizza){
        return pizzaRepo.save(pizza);
    }
}

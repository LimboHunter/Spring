package pizzas.web;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;
import pizzas.Ingredient;
import pizzas.Ingredient.Type;
import pizzas.Pizza;
import pizzas.PizzaOrder;

import javax.validation.Valid;
import org.springframework.validation.Errors;
@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("pizzaOrder")
public class DesignPizzaController {

//end::head[]
    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("ThinC", "Thin Crust", Type.CRUST),
                new Ingredient("ThickC", "Thick Crust", Type.CRUST),
                new Ingredient("WFC", "Wood-Fired Crust", Type.CRUST),
                new Ingredient("TS", "Tomato Sause", Type.SAUCE),
                new Ingredient("WS", "White Sause", Type.SAUCE),
                new Ingredient("SLM", "Salami", Type.MEAT),
                new Ingredient("CHICK", "Chicken", Type.MEAT),
                new Ingredient("BCN", "Bacon", Type.MEAT),
                new Ingredient("ONS", "Onions", Type.VEGGIES),
                new Ingredient("SWPS", "Sweet Papers", Type.VEGGIES),
                new Ingredient("PIAP", "Pinespple", Type.VEGGIES),
                new Ingredient("TMS", "Tomatoes", Type.VEGGIES),
                new Ingredient("MSRM", "Mushroom", Type.VEGGIES),
                new Ingredient("MOZA", "Mozarella", Type.CHEESE),
                new Ingredient("Prov", "Provolone", Type.CHEESE),
                new Ingredient("GC", "Goat Cheese", Type.CHEESE),
                new Ingredient("RC", "Ricotta Cheese", Type.CHEESE)
        );

        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
            filterByType(ingredients, type));
        }
    }

    @ModelAttribute(name = "pizzaOrder")
    public PizzaOrder order() {
        return new PizzaOrder();
    }

    @ModelAttribute(name = "pizza")
    public Pizza pizza(){
        return new Pizza();
    }

    @GetMapping
    public String showDesignForm(){
        return "design";
    }

    @PostMapping
    public String processPizza(@Valid Pizza pizza, Errors errors,
                               @ModelAttribute PizzaOrder pizzaOrder){
        if(errors.hasErrors()){
            return "design";
        }

        pizzaOrder.addPizza(pizza);
        log.info("Processing pizza: {}", pizza);

        return "redirect:/orders/current";
    }

    private Iterable<Ingredient> filterByType(
            List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}



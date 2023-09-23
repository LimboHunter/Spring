package pizzas.web;

import java.util.HashMap;
import java.util.Map;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import pizzas.Ingredient;
import pizzas.Ingredient.Type;

@Component
public class IngredientByIdConverter implements Converter<String,Ingredient> {
    
    private Map<String, Ingredient> ingredientMap = new HashMap<>();
    
    public IngredientByIdConverter(){
        ingredientMap.put( "ThinC" ,new Ingredient("ThinC", "Thin Crust", Type.CRUST));
                ingredientMap.put( "ThickC" ,new Ingredient("ThickC", "Thick Crust", Type.CRUST));
                ingredientMap.put( "WFC" ,new Ingredient("WFC", "Wood-Fired Crust", Type.CRUST));
                ingredientMap.put( "TS" ,new Ingredient("TS", "Tomato Sause", Type.SAUCE));
                ingredientMap.put( "WS" ,new Ingredient("WS", "White Sause", Type.SAUCE));
                ingredientMap.put( "SLM" ,new Ingredient("SLM", "Salami", Type.MEAT));
                ingredientMap.put( "CHICK" ,new Ingredient("CHICK", "Chicken", Type.MEAT));
                ingredientMap.put( "BCN" ,new Ingredient("BCN", "Bacon", Type.MEAT));
                ingredientMap.put( "ONS" ,new Ingredient("ONS", "Onions", Type.VEGGIES));
                ingredientMap.put( "SWPS" ,new Ingredient("SWPS", "Sweet Papers", Type.VEGGIES));
                ingredientMap.put( "PIAP" ,new Ingredient("PIAP", "Pinespple", Type.VEGGIES));
                ingredientMap.put( "TMS" ,new Ingredient("TMS", "Tomatoes", Type.VEGGIES));
                ingredientMap.put( "MSRM" ,new Ingredient("MSRM", "Mushroom", Type.VEGGIES));
                ingredientMap.put( "MOZA" ,new Ingredient("MOZA", "Mozarella", Type.CHEESE));
                ingredientMap.put( "Prov" ,new Ingredient("Prov", "Provolone", Type.CHEESE));
                ingredientMap.put( "GC" ,new Ingredient("GC", "Goat Cheese", Type.CHEESE));
                ingredientMap.put( "RC" ,new Ingredient("RC", "Ricotta Cheese", Type.CHEESE));
    };
    
    @Override
    public Ingredient convert(String id){
        return ingredientMap.get(id);
    }
}

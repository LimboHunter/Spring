package pizzas.web.api;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pizzas.PizzaOrder;
import pizzas.data.OrderRepository;
import pizzas.messaging_jms.OrderMessagingService;

@RestController
@RequestMapping(path="/api/orders",
        produces="application/json")
@CrossOrigin(origins="http://pizzacloud:8080")
public class OrderApiController {

    private OrderRepository repo;
    private OrderMessagingService messageService;

    public OrderApiController(OrderRepository repo,
                              OrderMessagingService messageService){
        this.repo = repo;
        this.messageService = messageService;
    }

    @GetMapping(produces = "application/json")
    public Iterable<PizzaOrder> allOrders(){ return repo.findAll();}

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public PizzaOrder postOrder(@RequestBody PizzaOrder order){
        messageService.sendOrder(order);
        return repo.save(order);
    }

    @PutMapping(path = "/{orderId", consumes = "application/json")
    public PizzaOrder putOrder(
            @PathVariable("orderId") Long orderId,
            @RequestBody PizzaOrder order){
        order.setId(orderId);
        return repo.save(order);
    }

    @PatchMapping(path="/{orderId}", consumes="application/json")
    public PizzaOrder patchOrder(@PathVariable("orderId") Long orderId,
                                @RequestBody PizzaOrder patch) {

        PizzaOrder order = repo.findById(orderId).get();
        if (patch.getDeliveryName() != null) {
            order.setDeliveryName(patch.getDeliveryName());
        }
        if (patch.getDeliveryStreet() != null) {
            order.setDeliveryStreet(patch.getDeliveryStreet());
        }
        if (patch.getDeliveryCity() != null) {
            order.setDeliveryCity(patch.getDeliveryCity());
        }
        if (patch.getDeliveryState() != null) {
            order.setDeliveryState(patch.getDeliveryState());
        }
        if (patch.getDeliveryZip() != null) {
            order.setDeliveryZip(patch.getDeliveryZip());
        }
        if (patch.getCcNumber() != null) {
            order.setCcNumber(patch.getCcNumber());
        }
        if (patch.getCcExpiration() != null) {
            order.setCcExpiration(patch.getCcExpiration());
        }
        if (patch.getCcCVV() != null) {
            order.setCcCVV(patch.getCcCVV());
        }
        return repo.save(order);
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("orderId") Long orderId) {
        try {
            repo.deleteById(orderId);
        } catch (EmptyResultDataAccessException e) {}
    }
}

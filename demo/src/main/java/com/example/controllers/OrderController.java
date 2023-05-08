package com.example.controllers;

import com.example.Order;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class OrderController {


    // Targil:
    // GET
    // GET -- query params
    // GET/ID
    // POST
    // PUT/ID -- update/add
    // DELETE/ID
    // PATCH/ID -- update only if exists

    ArrayList<Order> orders = new ArrayList<>(Arrays.asList(new Order(1, "Computer"), new Order(2, "Laptop"),
                                                        new Order(3, "TV")));

    @GetMapping(value = "/orders")
    public List<Order> getAllOrders(){
        return orders;
    }

    @GetMapping(value = "/order")
    public Order getByQuery(@RequestParam(value="id") Integer id){
        return orders.get(id-1);
    }
    @GetMapping(value = "/order")
    public Order getByQuery(@RequestParam(value="name") String name){
        for (Order order: orders) {
            if (orders.contains(name)){
                return order;
            }
        }
        return null;
    }

    @GetMapping(value = "/order/id={id}")
    public Order GetById(@PathVariable Integer id){
        return orders.get(id-1);
    }

    @PostMapping(value = "/order_post")
    public Order postByQuery(@RequestParam(value="id") Integer id,
                            @RequestParam(value = "name") String name){
        Order order = new Order(id-1, name);
        orders.add(order);
        return orders.get(order.id);
    }

    @PutMapping(value = "/order_put")
    public Order putByParams(@RequestParam(value="id") Integer id,
                              @RequestParam(value = "name") String name){
        Order order = new Order(id-1, name);
        if (orders.contains(order)){
            orders.set(order.id, order);
        }
        else {
            orders.add(order);
        }
        return order;
    }

    @DeleteMapping(value = "/order_delete/id={id}")
    public String deleteById(@PathVariable Integer id){
        orders.remove(id-1);
        return String.format("the item with the %d id ha been deleted", id);
    }

    @PatchMapping(value = "/order_patch")
    public Order patchByParams(@RequestParam(value="id") Integer id,
                               @RequestParam(value="name") String name){
        Order order = new Order(id-1, name);
        if (orders.contains(order.id)){
            orders.set(order.id, order);
        }
        else {
            String.format("could not update if not exists");
        }
        return order;
    }




















    //get query params
    @GetMapping(value = "/order_params")
    public String getById(@RequestParam(value = "orderId", defaultValue = "0") Integer orderId,
                          @RequestParam(value = "name", defaultValue = "incognito") String name) {
        return String.format("{id:%d, name:'%s' }", orderId, name);
    }


    //get path params
    @GetMapping(value = "/order/{orderId}")
    public String getOrders(@PathVariable Integer orderId){

        return String.format("id: %d", orderId);
    }

    //post body params
    @PostMapping(value = "/order")
    public String postOrder(@RequestBody Order order){
        return order.name;
    }
}

package ar.com.augustoocc.controller;

import ar.com.augustoocc.model.Order;
import ar.com.augustoocc.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

    @Autowired
    OrderService ordersService;

    @GetMapping(value = "/find-orders/{orders}")
    public ResponseEntity<Order> findOrder(@PathVariable(name = "orders", required = true) Long ordersId) {
        if (ordersService.findOrder(ordersId) != null) {
            Order orders = ordersService.findOrder(ordersId);
            return new ResponseEntity("Order: " + orders + " found", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity("Order not found", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/new-orders")
    public ResponseEntity<Order> newOrder(@RequestBody Orders orders) {
        if (ordersService.findOrder(orders.getId()) != null) {
            return new ResponseEntity("Order: " + orders + " already exist", HttpStatus.BAD_REQUEST);
        } else {
            ordersService.createOrder(orders);
            return new ResponseEntity("Order created properly with id: " + orders.getId(), HttpStatus.ACCEPTED);
        }
    }

    @PatchMapping(value = "/update-orders")
    public ResponseEntity<Orders> updateOrder(@RequestBody Orders orders) {
        if (ordersService.findOrder(orders.getId()) != null) {
            Orders newOrder = ordersService.findOrder(orders.getId());
            ordersService.updateOrder(orders);
            return new ResponseEntity("Order: " + newOrder + " properly updated", HttpStatus.ACCEPTED);
        }
        if (ordersService.findOrder(orders.getId()) == null) {
            return new ResponseEntity("Order doesn't exist!!", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity("Order couldn't be updated", HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping(value = "/delete-orders")
    public ResponseEntity<Orders> deleteOrder(@RequestBody Orders orders) {
        if (ordersService.findOrder(orders.getId()) != null) {
            Orders newOrder = ordersService.findOrder(orders.getId());
            ordersService.deleteOrder(orders);
            return new ResponseEntity("Order: " + orders + " properly deleted", HttpStatus.ACCEPTED);
        }
        if (ordersService.findOrder(orders.getId()) == null) {
            return new ResponseEntity("Order doesn't exist!!", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity("Order couldn't be deleted", HttpStatus.BAD_REQUEST);
        }
    }



}

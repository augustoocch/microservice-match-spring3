package ar.com.augustoocc.service;


import ar.com.augustoocc.model.Order;

public interface OrderService {

    public void deleteOrder(Order orders);
    public void createOrder(Order orders);
    public void updateOrder(Order orders);
    public Order findOrder(long id);
}

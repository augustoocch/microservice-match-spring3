package ar.com.augustoocc.service;


public interface OrderService {

    public void deleteOrder(Order orders);
    public void createOrder(Order orders);
    public void updateOrder(Order orders);
    public Order findOrder(long id);
}

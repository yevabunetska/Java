import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

public class OrderService {
    List<Order> orders = new ArrayList<>();

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void removeOrder(long orderId) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).id == orderId) {
                orders.remove(i);
                return;
            }
        }
    }

    public Order findOrderById(long orderId) {
        for (Order order : orders) {
            if (order.id == orderId) {
                return order;
            }
        }
        return null;
    }

    public List<Order> findOrdersByCustomer(long customerId) {
        List<Order> result = new ArrayList<>();
        for (Order order : orders) {
            if (order.customer.id == customerId) {
                result.add(order);
            }
        }
        return result;
    }

    public List<Order> findOrdersByStatus(OrderStatus status) {
        List<Order> result = new ArrayList<>();
        for (Order order : orders) {
            if (order.status == status) {
                result.add(order);
            }
        }
        return result;
    }

    public List<Order> findOrdersContainingProduct(long productId) {
        List<Order> result = new ArrayList<>();
        for (Order order : orders) {
            for (Product product : order.products) {
                if (product.id == productId) {
                    result.add(order);
                    break;
                }
            }
        }
        return result;
    }

    public double calculateOrderTotal(long orderId) {
        Order order = findOrderById(orderId);
        if (order == null) {
            return 0;
        }
        double total = 0;
        for (Product product : order.products) {
            total = total + product.price;
        }
        return total;
    }

    public Set<Customer> findCustomersWithOrders() {
        Set<Customer> result = new HashSet<>();
        for (Order order : orders) {
            result.add(order.customer);
        }
        return result;
    }

    public List<Order> sortOrdersByTotalPrice() {
        List<Order> result = new ArrayList<>(orders);
        Collections.sort(result, (order1, order2) -> {
            double total1 = calculateOrderTotal(order1.id);
            double total2 = calculateOrderTotal(order2.id);
            return Double.compare(total1, total2);
        });
        return result;
    }

    public Map<OrderStatus, Long> countOrdersByStatus() {
        Map<OrderStatus, Long> result = new HashMap<>();
        for (OrderStatus status : OrderStatus.values()) {
            result.put(status, 0L);
        }
        for (Order order : orders) {
            long count = result.get(order.status);
            result.put(order.status, count + 1);
        }
        return result;
    }
}

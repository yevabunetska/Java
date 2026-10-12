import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        OrderService service = new OrderService();

        Customer customer1 = new Customer(
                1, "Єва", "yeva123@gmail.com");
        Customer customer2 = new Customer(
                2, "Ярослав", "yarik2005@gmail.com");
        Customer customer3 = new Customer(
                3, "Євгенія", "yevgeniabun@gmail.com");

        Product product1 = new Product(
                1, "Ноутбук", ProductCategory.ELECTRONICS, 25000);
        Product product2 = new Product(
                2, "Кросівки", ProductCategory.CLOTHING, 4400);
        Product product3 = new Product(
                3, "Книга", ProductCategory.BOOKS, 600);
        Product product4 = new Product(
                4, "Навушники", ProductCategory.ELECTRONICS, 1500);

        List<Product> products1 = new ArrayList<>();
        products1.add(product1);
        products1.add(product3);

        List<Product> products2 = new ArrayList<>();
        products2.add(product2);
        products2.add(product3);

        List<Product> products3 = new ArrayList<>();
        products3.add(product2);
        products3.add(product4);

        List<Product> products4 = new ArrayList<>();
        products4.add(product3);
        products4.add(product4);

        Order order1 = new Order(
                1, customer1, products1, OrderStatus.NEW);
        Order order2 = new Order(
                2, customer2, products2, OrderStatus.SHIPPED);
        Order order3 = new Order(
                3, customer1, products3, OrderStatus.DELIVERED);
        Order order4 = new Order(
                4, customer3, products4, OrderStatus.PROCESSING);

        service.addOrder(order1);
        service.addOrder(order2);
        service.addOrder(order3);
        service.addOrder(order4);

        System.out.println("Усі замовлення:");
        for (Order order : service.orders) {
            System.out.println(order);
        }

        System.out.println("\nПошук замовлення №2:");
        System.out.println(service.findOrderById(2));

        System.out.println("\nЗамовлення покупця з ID 1:");
        System.out.println(service.findOrdersByCustomer(1));

        System.out.println("\nНові замовлення:");
        System.out.println(service.findOrdersByStatus(OrderStatus.NEW));

        System.out.println("\nЗамовлення з книгами:");
        System.out.println(service.findOrdersContainingProduct(3));

        System.out.println("\nВартість замовлення №1:");
        System.out.println(service.calculateOrderTotal(1) + " грн");

        System.out.println("\nПокупці із замовленнями:");
        System.out.println(service.findCustomersWithOrders());

        System.out.println("\nСортування за вартістю:");

        List<Order> sortedOrders = service.sortOrdersByTotalPrice();

        for (Order order : sortedOrders) {
            System.out.println(order + ", сума: " + service.calculateOrderTotal(order.id) + " грн");
        }

        System.out.println("\nКількість замовлень за статусами:");
        System.out.println(service.countOrdersByStatus());

        System.out.println("\nВидалення замовлення №2:");
        service.removeOrder(2);

        for (Order order : service.orders) {
            System.out.println(order);
        }
    }
}
package bg.softuni;

import bg.softuni.entities.Order;
import bg.softuni.entities.User;
import bg.softuni.orm.EntityManager;
import bg.softuni.orm.MyConnector;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class Main
{
    public static void main(String[] args) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException
    {
        MyConnector.createConnection("root", "12345", "mini_orm");
        Connection connection = MyConnector.getConnection();

        // 2. Create EntityManagers for User and Order
        EntityManager<User> userEm = new EntityManager<>(connection);
        EntityManager<Order> orderEm = new EntityManager<>(connection);

        // 3. INSERT new User and Order
        User newUser = new User("john_doe", 30, LocalDate.now());
        Order newOrder = new Order(199.99, LocalDate.now());

        userEm.persist(newUser);
        orderEm.persist(newOrder);
        System.out.println("[INSERT] New user and order saved.");

        // 4. FETCH ALL Users
        System.out.println("\n[ALL USERS]");
        for (User user : userEm.find(User.class))
        {
            System.out.printf("ID: %d, Username: %s, Age: %d, RegDate: %s%n",
                    user.getId(), user.getUsername(), user.getAge(), user.getRegistration());
        }

        // 5. FETCH ALL Orders WHERE amount > 100
        System.out.println("\n[ORDERS WITH amount > 100]");
        for (Order order : orderEm.find(Order.class, "amount > 100"))
        {
            System.out.printf("ID: %d, Amount: %.2f, CreatedAt: %s%n",
                    order.getOrderId(), order.getAmount(), order.getCreatedAt());
        }

        // 6. FETCH FIRST User
        User firstUser = userEm.findFirst(User.class);
        System.out.printf("\n[FIRST USER] ID: %d, Username: %s%n", firstUser.getId(), firstUser.getUsername());

        // 7. FETCH FIRST Order WHERE amount < 500
        Order firstCheapOrder = orderEm.findFirst(Order.class, "amount < 500");
        System.out.printf("[FIRST CHEAP ORDER] ID: %d, Amount: %.2f%n", firstCheapOrder.getOrderId(), firstCheapOrder.getAmount());

        // 8. UPDATE a user (test doUpdate)
        firstUser.setUsername("updated_user");
        firstUser.setAge(99);
        userEm.persist(firstUser); // Triggers UPDATE because ID != 0
        System.out.println("[UPDATE] User updated.");

        // 9. FETCH the same user again to confirm update
        User updatedUser = userEm.findFirst(User.class, "id = " + firstUser.getId());
        System.out.printf("[AFTER UPDATE] Username: %s, Age: %d%n", updatedUser.getUsername(), updatedUser.getAge());
    }
}
package ee.sda.mackirill;

import ee.sda.mackirill.entities.*;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Save_order {
    public static void main(String[] args) {
        Order order = new Order();

        //Create person
        PersonType clientType = new PersonType("client");
        Person client = new Person("client name", "email@asdf.com", "pass", "37235235", clientType);
        clientType.setPerson(client);

        //Create table
        Table table = new Table(5, false);
        table.getOrders().add(order);

        //Create payment type
        PaymentType cash = new PaymentType("cash");
        cash.setOrder(order);
        //Create order status
        OrderStatus open_order = new OrderStatus("open");

        //Set orders person
        order.setPerson(client);
        //Set peoples count
        order.setPeoples(5);
        //Set time to order
        order.setTimeToOrder(LocalDateTime.now());
        //Set table
        order.setTable(table);
        //Set total sum
        order.setTotalSum(new BigDecimal(1000));
        //Set payment type
        order.setPaymentType(cash);
        //Set order status
        order.setStatus(open_order);
        //Set create date
        order.setCreateDate(LocalDateTime.now());
        //Set update date
        order.setUpdateDate(LocalDateTime.now());

        try(Session session = new Configuration().configure().buildSessionFactory().openSession()) {
            session.beginTransaction();

            session.saveOrUpdate(order);
            session.saveOrUpdate(open_order);
            session.saveOrUpdate(cash);
            session.saveOrUpdate(table);
            session.saveOrUpdate(clientType);
            session.saveOrUpdate(client);

            session.getTransaction().commit();
        }
    }
}
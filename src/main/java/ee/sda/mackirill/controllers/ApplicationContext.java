package ee.sda.mackirill.controllers;

import ee.sda.mackirill.entities.OrderStatus;
import ee.sda.mackirill.entities.PaymentType;
import ee.sda.mackirill.entities.PersonType;
import ee.sda.mackirill.enums.OrderStatusEnum;
import ee.sda.mackirill.enums.PaymentTypeEnum;
import ee.sda.mackirill.enums.PersonTypeEnum;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import java.util.Optional;
import java.util.Scanner;

public class ApplicationContext {
    private static Session session;
    private static Scanner scanner = new Scanner(System.in);
    public ApplicationContext() {
        session = connectToDb();
        checkDBHaveEnumsValue();
    }

    private Session connectToDb() {
        return new Configuration().configure().buildSessionFactory().openSession();
    }

    public Session getSession() {
        return session;
    }

    public static Scanner getScanner() {
        return scanner;
    }

    private void checkDBHaveEnumsValue () {
        session.beginTransaction();
        for (PersonTypeEnum personTypeEnum: PersonTypeEnum.values()) {
            PersonType personType = session.byNaturalId(PersonType.class)
                    .using("type", personTypeEnum.toString().toLowerCase()).load();
            if(personType == null) {
                personType = new PersonType(personTypeEnum.toString().toLowerCase());
                session.saveOrUpdate(personType);

            }
        }

        for(OrderStatusEnum orderStatusEnum: OrderStatusEnum.values()) {
            OrderStatus orderStatus = session.byNaturalId(OrderStatus.class)
                    .using("name", orderStatusEnum.toString().toLowerCase()).load();
            if(orderStatus == null) {
                orderStatus = new OrderStatus(orderStatusEnum.toString().toLowerCase());
                session.saveOrUpdate(orderStatus);
            }
        }

        for (PaymentTypeEnum paymentTypeEnum: PaymentTypeEnum.values()) {
            Optional<PaymentType> paymentType = session.byNaturalId(PaymentType.class)
                    .using("paymentName", paymentTypeEnum.toString().toLowerCase()).loadOptional();
            if(paymentType.isEmpty()) {
                session.saveOrUpdate(new PaymentType(paymentTypeEnum.toString().toLowerCase()));
            }
        }
        session.getTransaction().commit();
    }
}

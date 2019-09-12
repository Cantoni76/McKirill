package ee.sda.mckirill.controllers.entity;

import ee.sda.mckirill.entities.MenuItem;

public class MenuController extends AbstractEntityController {
    public static void saveMenuItem(MenuItem menuItem) {
        session.beginTransaction();
        session.saveOrUpdate(menuItem);
        session.getTransaction().commit();
    }
}
package ee.sda.mckirill.controllers.ui;

import ee.sda.mckirill.controllers.entity.MenuController;
import ee.sda.mckirill.entities.MenuItem;
import ee.sda.mckirill.entities.Person;
import ee.sda.mckirill.enums.MenuItemsTypeEnum;
import ee.sda.mckirill.strings.BaseString;
import ee.sda.mckirill.strings.MenuStrings;

public class MenuUIController extends AbstractUIController {
    public MenuUIController(Person person) {
        super(person);
    }

    @Override
    public void start() {
        while (true) {
            System.out.println(MenuStrings.MANAGER_MENU_MAIN_ACTION);
            String actionSelect = scanner.nextLine();
            switch (actionSelect) {
                case "1":
                    break;
                case "2":
                    System.out.println(MenuStrings.MENU_ADD_NEW);
                    editMenu(new MenuItem());
                    endOfUIIntercation();
                    break;
                case "0":
                    return;
                default:
                    System.out.println(BaseString.WRONG_COMMAND);
            }
        }
    }

    private void editMenu(MenuItem menuItem) {
        while (true) {
            System.out.println(MenuStrings.MENU_SET_NAME);
            menuItem.setName(scanner.nextLine());
            if(!menuItem.getName().isEmpty()) {
                break;
            } else {
                System.out.println(MenuStrings.MENU_EMPTY_NAME);
            }
        }
        while (true) {
            System.out.println(MenuStrings.MENU_SET_TYPE);
            for(MenuItemsTypeEnum menuItemsTypeEnum: MenuItemsTypeEnum.values()) {
                System.out.println( menuItemsTypeEnum.toString());
            }
            try {
                menuItem.setType(MenuItemsTypeEnum.valueOf(scanner.nextLine().toUpperCase()));
                break;
            } catch (Exception e) {
                System.out.println(MenuStrings.MENU_WRONG_TYPE);
            }
        }
        while (true) {
            System.out.println(MenuStrings.MENU_SET_PRICE);
            menuItem.setPrice(scanner.nextBigDecimal());
            if (menuItem.getPrice().signum() >= 0 ) {
                break;
            } else {
                System.out.println(MenuStrings.MENU_PRICE_0_LOW);
            }
        }
        MenuController.saveMenuItem(menuItem);
        System.out.println(BaseString.SAVE_IN_DB);
    }

}
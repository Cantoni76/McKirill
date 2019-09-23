package ee.sda.mckirill.controllers.ui;

import ee.sda.mckirill.entities.Holiday;
import ee.sda.mckirill.entities.Person;
import ee.sda.mckirill.strings.BaseString;
import ee.sda.mckirill.strings.HolidaysStrings;
import ee.sda.mckirill.util.ConsoleTablePrint;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class HolidaysUIController extends AbstractUIController {
    public HolidaysUIController(Person person) {
        super(person);
    }

    @Override
    public void start() {
        Map<Integer, Consumer> holidaysActionMenu = new HashMap<>();
        holidaysActionMenu.put(1, T -> showAllHolidays());
        holidaysActionMenu.put(2, T -> editHoliday(new Holiday()));
        holidaysActionMenu.put(3, T -> System.out.println(BaseString.TODO));
        holidaysActionMenu.put(4, T -> System.out.println(BaseString.TODO));

        selectMenuAction(HolidaysStrings.HOLIDAYS_MENU_MAIN_ACTION, holidaysActionMenu);
    }

    private void showAllHolidays() {
        List<Holiday> holidays = getListFromNamedQuery("get_all_holidays", Holiday.class);
        ConsoleTablePrint holidayTable = new ConsoleTablePrint();
        holidayTable.setShowVerticalLines(true);
        holidayTable.setHeaders(HolidaysStrings.TABLE_ID, HolidaysStrings.TABLE_DATE, HolidaysStrings.TABLE_FROM_TIME, HolidaysStrings.TABLE_TO_TIME);
        for(Holiday holiday: holidays) {
            holidayTable.addRow(holiday.getId() + "", holiday.getDate().toString(), holiday.getFromTime().toString(), holiday.getToTime().toString());
        }
        holidayTable.print();
    }

    private void editHoliday(Holiday holiday) {
        holiday.setDate(selectDate());
        
    }
}
package services;

import models.Booking;
import utils.DataBookingUtil;
import utils.MenuModifier;

import java.awt.print.Book;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class BookingServiceImpl implements IBookingService {
    static Scanner sc = new Scanner(System.in);
    static TreeSet<Booking> bookingTreeSet = new TreeSet<>();
    static DataBookingUtil dataBookingUtil = new DataBookingUtil();

    static {
        bookingTreeSet.add(new Booking("43DN", LocalDate.of(2023, 02, 06), LocalDate.of(2023, 02, 10), "77C2", "SVVL-0001"));
        bookingTreeSet.add(new Booking("73QB", LocalDate.of(2023, 02, 04), LocalDate.of(2023, 02, 15), "92C3", "SVRO-0001"));
        bookingTreeSet.add(new Booking("92QN", LocalDate.of(2023, 01, 15), LocalDate.of(2023, 03, 04), "43G2", "SVRO-0003"));
        dataBookingUtil.writeDataBookingToFile(bookingTreeSet);
    }

    MenuModifier menuModifier = new MenuModifier();

    @Override
    public void displayList() {
        TreeSet<Booking> bookingTreeSet = dataBookingUtil.readDataBookingFromFile();
        for (Booking booking: bookingTreeSet) {
            System.out.println(booking);
        }
        System.out.println("Completed !!");
    }

    @Override
    public boolean add() {
        System.out.println("Nhap ma booking: ");
        String codeBooking = sc.nextLine();
        System.out.println("Nhap ngay bat dau theo format(dd/mm/yyyy): ");
        String startDateString = sc.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate startDate = LocalDate.parse(startDateString, formatter);
        System.out.println("Nhap ngay ket thuc theo format(dd/mm/yyyy): ");
        String endDateString = sc.nextLine();
        LocalDate endDate = LocalDate.parse(endDateString, formatter);
        System.out.println("Ma khach hang: ");
        String codeCustomer = menuModifier.getCodeCustomer();
        System.out.println("Ten dich vu: ");
        String nameService = menuModifier.getCodeService();
        bookingTreeSet.add(new Booking(codeBooking, startDate, endDate, codeCustomer, nameService));
        dataBookingUtil.writeDataBookingToFile(bookingTreeSet);
        System.out.println("Completed !!");
        return true;
    }
}

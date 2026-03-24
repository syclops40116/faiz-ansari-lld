package design_problems.personal.bookmyshow;

import design_problems.personal.bookmyshow.enums.PaymentType;
import design_problems.personal.bookmyshow.enums.SeatType;
import design_problems.personal.bookmyshow.model.*;
import design_problems.personal.bookmyshow.repository.BookingRepository;
import design_problems.personal.bookmyshow.repository.MovieRepository;
import design_problems.personal.bookmyshow.repository.ShowRepository;
import design_problems.personal.bookmyshow.repository.TheatreRepository;
import design_problems.personal.bookmyshow.service.BookingService;
import design_problems.personal.bookmyshow.service.MovieService;
import design_problems.personal.bookmyshow.service.ShowService;
import design_problems.personal.bookmyshow.service.TheatreService;
import design_problems.personal.bookmyshow.strategy.locking.DefaultLockProvider;
import design_problems.personal.bookmyshow.strategy.locking.LockProvider;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // repositories
        TheatreRepository theatreRepository = new TheatreRepository();
        MovieRepository movieRepository = new MovieRepository();
        ShowRepository showRepository = new ShowRepository();
        BookingRepository bookingRepository = new BookingRepository();

        // lock provider
        LockProvider lockProvider = new DefaultLockProvider();

        // services
        TheatreService theatreService = new TheatreService(theatreRepository);
        MovieService movieService = new MovieService(movieRepository);
        ShowService showService = new ShowService(showRepository, movieRepository, theatreRepository);
        BookingService bookingService = new BookingService(bookingRepository, lockProvider);

        // create theatre and screen
        Theatre theatre = theatreService.createTheatre("t1", "PVR Phoenix", "Bangalore");
        Screen s1 = new Screen("s1");
        theatreService.addScreen(theatre.getId(), s1);

        for (int i = 1; i <= 5; i++) {
            theatreService.addSeat(theatre.getId(), "s1", new Seat("s1-" + i, SeatType.REGULAR, 150.0));
            theatreService.addSeat(theatre.getId(), "s1", new Seat("s1-"+ (i+5) , SeatType.RECLINER, 300.0));
        }

        Movie movie = movieService.createMovie("m1", "Avengers", 120);

        // create show for 6:30 PM 20th June 2026
        LocalDateTime dateTime = LocalDateTime.of(2026, 6, 20, 18, 30);
        Show show = showService.createShow("show1", movie.getId(), theatre.getId(), s1.getId(), dateTime);

        Thread t1 = new Thread(() -> {
            try {
                Booking booking = bookingService.createBooking("User1", show, List.of("s1-1", "s1-2"));
                bookingService.confirmBooking(booking, PaymentType.UPI);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                Booking booking = bookingService.createBooking("User2", show, List.of("s1-2", "s1-3"));
                bookingService.confirmBooking(booking, PaymentType.CARD);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        });

//        t1.start();
//        t2.start();
//
//        try {
//            t1.join();
//            t2.join();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }

        Booking booking = bookingService.createBooking("User1", show, List.of("s1-1", "s1-2"));
        try {
            Thread.sleep(6000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        bookingService.confirmBooking(booking, PaymentType.CARD);
    }
}

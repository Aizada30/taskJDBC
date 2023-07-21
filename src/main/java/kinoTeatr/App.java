package kinoTeatr;

import kinoTeatr.model.Movie;
import kinoTeatr.model.ShowTime;
import kinoTeatr.service.MovieService;
import kinoTeatr.service.ShowTimeService;
import kinoTeatr.service.serviceImpl.MovieServiceImpl;
import kinoTeatr.service.serviceImpl.ShowTimeServiceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        MovieService movieService = new MovieServiceImpl();
        ShowTimeService showTimeService = new ShowTimeServiceImpl();

//        System.out.println(movieService.createMovie(
//                "show_times",
//                List.of(
//                        "id serial primary key ",
//                        "movie_id int references movies(id)",
//                        "theatre_id int references theartes(id)",
//                        "start_time timestamp",
//                        "end_time timestamp"
//                )
//        ));

        movieService.getById(2L)

        Scanner scannerForSt = new Scanner(System.in);
        Scanner scannerForInt = new Scanner(System.in);

        while (true) {
            switch (new Scanner(System.in).nextLine()) {
                case "1", "save" -> {
                    System.out.println("write title: ");
                    String title = scannerForSt.nextLine();
                    System.out.println("write genre: ");
                    String genre = scannerForSt.nextLine();
                    System.out.println("write duration: ");
                    int duration = scannerForInt.nextInt();
                    movieService.saveMovie(new Movie(title, genre, duration));
                }
                case "2", "find" -> {
                    System.out.println("write movie id: ");
                    System.out.println(movieService.findMovieById(scannerForInt.nextLong()));
                }
                case "3" -> {
                    showTimeService.save(new ShowTime(1L, 1L, LocalDateTime.of(2023, 7,
                            20,
                            18,
                            30,
                            0),
                            LocalDateTime.of(2023,16)));
                }
            }
        }
    }
}

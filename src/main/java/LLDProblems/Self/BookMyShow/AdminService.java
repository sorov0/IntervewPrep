package LLDProblems.Self.BookMyShow;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//Adds Movies, Theatres, Screens, Shows.
class AdminService {

    private final List<Movie> movies = new ArrayList<>();
    private final List<City> cities = new ArrayList<>();

    public void addMovie(Movie movie) {
        movies.add(movie);
        System.out.println("Added movie: " + movie);
    }

    public void addCity(City city) {
        cities.add(city);
        System.out.println("Added city: " + city);
    }

    public void addTheatreToCity(Theatre theatre, City city) {
        city.addTheatre(theatre);
    }

    public void addScreenToTheatre(Screen screen, Theatre theatre) {
        theatre.addScreen(screen);
    }

    public Show addShowToScreen(Movie movie, Screen screen, long start, long end) {
        Show show = new Show(UUID.randomUUID().toString(), movie, screen, start, end);
        System.out.println("Added show: " + show);
        return show;
    }

    public List<City> getCities() { return cities; }
    public List<Movie> getMovies() { return movies; }
}


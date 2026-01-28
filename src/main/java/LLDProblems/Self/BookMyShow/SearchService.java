package LLDProblems.Self.BookMyShow;

import java.util.ArrayList;
import java.util.List;

class SearchService {

    private final AdminService adminService;

    public SearchService(AdminService adminService) {
        this.adminService = adminService;
    }

    public List<Movie> searchMovies(String cityName, String language) {
        return adminService.getMovies().stream()
                .filter(m -> m.getLanguage().equalsIgnoreCase(language))
                .toList();
    }

    public List<Theatre> getTheatresByCity(String cityName) {
        return adminService.getCities().stream()
                .filter(c -> c.getName().equalsIgnoreCase(cityName))
                .findFirst()
                .map(City::getTheatres)
                .orElse(List.of());
    }

    public List<Show> getShowsByMovie(String cityName, String movieTitle) {
        List<Theatre> theatres = getTheatresByCity(cityName);

        List<Show> shows = new ArrayList<>();

        for (Theatre th : theatres) {
            for (Screen sc : th.getScreens()) {
/*                for (Show show : sc.getShowSeats().get(0).show.getScreen().getTheatre().getScreens()
                ) {
                    if (show.getMovie().getTitle().equalsIgnoreCase(movieTitle)) {
                        shows.add(show);
                    }
                }*/
            }
        }
        return shows;
    }
}


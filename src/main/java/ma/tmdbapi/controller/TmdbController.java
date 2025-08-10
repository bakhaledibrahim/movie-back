package ma.tmdbapi.controller;




import ma.tmdbapi.service.TmdbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tmdb")
@CrossOrigin(origins = "http://localhost:3000")
public class TmdbController {

    @Autowired
    private TmdbService tmdbService;

    // New endpoint for clickable cast
    @GetMapping("/person/{personId}/credits")
    public String getPersonCredits(@PathVariable String personId) {
        return tmdbService.getPersonCredits(personId);
    }

    // All other endpoints remain the same...
    @GetMapping("/movie/now_playing")
    public String getNowPlayingMovies(@RequestParam(defaultValue = "") String genreId, @RequestParam(defaultValue = "1") int page) {
        return tmdbService.getNowPlayingMovies(genreId, page);
    }

    @GetMapping("/movie/popular")
    public String getPopularMovies(@RequestParam(defaultValue = "") String genreId, @RequestParam(defaultValue = "1") int page) {
        return tmdbService.getPopularMovies(genreId, page);
    }

    @GetMapping("/movie/top_rated")
    public String getTopRatedMovies(@RequestParam(defaultValue = "") String genreId, @RequestParam(defaultValue = "1") int page) {
        return tmdbService.getTopRatedMovies(genreId, page);
    }

    @GetMapping("/tv/popular")
    public String getPopularTvShows(@RequestParam(defaultValue = "") String genreId, @RequestParam(defaultValue = "1") int page) {
        return tmdbService.getPopularTvShows(genreId, page);
    }

    @GetMapping("/tv/top_rated")
    public String getTopRatedTvShows(@RequestParam(defaultValue = "") String genreId, @RequestParam(defaultValue = "1") int page) {
        return tmdbService.getTopRatedTvShows(genreId, page);
    }

    @GetMapping("/trending/movie")
    public String getTrendingMovies(@RequestParam(defaultValue = "1") int page) {
        return tmdbService.getTrendingMovies(page);
    }

    @GetMapping("/upcoming/movie")
    public String getUpcomingMovies(@RequestParam(defaultValue = "1") int page) {
        return tmdbService.getUpcomingMovies(page);
    }

    @GetMapping("/genres/movie")
    public String getMovieGenres() { return tmdbService.getMovieGenres(); }

    @GetMapping("/genres/tv")
    public String getTvShowGenres() { return tmdbService.getTvShowGenres(); }

    @GetMapping("/movie/{id}")
    public String getMovieDetails(@PathVariable String id) { return tmdbService.getMovieDetails(id); }

    @GetMapping("/tv/{id}")
    public String getTvShowDetails(@PathVariable String id) { return tmdbService.getTvShowDetails(id); }

    @GetMapping("/tv/{tvShowId}/season/{seasonNumber}")
    public String getSeasonDetails(@PathVariable String tvShowId, @PathVariable String seasonNumber) {
        return tmdbService.getSeasonDetails(tvShowId, seasonNumber);
    }

    @GetMapping("/movie/{id}/similar")
    public String getSimilarMovies(@PathVariable String id) { return tmdbService.getSimilarMovies(id); }

    @GetMapping("/tv/{id}/similar")
    public String getSimilarTvShows(@PathVariable String id) { return tmdbService.getSimilarTvShows(id); }

    @GetMapping("/search/movie")
    public String searchMovies(@RequestParam String query) { return tmdbService.searchMovies(query); }

    @GetMapping("/search/tv")
    public String searchTvShows(@RequestParam String query) { return tmdbService.searchTvShows(query); }

    @GetMapping("/movie/high_quality")
    public String getHighQualityMovies(@RequestParam(defaultValue = "") String genreId, @RequestParam(defaultValue = "1") int page) {
        return tmdbService.getHighQualityMovies(genreId, page);
    }
}
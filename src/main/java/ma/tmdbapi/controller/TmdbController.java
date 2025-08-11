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

    @GetMapping("/search/multi")
    public String searchMulti(@RequestParam String query, @RequestParam(defaultValue = "1") int page) {
        return tmdbService.searchMulti(query, page);
    }

    @GetMapping("/movie/{movieId}/recommendations")
    public String getMovieRecommendations(@PathVariable String movieId) {
        return tmdbService.getMovieRecommendations(movieId);
    }

    @GetMapping("/tv/{showId}/recommendations")
    public String getTvRecommendations(@PathVariable String showId) {
        return tmdbService.getTvRecommendations(showId);
    }

    @GetMapping("/collection/{collectionId}")
    public String getCollectionDetails(@PathVariable String collectionId) {
        return tmdbService.getCollectionDetails(collectionId);
    }

    @GetMapping("/movie/acclaimed_scifi")
    public String getCriticallyAcclaimedSciFi(@RequestParam(defaultValue = "1") int page) {
        return tmdbService.getCriticallyAcclaimedSciFi(page);
    }

    @GetMapping("/movie/mind_bending")
    public String getMindBendingThrillers(@RequestParam(defaultValue = "1") int page) {
        return tmdbService.getMindBendingThrillers(page);
    }

    @GetMapping("/tv/netflix")
    public String getNetflixOriginals(@RequestParam(defaultValue = "1") int page) {
        return tmdbService.getTvShowsByNetwork(213, page);
    }

    @GetMapping("/tv/disney")
    public String getDisneyOriginals(@RequestParam(defaultValue = "1") int page) {
        return tmdbService.getTvShowsByNetwork(2739, page);
    }

    @GetMapping("/tv/amazon")
    public String getAmazonOriginals(@RequestParam(defaultValue = "1") int page) {
        return tmdbService.getTvShowsByNetwork(1024, page);
    }

    @GetMapping("/tv/apple")
    public String getAppleOriginals(@RequestParam(defaultValue = "1") int page) {
        return tmdbService.getTvShowsByNetwork(2552, page);
    }

    @GetMapping("/movie/high_quality")
    public String getHighQualityMovies(@RequestParam(defaultValue = "") String genreId, @RequestParam(defaultValue = "1") int page) {
        return tmdbService.getHighQualityMovies(genreId, page);
    }

    @GetMapping("/trending/tv")
    public String getTrendingTvShows(@RequestParam(defaultValue = "1") int page) {
        return tmdbService.getTrendingTvShows(page);
    }

    @GetMapping("/movie/new_releases")
    public String getNewMovieReleases(@RequestParam(defaultValue = "1") int page) {
        return tmdbService.getNewMovieReleases(page);
    }

    @GetMapping("/person/{personId}/credits")
    public String getPersonCredits(@PathVariable String personId) { return tmdbService.getPersonCredits(personId); }

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

    @GetMapping("/{mediaType}/{id}/videos")
    public String getVideos(@PathVariable String mediaType, @PathVariable String id) {
        return tmdbService.getVideos(mediaType, id);
    }


    @GetMapping("/anime/popular")
    public String getPopularAnime(@RequestParam(defaultValue = "1") int page) {
        return tmdbService.getPopularAnime(page);
    }

    @GetMapping("/anime/top_rated")
    public String getTopRatedAnime(@RequestParam(defaultValue = "1") int page) {
        return tmdbService.getTopRatedAnime(page);
    }

    @GetMapping("/anime/netflix")
    public String getNetflixAnime(@RequestParam(defaultValue = "1") int page) {
        return tmdbService.getAnimeByNetwork(213, page);
    }

    @GetMapping("/anime/disney")
    public String getDisneyAnime(@RequestParam(defaultValue = "1") int page) {
        return tmdbService.getAnimeByNetwork(2739, page);
    }

    @GetMapping("/anime/hbo")
    public String getHboAnime(@RequestParam(defaultValue = "1") int page) {
        return tmdbService.getAnimeByNetwork(49, page);
    }

    @GetMapping("/anime/apple")
    public String getAppleAnime(@RequestParam(defaultValue = "1") int page) {
        return tmdbService.getAnimeByNetwork(2552, page);
    }
}
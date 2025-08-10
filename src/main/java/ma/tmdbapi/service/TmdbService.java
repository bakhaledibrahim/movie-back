package ma.tmdbapi.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TmdbService {

    @Value("${tmdb.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();
    private final String BASE_URL = "https://api.themoviedb.org/3";

    // Helper method for discover endpoint
    private String buildDiscoverUrl(String mediaType, String sortBy, String genreId, int page) {
        String url = BASE_URL + "/discover/" + mediaType + "?api_key=" + apiKey + "&sort_by=" + sortBy + "&page=" + page;
        if (genreId != null && !genreId.isEmpty()) {
            url += "&with_genres=" + genreId;
        }
        if (sortBy.contains("vote_average")) {
            url += "&vote_count.gte=300"; // Higher threshold for critically acclaimed
        }
        return url;
    }

    // --- ANIME METHODS ---
    public String getPopularAnime(int page) {
        // Genre ID for Animation is 16
        return restTemplate.getForObject(buildDiscoverUrl("tv", "popularity.desc", "16", page), String.class);
    }

    public String getTopRatedAnime(int page) {
        return restTemplate.getForObject(buildDiscoverUrl("tv", "vote_average.desc", "16", page), String.class);
    }

    public String getAnimeByNetwork(int networkId, int page) {
        String url = BASE_URL + "/discover/tv?api_key=" + apiKey + "&with_networks=" + networkId + "&with_genres=16&sort_by=popularity.desc&page=" + page;
        return restTemplate.getForObject(url, String.class);
    }

    // --- GENERAL TV & MOVIE METHODS ---
    public String getTvShowsByNetwork(int networkId, int page) {
        String url = BASE_URL + "/discover/tv?api_key=" + apiKey + "&with_networks=" + networkId + "&sort_by=popularity.desc&page=" + page;
        return restTemplate.getForObject(url, String.class);
    }

    public String getHighQualityMovies(String genreId, int page) {
        String url = buildDiscoverUrl("movie", "popularity.desc", genreId, page);
        url += "&with_release_type=5|4"; // Digital or Physical release
        return restTemplate.getForObject(url, String.class);
    }

    public String searchMovies(String query) {
        String url = BASE_URL + "/search/movie?api_key=" + apiKey + "&query=" + query;
        return restTemplate.getForObject(url, String.class);
    }

    public String searchTvShows(String query) {
        String url = BASE_URL + "/search/tv?api_key=" + apiKey + "&query=" + query;
        return restTemplate.getForObject(url, String.class);
    }

    public String getPersonCredits(String personId) {
        String url = BASE_URL + "/person/" + personId + "/combined_credits?api_key=" + apiKey;
        return restTemplate.getForObject(url, String.class);
    }

    public String getMovieGenres() {
        String url = BASE_URL + "/genre/movie/list?api_key=" + apiKey;
        return restTemplate.getForObject(url, String.class);
    }

    public String getTvShowGenres() {
        String url = BASE_URL + "/genre/tv/list?api_key=" + apiKey;
        return restTemplate.getForObject(url, String.class);
    }

    public String getNowPlayingMovies(String genreId, int page) {
        String url = BASE_URL + "/movie/now_playing?api_key=" + apiKey + "&page=" + page;
        if (genreId != null && !genreId.isEmpty()) {
            url += "&with_genres=" + genreId;
        }
        return restTemplate.getForObject(url, String.class);
    }

    public String getPopularMovies(String genreId, int page) {
        return restTemplate.getForObject(buildDiscoverUrl("movie", "popularity.desc", genreId, page), String.class);
    }

    public String getTopRatedMovies(String genreId, int page) {
        return restTemplate.getForObject(buildDiscoverUrl("movie", "vote_average.desc", genreId, page), String.class);
    }

    public String getPopularTvShows(String genreId, int page) {
        return restTemplate.getForObject(buildDiscoverUrl("tv", "popularity.desc", genreId, page), String.class);
    }

    public String getTopRatedTvShows(String genreId, int page) {
        return restTemplate.getForObject(buildDiscoverUrl("tv", "vote_average.desc", genreId, page), String.class);
    }

    public String getTrendingMovies(int page) {
        String url = BASE_URL + "/trending/movie/week?api_key=" + apiKey + "&page=" + page;
        return restTemplate.getForObject(url, String.class);
    }

    public String getTrendingTvShows(int page) {
        String url = BASE_URL + "/trending/tv/week?api_key=" + apiKey + "&page=" + page;
        return restTemplate.getForObject(url, String.class);
    }

    public String getUpcomingMovies(int page) {
        String url = BASE_URL + "/movie/upcoming?api_key=" + apiKey + "&page=" + page;
        return restTemplate.getForObject(url, String.class);
    }

    public String getNewMovieReleases(int page) {
        String url = BASE_URL + "/discover/movie?api_key=" + apiKey + "&sort_by=popularity.desc&page=" + page;
        url += "&primary_release_date.lte=2025-08-10&primary_release_date.gte=2025-07-10";
        url += "&with_release_type=4|5";
        return restTemplate.getForObject(url, String.class);
    }

    public String getMovieDetails(String id) {
        String url = BASE_URL + "/movie/" + id + "?api_key=" + apiKey + "&append_to_response=videos,images,credits";
        return restTemplate.getForObject(url, String.class);
    }

    public String getTvShowDetails(String id) {
        String url = BASE_URL + "/tv/" + id + "?api_key=" + apiKey + "&append_to_response=videos,images,credits";
        return restTemplate.getForObject(url, String.class);
    }

    public String getSeasonDetails(String tvShowId, String seasonNumber) {
        String url = BASE_URL + "/tv/" + tvShowId + "/season/" + seasonNumber + "?api_key=" + apiKey;
        return restTemplate.getForObject(url, String.class);
    }

    public String getSimilarMovies(String id) {
        String url = BASE_URL + "/movie/" + id + "/similar?api_key=" + apiKey;
        return restTemplate.getForObject(url, String.class);
    }

    public String getSimilarTvShows(String id) {
        String url = BASE_URL + "/tv/" + id + "/similar?api_key=" + apiKey;
        return restTemplate.getForObject(url, String.class);
    }

    // --- NEW METHODS FOR RECOMMENDATIONS ---
    public String getMovieRecommendations(String movieId) {
        String url = BASE_URL + "/movie/" + movieId + "/recommendations?api_key=" + apiKey;
        return restTemplate.getForObject(url, String.class);
    }

    public String getTvRecommendations(String showId) {
        String url = BASE_URL + "/tv/" + showId + "/recommendations?api_key=" + apiKey;
        return restTemplate.getForObject(url, String.class);
    }

    public String getVideos(String mediaType, String id) {
        String url = BASE_URL + "/" + mediaType + "/" + id + "/videos?api_key=" + apiKey;
        return restTemplate.getForObject(url, String.class);
    }
}
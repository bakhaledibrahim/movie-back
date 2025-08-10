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

    // Helper method to build discover URLs
    private String buildDiscoverUrl(String mediaType, String sortBy, String genreId, int page) {
        String url = BASE_URL + "/discover/" + mediaType + "?api_key=" + apiKey + "&sort_by=" + sortBy + "&page=" + page;
        if (genreId != null && !genreId.isEmpty()) {
            url += "&with_genres=" + genreId;
        }
        // Add a vote count minimum for top rated to get better results
        if (sortBy.contains("vote_average")) {
            url += "&vote_count.gte=200";
        }
        return url;
    }

    // --- All list methods now use the correct /discover endpoint for filtering ---

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

    // These methods don't use genre filters, so they remain the same
    public String getNowPlayingMovies(String genreId, int page) {
        String url = BASE_URL + "/movie/now_playing?api_key=" + apiKey + "&page=" + page;
        if (genreId != null && !genreId.isEmpty()) {
            url += "&with_genres=" + genreId; // now_playing supports this, so it's a special case
        }
        return restTemplate.getForObject(url, String.class);
    }

    public String getTrendingMovies(int page) {
        String url = BASE_URL + "/trending/movie/week?api_key=" + apiKey + "&page=" + page;
        return restTemplate.getForObject(url, String.class);
    }

    public String getUpcomingMovies(int page) {
        String url = BASE_URL + "/movie/upcoming?api_key=" + apiKey + "&page=" + page;
        return restTemplate.getForObject(url, String.class);
    }

    // --- All other non-list methods remain unchanged ---

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


    // New method for High Quality releases
    public String getHighQualityMovies(String genreId, int page) {
        String url = buildDiscoverUrl("movie", "popularity.desc", genreId, page);
        // 4 = Digital, 5 = Physical (Blu-ray). This finds movies available in high quality.
        url += "&with_release_type=5|4";
        return restTemplate.getForObject(url, String.class);
    }
}
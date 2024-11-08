package amadda.amadda.jpa.ctrl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import amadda.amadda.jpa.domain.entity.PostEntity;
import amadda.amadda.jpa.domain.entity.PostResponseDTO;
import amadda.amadda.jpa.domain.entity.UserRequestDTO;
import amadda.amadda.jpa.domain.entity.WeatherResponseDTO;
import amadda.amadda.jpa.service.PostService;
import amadda.amadda.jpa.service.UserService;
import amadda.amadda.jpa.service.WeatherService;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/amadda")
@RequiredArgsConstructor
public class Controller {

    private final UserService userService;
    private final PostService postService;

    @PostMapping("/user/save")
    public ResponseEntity<UserRequestDTO> save(@RequestBody UserRequestDTO params) {
        System.out.println("client endpoint : /api/jpa/save " + userService);
        System.out.println("params = " + params);
        UserRequestDTO result = userService.save(params);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/postsByWeather")
    public ResponseEntity<List<PostResponseDTO>> getPostsByWeather(@RequestParam String weather) {
        try {
            System.out.println("params = " + weather);
            List<PostResponseDTO> posts = postService.getPostsByWeather(weather);
            return ResponseEntity.ok(posts);
        } catch (Exception e) {
            System.err.println("Error fetching posts by weather: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/posts/postId")
    public ResponseEntity<List<PostResponseDTO>> getPostsByIds(@RequestParam List<Integer> postIds) {
        List<PostResponseDTO> posts = postService.getPostsByIds(postIds);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/posts/mood")
    public ResponseEntity<List<PostResponseDTO>> getPostsByMood(@RequestParam List<String> moods) {
        System.out.println("params = " + moods); // 수신된 moods 로그 출력
        List<PostResponseDTO> posts = postService.getPostsByMood(moods);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/posts/privacy")
    public ResponseEntity<List<PostResponseDTO>> getPostsByPrivacy(@RequestParam PostResponseDTO.Privacy privacy) {
        List<PostResponseDTO> posts = postService.getPostsByPrivacy(privacy);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/posts/pinColor")
    public ResponseEntity<List<PostResponseDTO>> getPostsByColor(@RequestParam String color) {
        List<PostResponseDTO> posts = postService.getPostsByColor(color);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/posts/searchText")
    public ResponseEntity<List<PostResponseDTO>> searchPosts(@RequestParam String searchText) {
        System.out.println("params = " + searchText);
        List<PostResponseDTO> result = postService.getPostsBySearchText(searchText);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/posts/tags")
    public ResponseEntity<List<PostEntity>> getPostsByTags(@RequestParam List<String> tagNames) {
        System.out.println("params = " + tagNames);
        List<PostEntity> posts = postService.getPostsByTags(tagNames);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/posts/topics")
    public ResponseEntity<List<PostEntity>> getPostsByTopics(@RequestParam List<String> topicNames) {
        System.out.println("params = " + topicNames);
        List<PostEntity> posts = postService.getPostsByTopics(topicNames);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/posts/latest")
    public ResponseEntity<List<PostResponseDTO>> getLatestPosts() {
        List<PostResponseDTO> posts = postService.getLatestPosts();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/posts/verification")
    public List<PostResponseDTO> getPostsByReceiptVerification(@RequestParam Boolean receiptVerification) {
        return postService.findPostsByReceiptVerification(receiptVerification);
    }

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/weatherByLocation")
    public ResponseEntity<WeatherResponseDTO> getWeatherByLocation(@RequestParam double lat, @RequestParam double lon) {
        try {
            WeatherResponseDTO weatherResponse = weatherService.getWeatherByLocation(lat, lon);
            return ResponseEntity.ok(weatherResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/foodImage")
    public ResponseEntity<List<String>> getFirstFoodImage(@RequestParam Integer postId) {
        List<String> images = postService.getFirstFoodImageUrl(postId);
        return ResponseEntity.ok(images);
    }

    @GetMapping("/foodImages")
    public ResponseEntity<Map<Integer, String>> getFoodImagesByPostIds(@RequestParam List<Integer> postIds) {
        Map<Integer, String> images = postService.getFirstFoodImagesByPostIds(postIds);
        return ResponseEntity.ok(images);
    }

    @GetMapping("/posts/dailyViews")
    public ResponseEntity<List<PostResponseDTO>> getPostsSortedByViews() {
        List<PostResponseDTO> posts = postService.getPostsSortedByDailyViews();
        return ResponseEntity.ok(posts);
    }

}

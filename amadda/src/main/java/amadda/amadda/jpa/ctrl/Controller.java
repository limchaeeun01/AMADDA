package amadda.amadda.jpa.ctrl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import amadda.amadda.jpa.domain.entity.PostResponseDTO;
import amadda.amadda.jpa.domain.entity.UserRequestDTO;
import amadda.amadda.jpa.service.PostService;
import amadda.amadda.jpa.service.UserService;
import lombok.RequiredArgsConstructor;

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

    @GetMapping("/posts/weather")
    public ResponseEntity<List<PostResponseDTO>> getPostsByWeather(@RequestParam PostResponseDTO.Weather weather) {
        List<PostResponseDTO> posts = postService.getPostsByWeather(weather);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/posts/mood")
    public ResponseEntity<List<PostResponseDTO>> getPostsByMood(@RequestParam PostResponseDTO.Mood mood) {
        List<PostResponseDTO> posts = postService.getPostsByMood(mood);
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

}

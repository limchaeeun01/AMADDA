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

import amadda.amadda.jpa.service.PostService;
import amadda.amadda.jpa.service.UserService;
import amadda.amadda.jpa.domain.entity.PostResponseDTO;
import amadda.amadda.jpa.domain.entity.UserRequestDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/jpa")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PostService postService;

    @PostMapping("/save")
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
}

package amadda.amadda.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import amadda.amadda.jpa.dao.PostDAO;
import amadda.amadda.jpa.domain.entity.PostResponseDTO;

@Service
public class PostService {

    @Autowired
    private PostDAO postDAO;

    public List<PostResponseDTO> getPostsByWeather(String weather) {
        return postDAO.findPostsByWeather(weather);
    }

    public List<PostResponseDTO> getPostsByMood(PostResponseDTO.Mood mood) {
        return postDAO.findPostsByMood(mood);
    }

    public List<PostResponseDTO> getPostsByPrivacy(PostResponseDTO.Privacy privacy) {
        return postDAO.findPostsByPrivacy(privacy);
    }

    public List<PostResponseDTO> getPostsByColor(String color) {
        int minPosts = getMinPostsByColor(color);
        return postDAO.findPostsByColor(minPosts);
    }

    private int getMinPostsByColor(String color) {
        switch (color) {
            case "Purple":
                return 400;
            case "Yellow":
                return 300;
            case "Blue":
                return 200;
            case "Orange":
                return 100;
            case "Red":
                return 50;
            default:  // "Black"
                return 0;
        }
    }
}

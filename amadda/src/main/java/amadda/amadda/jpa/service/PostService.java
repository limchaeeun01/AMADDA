package amadda.amadda.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import amadda.amadda.jpa.dao.PostDAO;
import amadda.amadda.jpa.dao.UserDAO;
import amadda.amadda.jpa.domain.entity.PostResponseDTO;

@Service
public class PostService {

    @Autowired
    private PostDAO postDAO;

    public List<PostResponseDTO> getPostsByWeather(PostResponseDTO.Weather weather) {
        return postDAO.findPostsByWeather(weather);
    }
}

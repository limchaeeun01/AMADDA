package amadda.amadda.jpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import amadda.amadda.jpa.domain.entity.PostResponseDTO;

@Repository
public interface PostDAO extends JpaRepository<PostResponseDTO, Integer> {

    @Query("SELECT p FROM PostResponseDTO p WHERE p.weather = :weather")
    List<PostResponseDTO> findPostsByWeather(@Param("weather") PostResponseDTO.Weather weather);
}

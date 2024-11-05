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
    List<PostResponseDTO> findPostsByWeather(@Param("weather") String weather);

    @Query("SELECT p FROM PostResponseDTO p WHERE p.mood = :mood")
    List<PostResponseDTO> findPostsByMood(@Param("mood") PostResponseDTO.Mood mood);

    @Query("SELECT p FROM PostResponseDTO p WHERE p.privacy = :privacy")
    List<PostResponseDTO> findPostsByPrivacy(@Param("privacy") PostResponseDTO.Privacy privacy);

    @Query("SELECT p FROM PostResponseDTO p JOIN p.restaurant r WHERE r.totalPost >= :minPosts")
    List<PostResponseDTO> findPostsByColor(@Param("minPosts") int minPosts);

    @Query("SELECT p FROM PostResponseDTO p JOIN p.restaurant r WHERE r.restaurantName LIKE %:searchText%")
    List<PostResponseDTO> findByRestaurantName(@Param("searchText") String searchText);

    @Query("SELECT p FROM TagEntity t JOIN PostResponseDTO p ON t.post.postId = p.postId WHERE t.tagName = :searchText")
    List<PostResponseDTO> findByTagTagName(@Param("searchText") String searchText);

    List<PostResponseDTO> findByMoodIn(List<String> moods);

    List<PostResponseDTO> findAllByOrderByPostDateAsc();

    List<PostResponseDTO> findByReceiptVerification(Boolean receiptVerification);

}

package amadda.amadda.jpa.domain.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name = "post")
@Data
@Entity
@DynamicUpdate
public class PostResponseDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Integer postId;

    @Column(name = "post_title")
    private String postTitle;

    @Column(name = "post_content")
    private String postContent;

    @Column(name = "post_date")
    private LocalDateTime postDate = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(name = "privacy")
    private Privacy privacy = Privacy.PUBLIC;

    @Enumerated(EnumType.STRING)
    @Column(name = "food_category")
    private FoodCategory foodCategory;

    @Enumerated(EnumType.STRING)
    @Column(name = "mood")
    private Mood mood;

    @Enumerated(EnumType.STRING)
    @Column(name = "weather")
    private Weather weather;

    @Column(name = "receipt_verification")
    private Boolean receiptVerification = false;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private RestaurantEntity restaurant;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserRequestDTO user;

    @ManyToOne
    @JoinColumn(name = "theme_id")
    private ThemeEntity theme;

    public enum Privacy {
        public  , private  , only_me
    }

    public enum FoodCategory {
        한식, 중식, 양식, 일식, 아시아요리, 패스트푸드, 디저트
    }

    public enum Mood {
        기쁨, 슬픔, 화남, 평온
    }

    public enum Weather {
        맑음, 눈, 비, 흐림, 바람
    }
}

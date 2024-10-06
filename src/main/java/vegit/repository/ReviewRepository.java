package vegit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vegit.domain.Review;

public interface ReviewRepository extends JpaRepository<Review,Long> {


}

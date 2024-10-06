package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vegit.domain.Tag;

public interface TagRepository extends JpaRepository<Tag,Long> {


}

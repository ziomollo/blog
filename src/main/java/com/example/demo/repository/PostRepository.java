package com.example.demo.repository;

import com.example.demo.model.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("Select p From Post p LEFT JOIN FETCH p.author ORDER BY p.date DESC")
    List<Post> findLatest5Posts(Pageable pageable);

    Post findByTitle(String title);

}

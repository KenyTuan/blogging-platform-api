package com.test.bloggingplatformapi.repository;


import com.test.bloggingplatformapi.model.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface BlogRepository extends MongoRepository<Blog, String> {

    @Query("{ '_id': ?0, 'objStatus':  'ACTIVE'}")
    Optional<Blog> findBlogActiveById(String id);

    @Query("{ 'title': { $regex: ?0, $options: 'i' } }")
    Page<Blog> findByTitleContaining(String title, Pageable pageable);

}
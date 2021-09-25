package com.saname.myblog.models;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MyBlogRepository extends JpaRepository<MyBlog,Long> {
}

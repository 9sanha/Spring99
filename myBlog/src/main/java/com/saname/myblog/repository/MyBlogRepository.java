package com.saname.myblog.repository;

import com.saname.myblog.models.MyBlog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyBlogRepository extends JpaRepository<MyBlog,Long> {
}

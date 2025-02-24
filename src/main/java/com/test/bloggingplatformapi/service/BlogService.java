package com.test.bloggingplatformapi.service;

import com.test.bloggingplatformapi.dtos.req.BlogRes;
import com.test.bloggingplatformapi.dtos.res.BlogReq;
import com.test.bloggingplatformapi.dtos.req.PageRes;
import com.test.bloggingplatformapi.model.enums.Tag;

import java.util.Set;

public interface BlogService {
    Set<BlogRes> getAllBlogs();

    BlogRes getBlogById(String id);

    PageRes<BlogRes> searchBlogsByTitle(String title, int page, int size, String sortBy, String sortDir);

    BlogRes addBlog(BlogReq req);

    BlogRes updateBlog(String id, BlogReq req);

    BlogRes updateTagsBlog(String id, Set<Tag> tags);

    void deleteBlog(String id);
}

package com.test.bloggingplatformapi.controller;

import com.test.bloggingplatformapi.constants.APIEndPoints;
import com.test.bloggingplatformapi.dtos.req.BlogRes;
import com.test.bloggingplatformapi.dtos.res.BlogReq;
import com.test.bloggingplatformapi.dtos.req.PageRes;
import com.test.bloggingplatformapi.model.enums.Tag;
import com.test.bloggingplatformapi.service.BlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Controller manages the APIs associated with the blog.
 * Use the @RestController annotation to mark this as a controller that handles HTTP requests.
 * Annotation @RequestMapping(APIEndPoints.PREFIX) to define the base URL for all endpoints in this controller.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping(APIEndPoints.PREFIX)
public class BlogController {

    private final BlogService blogService;

    /**
     * Get All Blogs
     * GET /api/v1/blogs
     *
     * @return Set<BlogRes> - List Blogs as DTO.
     */
    @GetMapping(APIEndPoints.BLOG_V1_BASE)
    public Set<BlogRes> getAllBlogs() {
        return blogService.getAllBlogs();
    }

    /**
     * Get the details of a blog based on ID.
     * GET /api/v1/blogs/{id}
     *
     * @param id - ID of the blog to get information from.
     * @return BlogRes - The blog details as a DTO.
     */
    @GetMapping(APIEndPoints.BLOG_V1_BASE + "/{id}")
    public BlogRes getBlogById(@PathVariable String id) {
        return blogService.getBlogById(id);
    }

    /**
     * Search blogs by title and paginate the results.
     * GET /api/v1/blogs/search?title={title}&page={page}&size={size}&sortBy={sortBy}&sortDir={sortDir}
     *
     * @param title - Title to search.
     * @param page - Page number (starting from 0).
     * @param size - Number of elements per page.
     * @param sortBy - Field to sort by (default is "title").
     * @param sortDir - Sort direction (default is "asc").
     * @return PageRes<BlogRes> - Pagination results as DTO.
     */
    @GetMapping(APIEndPoints.BLOG_V1_BASE + "/search")
    public PageRes<BlogRes> searchBlogsByTitle(
            @RequestParam String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "asc") String sortBy,
            @RequestParam(defaultValue = "title") String sortDir) {
        return blogService.searchBlogsByTitle(title,page,size,sortBy,sortDir);
    }

    /**
     * Create a new blog.
     * POST /api/v1/blogs
     *
     * @param req - Object containing the information needed to create the blog.
     * @return BlogRes - The newly created blog information as a DTO.
     */
    @PostMapping(APIEndPoints.BLOG_V1_BASE)
    @ResponseStatus(HttpStatus.CREATED)
    public BlogRes createBlog(@RequestBody @Valid BlogReq req) {
        return blogService.addBlog(req);
    }

    /**
     * Update a blog's information based on ID.
     * PUT /api/v1/blogs/{id}
     *
     * @param id - ID of the blog to update.
     * @param res - Object containing the information to update.
     * @return BlogRes - Blog information after update as DTO.
     */
    @PutMapping(APIEndPoints.BLOG_V1_BASE + "/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public BlogRes updateBlog(@PathVariable String id,@RequestBody @Valid BlogReq res) {
        return blogService.updateBlog(id,res);
    }

    /**
     * Update tags of a blog based on ID.
     * PATCH /api/v1/blogs/{id}
     *
     * @param id - ID of the blog whose tags need to be updated.
     * @param tags - List of new tags.
     * @return BlogRes - Blog information after updating tags as DTO.
     */
    @PatchMapping(APIEndPoints.BLOG_V1_BASE + "/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public BlogRes updateTabBlog(@PathVariable String id,@RequestBody Set<Tag> tags) {
        return blogService.updateTagsBlog(id,tags);
    }

    /**
     * Delete a blog based on ID.
     * DELETE /api/v1/blogs/{id}
     *
     * @param id - ID of the blog to delete.
     */
    @DeleteMapping(APIEndPoints.BLOG_V1_BASE + "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBlog(@PathVariable String id) {
        blogService.deleteBlog(id);
    }
}

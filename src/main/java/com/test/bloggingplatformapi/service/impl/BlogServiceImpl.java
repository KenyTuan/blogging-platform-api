package com.test.bloggingplatformapi.service.impl;

import com.test.bloggingplatformapi.converter.BlogConverter;
import com.test.bloggingplatformapi.dtos.req.BlogRes;
import com.test.bloggingplatformapi.dtos.res.BlogReq;
import com.test.bloggingplatformapi.dtos.req.PageRes;
import com.test.bloggingplatformapi.exception.NotFoundException;
import com.test.bloggingplatformapi.model.entity.Blog;
import com.test.bloggingplatformapi.model.enums.ObjStatus;
import com.test.bloggingplatformapi.model.enums.Tag;
import com.test.bloggingplatformapi.repository.BlogRepository;
import com.test.bloggingplatformapi.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BlogServiceImpl implements BlogService {
    private final BlogRepository blogRepository;

    /**
     * Get all blogs and convert to DTO.

     * @return Set<BlogRes> - List of blogs as DTO.
     */
    @Override
    public Set<BlogRes> getAllBlogs() {
        return BlogConverter.convertToDtoList(
                blogRepository.findAll());
    }

    /**
     * Get the details of a blog based on ID.

     * @param id - ID of the blog to get information from.

     * @return BlogRes - The details of the blog as a DTO.

     */
    @Override
    public BlogRes getBlogById(String id) {
        return BlogConverter.covertToDto(findBlogById(id));
    }

    /**
     * Search blogs by title and paginate the results.
     *
     * @param title - Title to search.
     * @param page - Page number (starting from 0).
     * @param size - Number of elements per page.
     * @param sortBy - Field to sort by.
     * @param sortDir - Sort direction (asc or desc).
     * @return PageRes<BlogRes> - Pagination results as DTO.
     */
    @Override
    public PageRes<BlogRes> searchBlogsByTitle(String title, int page, int size,
                                               String sortBy, String sortDir) {
        final Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();

        final Pageable pageable = PageRequest.of(page, size, sort);

        final Page<Blog> blogs = blogRepository
                .findByTitleContaining(title, pageable);

        final Set<BlogRes> blogRes = blogs
                .stream()
                .filter(blog -> blog.getObjStatus().equals(ObjStatus.ACTIVE))
                .map(BlogConverter::covertToDto)
                .collect(Collectors.toSet());

        return new PageRes<>(
                blogRes,
                blogs.getNumber(),
                blogs.getSize(),
                blogs.getTotalElements(),
                blogs.getTotalPages(),
                blogs.isLast()
        );
    }

    /**
     * Create a new blog.
     *
     * @param req - Object containing the information needed to create the blog.
     * @return BlogRes - The newly created blog information as a DTO.
     */
    @Override
    @Transactional
    public BlogRes addBlog(BlogReq req) {
        final Blog blog = BlogConverter.convertToEntity(req);

        blogRepository.save(blog);

        return BlogConverter.covertToDto(blog);
    }

    /**
     * Update information of a blog based on ID.

     * @param id - ID of the blog to update.

     * @param req - Object containing information to update.

     * @return BlogRes - Blog information after update as DTO.

     */
    @Override
    @Transactional
    public BlogRes updateBlog(String id, BlogReq req) {
        deleteBlog(id);
        final Blog updatedBlog = BlogConverter.convertToEntity(req);

        return BlogConverter.covertToDto(blogRepository.save(updatedBlog));
    }

    /**
     * Update tags of a blog based on ID.

     * @param id - ID of the blog whose tags need to be updated.

     * @param tags - List of new tags.

     * @return BlogRes - Blog information after updating tags as DTO.

     */
    @Override
    @Transactional
    public BlogRes updateTagsBlog(String id, Set<Tag> tags) {
        final Blog blog = findBlogById(id);
        blog.setTags(tags);
        return BlogConverter.covertToDto(blogRepository.save(blog));
    }

    /**
     * Delete a blog based on ID (marked as DELETED).
     *
     * @param id - ID of the blog to delete.
     */
    @Override
    @Transactional
    public void deleteBlog(String id) {
        final Blog blog = findBlogById(id);
        blog.setObjStatus(ObjStatus.DELETED);
        blogRepository.save(blog);
    }

    /**
     * Search for blogs by ID and make sure the blog is ACTIVE.

     * @param id - ID of the blog to find.

     * @return Blog - The found blog object.

     * @throws NotFoundException - If the blog does not exist or is not ACTIVE.

     */
    private Blog findBlogById(String id) {
        return blogRepository.findBlogActiveById(id)
                .orElseThrow(()-> new NotFoundException("Blog not found!"));
    }
}

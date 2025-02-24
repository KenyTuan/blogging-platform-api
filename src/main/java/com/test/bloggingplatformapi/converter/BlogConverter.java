package com.test.bloggingplatformapi.converter;

import com.test.bloggingplatformapi.dtos.req.BlogRes;
import com.test.bloggingplatformapi.dtos.res.BlogReq;
import com.test.bloggingplatformapi.model.entity.Blog;
import com.test.bloggingplatformapi.model.enums.ObjStatus;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Converter for converting between Blog and BlogReq.
 */
@Component
public class BlogConverter {

    /**
     * Converts a BlogReq to a Blog.
     *
     * @param req The BlogReq to convert.
     * @return The converted Blog.
     */
    public static Blog convertToEntity(BlogReq req) {
        return Blog
                .builder()
                .title(req.getTitle())
                .content(req.getContent())
                .category(req.getCategory())
                .tags(req.getTags())
                .ObjStatus(ObjStatus.ACTIVE)
                .build();
    }


    /**
     * Converts a Blog to a BlogRes.
     *
     * @param blog The Blog to convert.
     * @return The converted BlogRes.
     */
    public static BlogRes covertToDto(Blog blog) {
        return new BlogRes(
                blog.getId(),
                blog.getTitle(),
                blog.getContent(),
                blog.getCategory(),
                blog.getTags(),
                blog.getCreatedAt(),
                blog.getUpdatedAt()
        );
    }

    /**
     * Converts a list of Blog objects to a list of BlogRes objects.
     *
     * @param blogs The list of Blog objects to convert.
     * @return The list of converted BlogRes objects.
     */
    public static Set<BlogRes> convertToDtoList(List<Blog> blogs) {
        if (blogs.isEmpty()) {
            return new HashSet<>();
        }

        return blogs.stream()
                .map(BlogConverter::covertToDto)
                .collect(Collectors.toSet());
    }

    /**
     * Converts a list of BlogReq objects to a list of Blog objects.
     *
     * @param reqs The list of BlogReq objects to convert.
     * @return The list of converted Blog objects.
     */
    public Set<Blog> convertToEntitySet(Set<BlogReq> reqs) {
        return reqs.stream()
                .map(BlogConverter::convertToEntity)
                .collect(Collectors.toSet());
    }

}

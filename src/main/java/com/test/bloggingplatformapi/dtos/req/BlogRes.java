package com.test.bloggingplatformapi.dtos.req;

import com.test.bloggingplatformapi.model.enums.Category;
import com.test.bloggingplatformapi.model.enums.Tag;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

public record BlogRes(
        String id,
        String title,
        String content,
        Category category,
        Set<Tag> tags,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) implements Serializable {
}

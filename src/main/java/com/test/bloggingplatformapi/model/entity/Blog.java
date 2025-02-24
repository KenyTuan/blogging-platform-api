package com.test.bloggingplatformapi.model.entity;

import com.test.bloggingplatformapi.model.enums.Category;
import com.test.bloggingplatformapi.model.enums.ObjStatus;
import com.test.bloggingplatformapi.model.enums.Tag;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Set;

@Document(collection = "blogs")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class Blog {

    @Id
    private String id;

    private String title;

    private String content;

    private Category category;

    private Set<Tag> tags;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    private ObjStatus ObjStatus;
}

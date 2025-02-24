package com.test.bloggingplatformapi.dtos.res;

import com.test.bloggingplatformapi.constants.MessageException;
import com.test.bloggingplatformapi.model.enums.Category;
import com.test.bloggingplatformapi.model.enums.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Builder @Getter
@NoArgsConstructor
@AllArgsConstructor
public class BlogReq {

    @NotBlank(message = MessageException.MSG_TITLE_NOT_NULL)
    private String title;

    @NotBlank(message = MessageException.MSG_CONTENT_NOT_NULL)
    private String content;

    @NotNull(message = MessageException.MSG_CATEGORY_NOT_NULL)
    private Category category;

    @Builder.Default
    private Set<Tag> tags = new HashSet<>();
}

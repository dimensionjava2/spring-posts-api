package com.dimension.posts.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostsRequest {

    private Integer userId;
    private Boolean includeComments;
}

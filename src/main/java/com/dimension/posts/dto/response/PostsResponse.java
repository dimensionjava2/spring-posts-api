package com.dimension.posts.dto.response;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostsResponse {
    private UserDto user;
    private Integer totalPosts;
    private List<PostDto> posts;
}

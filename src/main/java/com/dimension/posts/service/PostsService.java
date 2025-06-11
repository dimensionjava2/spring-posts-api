package com.dimension.posts.service;

import com.dimension.posts.client.JsonPlaceholderClient;
import com.dimension.posts.dto.external.ExternalPost;
import com.dimension.posts.dto.external.ExternalUser;
import com.dimension.posts.dto.request.PostsRequest;
import com.dimension.posts.dto.response.PostDto;
import com.dimension.posts.dto.response.PostsResponse;
import com.dimension.posts.dto.response.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostsService {

    private final JsonPlaceholderClient jsonPlaceholderClient;

    public PostsResponse getUserPosts(PostsRequest request) {
        log.info("Getting posts for user: {}", request.getUserId());

        // Get user information
        ExternalUser externalUser = jsonPlaceholderClient.getUser(request.getUserId());

        // Get user posts
        List<ExternalPost> externalPosts = jsonPlaceholderClient.getUserPosts(request.getUserId());

        // Map user data
        UserDto userDto = mapToUserDto(externalUser);

        // Map posts data
        List<PostDto> postDtos = externalPosts.stream()
                .map(post -> mapToPostDto(post, request.getIncludeComments()))
                .collect(Collectors.toList());

        log.info("Successfully retrieved {} posts for user {}", postDtos.size(), request.getUserId());

        return PostsResponse.builder()
                .user(userDto)
                .totalPosts(postDtos.size())
                .posts(postDtos)
                .build();
    }

    private UserDto mapToUserDto(ExternalUser externalUser) {
        return UserDto.builder()
                .id(externalUser.getId())
                .name(externalUser.getName())
                .email(externalUser.getEmail())
                .build();
    }

    private PostDto mapToPostDto(ExternalPost externalPost, Boolean includeComments) {

        PostDto.PostDtoBuilder builder = PostDto.builder()
                .id(externalPost.getId())
                .title(externalPost.getTitle())
                .body(externalPost.getBody());

        if (includeComments) {
            int commentsCount = jsonPlaceholderClient.getPostComments(externalPost.getId()).size();
            builder.commentsCount(commentsCount);
        }

        return builder.build();
    }
}

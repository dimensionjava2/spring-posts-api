package com.dimension.posts.controller;

import com.dimension.posts.dto.request.PostsRequest;
import com.dimension.posts.dto.response.PostsResponse;
import com.dimension.posts.service.PostsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts/api/v1")
@RequiredArgsConstructor
@Slf4j
public class PostsController {

    private final PostsService postsService;

    @PostMapping("/user/posts")
    public ResponseEntity<PostsResponse> getUserPosts(@RequestBody PostsRequest request) {
        log.info("Received request to get posts for user: {}", request.getUserId());

        PostsResponse response = postsService.getUserPosts(request);

        return ResponseEntity.ok(response);
    }

    // Bonus endpoint
    @GetMapping("/user/{userId}/summary")
    public ResponseEntity<PostsResponse> getUserPostsSummary(
            @PathVariable Integer userId,
            @RequestParam(defaultValue = "false") Boolean includeComments) {

        PostsRequest request = new PostsRequest(userId, includeComments);
        PostsResponse response = postsService.getUserPosts(request);

        return ResponseEntity.ok(response);
    }
}

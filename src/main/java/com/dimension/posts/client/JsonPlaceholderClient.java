package com.dimension.posts.client;


import com.dimension.posts.dto.external.ExternalComment;
import com.dimension.posts.dto.external.ExternalPost;
import com.dimension.posts.dto.external.ExternalUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(
        name = "jsonplaceholder-client",
        url = "${jsonplaceholder.api.base-url}"
)
public interface JsonPlaceholderClient {

    @GetMapping("/users/{userId}")
    ExternalUser getUser(@PathVariable("userId") Integer userId);

    @GetMapping("/users/{userId}/posts")
    List<ExternalPost> getUserPosts(@PathVariable("userId") Integer userId);

    @GetMapping("/posts/{postId}/comments")
    List<ExternalComment> getPostComments(@PathVariable("postId") Integer postId);
}

package com.dimension.posts.dto.external;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExternalComment {
    private Integer postId;
    private Integer id;
    private String name;
    private String email;
    private String body;
}

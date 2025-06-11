package com.dimension.posts.dto.external;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExternalPost {
    private Integer userId;
    private Integer id;
    private String title;
    private String body;
}

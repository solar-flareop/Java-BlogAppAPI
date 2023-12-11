package com.solarflare.blogAppAPI.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @NoArgsConstructor
public class PostResponse {
    private List<PostDTO>content;
    private int pageNo;
    private int pageSize;
    private long totalPosts;
    private int totalPages;
    private boolean lastPage;
}

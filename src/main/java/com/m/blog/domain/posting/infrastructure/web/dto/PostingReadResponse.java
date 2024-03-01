package com.m.blog.domain.posting.infrastructure.web.dto;


import com.m.blog.domain.posting.infrastructure.repository.PostingDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class PostingReadResponse {
    private int postingId;
    private String title;
    private String content;
    private int boardId;
    private String boardName;
    private int boardCollectionId;
    private String boardCollectionName;
    private LocalDateTime createdTime;

    public static PostingReadResponse of(PostingDto postingDto){
        return PostingReadResponse.builder()
                .postingId(postingDto.getPostingId())
                .title(postingDto.getTitle())
                .content(postingDto.getContent())
                .boardId(postingDto.getBoardId())
                .boardName(postingDto.getBoardName())
                .boardCollectionId(postingDto.getBoardCollectionId())
                .boardCollectionName(postingDto.getBoardCollectionName())
                .createdTime(postingDto.getCreatedTime())
                .build();
    }
}

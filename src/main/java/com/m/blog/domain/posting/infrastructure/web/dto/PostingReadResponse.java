package com.m.blog.domain.posting.infrastructure.web.dto;


import com.m.blog.domain.posting.application.domain.Posting;
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

    public static PostingReadResponse of(Posting.Sophisticated sophisticated){
        return PostingReadResponse.builder()
                .postingId(sophisticated.getPostingId())
                .title(sophisticated.getTitle())
                .content(sophisticated.getContent())
                .boardId(sophisticated.getBoardId())
                .boardName(sophisticated.getBoardName())
                .boardCollectionId(sophisticated.getBoardCollectionId())
                .boardCollectionName(sophisticated.getBoardCollectionName())
                .createdTime(sophisticated.getCreatedTime())
                .build();
    }
}

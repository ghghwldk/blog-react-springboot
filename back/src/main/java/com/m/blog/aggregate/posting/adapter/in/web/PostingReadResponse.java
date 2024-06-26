package com.m.blog.aggregate.posting.adapter.in.web;


import com.m.blog.aggregate.posting.adapter.out.persistence.PostingDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class PostingReadResponse {
    private String postingId;
    private String title;
    private String content;
    private String boardId;
    private String boardName;
    private String boardCollectionId;
    private String boardCollectionName;
    private LocalDateTime createdTime;

    public static PostingReadResponse of(PostingDto dto){
        return PostingReadResponse.builder()
                .postingId(dto.getPostingId())
                .title(dto.getTitle())
                .content(dto.getContent())
                .boardId(dto.getBoardId())
                .boardName(dto.getBoardName())
                .boardCollectionId(dto.getBoardCollectionId())
                .boardCollectionName(dto.getBoardCollectionName())
                .createdTime(dto.getCreatedTime())
                .build();
    }
}

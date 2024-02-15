package com.m.blog.domain.posting.dto;


import com.m.blog.domain.posting.dto.dsl.PostingDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class PostingReadResponseDto {
    private int postingId;
    private String title;
    private String content;
    private int boardId;
    private String boardName;
    private int boardCollectionId;
    private String boardCollectionName;
    private LocalDateTime createdTime;

    public static PostingReadResponseDto of(PostingDto postingDto){
        return PostingReadResponseDto.builder()
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

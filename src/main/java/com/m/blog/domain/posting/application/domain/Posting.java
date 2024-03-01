package com.m.blog.domain.posting.application.domain;

import com.m.blog.common.Adapter;
import com.m.blog.domain.posting.infrastructure.web.dto.PostingReadFilteredPagingRequest;
import lombok.*;
import org.springframework.data.domain.Pageable;

import javax.persistence.Id;

@Data
@Builder
@AllArgsConstructor
public class Posting {
    PostingId postingId;
    Integer boardId;
    Integer boardCollectionId;
    String title;
    String content;

    public static Posting.InBoardCondition forFilteredPage(int boardCollectionId, int boardId){
        return Posting.InBoardCondition.builder()
                .boardCollectionId(boardCollectionId)
                .boardId(boardId)
                .build();
    }

    public static Posting.SingleCondition get(int boardCollectionId, int boardId, int postingId){
        return Posting.SingleCondition.builder()
                .boardCollectionId(boardCollectionId)
                .boardId(boardId)
                .postingId(postingId)
                .build();
    }

    public static Posting withId(
            int postingId, int boardCollectionId, int boardId,
            String title, String content) {
        return Posting.builder()
                .postingId(new PostingId(postingId))
                .boardCollectionId(boardCollectionId)
                .boardId(boardId)
                .title(title)
                .content(content)
                .build();
    }

    @Getter
    @AllArgsConstructor
    public static class PostingId{
        private int value;
    }

    @AllArgsConstructor
    @Builder
    @Getter
    public static class InBoardCondition{
        private int boardCollectionId;
        private int boardId;
    }

    @AllArgsConstructor
    @Builder
    @Getter
    public static class SingleCondition{
        private int boardCollectionId;
        private int boardId;
        private int postingId;
    }
}

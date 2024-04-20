package com.m.blog.boardCollection.application.domain;

import com.m.blog.boardCollection.application.domain.Board;
import lombok.*;
import org.thymeleaf.util.StringUtils;

@Getter
@Builder
public class Posting {
    @NonNull private final PostingId postingId;
    @NonNull private final Board.BoardId boardId;
    private String title;
    private String content;

    public static PerBoardCondition of(@NonNull String boardId){
        return PerBoardCondition.builder()
                .boardId(boardId)
                .build();
    }

    public static PostingId get(@NonNull String postingId){
        return PostingId.builder()
                .value(postingId)
                .build();
    }


    @Getter
    @Builder
    @AllArgsConstructor
    public static class PostingId {
        private String value;

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (!(o instanceof PostingId))
                return false;
            PostingId converted = (PostingId) o;
            return StringUtils.equals(value, converted.getValue());
        }
    }



    @AllArgsConstructor
    @Builder
    @Getter
    public static class PerBoardCondition {
        private String boardCollectionId;
        private String boardId;
    }

    @AllArgsConstructor
    @Builder
    @Getter
    public static class WithoutId {
        private String title;
        private String content;
    }



}

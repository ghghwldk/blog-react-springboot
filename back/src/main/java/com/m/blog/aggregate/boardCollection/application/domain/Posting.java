package com.m.blog.aggregate.boardCollection.application.domain;

import lombok.*;
import org.thymeleaf.util.StringUtils;

@Getter
@Builder
public class Posting {
    @NonNull private final PostingId postingId;
    @NonNull private final Board.BoardId boardId;
    @NonNull private String title;
    @NonNull private String content;
    @Builder.Default
    private boolean isPostingUpdated = false;
    @Builder.Default
    private boolean isPostingAdded = false;

    public static PostingId get(@NonNull String postingId){
        return PostingId.builder()
                .value(postingId)
                .build();
    }

    void update(@NonNull Posting after){
        this.title = after.getTitle();
        this.content = after.getContent();

        isPostingUpdated = true;
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

            return this.value.equals(((PostingId) o).getValue());
        }
    }



    @AllArgsConstructor
    @Builder
    @Getter
    public static class PerBoardCondition {
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

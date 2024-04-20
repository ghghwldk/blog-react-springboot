package com.m.blog.aggregate.boardCollection.application.domain;

import lombok.*;
import org.thymeleaf.util.StringUtils;

@Builder
public class Board {
    @NonNull @Getter private final BoardId boardId;
    @NonNull @Getter private final BoardCollection.BoardCollectionId boardCollectionId;
    @NonNull @Getter private String name;
    @NonNull @Getter private String description;
    @NonNull private final PostingWindow postingWindow;
    @Builder.Default
    @Getter private boolean isBoardAdded = false;
    @Builder.Default
    @Getter private boolean isBoardUpdated = false;
    @Builder.Default
    @Getter private boolean isPostingAdded = false;
    @Builder.Default
    @Getter private boolean isPostingUpdated = false;

    void change(@NonNull Board after){
        this.name = after.name;
        this.description = after.name;

        isBoardUpdated = true;
    }

    void change(@NonNull Posting after){
        this.postingWindow.update(after);

        isPostingUpdated = true;
    }

    void add(@NonNull Posting posting){
        this.postingWindow.add(posting);
        isPostingAdded = true;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class BoardId {
        private String value;
    }

    Posting getUpdated(){
        return postingWindow.getUpdated();
    }

    Posting getAdded(){
        return postingWindow.getAdded();
    }
}

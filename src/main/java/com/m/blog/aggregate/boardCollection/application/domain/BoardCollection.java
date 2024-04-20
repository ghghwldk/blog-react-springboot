package com.m.blog.aggregate.boardCollection.application.domain;

import com.m.blog.global.exception.DataNotFoundException;
import lombok.*;

@Builder
@AllArgsConstructor
public class BoardCollection{
    @NonNull @Getter private final BoardCollection.BoardCollectionId boardCollectionId;
    @NonNull private String name;
    @NonNull private BoardWindow boardWindow;

    @Builder.Default
    @Getter private boolean isBoardCollectionUpdated = false;
    @Builder.Default
    @Getter private boolean isBoardAdded = false;
    @Builder.Default
    @Getter private boolean isBoardUpdated = false;
    @Builder.Default
    private boolean isPostingUpdated = false;

    private boolean isPostingAdded = false;


    public String remove(@NonNull Board.BoardId boardId){
        return boardWindow.remove(boardId);
    }


    @Data
    @Builder
    @AllArgsConstructor
    public static class BoardCollectionId {
        private String value;
    }

    public Posting getUpdatedPosting(){
        if(! isPostingUpdated){
            throw new DataNotFoundException();
        }
        return this.boardWindow.getUpdatedPosting();
    }

    public Posting getAddedPosting(){
        if(! isPostingAdded){
            throw new DataNotFoundException();
        }
        return this.boardWindow.getAddedPosting();
    }

    public void add(Board board){
        boardWindow.add(board);
        isBoardAdded = true;
    }

    public void add(Posting posting){
        this.boardWindow.add(posting);
        isPostingAdded = true;
    }

    public void update(Posting posting){
        this.boardWindow.update(posting);
        isPostingUpdated = true;
    }
}

package com.m.blog.aggregate.boardCollection.application.domain;

import com.m.blog.global.customAnnotation.Domain;
import com.m.blog.global.customAnnotation.Root;
import com.m.blog.global.exception.DataNotFoundException;
import lombok.*;

@Builder
@AllArgsConstructor
@Root
@Domain
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
    @Builder.Default
    private boolean isPostingAdded = false;


    public String remove(@NonNull Board.BoardId boardId){
        return boardWindow.remove(boardId);
    }


    @Getter
    @Builder
    @AllArgsConstructor
    public static class BoardCollectionId {
        private String value;

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (!(o instanceof BoardCollectionId))
                return false;

            return this.value.equals(((BoardCollectionId) o).getValue());
        }
    }

    public Posting getUpdated(){
        if(! isPostingUpdated){
            throw new DataNotFoundException();
        }
        return this.boardWindow.getUpdated();
    }

    public Posting getAdded(){
        if(! isPostingAdded){
            throw new DataNotFoundException();
        }
        return this.boardWindow.getAdded();
    }

    public void add(@NonNull Board board){
        boardWindow.add(board);
        isBoardAdded = true;
    }

    public void add(@NonNull Posting posting){
        this.boardWindow.add(posting);
        isPostingAdded = true;
    }

    public void update(@NonNull Posting posting){
        this.boardWindow.update(posting);
        isPostingUpdated = true;
    }
}

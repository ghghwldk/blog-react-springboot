package com.m.blog.aggregate.boardCollection.application.domain;

import com.m.blog.global.customAnnotation.Domain;
import com.m.blog.global.customAnnotation.Root;
import com.m.blog.global.exception.DataNotFoundException;
import lombok.*;

@AllArgsConstructor
@Root
@Domain
public class BoardCollection{
    @Getter private final BoardCollection.BoardCollectionId boardCollectionId;
    @Getter private String name;
    @Getter private BoardWindow boardWindow;

    @Getter private boolean isBoardCollectionUpdated = false;
    @Getter private boolean isBoardAdded = false;
    @Getter private boolean isBoardUpdated = false;
    @Getter private boolean isPostingUpdated = false;
    @Getter private boolean isPostingAdded = false;


    public String remove(@NonNull Board.BoardId boardId){
        return boardWindow.remove(boardId);
    }

    public BoardCollection(String boardCollectionId, String name, BoardWindow boardWindow){
        this.boardCollectionId = new BoardCollectionId(boardCollectionId);
        this.name = name;
        this.boardWindow = boardWindow;
    }

    @Getter
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

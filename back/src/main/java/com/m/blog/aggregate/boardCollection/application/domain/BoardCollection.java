package com.m.blog.aggregate.boardCollection.application.domain;

import com.m.blog.aggregate.posting.application.domain.Posting;
import com.m.blog.global.customAnnotation.Domain;
import com.m.blog.global.customAnnotation.Root;
import com.m.blog.global.exception.DataNotFoundException;
import com.m.blog.global.exception.TooManyException;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@Root
@Domain
public class BoardCollection{
    @Getter private final BoardCollection.BoardCollectionId boardCollectionId;
    @Getter private String name;
    @Getter private _BoardStore boardStore;


    public BoardCollection(String boardCollectionId, String name, _BoardStore boardStore){
        this.boardCollectionId = new BoardCollectionId(boardCollectionId);
        this.name = name;
        this.boardStore = boardStore;
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



    public void add(@NonNull Board board){
        boardStore.save(board);
    }

    public void add(@NonNull Posting posting){
        this.boardStore.save(posting);
    }

    public void update(@NonNull Posting posting){
        this.boardStore.update(posting);
    }

    private Object getSingle(List list){
        if(list.isEmpty()){
            throw new DataNotFoundException();
        }
        if(list.size()>1){
            throw new TooManyException();
        }
        return list.get(0);
    }
    public void delete(@NonNull Board.BoardId boardId){
        boardStore.delete(boardId);
    }


    public Posting getUpdated(){
        return (Posting) getSingle(this.boardStore.getUpdatedPostings());
    }

    public Posting getAdded(){
        return (Posting) getSingle(this.boardStore.getSavedPostings());
    }
}

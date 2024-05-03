package com.m.blog.aggregate.boardCollection.application.domain;

import com.m.blog.aggregate.posting.application.domain.Posting;
import com.m.blog.global.exception.AlreadyExistException;
import com.m.blog.global.exception.DataNotFoundException;
import lombok.NonNull;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class _BoardStore {
    @NonNull private final List<Board> data;
    @NonNull private final List<Board.BoardId> savedIds = new LinkedList<>();
    @NonNull private final List<Board.BoardId> updatedIds = new LinkedList<>();
    @NonNull private final List<Board.BoardId> deletedIds = new LinkedList<>();

    public _BoardStore(@NonNull List<Board> boards){
        this.data = boards;
    }

    void delete(@NonNull Board.BoardId boardId) {
        if (!data.removeIf(e -> e.getBoardId().equals(boardId))) {
            throw new DataNotFoundException();
        }

        deletedIds.add(boardId);
    }

    void save(@NonNull Board board){
        List<Board.BoardId> ids =  data.stream().map(Board::getBoardId).collect(Collectors.toList());

        if(ids.contains(board.getBoardId())){
            throw new AlreadyExistException();
        }

        data.add(board);
        savedIds.add(board.getBoardId());
    }

    void update(@NonNull Board after){
        Board found = data.stream()
                .filter(e->e.getBoardId().equals(after.getBoardId()))
                .findAny()
                .orElseThrow(DataNotFoundException::new);

        savedIds.removeIf(e-> e.equals(after.getBoardId()));
        updatedIds.add(after.getBoardId());
    }



    void save(@NonNull Posting posting){
        Board found = data.stream()
                .filter(e->e.getBoardId().equals(posting.getBoardId()))
                .findAny().orElseThrow(DataNotFoundException::new);

        found.save(posting);
    }

    void update(@NonNull Posting after){
        Board found = data.stream()
                .filter(e->e.getBoardId().equals(after.getBoardId()))
                .findAny()
                .orElseThrow(DataNotFoundException::new);

        found.update(after);
    }

    List<Posting> getSavedPostings(){
        return data.stream()
                .flatMap(board -> board.getSavedPostings().stream())
                .collect(Collectors.toList());
    }

    List<Posting> getUpdatedPostings(){
        return data.stream()
                .flatMap(board -> board.getUpdatedPostings().stream())
                .collect(Collectors.toList());
    }
}

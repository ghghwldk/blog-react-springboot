package com.m.blog.aggregate.boardCollection.application.domain;

import com.m.blog.global.exception.DataNotFoundException;
import com.m.blog.global.exception.TooManyException;
import lombok.NonNull;

import java.util.List;
import java.util.stream.Collectors;

public class BoardWindow {
    @NonNull private final List<Board> boards;

    public BoardWindow(@NonNull List<Board> boards){
        this.boards = boards;
    }

    String remove(@NonNull Board.BoardId boardId) {
        if (!boards.removeIf(e -> e.getBoardId().getValue().equals(boardId.getValue()))) {
            throw new DataNotFoundException();
        }
        return boardId.getValue();
    }

    void update(@NonNull Posting after){
        Board found = boards.stream()
                .filter(e->e.getBoardId().equals(after.getBoardId()))
                .findAny()
                .orElseThrow(DataNotFoundException::new);

        found.change(after);
    }

    Posting getUpdated(){
        List<Board> postingUpdatedBoards = boards.stream().filter(Board::isPostingUpdated).collect(Collectors.toList());
        if(postingUpdatedBoards.isEmpty()){
            throw new DataNotFoundException();
        }
        if(postingUpdatedBoards.size() > 1){
            throw new TooManyException();
        }

        return postingUpdatedBoards.get(0).getUpdated();
    }

    void changeBoard(@NonNull Board after){
        Board found = boards.stream()
                .filter(e->e.getBoardId().equals(after.getBoardId()))
                .findAny()
                .orElseThrow(DataNotFoundException::new);

        found.change(after);
    }

    void add(@NonNull Board board){boards.add(board);}

    void add(@NonNull Posting posting){
        Board board = boards.stream()
                .filter(e->e.getBoardIdValue().equals(posting.getBoardIdValue()))
                .findAny().orElseThrow(DataNotFoundException::new);

        board.add(posting);
    }

    Posting getAdded(){
        List<Board> postingAddedBoards = boards.stream()
                .filter(Board::isPostingAdded)
                .collect(Collectors.toList());
        if(postingAddedBoards.isEmpty()){
            throw new DataNotFoundException();
        }
        if(postingAddedBoards.size()>1){
            throw new TooManyException();
        }

        return postingAddedBoards.get(0).getAdded();
    }
}

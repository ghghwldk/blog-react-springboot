package com.m.blog.boardCollection.application.domain;

import com.m.blog.global.exception.DataNotFoundException;
import com.m.blog.global.exception.MultipleChangedException;
import com.m.blog.global.exception.NothingChangedException;
import lombok.NonNull;

import java.util.List;
import java.util.stream.Collectors;

public class BoardWindow {
    @NonNull private final List<Board> boards;

    BoardWindow(@NonNull List<Board> boards){
        this.boards = boards;
    }

    String remove(@NonNull Board.BoardId boardId) {
        if (!boards.removeIf(e -> e.getBoardId().getValue().equals(boardId.getValue()))) {
            throw new DataNotFoundException();
        }
        return boardId.getValue();
    }

    void update(Posting after){
        Board found = boards.stream()
                .filter(e->e.getBoardId().equals(after.getBoardId()))
                .findAny()
                .orElseThrow(DataNotFoundException::new);

        found.change(after);
    }

    Posting getUpdatedPosting(){
        List<Board> postingUpdatedBoards = boards.stream().filter(Board::isPostingUpdated).collect(Collectors.toList());
        if(postingUpdatedBoards.isEmpty()){
            throw new NothingChangedException();
        }
        if(postingUpdatedBoards.size() > 1){
            throw new MultipleChangedException();
        }

        return postingUpdatedBoards.get(0).getUpdatedPosting();
    }

    void changeBoard(Board after){
        Board found = boards.stream()
                .filter(e->e.getBoardId().equals(after.getBoardId()))
                .findAny()
                .orElseThrow(DataNotFoundException::new);

        found.change(after);
    }

    void add(@NonNull Board board){boards.add(board);}

    void add(@NonNull Posting posting){
        Board board = boards.stream()
                .filter(e->e.getBoardId().equals(posting.getBoardId().getValue()))
                .findAny().orElseThrow(DataNotFoundException::new);

        board.add(posting);
    }

    Posting getAddedPosting(){
        List<Board> postingAddedBoards = boards.stream()
                .filter(Board::isPostingAdded)
                .collect(Collectors.toList());
        if(postingAddedBoards.isEmpty()){
            throw new DataNotFoundException();
        }
        if(postingAddedBoards.size()>1){
            throw new MultipleChangedException();
        }

        return postingAddedBoards.get(0).getAddedPosting();
    }
}

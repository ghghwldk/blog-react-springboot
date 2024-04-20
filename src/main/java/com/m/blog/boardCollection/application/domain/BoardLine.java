package com.m.blog.boardCollection.application.domain;

import com.amazonaws.services.kms.model.NotFoundException;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

public class BoardLine {
    @NonNull private final List<Board> boards;
    @Getter private long count;

    BoardLine(@NonNull List<Board> boards){
        this.boards = boards;
        this.count = boards.size();
    }

    String remove(@NonNull Board.BoardId boardId) {
        if (!boards.removeIf(e -> e.getBoardId().getValue().equals(boardId.getValue()))) {
            throw new NotFoundException("target board doesn't exist");
        }

        this.count -= 1;
        return boardId.getValue();
    }

    String add(@NonNull Board board){
        boards.add(board);

        this.count += 1;
        return board.getBoardId().getValue();
    }
}

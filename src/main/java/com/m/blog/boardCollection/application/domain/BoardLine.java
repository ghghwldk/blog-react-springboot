package com.m.blog.boardCollection.application.domain;

import com.amazonaws.services.kms.model.NotFoundException;
import lombok.Builder;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Builder
@RequiredArgsConstructor
public class BoardLine {
    @NonNull private final List<Board> boards;

    String remove(@NonNull Board.BoardId boardId) {
        if (!boards.removeIf(e -> e.getBoardId().getValue().equals(boardId.getValue()))) {
            throw new NotFoundException("target board doesn't exist");
        }
        // some additional business logic if needed
        return boardId.getValue();
    }

}

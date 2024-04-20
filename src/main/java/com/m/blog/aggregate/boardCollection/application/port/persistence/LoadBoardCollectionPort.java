package com.m.blog.aggregate.boardCollection.application.port.persistence;

import com.m.blog.aggregate.boardCollection.application.domain.Board;
import com.m.blog.aggregate.boardCollection.application.domain.BoardCollection;
import com.m.blog.aggregate.boardCollection.application.domain.Posting;

import java.time.LocalDateTime;

public interface LoadBoardCollectionPort {
    BoardCollection load(Posting.PostingId postingId);
    BoardCollection load(Board.BoardId boardId);

    BoardCollection load(BoardCollection.BoardCollectionId boardCollectionId);
}

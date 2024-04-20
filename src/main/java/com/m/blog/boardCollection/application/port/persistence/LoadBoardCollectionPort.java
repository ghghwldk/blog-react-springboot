package com.m.blog.boardCollection.application.port.persistence;

import com.m.blog.boardCollection.application.domain.Board;
import com.m.blog.boardCollection.application.domain.BoardCollection;
import com.m.blog.boardCollection.application.domain.Posting;

import java.time.LocalDateTime;

public interface LoadBoardCollectionPort {
    BoardCollection load(Posting.PostingId postingId);
    BoardCollection load(Board.BoardId boardId);

    BoardCollection load(BoardCollection.BoardCollectionId boardCollectionId);
}

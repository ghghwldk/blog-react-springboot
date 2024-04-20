package com.m.blog.aggregate.boardCollection.adapter.persistence;

import com.m.blog.aggregate.boardCollection.application.domain.Board;
import com.m.blog.aggregate.boardCollection.application.domain.BoardCollection;
import com.m.blog.aggregate.boardCollection.application.domain.Posting;
import com.m.blog.aggregate.boardCollection.application.port.persistence.ChangePostingPersistencePort;
import com.m.blog.aggregate.boardCollection.application.port.persistence.LoadBoardCollectionPort;
import com.m.blog.aggregate.boardCollection.application.port.persistence.SavePostingPersistencePort;

import com.m.blog.common.Adapter;
import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
public class LoadBoardCollectionAdapter implements LoadBoardCollectionPort {

    @Override
    public BoardCollection load(Posting.PostingId postingId) {
        BoardCollection.BoardCollectionId boardCollectionId = null;

        return this.load(boardCollectionId);
    }

    @Override
    public BoardCollection load(Board.BoardId boardId) {
        BoardCollection.BoardCollectionId boardCollectionId = null;

        return this.load(boardCollectionId);
    }

    @Override
    public BoardCollection load(BoardCollection.BoardCollectionId boardCollectionId) {

        return null;
    }
}


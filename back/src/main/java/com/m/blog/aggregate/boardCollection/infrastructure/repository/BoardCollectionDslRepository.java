package com.m.blog.aggregate.boardCollection.infrastructure.repository;

import com.m.blog.aggregate.boardCollection.application.domain.Board;
import com.m.blog.aggregate.posting.application.domain.Posting;

import java.util.List;
import java.util.Optional;

public interface BoardCollectionDslRepository {
    List<BoardAggregationDto> getAggregationDtos();
    Optional<BoardCollectionIdDto> get(Board.BoardId boardId);
    Optional<BoardCollectionIdDto> get(Posting.PostingId postingId);
}

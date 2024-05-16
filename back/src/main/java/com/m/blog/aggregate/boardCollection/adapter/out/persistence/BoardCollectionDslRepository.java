package com.m.blog.aggregate.boardCollection.adapter.out.persistence;

import com.m.blog.aggregate.board.application.domain.Board;
import com.m.blog.aggregate.board.application.domain.BoardId;
import com.m.blog.aggregate.posting.application.domain.Posting;
import com.m.blog.aggregate.posting.application.domain.PostingId;

import java.util.List;
import java.util.Optional;

public interface BoardCollectionDslRepository {
    List<BoardAggregationDto> getAggregationDtos();
    Optional<BoardCollectionIdDto> get(BoardId boardId);
    Optional<BoardCollectionIdDto> get(PostingId postingId);
}

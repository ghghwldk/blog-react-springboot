package com.m.blog.domain.boardCollection.service;

import com.m.blog.domain.boardCollection.adapter.out.BoardAggregationDto;
import com.m.blog.domain.boardCollection.adapter.out.BoardCollectionDslRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
@RequiredArgsConstructor
public class BoardCollectionDslService implements BoardCollectionDslRepository {
    private final JPAQueryFactory query;

    @Override
    public List<BoardAggregationDto> getBoardAggregationDtos(){
//        List<BoardAggregationDto> result = query
//                .select(
//                        new QBoardAggregationDto(
//                                QBoardCollection.boardCollection.id,
//                                QBoardCollection.boardCollection.name,
//                                QBoard.board.id,
//                                QBoard.board.name,
//                                board.count()
//                        )
//
//                )
//                .from(QBoardCollection.boardCollection)
//                .join(QBoard.board).on(QBoardCollection.boardCollection.id.eq(QBoard.board.boardCollectionId))
//                .join(QPosting.posting).on(QBoard.board.boardCollectionId.eq(QPosting.posting.boardCollectionId)
//                        .and(QBoard.board.id.eq(QPosting.posting.boardId)))
//                .groupBy(
//                        QBoardCollection.boardCollection.id,
//                        QBoardCollection.boardCollection.name,
//                        QBoard.board.id,
//                        QBoard.board.name,
//                        QBoard.board.description,
//                        QBoard.board.createdTime,
//                        QBoard.board.updatedTime
//                ).fetch();
//        return result;
        return null;
    }
}

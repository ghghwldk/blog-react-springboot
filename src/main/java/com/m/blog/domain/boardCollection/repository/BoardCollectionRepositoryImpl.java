package com.m.blog.domain.boardCollection.repository;

import com.m.blog.domain.board.entity.QBoard;
import com.m.blog.domain.boardCollection.dto.BoardAggregationDto;
import com.m.blog.domain.boardCollection.dto.QBoardAggregationDto;
import com.m.blog.domain.boardCollection.entity.QBoardCollection;
import com.m.blog.domain.posting.entity.QPosting;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.m.blog.domain.board.entity.QBoard.board;


@Repository
public class BoardCollectionRepositoryImpl implements BoardCollectionRepository {
    @Autowired
    JPAQueryFactory query;

    @Override
    public List<BoardAggregationDto> get(){
        List<BoardAggregationDto> result = query
                .select(
                        new QBoardAggregationDto(
                                QBoardCollection.boardCollection.id,
                                QBoardCollection.boardCollection.name,
                                QBoard.board.id,
                                QBoard.board.name,
                                board.count()
                        )

                )
                .from(QBoardCollection.boardCollection)
                .join(QBoard.board).on(QBoardCollection.boardCollection.id.eq(QBoard.board.boardCollectionId))
                .join(QPosting.posting).on(QBoard.board.boardCollectionId.eq(QPosting.posting.boardCollectionId)
                        .and(QBoard.board.id.eq(QPosting.posting.boardId)))
                .groupBy(
                        QBoardCollection.boardCollection.id,
                        QBoardCollection.boardCollection.name,
                        QBoard.board.id,
                        QBoard.board.name,
                        QBoard.board.description,
                        QBoard.board.createdTime,
                        QBoard.board.updatedTime
                ).fetch();
        return result;
    }
}

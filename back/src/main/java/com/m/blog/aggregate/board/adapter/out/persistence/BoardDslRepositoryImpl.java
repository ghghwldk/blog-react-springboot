package com.m.blog.aggregate.board.adapter.out.persistence;

import com.m.blog.aggregate.boardCollection.adapter.out.persistence.QBoardCollectionEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class BoardDslRepositoryImpl implements BoardDslRepository {
    private final JPAQueryFactory query;

    @Override
    public Optional<BoardDto> findBoardDto(String boardId){
        QBoardCollectionEntity bc = QBoardCollectionEntity.boardCollectionEntity;
        QBoardEntity b = QBoardEntity.boardEntity;

        BoardDto fetched =
                query.select(
                        new QBoardDto(
                                b.boardCollectionId,
                                bc.name,
                                b.id,
                                b.name,
                                b.description,
                                b.createdTime,
                                b.updatedTime
                        ))
                        .from(bc)
                        .join(b).on(bc.id.eq(b.boardCollectionId))
                        .where(b.id.eq(boardId))
                        .orderBy(b.createdTime.desc())
                        .fetchOne();

        return Optional.ofNullable(fetched);
    }
}
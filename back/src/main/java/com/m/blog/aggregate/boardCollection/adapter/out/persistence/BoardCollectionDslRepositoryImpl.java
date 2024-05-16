package com.m.blog.aggregate.boardCollection.adapter.out.persistence;

import com.m.blog.aggregate.board.adapter.out.persistence.QBoardEntity;
import com.m.blog.aggregate.board.application.domain.Board;
import com.m.blog.aggregate.posting.adapter.out.persistence.QPostingEntity;
import com.m.blog.aggregate.posting.application.domain.Posting;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class BoardCollectionDslRepositoryImpl implements BoardCollectionDslRepository {
    private final JPAQueryFactory query;

    @Override
    public List<BoardAggregationDto> getAggregationDtos(){
        QBoardCollectionEntity bc = QBoardCollectionEntity.boardCollectionEntity;
        QBoardEntity b = QBoardEntity.boardEntity;
        QPostingEntity p = QPostingEntity.postingEntity;

        return query
                .select(
                        new QBoardAggregationDto(
                                bc.id,
                                bc.name,
                                b.id,
                                b.name,
                                p.count()
                        )

                )
                .from(bc)
                .leftJoin(b).on(bc.id.eq(b.boardCollectionId))
                .leftJoin(p).on(p.boardId.eq(b.id))
                .groupBy(
                        bc.id,
                        bc.name,
                        b.id,
                        b.name,
                        b.description,
                        b.createdTime,
                        b.updatedTime
                ).fetch();
    }

    @Override
    public Optional<BoardCollectionIdDto> get(Board.BoardId boardId) {
        QBoardCollectionEntity bc = QBoardCollectionEntity.boardCollectionEntity;
        QBoardEntity b = QBoardEntity.boardEntity;

        return Optional.ofNullable(query.select(new QBoardCollectionIdDto(bc.id))
                .from(bc)
                .join(b).on(bc.id.eq(b.boardCollectionId))
                .where(b.id.eq(boardId.getValue()))
                .fetchOne());
    }

    @Override
    public Optional<BoardCollectionIdDto> get(Posting.PostingId postingId) {
        QBoardCollectionEntity bc = QBoardCollectionEntity.boardCollectionEntity;
        QBoardEntity b = QBoardEntity.boardEntity;
        QPostingEntity p = QPostingEntity.postingEntity;

        return Optional.ofNullable(query.select(new QBoardCollectionIdDto(bc.id))
                .from(bc)
                .join(b).on(bc.id.eq(b.boardCollectionId))
                .join(p).on(p.boardId.eq(b.id))
                .where(p.id.eq(postingId.getValue()))
                .fetchOne());
    }

}

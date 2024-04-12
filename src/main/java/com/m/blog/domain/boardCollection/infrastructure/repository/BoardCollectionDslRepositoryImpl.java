package com.m.blog.domain.boardCollection.infrastructure.repository;

import com.m.blog.domain.board.infrastructure.repository.QBoardEntity;
import com.m.blog.domain.posting.infrastructure.repository.QPostingEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;



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
                                b.count()
                        )

                )
                .from(bc)
                .join(b).on(bc.id.eq(b.boardCollectionId))
                .join(p).on(p.boardId.eq(b.id))
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

}

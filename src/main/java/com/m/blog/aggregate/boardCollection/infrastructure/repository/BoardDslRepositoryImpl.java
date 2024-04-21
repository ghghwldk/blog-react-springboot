package com.m.blog.aggregate.boardCollection.infrastructure.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
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
    public List<BoardEntity> findBoards(String boardCollectionId){
//        return query.selectFrom(board)
//                .orderBy(board.boardCollectionId.asc(),
//                        board.id.desc())
//                .where(board.boardCollectionId.eq(boardCollectionId))
//                .fetch();
        return null;
    }
    public Page<BoardDto> findBoardPage(String boardCollectionId, Pageable pageable){
//        List<BoardDto> fetch=
//                query.select(
//                        new QBoardDto(
//                                board.boardCollectionId,
//                                boardCollection.name,
//                                board.id,
//                                board.name,
//                                board.description,
//                                board.createdTime,
//                                board.updatedTime
//                        ))
//                        .from(boardCollection)
//                        .join(board).on(boardCollection.id.eq(board.boardCollectionId))
//                        .where(board.boardCollectionId.eq(boardCollectionId))
//                        .orderBy(board.createdTime.desc())
//                        .offset(pageable.getOffset())
//                        .limit(pageable.getPageSize())
//                        .fetch();
//
//        JPAQuery<BoardDto> count=
//                query.select(
//                        new QBoardDto(
//                                board.boardCollectionId,
//                                boardCollection.name,
//                                board.id,
//                                board.name,
//                                board.description,
//                                board.createdTime,
//                                board.updatedTime
//                        ))
//                        .from(boardCollection)
//                        .join(board).on(boardCollection.id.eq(board.boardCollectionId))
//                        .where(board.boardCollectionId.eq(boardCollectionId))
//                        .orderBy(board.createdTime.desc())
//                        .offset(pageable.getOffset())
//                        .limit(pageable.getPageSize());
//
//        return PageableExecutionUtils.getPage(fetch, pageable, count::fetchCount);
        return null;
    }
}
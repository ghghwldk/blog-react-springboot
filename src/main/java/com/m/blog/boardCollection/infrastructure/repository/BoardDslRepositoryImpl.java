package com.m.blog.boardCollection.infrastructure.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
@RequiredArgsConstructor
public class BoardDslRepositoryImpl implements BoardDslRepository {
    private final JPAQueryFactory query;

    @Override
    public BoardDto findBoardDto(String boardId){
//        BoardDto fetched =
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
//                        .where(board.boardCollectionId.eq(boardCollectionId), board.id.eq(boardId))
//                        .orderBy(board.createdTime.desc())
//                        .fetchOne();
//        if(fetched == null){
//            throw new NotFoundException("board is not found");
//        }
//
//        return fetched;
        return null;
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
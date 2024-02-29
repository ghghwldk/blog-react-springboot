package com.m.blog.domain.board.service;

import com.amazonaws.services.kms.model.NotFoundException;
import com.m.blog.domain.board.application.port.out.GetBoardQuery;
import com.m.blog.domain.board.application.port.out.BoardDto;
import com.m.blog.domain.board.application.port.out.BoardEntity;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
@RequiredArgsConstructor
public class GetBoardService implements GetBoardQuery {
    private final JPAQueryFactory query;

    @Override
    public BoardDto findBoardDto(int boardCollectionId, int boardId){
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
    public List<BoardEntity> findBoards(int boardCollectionId){
//        return query.selectFrom(board)
//                .orderBy(board.boardCollectionId.asc(),
//                        board.id.desc())
//                .where(board.boardCollectionId.eq(boardCollectionId))
//                .fetch();
        return null;
    }
    public Page<BoardDto> findBoardPage(int boardCollectionId, Pageable pageable){
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
package com.m.blog.domain.board.repository;

import com.amazonaws.services.kms.model.NotFoundException;
import com.m.blog.domain.board.dto.BoardDto;
import com.m.blog.domain.board.dto.QBoardDto;
import com.m.blog.domain.board.entity.Board;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.m.blog.domain.board.entity.QBoard.board;
import static com.m.blog.domain.boardCollection.entity.QBoardCollection.boardCollection;


@Repository
@RequiredArgsConstructor
public class BoardDslRepositoryImpl implements BoardDslRepository{
    private final JPAQueryFactory query;

    @Override
    public BoardDto findBoardDto(int boardCollectionId, int boardId){
        BoardDto fetched =
                query.select(
                        new QBoardDto(
                                board.boardCollectionId,
                                boardCollection.name,
                                board.id,
                                board.name,
                                board.description,
                                board.createdTime,
                                board.updatedTime
                        ))
                        .from(boardCollection)
                        .join(board).on(boardCollection.id.eq(board.boardCollectionId))
                        .where(board.boardCollectionId.eq(boardCollectionId), board.id.eq(boardId))
                        .orderBy(board.createdTime.desc())
                        .fetchOne();
        if(fetched == null){
            throw new NotFoundException("board is not found");
        }

        return fetched;
    }
    public List<Board> findBoards(int boardCollectionId){
        return query.selectFrom(board)
                .orderBy(board.boardCollectionId.asc(),
                        board.id.desc())
                .where(board.boardCollectionId.eq(boardCollectionId))
                .fetch();
    }
    public Page<BoardDto> findBoardPage(int boardCollectionId, Pageable pageable){
        List<BoardDto> fetch=
                query.select(
                        new QBoardDto(
                                board.boardCollectionId,
                                boardCollection.name,
                                board.id,
                                board.name,
                                board.description,
                                board.createdTime,
                                board.updatedTime
                        ))
                        .from(boardCollection)
                        .join(board).on(boardCollection.id.eq(board.boardCollectionId))
                        .where(board.boardCollectionId.eq(boardCollectionId))
                        .orderBy(board.createdTime.desc())
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize())
                        .fetch();

        JPAQuery<BoardDto> count=
                query.select(
                        new QBoardDto(
                                board.boardCollectionId,
                                boardCollection.name,
                                board.id,
                                board.name,
                                board.description,
                                board.createdTime,
                                board.updatedTime
                        ))
                        .from(boardCollection)
                        .join(board).on(boardCollection.id.eq(board.boardCollectionId))
                        .where(board.boardCollectionId.eq(boardCollectionId))
                        .orderBy(board.createdTime.desc())
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize());

        return PageableExecutionUtils.getPage(fetch, pageable, count::fetchCount);
    }
}
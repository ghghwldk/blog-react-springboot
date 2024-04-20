package com.m.blog.boardCollection.infrastructure.repository;

import com.m.blog.boardCollection.application.domain.Posting;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.m.blog.boardCollection.infrastructure.repository.QPostingEntity.postingEntity;


@Repository
@RequiredArgsConstructor
class PostingDslRepositoryImpl implements PostingDslRepository {
    private final JPAQueryFactory query;

    @Override
    public Page<PostingDto> getPage(Pageable pageable){
        QPostingEntity p = postingEntity;
        QBoardEntity b = QBoardEntity.boardEntity;
        QBoardCollectionEntity bc = QBoardCollectionEntity.boardCollectionEntity;

        List<PostingDto> fetch=
                query.select(
                                new QPostingDto(
                                        p.id,
                                        p.title,
                                        p.content,
                                        b.id,
                                        b.name,
                                        bc.id,
                                        bc.name,
                                        p.createdTime
                                ))
                        .from(p)
                        .join(b).on(p.boardId.eq(b.id))
                        .join(bc).on(b.boardCollectionId.eq(bc.id))
                        .orderBy(p.createdTime.desc())
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize())
                        .fetch();

        JPAQuery<PostingDto> count=
                query.select(
                                new QPostingDto(
                                        p.id,
                                        p.title,
                                        p.content,
                                        b.id,
                                        b.name,
                                        bc.id,
                                        bc.name,
                                        p.createdTime
                                ))
                        .from(p)
                        .join(b).on(p.boardId.eq(b.id))
                        .join(bc).on(b.boardCollectionId.eq(bc.id))
                        .orderBy(p.createdTime.desc())
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize());

        return PageableExecutionUtils.getPage(fetch, pageable, count::fetchCount);
    }

    private PostingDto getPosting(String postingId){
        QPostingEntity p = new QPostingEntity("p");
        QBoardCollectionEntity bc = new QBoardCollectionEntity("bc");
        QBoardEntity b = new QBoardEntity("b");

        return query.select(
                        new QPostingDto(
                                p.id,
                                p.title,
                                p.content,
                                b.id,
                                b.name,
                                bc.id,
                                bc.name,
                                p.createdTime
                        ))
                .from(p)
                .join(b).on(p.boardId.eq(b.id))
                .join(bc).on(b.boardCollectionId.eq(bc.id))
                .where(p.id.eq(postingId))
                .fetchOne();
    }


    @Override
    public Page<PostingDto> getPagePerBoard(String boardCollectionId, String boardId, Pageable pageable){
        QPostingEntity p = postingEntity;
        QBoardEntity b = QBoardEntity.boardEntity;
        QBoardCollectionEntity bc = QBoardCollectionEntity.boardCollectionEntity;

        List<PostingDto> fetch=
                query.select(
                                new QPostingDto(
                                        p.id,
                                        p.title,
                                        p.content,
                                        b.id,
                                        b.name,
                                        bc.id,
                                        bc.name,
                                        p.createdTime
                                ))
                        .from(p)
                        .join(b).on(p.boardId.eq(b.id))
                        .join(bc).on(b.boardCollectionId.eq(bc.id))
                        .where(bc.id.eq(boardCollectionId), p.boardId.eq(boardId))
                        .orderBy(p.createdTime.desc())
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize())
                        .fetch();

        JPAQuery<PostingDto> count=
                query.select(
                                new QPostingDto(
                                        p.id,
                                        p.title,
                                        p.content,
                                        b.id,
                                        b.name,
                                        bc.id,
                                        bc.name,
                                        p.createdTime
                                ))
                        .from(p)
                        .join(b).on(p.boardId.eq(b.id))
                        .join(bc).on(b.boardCollectionId.eq(bc.id))
                        .where(b.boardCollectionId.eq(boardCollectionId), p.boardId.eq(boardId))
                        .orderBy(p.createdTime.desc())
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize());

        return PageableExecutionUtils.getPage(fetch, pageable, count::fetchCount);
    }

    @Override
    public PostingDto getSinglePage(Posting.PostingId condition) {
        return this.getPosting(condition.getValue());
    }
}


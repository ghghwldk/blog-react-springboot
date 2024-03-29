package com.m.blog.domain.posting.infrastructure.repository;
import com.m.blog.domain.board.infrastructure.repository.QBoardEntity;
import com.m.blog.domain.boardCollection.infrastructure.repository.QBoardCollectionEntity;
import com.m.blog.domain.posting.application.domain.Posting;
import com.m.blog.domain.posting.infrastructure.web.dto.PostingReadRequest;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

import static com.m.blog.domain.posting.infrastructure.repository.QPostingEntity.postingEntity;


@Repository
@RequiredArgsConstructor
class PostingDslRepositoryImpl implements PostingDslRepository {
    private final JPAQueryFactory query;

    @Transactional
    @Override
    public int findMaxId(int boardCollectionId, int boardId){
        QPostingEntity p = postingEntity;
        return  (int) query.selectFrom(p)
                .where(p.boardCollectionId.eq(boardCollectionId),
                        p.boardId.eq(boardId))
                .fetchCount();
    }


    @Override
    public Page<PostingDto> getNonFilteredPage(Pageable pageable){
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
                        .join(b).on(p.boardId.eq(b.id),
                                p.boardCollectionId.eq(b.boardCollectionId))
                        .join(bc).on(p.boardCollectionId.eq(bc.id))
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
                        .join(b).on(p.boardId.eq(b.id),
                                p.boardCollectionId.eq(b.boardCollectionId))
                        .join(bc).on(p.boardCollectionId.eq(bc.id))
                        .orderBy(p.createdTime.desc())
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize());

        return PageableExecutionUtils.getPage(fetch, pageable, count::fetchCount);
    }

    private PostingDto getPosting(int boardCollectionId, int boardId, int postingId){
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
                .join(b).on(p.boardId.eq(b.id),
                        p.boardCollectionId.eq(b.boardCollectionId))
                .join(bc).on(p.boardCollectionId.eq(bc.id))
                .where(bc.id.eq(boardCollectionId), b.id.eq(boardId), p.id.eq(postingId))
                .fetchOne();
    }


    @Override
    public Page<PostingDto> getFilteredPage(int boardCollectionId, int boardId, Pageable pageable){
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
                        .join(b).on(p.boardId.eq(b.id),
                                p.boardCollectionId.eq(b.boardCollectionId))
                        .join(bc).on(p.boardCollectionId.eq(bc.id))
                        .where(p.boardCollectionId.eq(boardCollectionId), p.boardId.eq(boardId))
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
                        .join(b).on(p.boardId.eq(b.id),
                                p.boardCollectionId.eq(b.boardCollectionId))
                        .join(bc).on(p.boardCollectionId.eq(bc.id))
                        .where(p.boardCollectionId.eq(boardCollectionId), p.boardId.eq(boardId))
                        .orderBy(p.createdTime.desc())
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize());

        return PageableExecutionUtils.getPage(fetch, pageable, count::fetchCount);
    }

    @Override
    public PostingDto getSinglePage(Posting.PostingId condition) {
        return this.getPosting(condition.getBoardCollectionId(), condition.getBoardId(), condition.getPostingId());
    }
}


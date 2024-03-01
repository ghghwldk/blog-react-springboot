package com.m.blog.domain.posting.infrastructure.repository;
import com.m.blog.domain.board.application.port.out.QBoardEntity;
import com.m.blog.domain.boardCollection.adapter.out.QBoardCollectionEntity;
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
    public int findNewId(int boardCollectionId, int boardId){
        QPostingEntity p = postingEntity;
        int count= (int) query.selectFrom(p)
                .where(p.boardCollectionId.eq(boardCollectionId),
                        p.boardId.eq(boardId))
                .fetchCount();

        return count+1;
    }


    @Override
    public Page<PostingDto> getPage(Pageable pageable){
        return this.getPageOfLatestPosting(pageable);
    }

    private Page<PostingDto> getPageOfLatestPosting(Pageable pageable){
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
    public PostingDto get(PostingReadRequest requestDto){
        return this.getPosting(requestDto.getBoardCollectionId(),
                requestDto.getBoardId(),
                requestDto.getId());
    }

    private PostingDto getPosting(int boardCollectionId, int boardId, int postingId){
        QPostingEntity p = new QPostingEntity("p");
        QBoardCollectionEntity bc = new QBoardCollectionEntity("bc");
        QBoardEntity b = new QBoardEntity("b");
        PostingDto fetch=
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
                        .where(bc.id.eq(boardCollectionId), b.id.eq(boardId), p.id.eq(postingId))
                        .fetchOne();
        return fetch;
    }


    public Page<PostingDto> getFiltered(int boardCollectionId, int boardId, Pageable pageable){
        return this.getPageOfPosting(boardCollectionId, boardId, pageable);
    }

    @Override
    public PostingDto getSingle(Posting.PostingId condition) {
        return this.getPosting(condition.getBoardCollectionId(), condition.getBoardId(), condition.getPostingId());
    }


    private Page<PostingDto> getPageOfPosting(int boardCollectionId, int boardId, Pageable pageable){
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
}


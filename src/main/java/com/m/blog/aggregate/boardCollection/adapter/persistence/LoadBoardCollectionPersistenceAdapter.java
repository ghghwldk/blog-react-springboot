package com.m.blog.aggregate.boardCollection.adapter.persistence;

import com.m.blog.aggregate.boardCollection.application.domain.*;
import com.m.blog.aggregate.boardCollection.application.port.persistence.LoadBoardCollectionPersistencePort;

import com.m.blog.aggregate.boardCollection.infrastructure.repository.*;
import com.m.blog.common.Adapter;
import com.m.blog.global.exception.DataNotFoundException;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Adapter
@RequiredArgsConstructor
public class LoadBoardCollectionPersistenceAdapter implements LoadBoardCollectionPersistencePort {
    private final BoardCollectionDslRepository boardCollectionDslRepository;
    private final BoardCollectionJpaRepository boardCollectionJpaRepository;
    private final BoardJpaRepository boardJpaRepository;
    private final PostingJpaRepository postingJpaRepository;

    @Override
    public BoardCollection load(Posting.PostingId postingId) {
        BoardCollection boardCollection = null;

        BoardCollection.BoardCollectionId boardCollectionId = boardCollectionDslRepository.get(postingId)
                .map(BoardCollectionPersistenceMapper::of)
                .orElseThrow(DataNotFoundException::new);

        BoardCollectionEntity boardCollectionEntity = boardCollectionJpaRepository.findById(boardCollectionId.getValue())
                .orElseThrow(EntityNotFoundException::new);


        List<BoardEntity> boardEntities = boardJpaRepository.findAllByBoardCollectionId(boardCollectionEntity.getId());

        List<String> boardEntityIds = boardEntities.stream()
                .map(BoardEntity::getId)
                .collect(Collectors.toList());

        List<PostingEntity> postingEntities = postingJpaRepository.findAllByBoardIdIn(boardEntityIds);

        HashMap<String, List<PostingEntity>> postingEntitiesPerBoardId = new HashMap<>();
        for (PostingEntity entity : postingEntities) {
            postingEntitiesPerBoardId.computeIfAbsent(entity.getBoardId(), k -> new LinkedList<>())
                    .add(entity);
        }

//        boardCollection = BoardCollectionPersistenceMapper.of(boardCollectionEntity, , postings);
        return null;
    }

}


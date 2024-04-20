package com.m.blog.aggregate.boardCollection.adapter.persistence;

import com.m.blog.aggregate.boardCollection.application.domain.*;
import com.m.blog.aggregate.boardCollection.application.port.persistence.LoadBoardCollectionPersistencePort;

import com.m.blog.aggregate.boardCollection.infrastructure.repository.*;
import com.m.blog.common.Adapter;
import com.m.blog.global.exception.DataNotFoundException;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.NonNull;
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
        BoardCollectionEntity boardCollectionEntity = getBoardCollectionEntity(postingId);
        List<BoardEntity> boardEntities = boardJpaRepository.findAllByBoardCollectionId(boardCollectionEntity.getId());
        List<PostingEntity> postingEntities = getPostingEntities(boardEntities);

        List<Board> boards = this.getBoards(postingEntities, boardEntities);

        return BoardCollectionPersistenceMapper.of(boardCollectionEntity , new BoardWindow(boards));
    }

    private BoardCollectionEntity getBoardCollectionEntity(Posting.PostingId postingId){
        BoardCollection.BoardCollectionId boardCollectionId = boardCollectionDslRepository.get(postingId)
                .map(BoardCollectionPersistenceMapper::of)
                .orElseThrow(DataNotFoundException::new);

        return boardCollectionJpaRepository.findById(boardCollectionId.getValue())
                .orElseThrow(EntityNotFoundException::new);
    }

    private List<PostingEntity> getPostingEntities(List<BoardEntity> boardEntities){
        List<String> boardEntityIds = boardEntities.stream()
                .map(BoardEntity::getId)
                .collect(Collectors.toList());
        return postingJpaRepository.findAllByBoardIdIn(boardEntityIds);
    }

    private List<Board> getBoards(List<PostingEntity> postingEntities, List<BoardEntity> boardEntities){
        HashMap<String, List<Posting>> map = new HashMap<>();

        for (PostingEntity entity : postingEntities) {
            map.computeIfAbsent(entity.getBoardId(), k -> new LinkedList<>())
                    .add(BoardCollectionPersistenceMapper.of(entity));
        }

        return boardEntities.stream()
                .map(e->BoardCollectionPersistenceMapper.of(e, new PostingWindow(map.get(e.getId()))))
                .collect(Collectors.toList());
    }

}

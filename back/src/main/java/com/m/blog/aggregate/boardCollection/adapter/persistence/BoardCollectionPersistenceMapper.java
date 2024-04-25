package com.m.blog.aggregate.boardCollection.adapter.persistence;

import com.m.blog.aggregate.boardCollection.application.domain.*;
import com.m.blog.aggregate.boardCollection.application.domain.BoardCollection.BoardCollectionId;
import com.m.blog.aggregate.boardCollection.infrastructure.repository.BoardCollectionEntity;
import com.m.blog.aggregate.boardCollection.infrastructure.repository.BoardCollectionIdDto;
import com.m.blog.aggregate.boardCollection.infrastructure.repository.BoardEntity;
import com.m.blog.aggregate.boardCollection.infrastructure.repository.PostingEntity;
import com.m.blog.global.customAnnotation.Mapper;
import lombok.NonNull;

@Mapper
class BoardCollectionPersistenceMapper {
    public static PostingEntity toEntity (Posting domain){
        return PostingEntity.builder()
                .id(domain.getPostingId().getValue())
                .boardId(domain.getBoardId().getValue())
                .title(domain.getTitle())
                .content(domain.getContent())
                .build();
    }


    public static Posting of(PostingEntity entity){
        return new Posting(entity.getId(), entity.getBoardId(),
                entity.getTitle(), entity.getContent());
    }

    public static Board of(BoardEntity boardEntity, PostingWindow postingWindow){
        return new Board(boardEntity.getId(), boardEntity.getBoardCollectionId(), postingWindow);
    }

    public static BoardCollection of(BoardCollectionEntity entity, BoardWindow boardWindow){
        return new BoardCollection(entity.getId(), entity.getName(), boardWindow);
    }


    public static BoardCollectionId of (BoardCollectionIdDto boardCollectionIdDto){
        return new BoardCollectionId(boardCollectionIdDto.getBoardCollectionId());
    }


}

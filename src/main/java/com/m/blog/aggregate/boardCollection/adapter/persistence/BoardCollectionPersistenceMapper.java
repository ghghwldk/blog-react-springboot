package com.m.blog.aggregate.boardCollection.adapter.persistence;

import com.m.blog.aggregate.boardCollection.application.domain.*;
import com.m.blog.aggregate.boardCollection.application.domain.BoardCollection.BoardCollectionId;
import com.m.blog.aggregate.boardCollection.infrastructure.repository.BoardCollectionEntity;
import com.m.blog.aggregate.boardCollection.infrastructure.repository.BoardCollectionIdDto;
import com.m.blog.aggregate.boardCollection.infrastructure.repository.BoardEntity;
import com.m.blog.aggregate.boardCollection.infrastructure.repository.PostingEntity;
import com.m.blog.common.Mapper;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;

@Mapper
class BoardCollectionPersistenceMapper {
    public static PostingEntity toEntity (Posting domain){
        return PostingEntity.builder()
                .id(domain.getPostingId().getValue())
                .title(domain.getTitle())
                .content(domain.getContent())
                .build();
    }


    public static Posting of(PostingEntity entity){
        return Posting.builder()
                .postingId(Posting.PostingId.builder()
                        .value(entity.getId())
                        .build())
                .boardId(Board.BoardId.builder()
                        .value(entity.getBoardId())
                        .build())
                .title(entity.getTitle())
                .content(entity.getContent())
                .build();
    }

    public static Board of(BoardEntity boardEntity, PostingWindow postingWindow){
        return Board.builder()
                .boardId(Board.BoardId.builder()
                        .value(boardEntity.getId())
                        .build())
                .boardCollectionId(of(boardEntity.getBoardCollectionId()))
                .name(boardEntity.getName())
                .description(boardEntity.getDescription())
                .postingWindow(postingWindow)
                .build();
    }

    public static BoardCollection of(BoardCollectionEntity entity, BoardWindow boardWindow){
        return BoardCollection.builder()
                .boardCollectionId(of(entity.getId()))
                .name(entity.getName())
                .boardWindow(boardWindow)
                .build();
    }

    public static BoardCollectionId of (String boardCollectionId){
        return BoardCollectionId.builder()
                .value(boardCollectionId)
                .build();
    }


    public static BoardCollectionId of (BoardCollectionIdDto boardCollectionIdDto){
        return BoardCollectionId.builder()
                .value(boardCollectionIdDto.getBoardCollectionId())
                .build();
    }


}

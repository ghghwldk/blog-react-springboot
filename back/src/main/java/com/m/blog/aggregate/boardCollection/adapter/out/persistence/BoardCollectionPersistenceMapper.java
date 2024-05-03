package com.m.blog.aggregate.boardCollection.adapter.out.persistence;

import com.m.blog.aggregate.boardCollection.application.domain.*;
import com.m.blog.aggregate.boardCollection.application.domain.BoardCollection.BoardCollectionId;
import com.m.blog.aggregate.boardCollection.infrastructure.repository.BoardCollectionEntity;
import com.m.blog.aggregate.boardCollection.infrastructure.repository.BoardCollectionIdDto;
import com.m.blog.aggregate.board.infrastructure.repository.BoardEntity;
import com.m.blog.global.customAnnotation.Mapper;

@Mapper
class BoardCollectionPersistenceMapper {


    public static Board of(BoardEntity boardEntity){
        return new Board(boardEntity.getId(), boardEntity.getBoardCollectionId());
    }

    public static BoardCollection of(BoardCollectionEntity entity, _BoardStore boardStore){
        return new BoardCollection(entity.getId(), entity.getName(), boardStore);
    }


    public static BoardCollectionId of (BoardCollectionIdDto boardCollectionIdDto){
        return new BoardCollectionId(boardCollectionIdDto.getBoardCollectionId());
    }


}

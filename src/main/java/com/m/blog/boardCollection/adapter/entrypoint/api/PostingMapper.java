package com.m.blog.boardCollection.adapter.entrypoint.api;

import com.m.blog.boardCollection.application.domain.Board;
import com.m.blog.boardCollection.application.domain.Posting;
import com.m.blog.boardCollection.infrastructure.web.dto.PostingCreateRequest;
import com.m.blog.boardCollection.infrastructure.web.dto.PostingReadPerBoardPagingRequest;
import com.m.blog.boardCollection.infrastructure.web.dto.PostingUpdateRequest;
import com.m.blog.global.entity.SnowflakeIdGenerator;

class PostingMapper {
    public static Posting.PostingId toId(PostingUpdateRequest request){
        return Posting.PostingId.builder()
                .value(request.getPostingId())
                .build();
    }

    public static Posting toEntity(PostingUpdateRequest request){
        return Posting.builder()
                .postingId(toId(request))
                .title(request.getTitle())
                .content(request.getMarkup())
                .build();
    }

    public static Posting from(PostingCreateRequest request){
        return Posting.builder()
                .postingId(Posting.PostingId.builder()
                        .value(SnowflakeIdGenerator.generateId())
                        .build())
                .title(request.getTitle())
                .content(request.getContent())
                .build();
    }

    public static Board.BoardId of(PostingReadPerBoardPagingRequest request){
        return Board.BoardId.builder()
                .value(request.getBoardId())
                .build();
    }

}

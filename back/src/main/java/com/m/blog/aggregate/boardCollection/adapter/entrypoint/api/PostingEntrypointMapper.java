package com.m.blog.aggregate.boardCollection.adapter.entrypoint.api;

import com.m.blog.aggregate.boardCollection.application.domain.Board;
import com.m.blog.aggregate.boardCollection.application.domain.Posting;
import com.m.blog.aggregate.boardCollection.infrastructure.web.dto.PostingCreateRequest;
import com.m.blog.aggregate.boardCollection.infrastructure.web.dto.PostingReadPerBoardPagingRequest;
import com.m.blog.aggregate.boardCollection.infrastructure.web.dto.PostingUpdateRequest;
import com.m.blog.global.customAnnotation.Mapper;
import com.m.blog.global.entity.SnowflakeIdGenerator;

@Mapper
class PostingEntrypointMapper {
    public static Posting from(PostingUpdateRequest request){
        return new Posting(request.getPostingId(), request.getBoardId(),
                request.getTitle(), request.getMarkup());
    }

    public static Posting from(PostingCreateRequest request){
        return Posting.withSnowflakeId(request.getBoardId(), request.getTitle(), request.getContent());
    }


}

package com.m.blog.aggregate.posting.adapter.in.web;

import com.m.blog.aggregate.posting.application.domain.Posting;
import com.m.blog.aggregate.posting.infrastructure.web.dto.PostingCreateRequest;
import com.m.blog.aggregate.posting.infrastructure.web.dto.PostingUpdateRequest;
import com.m.blog.global.customAnnotation.Mapper;

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

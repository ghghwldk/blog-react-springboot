package com.m.blog.aggregate.posting.application.service;

import com.m.blog.aggregate.posting.adapter.in.web.PostingCreateCommand;
import com.m.blog.aggregate.posting.adapter.in.web.PostingUpdateCommand;
import com.m.blog.aggregate.posting.application.domain.Posting;

public class PostingServiceMapper {
    static Posting from(PostingCreateCommand command){
        return Posting.withSnowflakeId(command.getBoardId(), command.getTitle(), command.getContent());
    }

    static Posting from(PostingUpdateCommand command){
        return new Posting(command.getPostingId(), command.getBoardId(),
                command.getTitle(), command.getMarkup());
    }
}

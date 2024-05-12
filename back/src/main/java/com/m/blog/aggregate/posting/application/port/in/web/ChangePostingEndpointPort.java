package com.m.blog.aggregate.posting.application.port.in.web;

import com.m.blog.aggregate.posting.infrastructure.web.dto.PostingCreateRequest;
import com.m.blog.aggregate.posting.infrastructure.web.dto.PostingUpdateRequest;

import java.util.List;

public interface ChangePostingEndpointPort {
    void update(PostingUpdateRequest requestDto);

    void create(PostingCreateRequest requestDto);
}

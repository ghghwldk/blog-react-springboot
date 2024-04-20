package com.m.blog.domain.posting.application.port.entrypoint.api;

import com.m.blog.domain.posting.infrastructure.web.dto.PostingCreateRequest;
import com.m.blog.domain.posting.infrastructure.web.dto.PostingUpdateRequest;

public interface ChangePostingEndpointPort {
    void update(PostingUpdateRequest requestDto);

    void create(PostingCreateRequest requestDto);
}

package com.m.blog.boardCollection.application.port.entrypoint.api;

import com.m.blog.boardCollection.infrastructure.web.dto.PostingCreateRequest;
import com.m.blog.boardCollection.infrastructure.web.dto.PostingUpdateRequest;

public interface ChangePostingEndpointPort {
    void update(PostingUpdateRequest requestDto);

    void create(PostingCreateRequest requestDto);
}

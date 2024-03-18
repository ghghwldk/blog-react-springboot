package com.m.blog.domain.posting.adapter.entrypoint.api;

import com.m.blog.common.Adapter;
import com.m.blog.domain.posting.application.port.entrypoint.api.ChangePostingEndpointPort;
import com.m.blog.domain.posting.application.usecase.SavePostingUsecase;
import com.m.blog.domain.posting.application.usecase.ChangePostingUsecase;
import com.m.blog.domain.posting.infrastructure.web.dto.PostingCreateRequest;
import com.m.blog.domain.posting.infrastructure.web.dto.PostingUpdateRequest;
import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
class ChangePostingEndpointAdapter implements ChangePostingEndpointPort {
    private final ChangePostingUsecase changePostingUsecase;
    private final SavePostingUsecase savePostingUsecase;

    @Override
    public void update(PostingUpdateRequest request){
        changePostingUsecase.update(
                PostingMapper.toId(request),
                PostingMapper.toMutable(request)
        );
    }

    @Override
    public void create(PostingCreateRequest request){
        savePostingUsecase.save(
                PostingMapper.toId(request),
                PostingMapper.toMutable(request)
        );
    }
}

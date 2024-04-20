package com.m.blog.boardCollection.adapter.entrypoint.api;

import com.m.blog.common.Adapter;
import com.m.blog.boardCollection.application.port.entrypoint.api.ChangePostingEndpointPort;
import com.m.blog.boardCollection.application.usecase.SavePostingUsecase;
import com.m.blog.boardCollection.application.usecase.ChangePostingUsecase;
import com.m.blog.boardCollection.infrastructure.web.dto.PostingCreateRequest;
import com.m.blog.boardCollection.infrastructure.web.dto.PostingUpdateRequest;
import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
class ChangePostingEndpointAdapter implements ChangePostingEndpointPort {
    private final ChangePostingUsecase changePostingUsecase;
    private final SavePostingUsecase savePostingUsecase;

    @Override
    public void update(PostingUpdateRequest request){
        changePostingUsecase.update(PostingMapper.toEntity(request));
    }

    @Override
    public void create(PostingCreateRequest request){
        savePostingUsecase.save(PostingMapper.from(request));
    }
}

package com.m.blog.aggregate.boardCollection.adapter.in.web;

import com.m.blog.global.customAnnotation.Adapter;
import com.m.blog.aggregate.boardCollection.application.port.entrypoint.api.ChangePostingEndpointPort;
import com.m.blog.aggregate.boardCollection.application.usecase.SavePostingUsecase;
import com.m.blog.aggregate.boardCollection.application.usecase.ChangePostingUsecase;
import com.m.blog.aggregate.boardCollection.infrastructure.web.dto.PostingCreateRequest;
import com.m.blog.aggregate.boardCollection.infrastructure.web.dto.PostingUpdateRequest;
import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
class ChangePostingEndpointAdapter implements ChangePostingEndpointPort {
    private final ChangePostingUsecase changePostingUsecase;
    private final SavePostingUsecase savePostingUsecase;

    @Override
    public void update(PostingUpdateRequest request){
        changePostingUsecase.update(PostingEntrypointMapper.from(request));
    }

    @Override
    public void create(PostingCreateRequest request){
        savePostingUsecase.save(PostingEntrypointMapper.from(request));
    }
}

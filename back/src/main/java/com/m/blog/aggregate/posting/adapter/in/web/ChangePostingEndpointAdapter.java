package com.m.blog.aggregate.posting.adapter.in.web;

import com.m.blog.aggregate.file.application.domain.File_;
import com.m.blog.aggregate.file.application.usecase.FileDeleteUsecase;
import com.m.blog.aggregate.posting.application.domain.Posting;
import com.m.blog.global.customAnnotation.Adapter;
import com.m.blog.aggregate.posting.application.port.in.web.ChangePostingEndpointPort;
import com.m.blog.aggregate.posting.application.usecase.SavePostingUsecase;
import com.m.blog.aggregate.posting.application.usecase.ChangePostingUsecase;
import com.m.blog.aggregate.posting.infrastructure.web.dto.PostingCreateRequest;
import com.m.blog.aggregate.posting.infrastructure.web.dto.PostingUpdateRequest;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Adapter
@RequiredArgsConstructor
class ChangePostingEndpointAdapter implements ChangePostingEndpointPort {
    private final ChangePostingUsecase changePostingUsecase;
    private final SavePostingUsecase savePostingUsecase;
    private final FileDeleteUsecase fileDeleteUsecase;

    @Override
    public void update(PostingUpdateRequest request){
        List<File_.FileId> existings = changePostingUsecase.update(PostingEntrypointMapper.from(request));

        Posting.PostingId postingId = new Posting.PostingId(request.getPostingId());

        fileDeleteUsecase.deleteUsing(existings, postingId);
    }

    @Override
    public void create(PostingCreateRequest request){
        savePostingUsecase.save(PostingEntrypointMapper.from(request));
    }
}

package com.m.blog.domain.posting.adapter.entrypoint.api;

import com.m.blog.common.Adapter;
import com.m.blog.domain.posting.application.port.entrypoint.api.ChangePostingEndpointPort;
import com.m.blog.domain.posting.application.port.persistence.DslPostingPort;
import com.m.blog.domain.posting.application.usecase.ChangePostingUsecase;
import com.m.blog.domain.posting.infrastructure.repository.PostingEntity;
import com.m.blog.domain.posting.infrastructure.repository.PostingJpaRepository;
import com.m.blog.domain.posting.infrastructure.web.dto.PostingCreateRequest;
import com.m.blog.domain.posting.infrastructure.web.dto.PostingUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Adapter
@RequiredArgsConstructor
public class ChangePostingEndpointAdapter implements ChangePostingEndpointPort {
    private final ChangePostingUsecase changePostingUsecase;


    private final PostingJpaRepository postingJpaRepository;
    private final DslPostingPort postingDslRepository;

    @Transactional
    @Override
    public void update(PostingUpdateRequest request){
        PostingEntity found = postingJpaRepository
                .findByBoardCollectionIdAndBoardIdAndId(
                        request.getBoardCollectionId(),
                        request.getBoardId(),
                        request.getPostingId()
                ).orElseThrow(RuntimeException::new);

        found.setContent(request.getMarkup());
        found.setTitle(request.getTitle());
    }

    @Transactional
    @Override
    public void create(PostingCreateRequest request){
        int newId = postingDslRepository.findNewId(request.getBoardCollectionId(), request.getBoardId());

        postingJpaRepository.save(
                PostingEntity.builder()
                        .id(newId)
                        .boardId(request.getBoardId())
                        .boardCollectionId(request.getBoardCollectionId())
                        .title(request.getTitle())
                        .content(request.getTitle())
                        .build()
        );
    }
}

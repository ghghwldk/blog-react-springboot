package com.m.blog.domain.posting.service;

import com.m.blog.domain.board.application.port.out.BoardDto;
import com.m.blog.domain.board.application.port.out.GetBoardQuery;
import com.m.blog.domain.posting.dto.*;
import com.m.blog.domain.posting.entity.PostingEntity;
import com.m.blog.domain.posting.repository.PostingDslRepository;
import com.m.blog.domain.posting.repository.PostingJpaRepository;
import com.m.blog.global.paging.PagingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostingServiceImpl implements PostingService{
    private final PostingJpaRepository postingJpaRepository;
    private final PostingDslRepository postingDslRepository;
    private final GetBoardQuery getBoardQuery;

    @Override
    public PagingResponse getPagingResponse(PostingReadFilteredPagingRequest requestDto){
        BoardDto found = getBoardQuery
                .findBoardDto(requestDto.getBoardCollectionId(), requestDto.getBoardId());

        return PagingResponse.get(postingDslRepository.get(requestDto), found);
    }


    @Override
    public PagingResponse getPagingResponse(PostingReadPagingRequest requestDto) {
        BoardDto found = null;

        return PagingResponse.get(postingDslRepository.get(requestDto), found);
    }

    @Transactional
    @Override
    public void update(PostingUpdateRequest requestDto){
        PostingEntity found = postingJpaRepository
                .findByBoardCollectionIdAndBoardIdAndId(
                        requestDto.getBoardCollectionId(),
                        requestDto.getBoardId(),
                        requestDto.getPostingId()
                ).orElseThrow(RuntimeException::new);

        found.setContent(requestDto.getMarkup());
        found.setTitle(requestDto.getTitle());
    }

    @Override
    public PostingReadResponse get(PostingReadRequest requestDto){
        return PostingReadResponse.of(postingDslRepository.get(requestDto));
    }

    @Transactional
    @Override
    public void create(PostingCreateRequest requestDto){
        int newId = postingDslRepository.findNewId(requestDto.getBoardCollectionId(), requestDto.getBoardId());

        postingJpaRepository.save(
                PostingEntity.builder()
                        .id(newId)
                        .boardId(requestDto.getBoardId())
                        .boardCollectionId(requestDto.getBoardCollectionId())
                        .title(requestDto.getTitle())
                        .content(requestDto.getTitle())
                        .build()
        );
    }
}
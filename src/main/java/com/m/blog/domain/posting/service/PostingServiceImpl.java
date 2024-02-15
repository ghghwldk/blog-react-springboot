package com.m.blog.domain.posting.service;

import com.amazonaws.services.kms.model.NotFoundException;
import com.m.blog.domain.board.dto.BoardDto;
import com.m.blog.domain.board.repository.BoardDslRepository;
import com.m.blog.domain.posting.dto.*;
import com.m.blog.domain.posting.entity.Posting;
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
    private final BoardDslRepository boardDslRepository;

    @Override
    public PagingResponse getPagingResponse(PostingReadFilteredPagingRequestDto requestDto){
        BoardDto found = boardDslRepository
                .findBoardDto(requestDto.getBoardCollectionId(), requestDto.getBoardId());

        return PagingResponse.get(postingDslRepository.get(requestDto), found);
    }


    @Override
    public PagingResponse getPagingResponse(PostingReadPagingRequestDto requestDto) {
        BoardDto found = null;

        return PagingResponse.get(postingDslRepository.get(requestDto), found);
    }

    @Transactional
    @Override
    public void update(PostingUpdateRequestDto requestDto){
        Posting found = postingJpaRepository
                .findByBoardCollectionIdAndBoardIdAndId(
                        requestDto.getBoardCollectionId(),
                        requestDto.getBoardId(),
                        requestDto.getPostingId()
                ).orElseThrow(RuntimeException::new);

        found.setContent(requestDto.getMarkup());
        found.setTitle(requestDto.getTitle());
    }

    @Override
    public PostingReadResponseDto get(PostingReadRequestDto requestDto){
        return PostingReadResponseDto.of(postingDslRepository.get(requestDto));
    }

    @Transactional
    @Override
    public void create(PostingCreateRequestDto requestDto){
        int newId = postingDslRepository.findNewId(requestDto.getBoardCollectionId(), requestDto.getBoardId());

        postingJpaRepository.save(
                Posting.builder()
                        .id(newId)
                        .boardId(requestDto.getBoardId())
                        .boardCollectionId(requestDto.getBoardCollectionId())
                        .title(requestDto.getTitle())
                        .content(requestDto.getTitle())
                        .build()
        );
    }
}
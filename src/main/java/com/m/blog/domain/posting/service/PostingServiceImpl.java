package com.m.blog.domain.posting.service;

import com.amazonaws.services.kms.model.NotFoundException;
import com.m.blog.domain.board.dto.BoardDto;
import com.m.blog.domain.board.repository.BoardDslRepository;
import com.m.blog.domain.posting.dto.*;
import com.m.blog.domain.posting.dto.dsl.PostingDto;
import com.m.blog.domain.posting.entity.Posting;
import com.m.blog.domain.posting.repository.PostingCustomRepository;
import com.m.blog.domain.posting.repository.PostingJpaRepository;
import com.m.blog.global.paging.PagingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostingServiceImpl implements PostingService{
    private final PostingJpaRepository postingJpaRepository;
    private final PostingCustomRepository postingCustomRepository;
    private final BoardDslRepository boardDslRepository;

    public PagingResponse getPagingResponse(PostingReadFilteredPagingRequestDto requestDto){
        Page<PostingDto> page = postingCustomRepository
                .getPageOfPosting(requestDto.getBoardCollectionId(), requestDto.getBoardId(), requestDto.getPageable());
        BoardDto boardDto = boardDslRepository.findBoardDto(requestDto.getBoardCollectionId(), requestDto.getBoardId());
        List<PostingDto> postingDtos = page.getContent();
        Integer totalPage= page.getTotalPages();
        Integer totalElements = (int) page.getTotalElements();
        return new PagingResponse(removeImg(postingDtos), totalPage, totalElements, boardDto.getBoardName()+" | "+boardDto.getBoardCollectionName());
    }

    @Override
    public PagingResponse getPagingResponse(PostingReadPagingRequestDto requestDto) {
        Page<PostingDto> page = postingCustomRepository.getPageOfLatestPosting(requestDto.getPageable());
        List<PostingDto> postingDtos =page.getContent();
        Integer totalPages= page.getTotalPages();
        Integer totalElements = (int) page.getTotalElements();
        return new PagingResponse(removeImg(postingDtos), totalPages, totalElements, "Home");
    }

    @Transactional
    @Override
    public void update(PostingUpdateRequestDto requestDto){
        postingJpaRepository
                .findByBoardCollectionIdAndBoardIdAndId(
                        requestDto.getBoardCollectionId(),
                        requestDto.getBoardId(),
                        requestDto.getPostingId()
                )
                .ifPresentOrElse(
                        posting -> {
                            posting.setContent(requestDto.getMarkup());
                            posting.setTitle(requestDto.getTitle());
                            postingJpaRepository.save(posting);
                        },
                        () -> {
                            throw new NotFoundException("Posting is not found.");
                        }
                );
    }


    @Override
    public PostingReadResponseDto get(PostingReadRequestDto requestDto){
        return PostingReadResponseDto.of(
                postingCustomRepository.getPosting(requestDto.getBoardCollectionId(),
                        requestDto.getBoardId(),
                        requestDto.getId())
        );
    }


    @Transactional
    @Override
    public void create(PostingCreateRequestDto requestDto){
        int newId = postingCustomRepository.findNewId(requestDto.getBoardCollectionId(), requestDto.getBoardId());
        postingJpaRepository.save(
                Posting.builder()
                        .id(newId)
                        .boardId(requestDto.getBoardId())
                        .boardCollectionId(requestDto.getBoardCollectionId())
                        .title(requestDto.getTitle())
                        .content(requestDto.getTitle())
                        .build()
        );
        return;
    }
    private List<PostingDto> removeImg(List<PostingDto> postingDtos){
        //String pattern="\\< ?img(.*?)\\>";
        String pattern="<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>";
        for(PostingDto l: postingDtos){
            String content= l.getContent();
            String converted=content.replaceAll(pattern,"");
            l.setContent(converted);
        }
        return postingDtos;
    }
}
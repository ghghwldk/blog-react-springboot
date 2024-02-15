package com.m.blog.domain.posting.service;

import com.amazonaws.services.kms.model.NotFoundException;
import com.m.blog.domain.board.dto.BoardDto;
import com.m.blog.domain.board.repository.BoardDslRepository;
import com.m.blog.domain.posting.dto.PostingCreateRequestDto;
import com.m.blog.domain.posting.dto.PostingReadRequestDto;
import com.m.blog.domain.posting.dto.PostingReadResponseDto;
import com.m.blog.domain.posting.dto.PostingUpdateRequestDto;
import com.m.blog.domain.posting.dto.dsl.PostingDto;
import com.m.blog.domain.posting.entity.Posting;
import com.m.blog.domain.posting.repository.PostingCustomRepository;
import com.m.blog.domain.posting.repository.PostingJpaRepository;
import com.m.blog.global.paging.PagingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostingService {
    private final PostingJpaRepository postingJpaRepository;
    private final PostingCustomRepository postingCustomRepository;
    private final BoardDslRepository boardDslRepository;

    public PagingResponse getPagingResponse( int boardId, int boardCollectionId, Pageable pageable){
        Page<PostingDto> page = postingCustomRepository.getPageOfPosting(boardCollectionId, boardId, pageable);
        BoardDto boardDto = boardDslRepository.findBoardDto(boardCollectionId, boardId);
        List<PostingDto> postingDtos = page.getContent();
        Integer totalPage= page.getTotalPages();
        Integer totalElements = (int) page.getTotalElements();
        return new PagingResponse(removeImg(postingDtos), totalPage, totalElements, boardDto.getBoardName()+" | "+boardDto.getBoardCollectionName());
    }

    @Transactional
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


    public PostingReadResponseDto get(PostingReadRequestDto requestDto){
        return PostingReadResponseDto.of(
                postingCustomRepository.getPosting(requestDto.getBoardCollectionId(),
                        requestDto.getBoardId(),
                        requestDto.getId())
        );
    }

    public PagingResponse getPagingResponse(Pageable pageable){
        Page<PostingDto> page = postingCustomRepository.getPageOfLatestPosting(pageable);
        List<PostingDto> postingDtos =page.getContent();
        Integer totalPages= page.getTotalPages();
        Integer totalElements = (int) page.getTotalElements();
        return new PagingResponse(removeImg(postingDtos), totalPages, totalElements, "Home");
    }
    @Transactional
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
    public List<PostingDto> removeImg(List<PostingDto> postingDtos){
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
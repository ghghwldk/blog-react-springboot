package com.m.blog.domain.posting.service;

import com.m.blog.domain.board.dto.BoardDto;
import com.m.blog.domain.board.repository.BoardDslRepository;
import com.m.blog.domain.posting.dto.PostingDto;
import com.m.blog.domain.posting.entity.Posting;
import com.m.blog.domain.posting.repository.PostingCustomRepository;
import com.m.blog.domain.posting.repository.PostingJpaRepository;
import com.m.blog.global.paging.PagingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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

    public PagingResponse getPagingResponse(Pageable pageable){
        Page<PostingDto> page = postingCustomRepository.getPageOfLatestPosting(pageable);
        List<PostingDto> postingDtos =page.getContent();
        Integer totalPages= page.getTotalPages();
        Integer totalElements = (int) page.getTotalElements();
        return new PagingResponse(removeImg(postingDtos), totalPages, totalElements, "Home");
    }
    @Transactional
    public void insertPosting(int boardCollectionId, int boardId, String title, String content){
        int newId = postingCustomRepository.findNewId(boardCollectionId, boardId);
        postingJpaRepository.save(
                Posting.builder()
                        .id(newId)
                        .boardId(boardId)
                        .boardCollectionId(boardCollectionId)
                        .title(title)
                        .content(content)
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
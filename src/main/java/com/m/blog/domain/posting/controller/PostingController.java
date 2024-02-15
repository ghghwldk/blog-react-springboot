package com.m.blog.domain.posting.controller;

import com.m.blog.domain.posting.dto.PostingCreateRequestDto;
import com.m.blog.domain.posting.dto.PostingReadRequestDto;
import com.m.blog.domain.posting.dto.PostingReadResponseDto;
import com.m.blog.domain.posting.dto.PostingUpdateRequestDto;
import com.m.blog.domain.posting.dto.dsl.PostingDto;
import com.m.blog.domain.posting.entity.Posting;
import com.m.blog.global.paging.PagingResponse;
import com.m.blog.domain.posting.repository.PostingCustomRepository;
import com.m.blog.domain.posting.repository.PostingJpaRepository;
import com.m.blog.domain.posting.service.PostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.HashMap;

@Controller
@RequestMapping("/posting")
@RequiredArgsConstructor
public class PostingController {
    private final PostingService postingService;
    private final PostingCustomRepository postingCustomRepository;

    @ResponseBody
    @GetMapping("/list/latest")
    public PagingResponse list(Pageable pageable){
        return postingService.getPagingResponse(pageable);
    }

    @ResponseBody
    @GetMapping("/list")
    public PagingResponse list(@RequestParam int boardId, @RequestParam int boardCollectionId, Pageable pageable){
        return postingService.getPagingResponse(boardId, boardCollectionId, pageable);
    }

    @ResponseBody
    @GetMapping("/data")
    public PostingReadResponseDto data(@RequestParam int boardCollectionId, @RequestParam int boardId, @RequestParam int id){
        return postingService.get(PostingReadRequestDto.builder()
                        .boardCollectionId(boardCollectionId)
                        .boardId(boardId)
                        .id(id)
                .build());
    }

    @Transactional
    @ResponseBody
    @PutMapping("/data/update")
    public void update(@RequestBody PostingUpdateRequestDto requestDto){
        postingService.update(requestDto);
    }

    @PostMapping("/data/insert/posting")
    @ResponseBody
    public void create(@RequestBody PostingCreateRequestDto requestDto){
        postingService.create(requestDto);
    }
}

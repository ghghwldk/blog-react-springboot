package com.m.blog.domain.posting.controller;

import com.m.blog.domain.posting.dto.*;
import com.m.blog.domain.posting.service.PostingService;
import com.m.blog.global.paging.PagingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@Controller
@RequestMapping("/posting")
@RequiredArgsConstructor
public class PostingController {
    private final PostingService postingService;

    @ResponseBody
    @GetMapping("/list/latest")
    public PagingResponse list(Pageable pageable){
        return postingService.getPagingResponse(PostingReadPagingRequest.builder()
                        .pageable(pageable)
                .build());
    }

    @ResponseBody
    @GetMapping("/list")
    public PagingResponse list(@RequestParam int boardId, @RequestParam int boardCollectionId, Pageable pageable){
        return postingService.getPagingResponse(PostingReadFilteredPagingRequest.builder()
                        .boardCollectionId(boardCollectionId)
                        .boardId(boardId)
                        .pageable(pageable)
                .build());
    }

    @ResponseBody
    @GetMapping("/data")
    public PostingReadResponse data(@RequestParam int boardCollectionId, @RequestParam int boardId, @RequestParam int id){
        return postingService.get(PostingReadRequest.builder()
                        .boardCollectionId(boardCollectionId)
                        .boardId(boardId)
                        .id(id)
                .build());
    }

    @Transactional
    @ResponseBody
    @PutMapping("/data/update")
    public void update(@RequestBody PostingUpdateRequest requestDto){
        postingService.update(requestDto);
    }

    @PostMapping("/data/insert/posting")
    @ResponseBody
    public void create(@RequestBody PostingCreateRequest requestDto){
        postingService.create(requestDto);
    }
}

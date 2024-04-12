package com.m.blog.domain.posting.infrastructure.web.controller;

import com.m.blog.domain.posting.application.port.entrypoint.api.ChangePostingEndpointPort;
import com.m.blog.domain.posting.application.port.entrypoint.api.FindPositngEndpointPort;
import com.m.blog.domain.posting.infrastructure.web.dto.*;
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
    private final FindPositngEndpointPort findPositngEndpointPort;
    private final ChangePostingEndpointPort changePostingEndpointPort;

    @ResponseBody
    @GetMapping("/list")
    public PagingResponse list(Pageable pageable){
        return findPositngEndpointPort.getPagingResponse(PostingReadPagingRequest.builder()
                        .pageable(pageable)
                .build());
    }

    @ResponseBody
    @GetMapping("/list-per-board")
    public PagingResponse list(@RequestParam String boardId, Pageable pageable){
        return findPositngEndpointPort.getPagingResponse(PostingReadPerBoardPagingRequest.builder()
                        .boardId(boardId)
                        .pageable(pageable)
                .build());
    }

    @ResponseBody
    @GetMapping
    public PostingReadResponse data(@RequestParam String id){
        return findPositngEndpointPort.get(PostingReadRequest.builder().id(id).build());
    }

    @Transactional
    @ResponseBody
    @PutMapping
    public void update(@RequestBody PostingUpdateRequest requestDto){
        changePostingEndpointPort.update(requestDto);
    }

    @PostMapping
    @ResponseBody
    public void create(@RequestBody PostingCreateRequest requestDto){
        changePostingEndpointPort.create(requestDto);
    }
}

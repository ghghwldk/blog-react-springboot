package com.m.blog.aggregate.boardCollection.infrastructure.web.controller;

import com.m.blog.aggregate.boardCollection.application.port.entrypoint.api.ChangePostingEndpointPort;
import com.m.blog.aggregate.boardCollection.application.port.entrypoint.api.FindPositngEndpointPort;
import com.m.blog.aggregate.boardCollection.infrastructure.web.dto.*;
import com.m.blog.global.paging.PagingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<PagingResponse> list(Pageable pageable){
        PostingReadPagingRequest request = PostingReadPagingRequest.builder()
                .pageable(pageable)
                .build();

        return ResponseEntity.ok(findPositngEndpointPort.getPagingResponse(request));
    }

    @ResponseBody
    @GetMapping("/list-per-board")
    public ResponseEntity<PagingResponse> list(@RequestParam String boardId, Pageable pageable){
        PostingReadPerBoardPagingRequest request = PostingReadPerBoardPagingRequest.builder()
                .boardId(boardId)
                .pageable(pageable)
                .build();

        return ResponseEntity.ok(findPositngEndpointPort.getPagingResponse(request));
    }

    @ResponseBody
    @GetMapping
    public ResponseEntity<PostingReadResponse> data(@RequestParam String id){
        PostingReadRequest request = PostingReadRequest.builder()
                .id(id)
                .build();

        return ResponseEntity.ok(findPositngEndpointPort.get(request));
    }

    @Transactional
    @ResponseBody
    @PutMapping
    public ResponseEntity update(@RequestBody PostingUpdateRequest request){
        changePostingEndpointPort.update(request);

        return (ResponseEntity) ResponseEntity.ok();
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity create(@RequestBody PostingCreateRequest request){
        changePostingEndpointPort.create(request);

        return (ResponseEntity) ResponseEntity.ok();
    }
}

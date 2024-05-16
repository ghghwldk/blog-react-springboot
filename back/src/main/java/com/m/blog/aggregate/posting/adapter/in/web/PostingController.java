package com.m.blog.aggregate.posting.adapter.in.web;

import com.m.blog.aggregate.file.application.domain.File_;
import com.m.blog.aggregate.file.application.usecase.FileDeleteUsecase;
import com.m.blog.aggregate.posting.application.domain.Posting;
import com.m.blog.aggregate.posting.application.port.in.web.FindPostingQuery;
import com.m.blog.aggregate.posting.application.port.in.web.ChangePostingUsecase;
import com.m.blog.aggregate.posting.application.port.in.web.SavePostingUsecase;
import com.m.blog.global.paging.PagingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/posting")
@RequiredArgsConstructor
public class PostingController {
    private final ChangePostingUsecase changePostingUsecase;
    private final SavePostingUsecase savePostingUsecase;
    private final FileDeleteUsecase fileDeleteUsecase;
    private final FindPostingQuery findPostingQuery;


    @ResponseBody
    @GetMapping("/list")
    public ResponseEntity<PagingResponse> list(Pageable pageable){
        PagingResponse response = findPostingQuery.getPagingResponse(pageable);

        return ResponseEntity.ok(response);
    }

    @ResponseBody
    @GetMapping("/list-per-board")
    public ResponseEntity<PagingResponse> list(@RequestParam String boardId, Pageable pageable){
        PagingResponse response =  findPostingQuery.get(boardId, pageable);

        return ResponseEntity.ok(response);
    }

    @ResponseBody
    @GetMapping
    public ResponseEntity<PostingReadResponse> data(@RequestParam String id){
        PostingReadResponse response =  findPostingQuery.get(id);
        return ResponseEntity.ok(response);
    }

    @ResponseBody
    @PutMapping
    public ResponseEntity update(@RequestBody PostingUpdateCommand command){
        List<File_.FileId> existings = changePostingUsecase.update(command);

        Posting.PostingId postingId = new Posting.PostingId(command.getPostingId());

        fileDeleteUsecase.deleteUsing(existings, postingId);

        return ResponseEntity.ok(null);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity create(@RequestBody PostingCreateCommand request){
        savePostingUsecase.save(request);

        return ResponseEntity.ok(null);
    }
}

package com.m.blog.domain.posting.controller;

import com.m.blog.domain.board.dto.BoardDto;
import com.m.blog.domain.board.repository.BoardDslRepository;
import com.m.blog.domain.posting.dto.PostingDto;
import com.m.blog.domain.posting.entity.Posting;
import com.m.blog.global.paging.PagingResponse;
import com.m.blog.domain.posting.repository.PostingCustomRepository;
import com.m.blog.domain.posting.repository.PostingJpaRepository;
import com.m.blog.domain.posting.service.PostingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
@Controller
@RequestMapping("/posting")
public class PostingController {
    @Autowired
    PostingService postingService;
    @Autowired
    PostingCustomRepository postingCustomRepository;
    @Autowired
    PostingJpaRepository postingJpaRepository;

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
    public PostingDto data(@RequestParam int boardCollectionId, @RequestParam int boardId, @RequestParam int id){
        return postingCustomRepository.getPosting(boardCollectionId, boardId, id);
    }

    @Transactional
    @ResponseBody
    @PutMapping("/data/update")
    public HashMap<String, String> update(@RequestBody HashMap<String, String> requestBody){
        String content=requestBody.get("markup");
        String title=requestBody.get("title");
        int boardCollectionId= Integer.parseInt(requestBody.get("boardCollectionId"));
        int boardId= Integer.parseInt(requestBody.get("boardId"));
        int postingId= Integer.parseInt(requestBody.get("postingId"));

        Posting posting = postingJpaRepository.findByBoardCollectionIdAndBoardIdAndId(boardCollectionId, boardId, postingId);
        posting.setContent(content);
        posting.setTitle(title);

        return new HashMap<String, String>();
    }


    @PostMapping("/data/insert/posting")
    @ResponseBody
    public HashMap<String, String> dataInsertPosting(@RequestBody HashMap<String, String> requestBody){
        int boardCollectionId= Integer.parseInt(requestBody.get("boardCollectionId"));
        int boardId= Integer.parseInt(requestBody.get("boardId"));
        String title= requestBody.get("title");
        String content= requestBody.get("content");
        postingService.insertPosting(boardCollectionId, boardId, title, content);

        return new HashMap<String, String>();
    }
}

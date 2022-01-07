package com.m.blog.controller;

import com.m.blog.dto.LatestPostingDto;
import com.m.blog.entity.Posting;
import com.m.blog.paging.PagingResponse;
import com.m.blog.repository.PostingCustomRepository;
import com.m.blog.repository.PostingJpaRepository;
import com.m.blog.service.PostingService;
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
        Page<LatestPostingDto> page = postingCustomRepository.getPageOfLatestPosting("",pageable);
        List<LatestPostingDto> latestPostingDtos=page.getContent();
        Integer totalPages= page.getTotalPages();
        return new PagingResponse(totalPages, postingService.removeImg(latestPostingDtos));
    }
    @ResponseBody
    @GetMapping("/list")
    public PagingResponse list(@RequestParam int boardId, @RequestParam int boardCollectionId, Pageable pageable){
        Page<LatestPostingDto> page = postingCustomRepository.getPageOfPosting(boardCollectionId, boardId,"",pageable);
        List<LatestPostingDto> latestPostingDtos = page.getContent();
        Integer totalPage= page.getTotalPages();
        return new PagingResponse(totalPage, postingService.removeImg(latestPostingDtos));
    }

    @ResponseBody
    @GetMapping("/data")
    public Posting data(@RequestParam int boardCollectionId, @RequestParam int boardId, @RequestParam int id){
        return postingJpaRepository.findByBoardCollectionIdAndBoardIdAndId(boardCollectionId, boardId, id);
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

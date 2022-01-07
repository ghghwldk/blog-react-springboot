package com.m.blog.service;

import com.m.blog.dto.LatestPostingDto;
import com.m.blog.entity.Posting;
import com.m.blog.repository.PostingCustomRepository;
import com.m.blog.repository.PostingJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;

@Service
public class PostingService {
    @Autowired
    PostingJpaRepository postingJpaRepository;
    @Autowired
    PostingCustomRepository postingCustomRepository;

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
    public List<LatestPostingDto> removeImg(List<LatestPostingDto> latestPostingDtos){
        //String pattern="\\< ?img(.*?)\\>";
        String pattern="<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>";
        for(LatestPostingDto l:latestPostingDtos){
            String content= l.getContent();
            String converted=content.replaceAll(pattern,"");
            l.setContent(converted);
        }
        return latestPostingDtos;
    }
}
package com.m.blog.domain.posting.service;

import com.m.blog.domain.posting.PostingDto;
import com.m.blog.domain.posting.entity.Posting;
import com.m.blog.domain.posting.repository.PostingCustomRepository;
import com.m.blog.domain.posting.repository.PostingJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
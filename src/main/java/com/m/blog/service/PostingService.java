package com.m.blog.service;

import com.m.blog.dto.LatestPostingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class PostingService {

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
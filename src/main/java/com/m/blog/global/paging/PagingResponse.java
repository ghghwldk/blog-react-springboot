package com.m.blog.global.paging;

import com.m.blog.domain.board.application.port.out.BoardDto;
import com.m.blog.domain.posting.dto.dsl.PostingDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class PagingResponse {

    private Object content;
    private Integer totalPages;
    private Integer totalElements;
    private String location;

    public static PagingResponse get(Page<PostingDto> page, BoardDto found){
        return PagingResponse.builder()
                .content(removeImg(page.getContent()))
                .totalPages(page.getTotalPages())
                .totalElements((int) page.getTotalElements())
                .location(getBoardLocation(found))
                .build();
    }

    private static String getBoardLocation(BoardDto found) {
        final String homeLocation = "Home";

        if (found != null) {
            return found.getBoardName() + " | " + found.getBoardCollectionName();
        } else {
            return homeLocation;
        }
    }

    private static List<PostingDto> removeImg(List<PostingDto> postingDtos){
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

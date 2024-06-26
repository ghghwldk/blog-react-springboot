package com.m.blog.global.paging;
import com.m.blog.aggregate.board.adapter.out.persistence.BoardDto;
import com.m.blog.aggregate.posting.adapter.out.persistence.PostingDto;
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
            return found.getBoardCollectionName() + " | " + found.getBoardName();
        } else {
            return homeLocation;
        }
    }

    private static List<PostingDto> removeImg(List<PostingDto> sophisticateds){
        //String pattern="\\< ?img(.*?)\\>";
        String pattern="<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>";

        sophisticateds.forEach(e->{
            String content= e.getContent();
            String converted=content.replaceAll(pattern,"");
            e.setContent(converted);
        });

        return sophisticateds;
    }
}

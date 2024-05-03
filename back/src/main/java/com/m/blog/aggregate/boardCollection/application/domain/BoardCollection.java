package com.m.blog.aggregate.boardCollection.application.domain;

import com.m.blog.aggregate.posting.application.domain.Posting;
import com.m.blog.global.customAnnotation.Domain;
import com.m.blog.global.customAnnotation.Root;
import com.m.blog.global.exception.DataNotFoundException;
import com.m.blog.global.exception.TooManyException;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@Root
@Domain
public class BoardCollection{
    @Getter private final BoardCollection.BoardCollectionId boardCollectionId;
    @Getter private String name;


    @Getter
    @AllArgsConstructor
    public static class BoardCollectionId {
        private String value;
    }

}

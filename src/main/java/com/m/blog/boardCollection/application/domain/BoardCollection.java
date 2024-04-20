package com.m.blog.boardCollection.application.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class BoardCollection {
    private Id id;
    private String name;

    @Data
    @Builder
    @AllArgsConstructor
    public static class Id {
        private int id;
    }

}

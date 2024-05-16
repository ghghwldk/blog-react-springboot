package com.m.blog.aggregate.posting.adapter.in.web;

import lombok.Data;

@Data
public class PostingCreateCommand {
    private String boardId;
    private String title;
    private String content;
}


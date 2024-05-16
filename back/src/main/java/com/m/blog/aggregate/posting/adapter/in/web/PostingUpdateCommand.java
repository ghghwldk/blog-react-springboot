package com.m.blog.aggregate.posting.adapter.in.web;

import lombok.Data;

@Data
public class PostingUpdateCommand {
    private String markup;
    private String title;
    private String boardId;
    private String postingId;
}

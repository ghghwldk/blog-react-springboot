package com.m.blog;

import com.m.blog.aggregate.posting.application.domain.Posting;
import com.m.blog.global.entity.SnowflakeIdGenerator;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

public class PostingTestData {
    private static final String originalContent
            = "<p>content4d<img src=\"/file/download/7188529605341347840.png\" alt=\"alt text\">&nbsp;asdfasdf</p>\n";
    private static final String updatedContent
            = "<p>content4d</p>\n";

    private static final String postingId = "7184122073848090634";

    public static Posting provideOriginal(){
        PostingTestData.PostingBuilder defaultBuilder
                = PostingTestData.defaultPosting();

        defaultBuilder.withContent(originalContent);
        return defaultBuilder.build();
    }

    public static Posting provideUpdated(){
        PostingTestData.PostingBuilder defaultBuilder
                = PostingTestData.defaultPosting();

        defaultBuilder.withContent(updatedContent);
        return defaultBuilder.build();
    }

    public static PostingBuilder defaultPosting(){
        return new PostingBuilder()
                .withPostingId(postingId)
                .withBoardId(SnowflakeIdGenerator.generateId())
                .withTitle("default-title");
    }

    public static class PostingBuilder {
        private String postingId;
        private String boardId;
        private String title;
        private String content;


        public PostingBuilder withPostingId(String postingId) {
            this.postingId = postingId;
            return this;
        }

        public PostingBuilder withBoardId(String boardId) {
            this.boardId = boardId;
            return this;
        }

        public PostingBuilder withTitle(String title) {
            this.title = title;
            return this;
        }

        public PostingBuilder withContent(String content) {
            this.content = content;
            return this;
        }

        public Posting build() {
            return new Posting(
                    this.postingId,
                    this.boardId,
                    this.title,
                    this.content
            );
        }

    }
}

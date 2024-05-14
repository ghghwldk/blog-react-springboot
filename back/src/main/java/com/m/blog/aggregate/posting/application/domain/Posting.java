package com.m.blog.aggregate.posting.application.domain;

import com.m.blog.aggregate.board.application.domain.Board;
import com.m.blog.aggregate.file.application.domain.File_;
import com.m.blog.aggregate.file.helper.FileCheckHelper;
import com.m.blog.global.entity.SnowflakeIdGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;


@Getter
public class Posting {
    private final PostingId postingId;
    private final Board.BoardId boardId;
    private String title;
    private String content;

    public Posting(@NonNull String postingId, @NonNull String boardId,
                   @NonNull String title, @NonNull String content) {
        this.postingId = new PostingId(postingId);
        this.boardId = new Board.BoardId(boardId);
        this.title = title;
        this.content = content;
    }

    public static Posting withSnowflakeId(String boardId, String title, String content){
        return new Posting(SnowflakeIdGenerator.generateId(), boardId,
                title, content);
    }

    public List<File_.FileId> update(@NonNull Posting after, String downloadPrefix){
        this.title = after.getTitle();
        this.content = after.getContent();

        return FileCheckHelper.getFileIdsInsideContent(after.getContent(), downloadPrefix);
    }



    public void validate(){

    }

    @Getter
    @AllArgsConstructor
    public static class PostingId {
        private String value;

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (!(o instanceof PostingId))
                return false;

            return this.value.equals(((PostingId) o).getValue());
        }
    }
}

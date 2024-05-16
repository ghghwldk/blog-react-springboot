package com.m.blog.aggregate.posting.application.domain;

import com.m.blog.aggregate.board.application.domain.Board;
import com.m.blog.aggregate.file.application.domain.File_;
import com.m.blog.global.entity.SnowflakeIdGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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

        return getFileIdsInsideContent(after.getContent(), downloadPrefix);
    }

    public static List<File_.FileId> getFileIdsInsideContent(String content, String downloadPrefix){
        List<File_.FileId> result = new LinkedList<>();
        // 정규 표현식 패턴
        String pattern = downloadPrefix + "(\\d+\\.\\w+)";

        // 패턴 컴파일
        Pattern p = Pattern.compile(pattern);

        // 매치 찾기
        Matcher m = p.matcher(content);

        // 매치된 문자열 출력
        while (m.find()) {
            result.add(new File_.FileId(m.group(1)));
        }

        return result;
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

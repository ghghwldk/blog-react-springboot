package com.m.blog.aggregate.posting.application.domain;

import com.m.blog.aggregate.board.application.domain.Board;
import com.m.blog.aggregate.board.application.domain.BoardId;
import com.m.blog.aggregate.file.application.domain.FileId;
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
    private final BoardId boardId;
    private String title;
    private String content;

    public Posting(@NonNull String postingId, @NonNull String boardId,
                   @NonNull String title, @NonNull String content) {
        this.postingId = new PostingId(postingId);
        this.boardId = new BoardId(boardId);
        this.title = title;
        this.content = content;
    }

    public static Posting withSnowflakeId(String boardId, String title, String content){
        return new Posting(SnowflakeIdGenerator.generateId(), boardId,
                title, content);
    }

    public List<FileId> update(@NonNull Posting after, String downloadPrefix){
        this.title = after.getTitle();
        this.content = after.getContent();

        return getFileIdsInsideContent(after.getContent(), downloadPrefix);
    }

    public static List<FileId> getFileIdsInsideContent(String content, String downloadPrefix){
        List<FileId> result = new LinkedList<>();
        // 정규 표현식 패턴
        String pattern = downloadPrefix + "(\\d+\\.\\w+)";

        // 패턴 컴파일
        Pattern p = Pattern.compile(pattern);

        // 매치 찾기
        Matcher m = p.matcher(content);

        // 매치된 문자열 출력
        while (m.find()) {
            result.add(new FileId(m.group(1)));
        }

        return result;
    }


    public void validate(){

    }
}

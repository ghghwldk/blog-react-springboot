package com.m.blog.aggregate.file.helper;

import com.m.blog.aggregate.file.application.domain.File_;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileCheckHelper {
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
}

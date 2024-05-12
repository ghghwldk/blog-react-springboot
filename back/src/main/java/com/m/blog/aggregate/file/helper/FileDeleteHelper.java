package com.m.blog.aggregate.file.helper;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileDeleteHelper {
    public static Set<String> getFileIdSet(String content, String downloadPrefix){
        Set<String> result = new HashSet<>();
        // 정규 표현식 패턴
        String pattern = downloadPrefix + "(\\d+\\.\\w+)";

        // 패턴 컴파일
        Pattern p = Pattern.compile(pattern);

        // 매치 찾기
        Matcher m = p.matcher(content);

        // 매치된 문자열 출력
        while (m.find()) {
            result.add(m.group(1));
        }

        return result;
    }
}

package com.m.blog.aggregate.file.infrastructure.file;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.eclipse.jdt.internal.compiler.ast.WhileStatement;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class FileDeleteUtilImpl implements FileDeleteUtil {
    private final int maximumLength = 1;
    private final BlockingQueue<String> waitings = new LinkedBlockingQueue<>();

    @Override
    public void enterQueue(List<String> fileKeys) {
        waitings.addAll(fileKeys);

        if (waitings.size() >= maximumLength) {
            List<String> current = new LinkedList<>();
            int countToDelete = waitings.size() - maximumLength + 1;

            for (int i = 0; i < countToDelete; i++) {
                current.add(waitings.poll());
            }

            delete(current);
        }

    }

    private void delete(List<String> current){
        waitings.stream()
                .map(File::new)
                .collect(Collectors.toList())
                .forEach(FileUtils::deleteQuietly);
    }

    @PreDestroy
    public void deleteRemains(){
        log.info("--PreDestroy to delete remains");

        delete((List<String>) waitings);
    }
}

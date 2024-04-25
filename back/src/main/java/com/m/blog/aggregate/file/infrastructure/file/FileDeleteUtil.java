package com.m.blog.aggregate.file.infrastructure.file;

import com.amazonaws.services.s3.AmazonS3;
import com.m.blog.global.properties.AwsProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
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
public abstract class FileDeleteUtil {
    private final int maximumLength = 1;

    protected final BlockingQueue<String> waitings = new LinkedBlockingQueue<>();

    public void wait(List<String> fileKeys) {
        addAndDelete(fileKeys);
    }

    private void addAndDelete(List<String> fileKeys){
        waitings.addAll(fileKeys);

        if (waitings.size() >= maximumLength) {
            delete(getTargets());
        }
    }

    private List<String> getTargets(){
        List<String> targets = new LinkedList<>();
        int countToDelete = waitings.size() - maximumLength + 1;

        for (int i = 0; i < countToDelete; i++) {
            targets.add(waitings.poll());
        }
        return targets;
    }

    protected abstract void delete(List<String> targets);

    @PreDestroy
    private void deleteRemains(){
        log.info("--PreDestroy to delete remains");

        delete((List<String>) waitings);
    }
}

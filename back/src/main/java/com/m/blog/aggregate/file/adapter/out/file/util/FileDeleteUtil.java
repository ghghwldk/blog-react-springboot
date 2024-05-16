package com.m.blog.aggregate.file.adapter.out.file.util;

import com.amazonaws.services.s3.AmazonS3;
import com.m.blog.aggregate.file.application.domain.FileId;
import com.m.blog.aggregate.file.application.domain.File_;
import com.m.blog.global.properties.AwsProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.io.File;
import java.lang.reflect.Field;
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

    protected final BlockingQueue<FileId> waitings = new LinkedBlockingQueue<>();

    public void wait(List<FileId> fileIds) {
        if(fileIds.size() == 0){
            return;
        }

        addAndDelete(fileIds);
    }

    private void addAndDelete(List<FileId> fileIds){
        waitings.addAll(fileIds);

        if (waitings.size() >= maximumLength) {
            delete(getTargets());
        }
    }

    private List<FileId> getTargets(){
        List<FileId> targets = new LinkedList<>();
        int countToDelete = waitings.size() - maximumLength + 1;

        for (int i = 0; i < countToDelete; i++) {
            targets.add(waitings.poll());
        }
        return targets;
    }

    protected abstract void delete(List<FileId> targets);

    @PreDestroy
    private void deleteRemains(){
        log.info("--PreDestroy to delete remains");

        delete((List<FileId>) waitings);
    }
}

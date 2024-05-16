package com.m.blog.aggregate.posting.application.service;

import com.m.blog.aggregate.file.application.domain.File_;
import com.m.blog.aggregate.posting.adapter.in.web.PostingCreateCommand;
import com.m.blog.aggregate.posting.adapter.in.web.PostingUpdateCommand;
import com.m.blog.aggregate.posting.application.domain.Posting;
import com.m.blog.aggregate.posting.application.port.out.persistence.ChangePostingPersistencePort;
import com.m.blog.aggregate.posting.application.port.out.persistence.LoadPostingPersistencePort;
import com.m.blog.aggregate.posting.application.port.out.persistence.SavePostingPersistencePort;
import com.m.blog.aggregate.posting.application.usecase.ChangePostingUsecase;
import com.m.blog.aggregate.posting.application.usecase.SavePostingUsecase;
import com.m.blog.global.customAnnotation.UseCase;
import com.m.blog.global.exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@UseCase
@RequiredArgsConstructor
@Transactional
class PostingService implements SavePostingUsecase, ChangePostingUsecase {
    private final SavePostingPersistencePort savePostingPersistencePort;
    private final ChangePostingPersistencePort changePostingPersistencePort;
    private final LoadPostingPersistencePort loadPostingPersistencePort;

    @Override
    @Transactional
    public void save(PostingCreateCommand command) {
        Posting brandNew = PostingServiceMapper.from(command);

        brandNew.validate();

        savePostingPersistencePort.save(brandNew);
    }

    @Override
    @Transactional
    public List<File_.FileId> update(PostingUpdateCommand command) {
        Posting posting = loadPostingPersistencePort.load(command.getPostingId())
                .orElseThrow(DataNotFoundException::new);

        Posting after = PostingServiceMapper.from(command);

        List<File_.FileId> existings = posting.update(after, File_.getDownloadPrefix());

        changePostingPersistencePort.update(posting);

        return existings;
    }
}
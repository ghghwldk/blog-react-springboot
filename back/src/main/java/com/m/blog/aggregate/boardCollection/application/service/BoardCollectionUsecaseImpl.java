package com.m.blog.aggregate.boardCollection.application.service;

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

@UseCase
@RequiredArgsConstructor
@Transactional
class BoardCollectionUsecaseImpl implements SavePostingUsecase, ChangePostingUsecase {
    private final SavePostingPersistencePort savePostingPersistencePort;
    private final ChangePostingPersistencePort changePostingPersistencePort;
    private final LoadPostingPersistencePort loadPostingPersistencePort;
    @Override
    public void save(Posting brandNew) {
        brandNew.validate();
        savePostingPersistencePort.save(brandNew);
    }

    @Override
    public void update(Posting after) {
        Posting posting = loadPostingPersistencePort.load(after.getPostingId())
                .orElseThrow(DataNotFoundException::new);

        posting.update(after);

        changePostingPersistencePort.update(posting);
    }



}
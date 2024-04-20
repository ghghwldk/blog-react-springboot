package com.m.blog.boardCollection.application.service;

import com.m.blog.common.UseCase;
import com.m.blog.boardCollection.application.domain.Posting;
import com.m.blog.boardCollection.application.port.persistence.SavePostingPersistencePort;
import com.m.blog.boardCollection.application.usecase.SavePostingUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
class SavePostingUsecaseImpl implements SavePostingUsecase {
    private final SavePostingPersistencePort savePostingPersistencePort;

    @Override
    @Transactional
    public void save(Posting posting) {
        savePostingPersistencePort.save(posting);
    }
}

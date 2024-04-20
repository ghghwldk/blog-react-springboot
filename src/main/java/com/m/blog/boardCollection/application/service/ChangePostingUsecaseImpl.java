package com.m.blog.boardCollection.application.service;

import com.m.blog.common.UseCase;
import com.m.blog.boardCollection.application.domain.Posting;
import com.m.blog.boardCollection.application.port.persistence.ChangePostingPersistencePort;
import com.m.blog.boardCollection.application.usecase.ChangePostingUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
class ChangePostingUsecaseImpl implements ChangePostingUsecase {
    private final ChangePostingPersistencePort changePostingPersistencePort;

    @Override
    @Transactional
    public void update(Posting after) {
        changePostingPersistencePort.update(after);
    }
}
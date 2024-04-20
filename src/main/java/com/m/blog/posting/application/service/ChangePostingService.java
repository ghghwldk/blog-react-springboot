package com.m.blog.domain.posting.application.service;

import com.m.blog.common.UseCase;
import com.m.blog.domain.posting.application.domain.Posting;
import com.m.blog.domain.posting.application.port.persistence.ChangePostingPersistencePort;
import com.m.blog.domain.posting.application.usecase.ChangePostingUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
class ChangePostingService implements ChangePostingUsecase {
    private final ChangePostingPersistencePort changePostingPersistencePort;

    @Override
    @Transactional
    public void update(Posting after) {
        changePostingPersistencePort.update(after);
    }
}
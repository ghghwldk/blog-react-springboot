package com.m.blog.domain.posting.application.service;

import com.m.blog.domain.posting.application.domain.Posting;
import com.m.blog.domain.posting.application.port.persistence.SavePostingPersistencePort;
import com.m.blog.domain.posting.application.usecase.SavePostingUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class SavePostingService implements SavePostingUsecase {
    private final SavePostingPersistencePort savePostingPersistencePort;

    @Override
    @Transactional
    public void save(Posting posting) {
        savePostingPersistencePort.save(posting);
    }
}

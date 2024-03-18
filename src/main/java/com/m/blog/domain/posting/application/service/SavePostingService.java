package com.m.blog.domain.posting.application.service;

import com.m.blog.domain.posting.application.domain.Posting;
import com.m.blog.domain.posting.application.port.persistence.FindPostingNewIdPersistencePort;
import com.m.blog.domain.posting.application.port.persistence.SavePostingPersistencePort;
import com.m.blog.domain.posting.application.usecase.SavePostingUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class SavePostingService implements SavePostingUsecase {
    private final SavePostingPersistencePort savePostingPersistencePort;
    private final FindPostingNewIdPersistencePort findPostingNewIdPersistencePort;

    @Override
    @Transactional
    public void save(Posting.IdWithoutPostingId idWithoutPostingId, Posting.Mutable target) {
        Posting.PostingId newId =
                Posting.PostingId.from(
                        idWithoutPostingId,
                        findPostingNewIdPersistencePort.findNewId(idWithoutPostingId).getValue()
                );

        savePostingPersistencePort.save(newId, target);
    }
}

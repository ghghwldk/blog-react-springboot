package com.m.blog.domain.posting.application.service;

import com.m.blog.domain.posting.application.domain.Posting;
import com.m.blog.domain.posting.application.port.persistence.FindPostingNewIdPort;
import com.m.blog.domain.posting.application.port.persistence.SavePostingPort;
import com.m.blog.domain.posting.application.usecase.SavePostingUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class SavePostingService implements SavePostingUsecase {
    private final SavePostingPort savePostingPort;
    private final FindPostingNewIdPort findPostingNewIdPort;

    @Override
    @Transactional
    public void save(Posting.IdWithoutPostingId idWithoutPostingId, Posting.Mutable target) {
        Posting.PostingId newId =
                Posting.PostingId.from(
                        idWithoutPostingId,
                        findPostingNewIdPort.findNewId(idWithoutPostingId).getValue()
                );

        savePostingPort.save(newId, target);
    }
}

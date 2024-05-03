package com.m.blog.aggregate.boardCollection.application.service;

import com.m.blog.aggregate.boardCollection.application.domain.BoardCollection;
import com.m.blog.aggregate.boardCollection.application.domain.Posting;
import com.m.blog.aggregate.posting.application.port.out.persistence.ChangePostingPersistencePort;
import com.m.blog.aggregate.boardCollection.application.port.out.persistence.LoadBoardCollectionPersistencePort;
import com.m.blog.aggregate.boardCollection.application.port.out.persistence.SavePostingPersistencePort;
import com.m.blog.aggregate.posting.application.usecase.ChangePostingUsecase;
import com.m.blog.aggregate.posting.application.usecase.SavePostingUsecase;
import com.m.blog.global.customAnnotation.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional
class BoardCollectionUsecaseImpl implements SavePostingUsecase, ChangePostingUsecase {
    private final SavePostingPersistencePort savePostingPersistencePort;
    private final ChangePostingPersistencePort changePostingPersistencePort;
    private final LoadBoardCollectionPersistencePort loadBoardCollectionPersistencePort;
    @Override
    public void save(Posting posting) {
        BoardCollection boardCollection
                = loadBoardCollectionPersistencePort.load(posting.getBoardId());

        boardCollection.add(posting);

        savePostingPersistencePort.save(boardCollection.getAdded());
    }

    @Override
    public void update(Posting after) {
        BoardCollection boardCollection
                = loadBoardCollectionPersistencePort.load(after.getPostingId());

        boardCollection.update(after);

        changePostingPersistencePort.update(boardCollection.getUpdated());
    }



}
package com.m.blog.boardCollection.application.service;

import com.m.blog.boardCollection.application.domain.BoardCollection;
import com.m.blog.boardCollection.application.domain.Posting;
import com.m.blog.boardCollection.application.port.persistence.ChangePostingPersistencePort;
import com.m.blog.boardCollection.application.port.persistence.LoadBoardCollectionPort;
import com.m.blog.boardCollection.application.port.persistence.SavePostingPersistencePort;
import com.m.blog.boardCollection.application.usecase.ChangePostingUsecase;
import com.m.blog.boardCollection.application.usecase.SavePostingUsecase;
import com.m.blog.common.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional
class BoardCollectionUsecaseImpl implements SavePostingUsecase, ChangePostingUsecase {
    private final SavePostingPersistencePort savePostingPersistencePort;
    private final ChangePostingPersistencePort changePostingPersistencePort;
    private final LoadBoardCollectionPort loadBoardCollectionPort;
    @Override
    public void save(Posting posting) {
        BoardCollection boardCollection
                = loadBoardCollectionPort.load(posting.getPostingId());
        boardCollection.add(posting);
        // 특유의 비즈니스로직을 거친 이후
        savePostingPersistencePort.save(boardCollection.getAddedPosting());
    }

    @Override
    public void update(Posting after) {
        BoardCollection boardCollection
                = loadBoardCollectionPort.load(after.getPostingId());
        boardCollection.update(after);
        // 특유의 비즈니스로직을 거친 후
        changePostingPersistencePort.update(boardCollection.getUpdatedPosting());
    }



}
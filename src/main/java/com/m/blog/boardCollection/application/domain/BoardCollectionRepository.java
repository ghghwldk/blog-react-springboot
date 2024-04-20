package com.m.blog.boardCollection.application.domain;

public interface BoardCollectionRepository {
    Posting getUpdatedPosting();

    Posting getAddedPosting();

    void add(Posting posting);

    void update(Posting posting);
}

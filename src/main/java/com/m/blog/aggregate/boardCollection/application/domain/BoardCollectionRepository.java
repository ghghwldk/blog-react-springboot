package com.m.blog.aggregate.boardCollection.application.domain;

public interface BoardCollectionRepository {
    Posting getUpdatedPosting();

    Posting getAddedPosting();

    void add(Posting posting);

    void update(Posting posting);
}

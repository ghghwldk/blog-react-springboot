package com.m.blog.aggregate.boardCollection.application.domain;

public interface BoardCollectionRepository {
    Posting getUpdated();

    Posting getAdded();

    void add(Posting posting);

    void update(Posting posting);
}

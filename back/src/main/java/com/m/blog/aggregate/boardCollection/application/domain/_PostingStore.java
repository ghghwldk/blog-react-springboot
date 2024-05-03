package com.m.blog.aggregate.boardCollection.application.domain;

import com.m.blog.aggregate.posting.application.domain.Posting;
import com.m.blog.global.exception.AlreadyExistException;
import com.m.blog.global.exception.DataNotFoundException;
import lombok.NonNull;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class _PostingStore {
    @NonNull private final List<Posting> data;

    @NonNull private final List<Posting.PostingId> savedIds = new LinkedList<>();
    @NonNull private final List<Posting.PostingId> updatedIds = new LinkedList<>();
    @NonNull private final List<Posting.PostingId> deletedIds = new LinkedList<>();


    public _PostingStore(@NonNull List<Posting> data){
        this.data = data;
    }

    void delete(@NonNull Posting.PostingId postingId) {
        if (!data.removeIf(e -> e.getPostingId().equals(postingId))) {
            throw new DataNotFoundException("target posting doesn't exist");
        }

        deletedIds.add(postingId);
    }

    void save(@NonNull Posting posting){
        List<Posting.PostingId> ids =  data.stream().map(Posting::getPostingId).collect(Collectors.toList());

        if(ids.contains(posting.getPostingId())){
            throw new AlreadyExistException();
        }

        data.add(posting);
        savedIds.add(posting.getPostingId());
    }

    void update(@NonNull Posting after){
        Posting found = this.data.stream()
                .filter(e->e.getPostingId().equals(after.getPostingId()))
                .findAny()
                .orElseThrow(DataNotFoundException::new);

        found.update(after);

        savedIds.removeIf(e-> e.equals(after.getPostingId()));
        updatedIds.add(after.getPostingId());
    }


    List<Posting> getSavedPostings(){
        return data.stream()
                .filter(e-> savedIds.contains(e.getPostingId()))
                .collect(Collectors.toList());
    }

    List<Posting> getUpdatedPostings(){
        return data.stream()
                .filter(e-> updatedIds.contains(e.getPostingId()))
                .collect(Collectors.toList());
    }
}

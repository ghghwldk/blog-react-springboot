package com.m.blog.aggregate.boardCollection.application.domain;

import com.amazonaws.services.kms.model.NotFoundException;
import com.m.blog.global.exception.DataNotFoundException;
import com.m.blog.global.exception.MultipleChangedException;
import com.m.blog.global.exception.NothingChangedException;
import lombok.NonNull;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PostingWindow {
    @NonNull private final List<Posting> postings;

    PostingWindow(@NonNull List<Posting> postings){
        this.postings = postings;
    }

    String remove(@NonNull Posting.PostingId postingId) {
        if (!postings.removeIf(e -> e.getPostingId().getValue().equals(postingId.getValue()))) {
            throw new NotFoundException("target posting doesn't exist");
        }

        return postingId.getValue();
    }

    String add(@NonNull Posting posting){
        postings.add(posting);

        return posting.getPostingId().getValue();
    }

    void update(@NonNull Posting after){
        Posting found = this.postings.stream()
                .filter(e->e.getPostingId().equals(after.getPostingId()))
                .findAny()
                .orElseThrow(DataNotFoundException::new);

        found.update(after);
    }

    Posting getUpdatedPosting(){
        List<Posting> changeds = postings.stream().filter(Posting::isPostingUpdated).collect(Collectors.toList());
        if(changeds.size()==0){
            throw new NothingChangedException();
        }
        if(changeds.size()>0){
            throw new MultipleChangedException();
        }

        return changeds.get(0);
    }

    Posting getAddedPosting(){
        List<Posting> addeds = postings.stream().filter(Posting::isPostingAdded).collect(Collectors.toList());
        if(addeds.size()==0){
            throw new NothingChangedException();
        }
        if(addeds.size()>0){
            throw new MultipleChangedException();
        }

        return addeds.get(0);
    }
}

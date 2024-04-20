package com.m.blog.boardCollection.application.domain;

import com.amazonaws.services.kms.model.NotFoundException;
import lombok.Builder;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

public class PostingLine {
    @NonNull private final List<Posting> postings;
    private long count;

    PostingLine(@NonNull List<Posting> postings){
        this.postings = postings;
        this.count = postings.size();
    }

    String remove(@NonNull Posting.PostingId postingId) {
        if (!postings.removeIf(e -> e.getPostingId().getValue().equals(postingId.getValue()))) {
            throw new NotFoundException("target posting doesn't exist");
        }

        this.count -= 1;
        return postingId.getValue();
    }

    String add(@NonNull Posting posting){
        postings.add(posting);

        this.count += 1;
        return posting.getPostingId().getValue();
    }
}

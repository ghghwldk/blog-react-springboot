package com.m.blog.boardCollection.application.domain;

import com.amazonaws.services.kms.model.NotFoundException;
import lombok.Builder;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Builder
@RequiredArgsConstructor
public class PostingLine {
    @NonNull private final List<Posting> postings;

    String remove(@NonNull Posting.PostingId postingId) {
        boolean removed = postings.removeIf(posting -> posting.getPostingId().equals(postingId));
        if (!removed) {
            throw new NotFoundException("Target board doesn't exist");
        }
        // some additional business logic if needed
        return postingId.getValue();
    }
}

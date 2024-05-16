package com.m.blog.aggregate.posting.application.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostingId {
    private String value;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof PostingId))
            return false;

        return this.value.equals(((PostingId) o).getValue());
    }
}
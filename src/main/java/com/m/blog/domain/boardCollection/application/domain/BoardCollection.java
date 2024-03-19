package com.m.blog.domain.boardCollection.application.domain;

import com.m.blog.domain.boardCollection.infrastructure.repository.BoardAggregationDto;
import com.m.blog.domain.boardCollection.infrastructure.repository.BoardCollectionEntity;
import com.m.blog.domain.menu.infrastructure.web.dto.MenuResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
public class BoardCollection {
    private BoardCollectionId key;
    private String name;

    @Data
    @Builder
    @AllArgsConstructor
    public static class BoardCollectionId{
        private int id;
    }

}

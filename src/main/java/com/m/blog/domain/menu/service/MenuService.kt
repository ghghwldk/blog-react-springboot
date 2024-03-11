package com.m.blog.domain.menu.service

import com.m.blog.domain.boardCollection.application.port.persistence.GetBoardCollectionPort
import com.m.blog.domain.menu.infrastructure.web.dto.MenuResponse
import com.m.blog.domain.menu.application.usecase.MenuUsecase
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
open class MenuService(private val getBoardCollectionQuery: GetBoardCollectionPort) : MenuUsecase {

    override fun get(): MenuResponse {
        return MenuResponse.of(
                getBoardCollectionQuery.getBoardCollectionEntities(),
                getBoardCollectionQuery.getBoardAggregationDtos()
        )
    }
}

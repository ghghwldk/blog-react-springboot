package com.m.blog.domain.posting.application.service;

import com.m.blog.domain.board.application.port.out.BoardDto;
import com.m.blog.domain.board.application.port.out.GetBoardQuery;
import com.m.blog.domain.posting.application.port.persistence.PostingService;
import com.m.blog.domain.posting.infrastructure.web.dto.*;
import com.m.blog.domain.posting.infrastructure.repository.PostingEntity;
import com.m.blog.domain.posting.infrastructure.repository.PostingDslRepository;
import com.m.blog.domain.posting.infrastructure.repository.PostingJpaRepository;
import com.m.blog.global.paging.PagingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostingServiceImpl implements PostingService {

}
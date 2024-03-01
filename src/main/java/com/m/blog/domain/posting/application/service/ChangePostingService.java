package com.m.blog.domain.posting.application.service;

import com.m.blog.domain.posting.application.usecase.ChangePostingUsecase;
import com.m.blog.domain.posting.application.usecase.FindPostingUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChangePostingService implements ChangePostingUsecase {

}
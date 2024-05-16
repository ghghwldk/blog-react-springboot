package com.m.blog;

import com.m.blog.aggregate.file.application.domain.File_;
import com.m.blog.aggregate.file.helper.FileCheckHelper;
import com.m.blog.aggregate.posting.application.domain.Posting;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;


//@SpringBootTest
class PojoTest {

    private static final String downloadPrefix = File_.getDownloadPrefix();

    private static Posting originalPosting = null;
    private static Posting updatedPosting = null;

    @BeforeEach
    public void setData(){
        originalPosting = PostingTestData.provideOriginal();
        updatedPosting = PostingTestData.provideUpdated();
    }

	@Test
    public void test(){

        List<File_.FileId> previous
                = Posting.getFileIdsInsideContent(originalPosting.getContent(), downloadPrefix);
        List<File_.FileId> updated
                = originalPosting.update(updatedPosting, downloadPrefix);

        List<File_.FileId> deleteTargets = File_.getDeleteTargets(updated, previous);
        Assertions.assertThat(deleteTargets.size()).isEqualTo(1);
    }

}



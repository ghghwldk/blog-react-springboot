package com.m.blog;

import com.m.blog.global.entity.SnowflakeIdGenerator;
import org.junit.jupiter.api.Test;

public class IdGeneratortTester {
    @Test
    public void generate(){
        for(int i=0; i<10; i++){
            System.out.println(SnowflakeIdGenerator.generateId());
        }
    }
}

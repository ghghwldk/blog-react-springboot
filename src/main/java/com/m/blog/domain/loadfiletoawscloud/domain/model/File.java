package com.m.blog.domain.loadfiletoawscloud.domain.model;


import com.m.blog.domain.loadfiletoawscloud.util.FileNature;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public class File {
    String name;
    String contentType;
    Long size;
    FileNature fileNature;
    byte[] data;
    String uuidAwsFile;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        File file = (File) o;
        return name.equals(file.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "File{" +
                "name='" + name + '\'' +
                ", contentType='" + contentType + '\'' +
                ", size=" + size +
                ", fileNature=" + fileNature +
                ", data=" + Arrays.toString(data) +
                ", uuidAwsFile='" + uuidAwsFile + '\'' +
                '}';
    }
}

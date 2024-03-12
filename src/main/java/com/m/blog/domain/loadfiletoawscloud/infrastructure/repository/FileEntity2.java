package com.m.blog.domain.loadfiletoawscloud.infrastructure.repository;

import com.m.blog.domain.loadfiletoawscloud.application.domain.FileNature;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name="file")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class FileEntity2 {
    @Id
    @GeneratedValue(generator = "uuid")
//    @GenericGenerator(name = "uuid")
    private Integer id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="contenttype", nullable = false)
    private String contentType;

    @Column(name="size", nullable = false)
    private Long size;

    @Column(name = "filenature", nullable = false)
    private FileNature fileNature;

    @Lob
    @Column(name="data", nullable = false)
    private byte[] data;

    @Column(name="uuid_aws", nullable = false)
    private String uuidAwsFile;
}

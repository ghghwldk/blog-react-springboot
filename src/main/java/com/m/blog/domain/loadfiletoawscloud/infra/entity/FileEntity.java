package com.m.blog.domain.loadfiletoawscloud.infra.entity;

import com.m.blog.domain.loadfiletoawscloud.util.FileNature;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name="file")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class FileEntity {
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

package com.clone_coding.danggeon.service;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@NoArgsConstructor
public class S3Service {
    private AmazonS3 s3Client;

//    @Value("${cloud.aws.credentials.accessKey}")
    @Value("AKIASMHMW5M2RB6R6NFB")
//    @Value("AKIAWXMOLSDH2F7RUZUH")
    private String accessKey;

    @Value("rmXhmQbSZUoNDs+e4RbkBiLdws4PFTErfPs9I7/k")
//    @Value("7Mf00O8OIM1avgIl+7Zv6f35GRoYORryiB+A5+Ex")
    private String secretKey;

    @Value("clonedanggeon")
//    @Value("clonefront")
    private String bucket;

    @Value("ap-northeast-2")
    private String region;

    @PostConstruct
    public void setS3Client() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);

        s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(this.region)
                .build();
    }

    public String upload(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename().replace(" ","");
        Date date_now = new Date(System.currentTimeMillis()); // 현재시간을 가져와 Date형으로 저장한다
        SimpleDateFormat fourteen_format = new SimpleDateFormat("yyyyMMddHHmmss");
        System.out.println(fourteen_format.format(date_now)); // 14자리 포멧으로 출력한다
        fileName = fourteen_format.format(date_now) + fileName;
        System.out.println(fileName);

        s3Client.putObject(new PutObjectRequest(bucket, fileName, file.getInputStream(), null)
                .withCannedAcl(CannedAccessControlList.PublicRead));
        return s3Client.getUrl(bucket, fileName).toString();
    }
}
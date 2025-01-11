package com.example.decofolio.global.s3;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Config {

    private final String accessKey = System.getenv("AWS_ACCESS_KEY");  // 환경 변수로 대체
    private final String secretKey = System.getenv("AWS_SECRET_KEY");  // 환경 변수로 대체
    private final String region = "ap-northeast-2";
    private final String endpoint = "https://kr.object.ncloudstorage.com";

    @Bean
    public AmazonS3 amazonS3() {
        if (accessKey == null || secretKey == null) {
            throw new IllegalStateException("AWS credentials are not set in environment variables.");
        }

        BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withEndpointConfiguration(
                        new AmazonS3ClientBuilder.EndpointConfiguration(endpoint, region)
                )
                .build();
    }
}

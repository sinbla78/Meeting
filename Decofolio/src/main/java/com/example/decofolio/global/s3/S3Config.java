package com.example.decofolio.global.s3;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Config {

    private final String accessKey = "ncp_iam_BPAMKRTv7SxDriwViICY";
    private final String secretKey = "ncp_iam_BPKMKR42wzQDcpP2os3KJiX4gxN2f9E8qi";
    private final String region = "ap-northeast-2";
    private final String endpoint = "https://kr.object.ncloudstorage.com";

    @Bean
    public AmazonS3 amazonS3() {
        BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withEndpointConfiguration(
                        new AmazonS3ClientBuilder.EndpointConfiguration(endpoint, region)
                )
                .build();
    }
}
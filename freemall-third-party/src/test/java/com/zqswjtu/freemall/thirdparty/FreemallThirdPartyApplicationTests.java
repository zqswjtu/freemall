package com.zqswjtu.freemall.thirdparty;

import com.aliyun.oss.OSSClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.InputStream;

@SpringBootTest
class FreemallThirdPartyApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    OSSClient oss;
    @Test
    void test() throws Exception {
        // 文件上传流
        InputStream inputStream = new FileInputStream("E:\\Recruitment\\Project\\freemall\\freemall-product\\src\\main\\resources\\static\\imgs\\Zelda.png");
        oss.putObject("freemall-bucket", "Zelda.png", inputStream);
        // 关闭OSSClient
        oss.shutdown();
        System.out.println("上传成功!");
    }

}

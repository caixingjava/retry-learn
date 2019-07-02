package com.caixing.learn.retrylearn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Retryable;

import java.time.LocalDateTime;

@SpringBootApplication
@EnableRetry
public class RetryLearnApplication {

    /**
     * value:指定捕捉的异常
     * maxAttempts:最大重试次数
     * backoff:重试策略
     * delay:第一次重试时间
     * multiplier:每次重试时间的倍数
     * 例如此处倍数设置为2，重试次数（包括首次执行）为4，那么第一次重试间隔为1s,第二次重试间隔为2s（翻2倍），第三次为4s（翻2倍）
     *
     */
    @Retryable(value = Exception.class,maxAttempts = 4,backoff = @Backoff(delay = 1000L,multiplier = 2))
    public void test(){
        System.err.println("开始重试:"+ LocalDateTime.now().getSecond());
        System.out.println(1/0);
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(RetryLearnApplication.class, args);
        RetryLearnApplication retryLearnApplication = applicationContext.getBean(RetryLearnApplication.class);
        retryLearnApplication.test();
    }

}

package wind.yang.jojolduBook;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements ApplicationRunner {
    @Value("${spring.security.oauth2.client.registration.naver.client-id}")
    String clientId;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("네이버 클라이언트ID : " + clientId);
    }
}

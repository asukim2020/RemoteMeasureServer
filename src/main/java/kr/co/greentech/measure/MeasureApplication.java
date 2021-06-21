package kr.co.greentech.measure;

import kr.co.greentech.measure.util.FileUploadProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
		FileUploadProperties.class
})

public class MeasureApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeasureApplication.class, args);
	}

}

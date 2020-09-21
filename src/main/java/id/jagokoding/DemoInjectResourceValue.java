package id.jagokoding;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

public class DemoInjectResourceValue {

	public static void main(String[] args) throws IOException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
		ClientBean bean = context.getBean(ClientBean.class);
		bean.doSomething();
		;
	}

	@Configuration
	public static class Config {

		@Bean
		public ClientBean clientBean() {
			return new ClientBean();
		}

	}

	private static class ClientBean {
		@Value("classpath:hello.txt")
		private Resource myResource;

		public void doSomething() throws IOException {
			File file = myResource.getFile();
			String s = new String(Files.readAllBytes(file.toPath()));
			System.out.println(s);
		}
	}

}

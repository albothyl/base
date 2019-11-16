package base.configuration;

import com.fasterxml.classmate.TypeResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Value("${server.port}")
	int port;

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
			.title("Base Project API")
			.termsOfServiceUrl("https://open.kakao.com/o/gvN9WV0")
			.contact(new Contact("Gwacheon Study KakaoTalk", "https://open.kakao.com/o/gvN9WV0", "jjhwqqq@naver.com"))
			.version("0.0.1-SNAPSHOT")
			.build();
	}

	@Bean
	public Docket getDocketDefault() throws UnknownHostException {
		TypeResolver tr = new TypeResolver();
		return new Docket(DocumentationType.SWAGGER_2)
			.host(InetAddress.getLocalHost().getHostAddress() + ":" + port)
			.pathMapping("/")
			.alternateTypeRules(
				AlternateTypeRules.newRule(
					tr.resolve(Map.class, WildcardType.class, tr.resolve(Collection.class, WildcardType.class)),
					tr.resolve(Map.class, WildcardType.class, tr.resolve(List.class, WildcardType.class))
				)
			)
			.forCodeGeneration(true)  //mandatory
			.apiInfo(apiInfo());
	}
}

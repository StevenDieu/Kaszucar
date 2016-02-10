package kaszucar.util;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.trimou.spring.web.view.TrimouViewResolver;


@Controller
public class ResolverTrimou {

	@Bean
	 public TrimouViewResolver trimouViewResolver() {
        TrimouViewResolver resolver = new TrimouViewResolver();
        resolver.setPrefix("/WEB-INF/html/");
        resolver.setSuffix("html");
        resolver.setCache(false);
        resolver.setContentType("text/html;charset=utf-8");

        return resolver;
    }
}
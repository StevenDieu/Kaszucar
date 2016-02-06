package kaszucar.util;

import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.mustache.MustacheTemplateLoader;
import org.springframework.web.servlet.view.mustache.MustacheViewResolver;

@Controller
public class ResolverMustache {

	@Bean
	public ViewResolver getViewResolver(ResourceLoader resourceLoader) {
	    MustacheViewResolver mustacheViewResolver = new MustacheViewResolver();
	    mustacheViewResolver.setPrefix("/WEB-INF/html/");
	    mustacheViewResolver.setSuffix(".html");
	    mustacheViewResolver.setCache(false);
	    mustacheViewResolver.setContentType("text/html;charset=utf-8");
	    
	    MustacheTemplateLoader mustacheTemplateLoader = new MustacheTemplateLoader();
	    mustacheTemplateLoader.setResourceLoader(resourceLoader);

	    mustacheViewResolver.setTemplateLoader(mustacheTemplateLoader);
	    return mustacheViewResolver;
	}
}
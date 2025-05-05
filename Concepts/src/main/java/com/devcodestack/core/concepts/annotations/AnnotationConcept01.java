package com.devcodestack.core.concepts.annotations;

/*
 * Mandatory and commonly used annotation in a spring boot application
 * 
 * Reference: https://www.baeldung.com/spring-boot-annotations
 */
public class AnnotationConcept01 {

	public static void main(String[] args) {
		
		String starter = "@SpringBootApplication";
		String[] embeddedStarter = new String[] {"@Configuration", "@EnableAutoConfiguration", "@ComponentScan"};
		
		/*
		 * Understanding
		 * 
		 * @Configuration marks a class holding necessary user config's
		 * @EnableAutoConfiguration ask the spring boot to look for classes marked with @Configuration
		 * @ComponentScan ask the spring boot to look for classes marked with @Component or its flavor
		 * 
		 */
		
	}
}

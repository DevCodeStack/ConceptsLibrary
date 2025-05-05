package com.devcodestack.core.concepts.annotations;

/*
 * Various conditional annotation in a spring boot application
 * 
 * Reference: https://www.baeldung.com/spring-boot-annotations
 */
public class AnnotationConcept02 {

	public static void main(String[] args) {
		
		conditional();
	}
	
	public static void conditional() {
		
		/* Conditions based on class in the annotation’s argument is present/absent: */
		String[] classWithin = new String[] {"@ConditionalOnClass", "@ConditionalOnMissingClass"};
		
		/* Conditions based on the presence or absence of a specific bean */
		String[] beanWithin = new String[] {"@ConditionalOnBean", "@ConditionalOnMissingBean"};
		
		/* Conditions on the values of properties */
		/*
		 * 
		    @Bean
			@ConditionalOnProperty(
			    name = "usemysql", 
			    havingValue = "local"
			)
		 *
		 */
		String propertyMatch = "@ConditionalOnProperty";
		
		/* Conditions when a specific resource is present */
		/*
		 * 
		    @ConditionalOnResource(resources = "classpath:mysql.properties")
			Properties additionalProperties() {
			    // ...
			}
		 *
		 */
		String resourceMatch = "@ConditionalOnResource";
		
		/* Conditions if the current application is or isn’t a web application */
		String[] webMatch = new String[] {"@ConditionalOnWebApplication ", "@ConditionalOnNotWebApplication"};
		
		/* Conditions when the SpEL expression is evaluated to true */
		String conditionMatch = "@ConditionalExpression";
		
		/* Conditions by defining a class evaluating the custom condition */
		/*
		 * 
		    @Conditional(HibernateCondition.class)
			Properties additionalProperties() {
			    //...
			}
		 *
		 */
		String conditionalMatch = "@Conditional";
		
	}

}

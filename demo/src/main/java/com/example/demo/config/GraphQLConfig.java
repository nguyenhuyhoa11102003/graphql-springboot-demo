package com.example.demo.config;


import graphql.schema.GraphQLScalarType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import graphql.scalars.ExtendedScalars;

@Configuration
public class GraphQLConfig {
	@Bean
	public GraphQLScalarType longScalar() {
		return ExtendedScalars.GraphQLLong;
	}
}
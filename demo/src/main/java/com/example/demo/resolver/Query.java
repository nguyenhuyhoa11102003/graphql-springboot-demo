package com.example.demo.resolver;

import com.example.demo.model.Author;
import com.example.demo.model.Tutorial;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.TutorialRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Query implements GraphQLQueryResolver {
	private final AuthorRepository authorRepository;
	private final TutorialRepository tutorialRepository;


	@Autowired
	public Query(AuthorRepository authorRepository, TutorialRepository tutorialRepository) {
		this.authorRepository = authorRepository;
		this.tutorialRepository = tutorialRepository;
	}


	GraphQLScalarType longScalar = ExtendedScalars.newAliasedScalar("Long")
			.aliasedScalar(ExtendedScalars.GraphQLLong)
			.build();

	public Iterable<Author> findAllAuthors() {
		return authorRepository.findAll();
	}

	public Iterable<Tutorial> findAllTutorials() {
		return tutorialRepository.findAll();
	}

	public long countAuthors() {
		return authorRepository.count();
	}

	public long countTutorials() {
		return tutorialRepository.count();
	}


}

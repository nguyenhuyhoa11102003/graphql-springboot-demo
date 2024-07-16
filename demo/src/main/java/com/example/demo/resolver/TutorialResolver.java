package com.example.demo.resolver;

import com.example.demo.model.Author;
import com.example.demo.model.Tutorial;
import com.example.demo.repository.AuthorRepository;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class TutorialResolver implements GraphQLResolver<Tutorial> {
	private final AuthorRepository authorRepository;
	public Author getAuthor(Tutorial tutorial) {
		return authorRepository.findById(tutorial.getAuthor().getId()).orElseThrow(null);
	}
}

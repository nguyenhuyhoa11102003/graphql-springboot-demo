package com.example.demo.resolver;

import com.example.demo.model.Author;
import com.example.demo.model.Tutorial;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.TutorialRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jakarta.persistence.EntityNotFoundException;

import java.util.Optional;

@Component
public class Mutation implements GraphQLMutationResolver {
	private AuthorRepository authorRepository;
	private TutorialRepository tutorialRepository;

	@Autowired
	public Mutation(AuthorRepository authorRepository, TutorialRepository tutorialRepository) {
		this.authorRepository = authorRepository;
		this.tutorialRepository = tutorialRepository;
	}

	public Author createAuthor(String name, Integer age) {
		Author author = new Author();
		author.setName(name);
		author.setAge(age);

		authorRepository.save(author);
		return author;
	}

	public Tutorial createTutorial(String title, String description, Long authorId) {
		Tutorial tutorial = new Tutorial();
		tutorial.setAuthor(authorRepository.findById(authorId).get());
		tutorial.setTitle(title);
		tutorial.setDescription(description);
		tutorialRepository.save(tutorial);
		return tutorial;
	}

	public boolean deleteTutorial(Long id) {
		tutorialRepository.deleteById(id);
		return true;
	}

	public Tutorial updateTutorial(Long id, String title, String description) throws EntityNotFoundException {
		Optional<Tutorial> optTutorial = tutorialRepository.findById(id);
		if (optTutorial.isPresent()) {
			Tutorial tutorial = optTutorial.get();
			if (title != null)
				tutorial.setTitle(title);
			if (description != null)
				tutorial.setDescription(description);

			tutorialRepository.save(tutorial);
			return tutorial;
		}
		throw new EntityNotFoundException("Not found Tutorial to update!");
	}
}

package com.codingdojo.dojooverflow.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codingdojo.dojooverflow.models.Answer;
import com.codingdojo.dojooverflow.repositories.AnswerRepository;


@Service
public class AnswerService {
	private final AnswerRepository answerRepository;
	
	public AnswerService(AnswerRepository answerRepository) {
		this.answerRepository = answerRepository;
	}
	
	public Answer createAnswer(Answer a) {
		return answerRepository.save(a);
	}
	
	public Answer findAnswer(Long id) {
		Optional<Answer> optionalAnswer = answerRepository.findById(id);
		if(optionalAnswer.isPresent()) {
			return optionalAnswer.get();
		} else {
			return null;
		}
	}
	
	public Answer getAnswerByContent(String content) {
		return answerRepository.findAnswerByContent(content);
	}
}

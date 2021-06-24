package com.codingdojo.dojooverflow.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codingdojo.dojooverflow.models.Answer;
import com.codingdojo.dojooverflow.models.Question;
import com.codingdojo.dojooverflow.models.Tag;
import com.codingdojo.dojooverflow.repositories.QuestionRepository;
import com.codingdojo.dojooverflow.repositories.TagRepository;

@Service
public class QuestionService {
	
	private final QuestionRepository questionRepository;
	private final TagRepository tagRepository;
	
	public QuestionService(QuestionRepository questionRepository, TagRepository tagRepository) {
		this.questionRepository = questionRepository;
		this.tagRepository = tagRepository;
	}
	
	public List<Question> allQuestions() {
		return questionRepository.findAll();
	}
	
	public Question findQuestion(Long id) {
        Optional<Question> optionalQuestion = questionRepository.findById(id);
        if(optionalQuestion.isPresent()) {
            return optionalQuestion.get();
        } else {
            return null;
        }
    }
	
	public Question createQuestion(Question q) {
		return questionRepository.save(q);
	}
	
	public Tag createOrRetrieveTag(String subject) {
		Optional<Tag> optionalTag = tagRepository.findTagBySubject(subject);
		if(optionalTag.isPresent()) {
			return optionalTag.get();
		} else {
			return tagRepository.save(new Tag(subject));
		}
	}
	
	public Question createQuestionWithTags(Question question) {
		List<Tag> tags = new ArrayList<Tag>();
		
		for(String tagSubject : question.getTagsInput().split(",")) {
			tags.add(createOrRetrieveTag(tagSubject));
		}
		question.setTags(tags);
		return questionRepository.save(question);
	}
	
	public Question addAnswerToQuestion(Question q, Answer a) {
		List<Answer> answers = q.getAnswers();
		answers.add(a);
		return questionRepository.save(q);
	}
	
	
	public Question updateQuestion(Long id, String text, String tagsInput) {
		Question q = this.findQuestion(id);
		
		q.setId(id);
		q.setText(text);
		q.setTagsInput(null);
		q.setTagsInput(tagsInput);
		
		return questionRepository.save(q);
	}
	
	public void deleteQuestion(Long id) {
    	questionRepository.deleteById(id);
    }
	
}

package com.codingdojo.dojooverflow.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.codingdojo.dojooverflow.models.Answer;
import com.codingdojo.dojooverflow.models.Question;
import com.codingdojo.dojooverflow.services.AnswerService;
import com.codingdojo.dojooverflow.services.QuestionService;
import com.codingdojo.dojooverflow.services.TagService;

@Controller
public class RenderingController {
	
	private final QuestionService questionService;
	private final AnswerService answerService;
	private final TagService tagService;
	
	public RenderingController(QuestionService questionService, AnswerService answerService, TagService tagService) {
		this.questionService = questionService;
		this.answerService = answerService;
		this.tagService = tagService;
	}
	
	
	@RequestMapping("/")
	public String index() {
		return "redirect:/questions";
	}
	
	@RequestMapping("/questions")
	public String questions(Model model) {
		List<Question> questions = questionService.allQuestions();
		model.addAttribute("questions", questions);
		return "/allQuestions.jsp";
	}
	
	@RequestMapping("/questions/new")
	public String newQuestion(@ModelAttribute("question") Question question) {
		return "/newQuestion.jsp";
	}
	
	@PostMapping("/questions")
	public String create(@Valid @ModelAttribute("question") Question question, BindingResult result) {
		if(result.hasErrors()) {
			return "/newQuestion.jsp";
		} else {
			questionService.createQuestionWithTags(question);
			return "redirect:/questions";
		}
	}
	
	@RequestMapping("/questions/{id}")
	public String showQuestion(@PathVariable("id") Long id, @ModelAttribute("answer") Answer answer, Model model) {
		Question question = questionService.findQuestion(id);
		List<Answer> answers = question.getAnswers();
		model.addAttribute("answers", answers);
		model.addAttribute("question", question);
		return "/showQuestion.jsp";
	}
	
	@PostMapping("/questions/{questionId}/answer")
	public String addAnswer(@PathVariable("questionId") Long questionId, @Valid @ModelAttribute("answer") Answer answer, BindingResult result) {
//		Question q = questionService.findQuestion(id);
//		Answer a = answerService.getAnswerByContent(content);
		
		if(result.hasErrors()) {
			return "/showQuestion.jsp";
		} else {
			System.out.println("Inside create answer.");
			Answer newAnswer = answerService.createAnswer(answer);
//			answerService.createAnswer(a);
			System.out.println(newAnswer.getQuestion());
//			questionService.addAnswerToQuestion(q, a);
			return "redirect:/questions/{questionId}";
		}
	}
	
	@RequestMapping("/questions/{id}/edit")
	public String edit(@PathVariable("id") Long id, Model model) {
		Question q = questionService.findQuestion(id);
		model.addAttribute("question", q);
		return "/editQuestion.jsp";
	}
	
	@RequestMapping(value="/questions/{id}", method=RequestMethod.PUT) 
	public String update(@Valid @ModelAttribute("question") Question question, BindingResult result) {
		if(result.hasErrors()) {
			return "/editQuestion.jsp";
		} else {
			questionService.updateQuestion(question.getId(), question.getText(), question.getTagsInput());
			return "redirect:/questions";
		}
		
	}
	
	@RequestMapping("/questions/{id}/delete")
	public String destroy(@PathVariable("id") Long id) {
		questionService.deleteQuestion(id);
		return "redirect:/questions";
	}
	
	
}

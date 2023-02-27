package com.AwsKCapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AwsKCapp.model.QuestionForm;
import com.AwsKCapp.model.Result;
import com.AwsKCapp.model.UniqueSetForm;
import com.AwsKCapp.service.QuizService;



@Controller
public class MainController {
	
	@Autowired
	Result result;
	@Autowired
	QuizService quizService;
	boolean submitted = false;
	
	@ModelAttribute("result")
	public Result getResult() {
		return result;
	}
	
	@GetMapping("/")
	public String home() {
		return "index.html";
	}
	
	
	  @PostMapping("/loadQuizContent") 
	  public String loadQuizContent(@RequestParam  String username, Model m , RedirectAttributes ra) {
		  if(username.equals(""))
		  {
	       ra.addFlashAttribute("warning", "you must enter your name"); 
	       return  "redirect:/"; 
	       } 
		  submitted=false;
		  result.setUsername(username); 
		  UniqueSetForm uniForm = quizService.getAllUniqueQueCont();
	      m.addAttribute("uniForm",  uniForm);
	      return "loadQuizContent.html";
	  }
	 
	
	
	  @PostMapping("/quiz")
	  public String quiz(@ModelAttribute UniqueSetForm uniForm, Model m)
	  {
	    QuestionForm qForm = quizService.getQuestions(uniForm); 
	    m.addAttribute("qForm", qForm); 
	  //return "quiz.html"; 
	  return "awsQuiz.html";
	  }
	 
	/*
	 * @PostMapping("/quiz") public String quiz(@RequestParam String username, Model
	 * m , RedirectAttributes ra) { if(username.equals("")) {
	 * ra.addFlashAttribute("warning", "you must enter your name"); return
	 * "redirect:/"; } submitted=false; result.setUsername(username); QuestionForm
	 * qForm = quizService.getQuestions(); m.addAttribute("qForm", qForm); //return
	 * "quiz.html"; return "awsQuiz.html"; }
	 */
	@PostMapping("/submit")
	public String submit(@ModelAttribute QuestionForm qForm, Model m) {
		if(qForm.getQuestions() == null)submitted = true;
		if(!submitted) {
			result.setTotalCorrect(quizService.getResult(qForm));
			quizService.saveScore(result);
			submitted = true;
		}
		else {
			result.setTotalCorrect(0);
			quizService.saveScore(result);
			submitted = true;
		}
		return "result.html";
	}
	@GetMapping("/score")
	public String score(Model m) {
		List<Result> sList = quizService.getTopScore();
		m.addAttribute("sList", sList);
		return "scoreboard.html";
	}

}

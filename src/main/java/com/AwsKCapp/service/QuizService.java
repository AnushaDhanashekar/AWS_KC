package com.AwsKCapp.service;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.AwsKCapp.model.Question;
import com.AwsKCapp.model.QuestionForm;
import com.AwsKCapp.model.Result;
import com.AwsKCapp.model.UniQueSet;
import com.AwsKCapp.model.UniqueSetForm;
import com.AwsKCapp.repository.QuestionRepo;
import com.AwsKCapp.repository.ResultRepo;
import com.AwsKCapp.repository.UniQueSetRepo;

import net.bytebuddy.asm.Advice.This;

@Component
public class QuizService {
    @Autowired
	Question question;
    @Autowired
    QuestionForm qForm;
    @Autowired
    UniqueSetForm uForm;
    @Autowired
    QuestionRepo qRepo;
    @Autowired
    Result result;
    @Autowired
    ResultRepo rRepo;
    @Autowired
    UniQueSetRepo uRepo;
    
	
	  public QuestionForm getQuestions(UniqueSetForm uniForm)
	  { 
		  Optional<UniQueSet> loadQuizCont = uniForm.getLoadQuizContent().stream().filter(x->x.getChoosenIndex()!=0).findFirst();
	    List<Question> allQues = qRepo.findByUniQueSet(loadQuizCont..getUniQueSet());
	    List<Question> qList = new ArrayList<Question>();
	  
	  Random random = new Random(); 
	  for(int i=0; i<5; i++) 
	  { 
		int rand = random.nextInt(allQues.size());
		qList.add(allQues.get(rand));
	    allQues.remove(rand);
	  }
	  qForm.setQuestions(qList); 
	  return qForm;
	  }
	 
	/*
	 * public QuestionForm getQuestions() {
	 * 
	 * List<Question> allQues = (List<Question>) qRepo.findAll(); List<Question>
	 * qList = new ArrayList<Question>();
	 * 
	 * Random random = new Random(); for(int i=0; i<5; i++) { int rand =
	 * random.nextInt(allQues.size()); qList.add(allQues.get(rand));
	 * allQues.remove(rand); } qForm.setQuestions(qList); return qForm; }
	 */
    
	
	  public UniqueSetForm getAllUniqueQueCont()
	  {
		  List<UniQueSet> uniQueSets = (List<UniQueSet>) uRepo.findAll();
		  uForm.setLoadQuizContent(uniQueSets);
		
		  return uForm;

	  }
	 
    
		/*
		 * public LoadQuizContent getUniqueSet(Question question) { LoadQuizContent
		 * quizContent = new LoadQuizContent();
		 * quizContent.setUniQueSet(question.getUniQueSet());
		 * quizContent.setTitle(question.getTitle());
		 * 
		 * return quizContent; }
		 */
    
    public int getResult(QuestionForm qForm) {
    	int correct = 0;
    	List<Question> allQues = (List<Question>) qRepo.findAll();
    	for(Question q: qForm.getQuestions())
    	{
    		if(allQues.get(q.getQues_id()).getAnswer() == q.getChoose())
    			correct++;
    	}
    	return correct;
    }
    
    public void saveScore(Result result) {
    	Result saveResult = new Result();
    	saveResult.setUsername(result.getUsername());
    	saveResult.setTotalCorrect(result.getTotalCorrect());
    	rRepo.save(saveResult);   	
    }
    
    public List<Result> getTopScore(){
    	List<Result> sList = (List<Result>) rRepo.findAll();
    	return sList;
    }
}

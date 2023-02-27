package com.AwsKCapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.AwsKCapp.model.Question;
import com.AwsKCapp.model.UniqueSetForm;

public interface QuestionRepo extends CrudRepository<Question, Integer> {
	

	List<Question> findByUniQueSet(int uniQueSet);
	
	

}

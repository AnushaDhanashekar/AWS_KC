package com.AwsKCapp.model;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="questions")
@Component
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ques_id;
	/*
	 * private int uniQueSet; private String title;
	 */
	private String actualQuestion;
	private String optionA;
	private String optionB;
	private String optionC;
	private String optionD;
	private int answer;
	private int choose;
	
    
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="fk_uni_que_set")
	private UniQueSet uniQueSet;
}

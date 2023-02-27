package com.AwsKCapp.model;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class UniqueSetForm {
	private List<UniQueSet> loadQuizContent;
}

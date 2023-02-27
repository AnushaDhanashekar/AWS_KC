package com.AwsKCapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@Table(name="uniquesets")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UniQueSet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="uni_que_pk")
	private int uniQueSet;
	private String Title;
	@Transient
	private int choosenIndex;
	
	/*
	 * @OneToOne(mappedBy="uniQueSet") private Question question;
	 */
}

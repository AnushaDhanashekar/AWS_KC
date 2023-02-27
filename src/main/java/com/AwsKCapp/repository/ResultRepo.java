package com.AwsKCapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.AwsKCapp.model.Result;

public interface ResultRepo extends CrudRepository<Result, Integer> {

}

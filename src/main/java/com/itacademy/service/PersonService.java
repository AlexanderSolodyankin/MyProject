package com.itacademy.service;

import com.itacademy.entity.Person;
import com.itacademy.entity.UserEntity;

import java.util.List;

public interface PersonService {
    List<Person> getAllPerson();
    Person save(Person person);
    Person delete(UserEntity userEntity);
    Person getPerson(UserEntity userEntity);
}

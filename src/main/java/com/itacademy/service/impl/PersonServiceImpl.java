package com.itacademy.service.impl;

import com.itacademy.entity.Person;
import com.itacademy.entity.UserEntity;
import com.itacademy.repository.PersonRepository;
import com.itacademy.service.PersonService;
import com.itacademy.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private UsersService usersService;

    @Override
    public List<Person> getAllPerson() {
        return personRepository.findAll();
    }

    @Override
    public Person save(Person person) {
        usersService.newUser(person.getUserEntity());
        person.setUserEntity(usersService.getByUserLogin(person.getUserEntity().getLogin()));
        return personRepository.save(person);
    }

    @Override
    public Person delete(UserEntity userEntity) {
        Person person = getPerson(userEntity);
        personRepository.delete(person);
        return person;
    }

    @Override
    public Person getPerson(UserEntity userEntity) {
        Person person = personRepository.findByUserEntity(userEntity).orElse(null);
        return null;
    }
}

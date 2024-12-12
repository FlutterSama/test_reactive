package com.ideanet.test_reactive.repository;

import com.ideanet.test_reactive.domain.Person;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class PersonRepositoryTest {
    PersonRepository personRepository = new PersonRepositoryImpl();

    @Test
    void testMonoByIdBlock(){
        Mono<Person> personMono = personRepository.findById(1);
        Person person = personMono.block();
        System.out.println(person);
    }

    @Test
    void  testGetByIdSubscriber(){
        Mono<Person> personMono = personRepository.findById(1);
        personMono.subscribe(System.out::println);
    }

    @Test
    void  testMapOperation(){
        Mono<Person> personMono = personRepository.findById(1);
        personMono.map(Person::getFirstName).subscribe(System.out::println);
    }

    @Test
    void  testFluxBlockFirst(){
        Flux<Person> personFlux = personRepository.findAll();
        Person person = personFlux.blockFirst();
        System.out.println(person);
    }

    @Test
    void  testFluxSubscriber(){
        Flux<Person> personFlux = personRepository.findAll();
        personFlux.subscribe(System.out::println);
    }

    @Test
    void  testFluxMap(){
        Flux<Person> personFlux = personRepository.findAll();
        personFlux.map(Person::getFirstName).subscribe(System.out::println);
    }

    @Test
    void testFluxList(){
        Flux<Person> personFlux = personRepository.findAll();
        Mono<List<Person>> listMono = personFlux.collectList();
        listMono.subscribe(list -> list.forEach(System.out::println));
    }
}

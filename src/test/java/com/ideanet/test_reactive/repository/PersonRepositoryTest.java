package com.ideanet.test_reactive.repository;

import com.ideanet.test_reactive.domain.Person;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    void testFilterFlux(){
        personRepository.findAll()
                .filter(person -> person.getFirstName().equals("kyaw"))
                .subscribe(person -> System.out.println(person.getFirstName()));
    }

    @Test
    void testGetById(){
        Mono<Person> personMono = personRepository.findAll()
                .filter(person -> person.getFirstName().equals("kyaw"))
                .next();
        personMono.subscribe(person -> System.out.println(person.getFirstName()));
    }

    @Test
    void testFindPersonByIdNotFound(){
        Flux<Person> personMono = personRepository.findAll();
        final int id = 8;
        Mono<Person> person = personMono.filter(p -> p.getId() == id).single()
                .doOnError(throwable -> {
                    System.out.println("Person not found");
                    System.out.println(throwable.toString());
                });
        person.subscribe(System.out::println, Throwable::printStackTrace);
    }


    @Test
    void testGetByIdFound(){
        Mono<Person> personMono = personRepository.findById(3);

        assertTrue(personMono.hasElement().block());
    }

    @Test
    void testGetByIdFoundStepVerifier(){
        Mono<Person> personMono = personRepository.findById(3);

        StepVerifier.create(personMono).expectNextCount(1).verifyComplete();
    }
}

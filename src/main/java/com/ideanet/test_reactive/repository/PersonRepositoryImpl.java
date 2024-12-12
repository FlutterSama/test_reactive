package com.ideanet.test_reactive.repository;

import com.ideanet.test_reactive.domain.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PersonRepositoryImpl implements PersonRepository {

    Person mgmg = Person.builder().id(1).firstName("mg").lastName("mg").build();
    Person kyawkyaw = Person.builder().id(2).firstName("kyaw").lastName("kyaw").build();
    Person aungaung = Person.builder().id(3).firstName("aung").lastName("aung").build();
    Person myomyo = Person.builder().id(4).firstName("myo").lastName("myo").build();

    @Override
    public Mono<Person> findById(Integer id) {
        return Mono.just(mgmg);
    }

    @Override
    public Flux<Person> findAll() {
        return Flux.just(mgmg, kyawkyaw, aungaung, myomyo);
    }
}

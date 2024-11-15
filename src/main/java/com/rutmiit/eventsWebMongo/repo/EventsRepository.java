package com.rutmiit.eventsWebMongo.repo;

import com.rutmiit.eventsWebMongo.model.Events;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface EventsRepository extends MongoRepository<Events, String> {

    Page<Events> findByDateAfter(LocalDateTime afterDate, Pageable pageable);
    @Query("{'host.name' : {$regex: ?0, $options:  'i'}}")
    Page<Events> findByHostName(String hostName, Pageable pageable);
}

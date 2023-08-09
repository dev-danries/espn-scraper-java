package ries.dan.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ries.dan.dao.Sport;

import java.util.UUID;

public interface SportRepository extends MongoRepository<Sport, UUID> {
}

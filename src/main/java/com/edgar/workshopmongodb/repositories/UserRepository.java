package com.edgar.workshopmongodb.repositories;

import com.edgar.workshopmongodb.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
                                                    //<tipo da classe, tipo do ID da classe>
}

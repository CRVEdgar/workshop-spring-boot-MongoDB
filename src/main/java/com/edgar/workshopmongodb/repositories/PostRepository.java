package com.edgar.workshopmongodb.repositories;

import com.edgar.workshopmongodb.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<Post,String> {
                                                    //<tipo da classe, tipo do ID da classe>
}

package knowledgebase.demo.Repositories;



import knowledgebase.demo.Constants.Status;
import knowledgebase.demo.Domain.Article;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ArticleRepository extends MongoRepository<Article,String> {
    @Query(value="{OwnerId:?0}")
    ArrayList<Article> findAllByOwnerId(String id);
    ArrayList<Article> findAllByStatus(Status status);

    Article findArticleById(String Id);








}

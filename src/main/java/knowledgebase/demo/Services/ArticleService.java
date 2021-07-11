package knowledgebase.demo.Services;




import knowledgebase.demo.Constants.Status;
import knowledgebase.demo.Domain.Article;
import knowledgebase.demo.Repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public Article createProperty(Article property){

            Article prop=articleRepository.insert(property);

        return prop;
    }

    public boolean removeProperty(String id) {

        articleRepository.deleteById(id);


        return true;
    }

    public ArrayList<Article> getPropertyByOwnerId(String id){

            ArrayList<Article> property = articleRepository.findAllByOwnerId(id);
        return  property;
    }




    public boolean updateProperty(Article property, String id){

        Article property1 = articleRepository.findArticleById(id);

        articleRepository.delete(property1);
        property.setId(id);
        property.setOwnerId(property1.getOwnerId());
        articleRepository.save(property);

        return true;
    }








    public Article findPropertyById(String id){

        return articleRepository.findArticleById(id);



    }


    public int deleteById(String id){
        articleRepository.deleteById(id);
        return 1;
    }

    public long totalProperties(){
      return  articleRepository.count();
    }

    public ArrayList<Article> getPublished(){return  articleRepository.findAllByStatus(Status.PUBLISHED);}

}

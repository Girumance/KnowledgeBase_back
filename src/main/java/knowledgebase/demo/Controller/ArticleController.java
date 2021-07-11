package knowledgebase.demo.Controller;


import knowledgebase.demo.Domain.Article;
import knowledgebase.demo.Services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/property")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping("/save")
    public Article saveProperty(@RequestBody Article property){

        return articleService.createProperty(property);

    }


    @PostMapping("/remove")
    public boolean removeProperty(@RequestBody String id){

        return  articleService.removeProperty(id);

    }


    @GetMapping("/owner/{id}")
    public ArrayList<Article> getPropertyByOwnerId(@PathVariable("id") String id){


        return  articleService.getPropertyByOwnerId(id);

    }



    @GetMapping("/getById/{id}")
    public Article getPropertyById(@PathVariable String id){

        return articleService.findPropertyById(id);
    }


    @GetMapping("delete/{id}")
    public int deleteById(@PathVariable String id){

        return articleService.deleteById(id);
    }

    @GetMapping("/count")
    public long totalProperty(){
        return  articleService.totalProperties();
    }


    @PostMapping("/update/{id}")
    public boolean updateProperty(@RequestBody Article property, @PathVariable String id){

        return articleService.updateProperty(property,id);
    }


    @GetMapping("published")
    public ArrayList<Article> getALlPublished() {
        return  articleService.getPublished();
    }

}

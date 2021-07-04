package knowledgebase.demo.Domain;



import knowledgebase.demo.Constants.Status;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import javax.persistence.GeneratedValue;
import java.util.ArrayList;
import java.util.Date;

@Data
@Document
@Component
public class Article {

    @Id
    @GeneratedValue
    private String id;
    private String Name;
    private String Content;
    private String Tag;
    private Date date=new Date();
    private String OwnerId;
    private Status status;
    private boolean approved=false;



    public void setStatus(String role){

        this.status=Status.valueOf(role);
    }

    public String getStatus(){

        return String.valueOf(status);
    }

}

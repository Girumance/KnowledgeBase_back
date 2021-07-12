package knowledgebase.demo.Domain;


import knowledgebase.demo.Constants.AccountType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import javax.persistence.GeneratedValue;


@Component
@Document
@Data
public class Profile {

    @Id
    @GeneratedValue
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private AccountType type;
    private String password;
    private boolean isAccountNonBlocked=true;
    private boolean isAccountEnabled=true;


    public void setAccountType(String role){
        this.type=AccountType.valueOf(role);
    }

    public String getAccountType(){

        return String.valueOf(type);
    }



}

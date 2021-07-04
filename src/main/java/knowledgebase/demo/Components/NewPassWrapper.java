package knowledgebase.demo.Components;


import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class NewPassWrapper {

    private String ownerId;
    private String currentPassword;
    private String newPassword;
    private String confirmPass;
}

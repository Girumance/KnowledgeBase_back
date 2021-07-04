package knowledgebase.demo.Repositories;


import knowledgebase.demo.Constants.AccountType;
import knowledgebase.demo.Domain.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


@Repository
public interface ProfileRepository extends MongoRepository<Profile,String> {

    public Profile findByEmail(String email);
    public ArrayList<Profile> findAllByType(AccountType type);
    public long countAllByAccountType(AccountType type);
    public Profile findProfileById(String Id);
}

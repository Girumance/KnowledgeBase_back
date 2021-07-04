package knowledgebase.demo.Services;

import knowledgebase.demo.Components.NewPassWrapper;
import knowledgebase.demo.Components.UserPrincipal;
import knowledgebase.demo.Constants.AccountType;
import knowledgebase.demo.Repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import knowledgebase.demo.Domain.Profile;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ProfileService implements UserDetailsService {

    @Autowired
   private ProfileRepository repository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Profile account = (Profile) repository.findByEmail(email);

        return new UserPrincipal(account);
    }



    public boolean createAccount(Profile account) {

        if (repository.findByEmail(account.getEmail()) != null)
            return false;

        account.setPassword(new BCryptPasswordEncoder().encode(account.getPassword()));

        return repository.save(account) != null ? true : false;


    }

    public Optional<Profile> getAccountbyId(String id) {
        return repository.findById(id);
    }

    public Profile getAccoutnByEmail(String email){

        return repository.findByEmail(email);
    }

    public int changePassword(NewPassWrapper newPassWrapper){

        Optional<Profile> account=repository.findById(newPassWrapper.getOwnerId());

        String password=new BCryptPasswordEncoder().encode(newPassWrapper.getCurrentPassword());

        System.out.println(password);



        if(new BCryptPasswordEncoder().matches(account.get().getPassword(),password)){

            repository.delete(account.get());

            String newPass=new BCryptPasswordEncoder().encode(newPassWrapper.getNewPassword());
            account.get().setPassword(newPass);
            repository.save(account.get());

            return 1;

        }


        return  0;
    }


    public Profile updateAccount(Profile account){

        Optional<Profile> old=repository.findById(account.getId());

        if(old.isPresent()){
            repository.delete(old.get());
            account.setPassword(old.get().getPassword());
            account.setAccountType(old.get().getAccountType());

            repository.save(account);
            return account;
        }
        return  old.get();

    }


    public ArrayList<Profile> getAllProfileByAccountType(AccountType type){

        return  repository.findAllByType(type);

    }

    public void addSuperAdmin(){
        Profile profile =new Profile();
        profile.setAccountType(String.valueOf(AccountType.SUPERADMIN));
        profile.setFirstName("Daniel");
        profile.setLastName("Tadeese");
        profile.setEmail("danitadesse@gmail.com");
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        profile.setPassword(encoder.encode("Dani"));

        repository.save(profile);
    }

    public Profile getById(String id){

       return repository.findProfileById(id);

    }

    public boolean deleteUser(String id){
        repository.deleteById(id);
        return true;
    }

   





}

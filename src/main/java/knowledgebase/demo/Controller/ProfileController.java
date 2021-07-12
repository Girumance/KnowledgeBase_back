package knowledgebase.demo.Controller;


import knowledgebase.demo.Components.NewPassWrapper;
import knowledgebase.demo.Constants.AccountType;
import knowledgebase.demo.Services.ProfileService;
import knowledgebase.demo.Domain.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileService service;


    @PostMapping("/signup")
    public boolean createAccount(@RequestBody Profile account){

        return  service.createAccount(account);

    }


    @GetMapping("/get/{id}")
    public Profile getProfile(@PathVariable String id){

        return service.getById(id);
    }


    @PostMapping("/changePassword")
    public int changePassword(@RequestBody NewPassWrapper newPassWrapper){

        return service.changePassword(newPassWrapper);
    }

    @PostMapping("/update")
    public Profile updateAccount(@RequestBody Profile account){

        return service.updateAccount(account);

    }


    @GetMapping("getAll/{type}")
    public ArrayList<Profile> getAllbyAccountTpe(@PathVariable String type){
        AccountType typee= AccountType.valueOf(type);

        return service.getAllProfileByAccountType(typee);
    }

    @GetMapping("/SAVE")
    public void save(){
        service.addSuperAdmin();
        System.out.println("super Ademin saved");
    }


    @GetMapping("/delete/{id}")
    public boolean deleteUser(@PathVariable String id){

        return  service.deleteUser(id);
    }

    @GetMapping("/enable/{id}")
    public Profile EnableUser(@PathVariable String id){

        return  service.DisableEnable(id,true);
    }

    @GetMapping("/disable/{id}")
    public Profile disableUser(@PathVariable String id){

        return  service.DisableEnable(id,false);
    }
}

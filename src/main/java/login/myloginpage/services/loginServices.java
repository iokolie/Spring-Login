package login.myloginpage.services;

import login.myloginpage.repository.loginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import login.myloginpage.domain.login;
@Service
public class loginServices {
    @Autowired
    private static loginRepository repo;

    public loginServices(loginRepository loginRepository) {
        this.repo = loginRepository;
    }

    public static login registerUser(String username, String password){

        if(username !=null && password !=null){

            login usersModel = new login();
            usersModel.setUsername(username);
            usersModel.setPassword(password);
            return repo.save(usersModel);
        }else {
            return null;
        }
    }

    public login login(String username, String password){
        return repo.findByUsernameAndPassword(username, password);
    }

}

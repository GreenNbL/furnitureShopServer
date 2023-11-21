package server;

import models.User;
import services.UserService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SignUp {
    private ObjectInputStream sois;
    private ObjectOutputStream soos;

    public SignUp(ObjectInputStream sois, ObjectOutputStream soos) {
        this.sois = sois;
        this.soos = soos;
    }

    public void Signingup() {
        User user = new User();
        User userDB = new User();
        UserService userService=new UserService();
        try {
            user = (User) sois.readObject();
            userDB=userService.findUserByLog(user);
            if(userDB==null) {
                soos.writeObject("No such user");
                System.out.println("Отправили");
                userService.saveUser(user);
            }
            else {
                soos.writeObject("there is such user");
                System.out.println(" такой уже есть ");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
}

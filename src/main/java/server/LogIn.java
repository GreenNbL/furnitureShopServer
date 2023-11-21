package server;

import models.User;
import services.UserService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class LogIn {
    private ObjectInputStream sois;
    private ObjectOutputStream soos;
    public LogIn(ObjectInputStream sois, ObjectOutputStream soos) {
        this.sois = sois;
        this.soos = soos;
    }
    public void LogingIn()
    {
        try {
            User user=new User();
            User userDB=new User();
            user=(User)sois.readObject();
            UserService userService=new UserService();
            userDB=userService.findUserByLogPas(user);
            soos.writeObject(userDB);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}

package dao;

import entity.User;
import service.representation.UserRepresentation;

public interface UserDAO
{
    public void addUser(UserRepresentation userRepresentation);
    public User getUserByEmail(String email);
}

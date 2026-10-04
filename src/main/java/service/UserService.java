package service;

import bean.UserBean;

public interface UserService {
    /** @return true if created, false if the name or email already exists. */
    boolean register(UserBean user);

    /** @param id user name or email. @return the user, or null if credentials are wrong. */
    UserBean login(String id, String password);
}

package lab.user.view;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lab.user.model.UsersModel;
import lab.user.model.UsersModel._User;
import lab.user.service.UserService;

/**
 * View bean for rendering list of users.
 */
@RequestScoped
@Named
public class UserList {

    /**
     * Service for managing users.
     */
    private final UserService service;

    /**
     * Characters list exposed to the view.
     */
    private UsersModel users;

    /**
     * @param service user service
     * @param factory factory producing functions for conversion between models and entities
     */
    @Inject
    public UserList(UserService service) {
        this.service = service;
    }

    /**
     * In order to prevent calling service on different steps of JSF request lifecycle, model property is cached using
     * lazy getter.
     *
     * @return all users
     */
    public UsersModel getUsers() {
        if (users == null) {
            users = UsersModel.mapper(service.findAll());
        }
        return users;
    }

    /**
     * Action for clicking delete action.
     *
     * @param user user to be removed
     * @return navigation case to list_users
     */
    public void deleteAction(_User user) {
        service.delete(user.getId());
        users=null;
        //return "user_list?faces-redirect=true";
    }

}

package com.xiedaimala.shopping_cart.web.user.view;

import com.xiedaimala.shopping_cart.web.user.model.User;

public class CreateUserResponse {
    private UserView user;

    public CreateUserResponse() {
    }

    public CreateUserResponse(UserView user) {
        this.user = user;
    }

    public UserView getUser() {
        return user;
    }

    public void setUserView(UserView user) {
        this.user = user;
    }
}

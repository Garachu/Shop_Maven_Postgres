package com.shop.module.user;

import com.shop.module.user.domain.SUserResponse;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

//import static org.springframework.hateoas.jaxrs.JaxRsLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by meg on 7/28/17.
 */


//extension of Spring HATEOAS’s ResourceSupport allows us to embed a link to the entire collection of bookmarks as well an individual "self" link.
public class SUserResource extends ResourceSupport{

    private final SUserResponse sUser;

    public SUserResource(SUserResponse sUser){
        this.sUser = sUser;
        this.add(new Link(sUser.getUsername(), "user-uri"));

    }

    public SUserResponse getsUser() {
        return sUser;
    }
}

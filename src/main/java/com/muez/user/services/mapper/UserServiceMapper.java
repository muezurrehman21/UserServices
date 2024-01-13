package com.muez.user.services.mapper;

import com.muez.user.services.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface UserServiceMapper  {
}
//extends EntityMapper<UserDTO, User>

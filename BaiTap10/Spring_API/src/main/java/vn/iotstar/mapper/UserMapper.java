package vn.iotstar.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import vn.iotstar.dto.request.UserCreationRequest;
import vn.iotstar.dto.request.UserUpdateRequest;
import vn.iotstar.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}

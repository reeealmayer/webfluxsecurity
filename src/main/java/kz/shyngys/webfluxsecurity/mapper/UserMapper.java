package kz.shyngys.webfluxsecurity.mapper;

import kz.shyngys.webfluxsecurity.dto.UserDto;
import kz.shyngys.webfluxsecurity.entity.UserEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto map(UserEntity userEntity);

    @InheritInverseConfiguration
    UserEntity map(UserDto dto);
}

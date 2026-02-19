package kz.shyngys.webfluxsecurity.mapper;

import kz.shyngys.webfluxsecurity.config.entity.UserEntity;
import kz.shyngys.webfluxsecurity.dto.UserDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto map(UserEntity userEntity);

    @InheritInverseConfiguration
    UserEntity map(UserDto dto);
}

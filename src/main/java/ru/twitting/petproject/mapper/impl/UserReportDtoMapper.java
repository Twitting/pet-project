package ru.twitting.petproject.mapper.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.twitting.petproject.dao.entity.UserEntity;
import ru.twitting.petproject.mapper.AbstractDtoMapper;
import ru.twitting.petproject.model.dto.UserReportDto;

@Component
public class UserReportDtoMapper extends AbstractDtoMapper<UserReportDto, UserEntity> {

    protected UserReportDtoMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    protected void setupMapper() {
        modelMapper
                .createTypeMap(sourceClass, destinationClass);
    }
}

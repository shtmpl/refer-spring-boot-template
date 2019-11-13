package refer.spring.boot.template.controller.api;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ApiMapper {

    ApiMapper INSTANCE = Mappers.getMapper(ApiMapper.class);
}

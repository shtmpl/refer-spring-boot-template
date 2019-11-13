package refer.spring.boot.template.controller.api;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DefaultMapper {

    DefaultMapper INSTANCE = Mappers.getMapper(DefaultMapper.class);
}

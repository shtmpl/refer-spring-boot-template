package refer.spring.boot.operation.controller.api;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import refer.spring.boot.operation.controller.api.response.ResponseOperation;
import refer.spring.boot.operation.domain.Operation;

@Mapper
public interface DefaultMapper {

    DefaultMapper INSTANCE = Mappers.getMapper(DefaultMapper.class);

    ResponseOperation toResponseOperation(Operation operation);
}

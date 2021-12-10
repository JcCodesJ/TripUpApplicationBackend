package carroll.tbel.tripupapplicationbackend.models.mapper;

public interface Mapper <ENTITY, DTO, FORM> {

    DTO entityToDTO(ENTITY entity);

    ENTITY formToEntity(FORM form);


}

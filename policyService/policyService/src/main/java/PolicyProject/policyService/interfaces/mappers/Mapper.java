package PolicyProject.policyService.interfaces.mappers;


import org.mapstruct.factory.Mappers;


@org.mapstruct.Mapper()
public interface Mapper {

    Mapper INSTANCE = Mappers.getMapper(Mapper.class);

}

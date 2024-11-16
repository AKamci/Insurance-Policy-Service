package PolicyProject.policyService.domain.dto.request.HouseRequest;

public record GetHouseWCustomerRequest(

        Integer number,
        Integer apartmentNumber,
        String city,
        String district,
        String neighborhood,
        String tckn
) {
}

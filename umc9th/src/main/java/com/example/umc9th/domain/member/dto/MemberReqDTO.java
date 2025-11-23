public class MemberReqDTO {

    public record JoinDTO(
            String name,
            Gender gender,
            LocalDate birth,
            Address address,
            String specAddress,
            List<Long> preferCategory
    ){}
}

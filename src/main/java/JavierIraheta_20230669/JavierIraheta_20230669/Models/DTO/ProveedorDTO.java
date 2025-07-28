package JavierIraheta_20230669.JavierIraheta_20230669.Models.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @EqualsAndHashCode
public class ProveedorDTO {
    private Long id;
    @NotBlank
    private String providerName;
    @NotBlank
    private String providerPhone;
    @NotBlank
    private String providerAddress;
    @NotBlank
    private String providerEmail;
    @NotBlank
    private String providerCode;
    @Positive(message = "El estado del proveedor no debe de ser negativo")
    @NotBlank
    private Long providerStatus;
    @NotBlank
    private String providerComments;
}

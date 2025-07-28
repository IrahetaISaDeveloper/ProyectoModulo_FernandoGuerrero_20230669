package JavierIraheta_20230669.JavierIraheta_20230669.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Entity
@Table(name = "TBPROVIDER")
@Getter @Setter @ToString @EqualsAndHashCode
public class ProveedorEntity {

    @Id @Column(name = "PROVIDERID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_provider")
    @SequenceGenerator(name = "seq_provider", sequenceName = "seq_provider", allocationSize = 1)
    private Long id;
    @Column(name = "PROVIDERNAME", unique = true)
    private String providerName;
    @Column(name = "PROVIDERPHONE")
    private String providerPhone;
    @Column(name = "PROVIDERADDRESS")
    private String providerAddress;
    @Column(name = "PROVIDEREMAIL")
    private String providerEmail;
    @Column(name = "PROVIDERCODE", unique = true)
    private String providerCode;
    @Column(name = "PROVIDERSTATUS")
    private Long providerStatus;
    @Column(name = "PROVIDERCOMMENTS")
    private String providerComments;
}

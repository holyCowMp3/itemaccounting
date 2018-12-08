package viti.kaf22.itemaccounting.models;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import sun.security.provider.SHA;
import viti.kaf22.itemaccounting.models.enums.UserRole;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private long user_id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String surname;

    @Getter
    @Setter
    private String thirdname;

    @Column(unique = true)
    @Getter
    @Setter
    @NonNull
    @NotEmpty
    @Email
    private String email;

    @Getter
    @Setter
    @NonNull
    @NotEmpty
    private String password;



    @Enumerated(value = EnumType.STRING)
    @Getter
    @Setter
    private UserRole role;

    @Getter
    @Setter
    private Date lastSeenDate;

    private String signerKey;

    public User(){
        super();
        this.role = UserRole.USER_VERIFIED;
    }


}

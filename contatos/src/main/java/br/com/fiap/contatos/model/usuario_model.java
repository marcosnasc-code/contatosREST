package br.com.fiap.contatos.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "TBL_USUARIOS")
@Getter
@Setter
@NoArgsConstructor //Fazem parte da biblioteca lombok, para facilitar a vida na criação da classe
@AllArgsConstructor
@EqualsAndHashCode
public class usuario_model implements UserDetails {

    @Id //Id biblioteca springframework.data
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_USUARIOS"
    )
    @SequenceGenerator(
            name = "SEQ_USUARIOS",
            sequenceName = "SEQ_USUARIOS",
            allocationSize = 1
    )
    @Column(name = "usuario_id")
    private Long usuarioId;

    private String nome;
    private String email;
    private String senha;


    @Enumerated(EnumType.STRING) //Saber se a classe enum vai buscar por valor numerico ou pelo texto cadastrado na classe enum
    private usuario_role role; //papel do usuario entre user normal ou user admin;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == usuario_role.ADMIN){
            return List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_USER")
            );
        }else {
            return List.of(
                    new SimpleGrantedAuthority("ROLE_USER")
            );
        }
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        //return UserDetails.super.isAccountNonExpired();
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        //return UserDetails.super.isAccountNonLocked();
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        //return UserDetails.super.isCredentialsNonExpired();
        return true;
    }

    @Override
    public boolean isEnabled() {
        //return UserDetails.super.isEnabled();
        return true;
    }
}

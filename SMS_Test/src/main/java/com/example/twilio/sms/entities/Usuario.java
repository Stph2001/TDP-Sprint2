package com.example.twilio.sms.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@Table(
        name="usuario",
        uniqueConstraints = {
                @UniqueConstraint(name = "usuario_nombre_unique",
                        columnNames = "nombre"),
                @UniqueConstraint(name = "usuario_correo_unique",
                        columnNames = "correo"),
                @UniqueConstraint(name = "usuario_cel_unique",
                        columnNames = "cel")
        }
)

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name="id",
            updatable = false
    )
    private Long id;

    @Column(
            name="correo",
            nullable = false,
            columnDefinition = "TEXT"
    )
    @Email
    private String correo;

    @Column(
            name="clave",
            nullable = false,
            columnDefinition = "TEXT"
    )
    @Size(min = 8, max = 30)
    private String clave;

    @Column(
            name="nombre",
            nullable = false,
            columnDefinition = "TEXT"
    )
    @Size(min = 5, max = 30)
    private String nombre;

    @Column(
            name="cel",
            nullable = false,
            columnDefinition = "TEXT"
    )
    @Size(min = 9, max = 9)
    private String cel;
}

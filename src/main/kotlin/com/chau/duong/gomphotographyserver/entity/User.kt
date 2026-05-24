package com.chau.duong.gomphotographyserver.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name = "app_user")
class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(unique = true, nullable = false)
    val username: String = "",

    @Column(nullable = false)
    val password: String = "",

    @Column
    val name: String = "",

    @Column
    val email: String = "",
    @Column
    val phone: String = "",
    @OneToMany(
        mappedBy = "user",
        cascade = [CascadeType.ALL],
        orphanRemoval = true,
        fetch = FetchType.LAZY
    )
    val albumUsers: MutableList<AlbumUser> = mutableListOf()

) {
    override fun toString(): String {
        return "User(id=$id, username='$username', password='$password', name='$name', email='$email', phone='$phone')"
    }
}

fun User.toDTO(): UserDTO {
    return UserDTO(
        id = this.id,
        username = this.username,
        password = this.password,
        email = this.email,
        phone = this.phone,
        name = this.name
    )
}
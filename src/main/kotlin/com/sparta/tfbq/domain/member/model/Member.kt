package com.sparta.tfbq.domain.member.model

import com.sparta.tfbq.domain.question.model.Question
import com.sparta.tfbq.global.entity.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "members")
class Member(
    name: String,
    email: String,
    nickname: String,
    memberRole: MemberRole,
    password: String
) : BaseEntity() {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
        private set

    @OneToMany(mappedBy = "member", cascade = [CascadeType.ALL])
    val questions = mutableListOf<Question>()

    @Column(name = "name")
    var name = name
        private set

    @Column(name = "nickname")
    var nickname = nickname
        private set

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    val role = memberRole

    @Column(name = "email")
    var email = email
        private set

    @Column(name = "password")
    var password = password
        private set

}
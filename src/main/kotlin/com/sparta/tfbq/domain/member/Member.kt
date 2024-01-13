package com.sparta.tfbq.domain.member

import com.sparta.tfbq.domain.member.model.MemberRole
import com.sparta.tfbq.domain.question.model.Question
import com.sparta.tfbq.global.entity.BaseEntity
import com.sparta.tfbq.global.util.PasswordEncoder.Companion.encode
import com.sparta.tfbq.global.util.RandomNicknameGenerator.Companion.generateRandomNickname
import jakarta.persistence.*

@Entity
@Table(name = "members")
class Member(
    name: String,
    email: String,
    nickname: String?,
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

    @Column(name = "nickname", unique = true)
    var nickname = nickname
        private set

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    val role = memberRole

    @Column(name = "email", unique = true)
    var email = email
        private set

    @Column(name = "password")
    var password = encode(password)
        private set

    fun makeNickname() {
        this.nickname = generateRandomNickname()
    }

}

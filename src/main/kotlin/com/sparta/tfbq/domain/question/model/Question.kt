package com.sparta.tfbq.domain.question.model

import com.sparta.tfbq.domain.answer.model.Answer
import com.sparta.tfbq.domain.member.model.Member
import com.sparta.tfbq.global.entity.BaseEntity
import com.sparta.tfbq.global.exception.AlreadyHaveAnswersException
import jakarta.persistence.*

@Entity
@Table(name = "questions")
class Question(
    member: Member,
    questionTo: Long,
    title: String,
    content: String,
    isPrivate: Boolean = false,
    isClose: Boolean = false
) : BaseEntity() {

    @Id
    @Column(name = "question_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
        private set

    @OneToMany(mappedBy = "question", cascade = [CascadeType.ALL])
    var answers = mutableListOf<Answer>()
        private set

    @Column(name = "question_to")
    var questionTo = questionTo
        private set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    var member = member
        private set

    @Column(name = "title")
    var title = title
        private set

    @Column(name = "content")
    var content = content
        private set

    @Column(name = "is_private")
    var isPrivate = isPrivate
        private set

    @Column(name = "is_close")
    var isClose = isClose
        private set

    fun update(title: String, content: String, isPrivate: Boolean) {
        this.title = title
        this.content = content
        this.isPrivate = isPrivate
    }
}
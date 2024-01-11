package com.sparta.tfbq.domain.answer.model

import com.sparta.tfbq.domain.question.model.Question
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "answers")
class Answer(
    content: String,
    question: Question
) {

    @Id
    @Column(name = "answer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
        private set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    var question = question
        private set

    @Column(name = "content")
    var content = content
        private set

    @Column(name = "created_at", columnDefinition = "TIMESTAMP(6)", nullable = false, updatable = false)
    var createdAt = LocalDateTime.now()
        private set
}
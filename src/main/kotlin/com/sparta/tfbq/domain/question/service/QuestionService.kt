package com.sparta.tfbq.domain.question.service

import com.sparta.tfbq.domain.member.model.MemberRole
import com.sparta.tfbq.domain.member.repository.MemberRepository
import com.sparta.tfbq.domain.question.dto.request.AddQuestionRequest
import com.sparta.tfbq.domain.question.dto.request.UpdateQuestionRequest
import com.sparta.tfbq.domain.question.model.Question
import com.sparta.tfbq.domain.question.repository.QuestionRepository
import com.sparta.tfbq.global.exception.AlreadyHaveAnswersException
import com.sparta.tfbq.global.exception.ModelNotFoundException
import com.sparta.tfbq.global.exception.WrongRoleException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QuestionService(
    private val questionRepository: QuestionRepository,
    private val memberRepository: MemberRepository
) {
    @Transactional
    fun addQuestion(request: AddQuestionRequest): Long {
        val member = getMember(request.memberId)

        // 질문 대상(튜터)을 옳게 설정했는지 확인
        validateAvailableTutor(request.tutorId)
        // 질문은 학생만 할 수 있다
        validateRole(member.role)

        val question = request.to(member)
        question.addMember(member)
        questionRepository.save(question)

        return question.id!!
    }

    @Transactional
    fun updateQuestion(questionId: Long, request: UpdateQuestionRequest) {
        val member = getMember(request.memberId)
        val question = getQuestion(questionId)

        // 질문 수정은 학생만 할 수 있다
        validateRole(member.role)
        // 질문에 답변이 1개 이상 존재하는 경우, 학생은 질문을 수정할 수 없다
        validateAvailabilityToUpdate(question)

        question.update(request.title, request.content, request.isPrivate)
    }

    @Transactional
    fun deleteQuestion(memberId: Long, questionId: Long) {
        val member = getMember(memberId)
        val question = getQuestion(questionId)

        // 질문 삭제는 학생만 할 수 있다
        validateRole(member.role)

        question.removeMember()
        questionRepository.delete(question)
    }

    private fun getMember(memberId: Long) =
        memberRepository.findByIdOrNull(memberId) ?: throw ModelNotFoundException("Member")

    private fun getQuestion(questionId: Long) =
        questionRepository.findByIdOrNull(questionId) ?: throw ModelNotFoundException("Question")

    private fun validateRole(role: MemberRole) {
        if (MemberRole.isTutor(role)) throw WrongRoleException("학생에게만 질문 작성 권한이 있습니다.")
    }

    private fun validateAvailabilityToUpdate(question: Question) {
        if (question.answers.isNotEmpty()) throw AlreadyHaveAnswersException()
    }

    private fun validateAvailableTutor(tutorId: Long){
        if (!memberRepository.existsById(tutorId)) throw ModelNotFoundException("Member")
    }

}
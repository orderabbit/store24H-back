package com.example.back.repository;

import com.example.back.entity.AnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<AnswerEntity, Long> {
    AnswerEntity findByAnswerId(Long AnswerId);

    @Query(
            value=
                    "SELECT * " +
                            "FROM question AS B " +
                            "WHERE question_id = ?1 ",
            nativeQuery = true
    )
    AnswerEntity getAnswer(Long questionId);

    List<AnswerEntity> findByUserId(String userId);
}

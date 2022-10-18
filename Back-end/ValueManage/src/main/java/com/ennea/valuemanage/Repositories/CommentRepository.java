package com.ennea.valuemanage.Repositories;

import com.ennea.valuemanage.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {

}

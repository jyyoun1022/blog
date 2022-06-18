package org.codej.blog.repository;

import org.codej.blog.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply,Long> {

}

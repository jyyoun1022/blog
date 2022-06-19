package org.codej.blog.repository;

import org.codej.blog.dto.ReplySaveRequestDTO;
import org.codej.blog.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ReplyRepository extends JpaRepository<Reply,Long> {

    @Modifying
    @Query(value = "insert INTO reply(userId,boardId,content) values(?1,?2,?3)",nativeQuery = true)
    int _Save(Long userId,Long boardId,String content);

}

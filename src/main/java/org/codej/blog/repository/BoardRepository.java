package org.codej.blog.repository;

import org.codej.blog.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<Board,Long> {


    @Modifying
    @Query("update Board b set b.viewCount = b.viewCount +1 where b.id =:boardId")
    int updateView(@Param("boardId")Long boardId);
}

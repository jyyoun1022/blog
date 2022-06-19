package org.codej.blog.controller.api;

import lombok.RequiredArgsConstructor;
import org.codej.blog.configuration.auth.PrincipalDetail;
import org.codej.blog.dto.ResponseDTO;
import org.codej.blog.model.Board;
import org.codej.blog.model.Reply;
import org.codej.blog.model.User;
import org.codej.blog.repository.ReplyRepository;
import org.codej.blog.service.BoardService;
import org.codej.blog.service.UserService;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;


@RestController
@RequiredArgsConstructor
public class boardApiController {

    private final BoardService boardService;

    @PostMapping("/api/board")
    public ResponseDTO<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principalDetail) {
        boardService.write(board,principalDetail.getUser());
        return new ResponseDTO<Integer>(HttpStatus.OK.value(), 1);
    }

    @DeleteMapping("/api/board/{id}")
    @Modifying
    public ResponseDTO<Integer> deleteById(@PathVariable Long id){
        boardService.delete(id);
        return new ResponseDTO<>(HttpStatus.OK.value(), 1);
    }

    @PutMapping("/api/board/{id}")
    public ResponseDTO<Integer> update(@PathVariable Long id,@RequestBody Board board ){
        boardService.update(id,board);
        return new ResponseDTO<>(HttpStatus.OK.value(),1);
    }
    @PostMapping("/api/board/{boardId}/reply")
    public ResponseDTO<Integer> replySave(@PathVariable Long boardId, @RequestBody Reply reply, @AuthenticationPrincipal PrincipalDetail principalDetail) {

        boardService.writeReply(principalDetail.getUser(),boardId,reply);
        return new ResponseDTO<>(HttpStatus.OK.value(), 1);
    }

}

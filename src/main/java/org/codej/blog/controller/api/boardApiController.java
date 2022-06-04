package org.codej.blog.controller.api;

import lombok.RequiredArgsConstructor;
import org.codej.blog.configuration.auth.PrincipalDetail;
import org.codej.blog.dto.ResponseDTO;
import org.codej.blog.model.Board;
import org.codej.blog.model.User;
import org.codej.blog.service.BoardService;
import org.codej.blog.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class boardApiController {

    private final BoardService boardService;

    @PostMapping("/api/board")
    public ResponseDTO<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principalDetail) {
        boardService.write(board,principalDetail.getUser());
        return new ResponseDTO<Integer>(HttpStatus.OK.value(), 1);
    }

}

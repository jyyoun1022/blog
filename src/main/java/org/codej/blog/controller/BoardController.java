package org.codej.blog.controller;

import lombok.RequiredArgsConstructor;
import org.codej.blog.configuration.auth.PrincipalDetail;
import org.codej.blog.model.Board;
import org.codej.blog.service.BoardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping({"/",""})
    public String index(Model model, @PageableDefault(size = 3,sort = "id",direction = Sort.Direction.DESC)Pageable pageable){
        Page<Board> list = boardService.getList(pageable);
        model.addAttribute("boards",list);

        return "index";
    }
    @GetMapping("/board/save")
    public String saveForm(){
        return "/board/save";
    }
    @GetMapping("/board/{id}")
    public String read(@PathVariable Long id,Model model){
        Board board = boardService.read(id);
        model.addAttribute("board",board);

        return "/board/read";
    }

    @GetMapping("/board/{id}/update")
    public String updateForm(@PathVariable Long id,Model model){
        model.addAttribute("board",boardService.read(id));

        return "/board/update";

    }



}

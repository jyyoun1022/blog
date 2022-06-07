package org.codej.blog.service;

import lombok.RequiredArgsConstructor;
import org.codej.blog.model.Board;
import org.codej.blog.model.User;
import org.codej.blog.repository.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public void write(Board board, User user){
        board.setUser(user);
        boardRepository.save(board);

    }
    @Transactional(readOnly = true)
    public Page<Board> getList(Pageable pageable){
        Page<Board> result = boardRepository.findAll(pageable);

        return result;
    }

    @Transactional(readOnly = true)
    public Board read(Long id){
        Optional<Board> byId = boardRepository.findById(id);
        Board board = byId.orElseThrow(() -> {
            return new IllegalArgumentException("글 살세보기 실패: 아이디를 찾을 수 없습니다.");
        });
        return board;
    }
    @Transactional
    public void delete(Long id){

        boardRepository.deleteById(id);
    }

    @Transactional
    public void update(Long id,Board requestBoard){
        Board board = boardRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("글 찾기 실패 : 아이디를 찾을 수 없습니다.");
        });//영속화

        board.setTitle(requestBoard.getTitle());
        board.setContent(requestBoard.getContent());
        //해당 함수에 종료시에 트랜잭션이 종료됩니다.
        //이떄 더티체킹이 발생합니다.(자동 업데이트가 flush)
    }
}

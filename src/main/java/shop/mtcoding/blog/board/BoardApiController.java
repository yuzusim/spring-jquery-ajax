package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController // 데이터 리턴
public class BoardApiController {
    private final BoardRepository boardRepository;

    @DeleteMapping("/api/boards/{id}")
    public ApiUtil<?> deleteById(@PathVariable Integer id, HttpServletResponse response){
        Board board = boardRepository.selectOne(id);
        if(board == null){
            response.setStatus(404);
            return new ApiUtil<>(404, "해당 게시글을 찾을 수 없습니다");
        }

        boardRepository.deleteById(id);
        return new ApiUtil<>(null);
    }




    // 데이터 응답하는 아이들 api
    // 복수형으로 쓴다. 게시판들 줘 테이블 명이랑 똑같을 필요 없다.
    @GetMapping("/api/boards")
    public ApiUtil<List<Board>> findAll(HttpServletResponse response) { // ApiUtil<?> 로 하면 알아서 해줌
        // response.setStatus(200);
        List<Board> boardList = boardRepository.selectAll();
        return new ApiUtil<>(boardList); // MessageConverter 가 발동한다. 스프링이 리턴 될때 / MessageConverter -> json으로


    }


}


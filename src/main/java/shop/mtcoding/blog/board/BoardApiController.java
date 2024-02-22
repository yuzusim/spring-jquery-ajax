package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController // 데이러틀 리턴
public class BoardApiController {
    private final BoardRepository boardRepository; // DI

    @PostMapping("/api/boards/{id}")
    public ApiUtil<?> update(@RequestBody BoardRequest.UpdateDTO requestDTO, @PathVariable Integer id){
        boardRepository.updateById(requestDTO, id);
        return new ApiUtil<>(null);
    }

    @PostMapping("/api/boards")
    public ApiUtil<?> write(@RequestBody BoardRequest.WriteDTO requestDTO){
        boardRepository.insert(requestDTO);
        return new ApiUtil<>(null);
    }

    // 삭제하기
    @DeleteMapping("api/boards/{id}") // 보드 중에 몇번을 삭제할게
    public ApiUtil<?> deleteById(@PathVariable Integer id, HttpServletResponse response) {
        Board board = boardRepository.selectOne(id);
        if (board == null) {
            response.setStatus(404);
            return new ApiUtil<>(404, "해당 데이터를 찾을 수 없습니다");
        }
        boardRepository.deleteById(id);
        return new ApiUtil<>(null); // 삭제는 데이터를 줄 것이 없음
    }

    // 주소 만들기
    @GetMapping("api/boards") // 보드 줘라는 주소, 복수는 보드들 줘, 보드들 중에 1번 줘해서 복수형을 씀
    public ApiUtil<List<Board>> findAll(HttpServletResponse response) {
        //response.setStatus(401);
        List<Board> boardList = boardRepository.selectAll(); // 상태코드랑 메세지랑 같이 줘야함
        return new ApiUtil<>(boardList); // MessageConverter라는 클래스가 오브젝트를 응답할때 자동 발동함
    }
}
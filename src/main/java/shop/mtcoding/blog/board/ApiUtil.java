package shop.mtcoding.blog.board;

import lombok.Data;

@Data
public class ApiUtil<T> {
    private Integer status; // 200, 400, 404
    private String msg; // 성공, 실패시 -> 정확한 메세지 200이 아닐때 즉 실패 했을때 메세지 띄워 주는게 중요
    private  T body;

    public ApiUtil(T body) {
        this.status = 200;
        this.msg = "성공";
        this.body = body;
    }

    public ApiUtil(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
        this.body = null;
    }


}

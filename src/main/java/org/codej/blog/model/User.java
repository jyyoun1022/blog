package org.codej.blog.model;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//시퀸스

    @Column(nullable = true,length = 20)
    private String username;//아이디

    @Column(nullable = false,length = 100)
    private String password;//비밀번호

    @Column(nullable = false,length = 50)
    private String email;//이메일

    @ColumnDefault("'user'")
    private String role;

    @CreationTimestamp //시간이 자동 입력
    private Timestamp createDate;


}
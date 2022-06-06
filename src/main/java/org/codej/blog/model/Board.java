package org.codej.blog.model;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@ToString
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 100)
    private String title;

    @Lob    //대용량 데이터
    private String content;//섬머노트 라이브러리<html>태그가 섞여서 디자인

    @ColumnDefault("0")
    private int viewCount;//조회수

    @ManyToOne(fetch = FetchType.EAGER)//기본전략
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "board",fetch = FetchType.EAGER)
    //mappedBy 연관관계의 주인이 아닙니다.(fk가 아님)(db에 컬럼을 만들지마세요)
    private List<Reply> reply;

    @CreationTimestamp
    private Timestamp createDate;






}

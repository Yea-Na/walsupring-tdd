package com.walsupring.gallery.domain;

import com.walsupring.user.domain.User;
import com.walsupring.util.Preconditions;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;

import java.time.Instant;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Gallery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String originalFileName;
    private String savedFileName;

    //Gallery: User(Long타입으로 저장됨(외래키))
    //   N   :  1
    // = ManyToOne
    //Gallery와 User간의 ForeignKey(외래키)-> Many에 위치


    //User: Gallery
    //  1   :  N
    // = OneToMany
    //User id = 1 => 삭제 -> 오류 외래키 오류
    //Gallery에 createdBy -> 1L

    //EAGER //열심히 -> 쿼리 두개가 같이 날아감
    //LAZY //게으른 -> 실제로 호출될 때 날아감
    //Select * from gallery where id = 1
    //user_id = 1
    //Select * from user where id = {user_id}


    //
    //OneToOne
    //MannyToManny  => 양방향 참조관계 = 서로가 서로를 알고있다
    //  ManyToOne
    //  Mapping Table
    //  ManyToOne
    //  -> manytomany를 사용하지 않기 위한 방법
    @Enumerated(EnumType.STRING)
    private GalleryStatus status = GalleryStatus.PUBLIC;
    @ManyToOne(fetch = FetchType.LAZY)
    private User createdBy;   // User ==USer,<--연관관계매핑-->  Long!=User
    private Instant createdAt  = Instant.now();


    public Gallery(String title, String content, String originalFileName, String savedFileName, User createdBy) {
        Preconditions.require(Strings.isNotBlank(title),"title is not null");
        Preconditions.require(Strings.isNotBlank(content),"content is not null");
        Preconditions.require(Strings.isNotBlank(originalFileName),"originalFileName is not null");
        Preconditions.require(Strings.isNotBlank(savedFileName),"savedFileName is not null");
        Preconditions.require(Objects.nonNull(createdBy),"createdBy is not null");

        this.title = title;
        this.content = content;
        this.originalFileName = originalFileName;
        this.savedFileName = savedFileName;
        this.createdBy = createdBy;
    }

    public void change(String title, String content, String originalFileName, String savedFileName) {
        Preconditions.require(Strings.isNotBlank(title),"title is not null");
        Preconditions.require(Strings.isNotBlank(content),"content is not null");
        Preconditions.require(Strings.isNotBlank(originalFileName),"originalFileName is not null");
        Preconditions.require(Strings.isNotBlank(savedFileName),"savedFileName is not null");

        this.title = title;
        this.content = content;
        this.originalFileName = originalFileName;
        this.savedFileName = savedFileName;
    }

    //삭제 2가지 전략
    //Hard Delete : 진짜 db에서 삭제하는것  ) xxxrepository.delete(gallery)
    //Soft Delete : DB에서 삭제하진 않지만 삭제가 된거처럼 처리
    //정보를 유지할 필요성이 있냐
    //상태를 관리할 필요성이 있냐 = 객체의 상태를 관리할 필요성이 있냐
    //생성 -> 나만보기 == 비공개 -> 삭제

    public void changeStatus(GalleryStatus status){
        Preconditions.require(Objects.nonNull(status), "status is not null");
        Preconditions.check(status != GalleryStatus.DELETED);
        this.status = status;
    }

    public void delete() {
        this.status = GalleryStatus.DELETED;
    }

}

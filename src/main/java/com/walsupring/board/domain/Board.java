package com.walsupring.board.domain;

import com.walsupring.util.Preconditions;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;

import java.time.Instant;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String originalFileName;
    private String savedFileName;
    private String createdBy;
    private Instant createdAt  = Instant.now();

    public Board(String title, String content, String originalFileName, String savedFileName) {
        Preconditions.require(Strings.isNotBlank(title),"title must not be null");
        Preconditions.require(Strings.isNotBlank(content),"content must not be null");
        Preconditions.require(Strings.isNotBlank(originalFileName),"originalFileName must not be null");
        Preconditions.require(Strings.isNotBlank(savedFileName),"savedFileName must not be null");

        this.title = title;
        this.content = content;
        this.originalFileName = originalFileName;
        this.savedFileName = savedFileName;
    }

    public void changeBoard(String title, String content, String originalFileName, String savedFileName){
        this.title = title;
        this.content = content;
        this.originalFileName = originalFileName;
        this.savedFileName = savedFileName;

    }



}

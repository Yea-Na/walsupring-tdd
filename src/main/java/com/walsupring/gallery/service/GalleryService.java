package com.walsupring.gallery.service;

import com.walsupring.gallery.domain.Gallery;
import com.walsupring.user.domain.User;

public interface GalleryService {
    Gallery create(String title, String content, String originalFileName, String savedFileName, User createdBy);
    Gallery get(Long id);
    Gallery change(Long id, String title, String content, String originalFileName, String savedFileName);
    void delete(Long id);

    void changePublic(Long id);
    void changePrivate(Long id);


    //Test code 띄어쓰기

    //given -> 준비
    //Repository에 저장

    //메서드호출

    // when -> 내가 테스트하고자 하는거

    //then -> 결과 검증


}

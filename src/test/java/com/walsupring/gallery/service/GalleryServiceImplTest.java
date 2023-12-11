package com.walsupring.gallery.service;

import com.walsupring.gallery.domain.Gallery;
import com.walsupring.gallery.domain.GalleryRepository;
import com.walsupring.gallery.domain.GalleryStatus;
import com.walsupring.user.domain.User;
import com.walsupring.user.domain.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;



@SpringBootTest
@Transactional
class GalleryServiceImplTest {

    @Autowired
    private GalleryService galleryService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GalleryRepository galleryRepository;

    //메소드가 실행되기 전에 어떤 거 실행
    //메소드가 실행되고 난 후에 어떤 거 실행

    private  User user;
    private Gallery gallery;

    @BeforeEach
    void setUp() {
        user = userRepository.save(new User("loginId","password","name","nickname"));
        gallery = galleryRepository.save(new Gallery("제목", "본문", "originalFileName", "SavedFileName", user));
    }

    @Test
    void 갤러리_생성_성공() {
        Gallery gallery = galleryService.create("제목", "본문", "originalFileName", "SavedFileName", user);
        Assertions.assertThat(gallery.getId()).isNotNull();
    }

    @Test
    void 갤러리_조회_성공() {
        Gallery foundGallery = galleryService.get(gallery.getId());
        Assertions.assertThat(foundGallery.getId()).isEqualTo(gallery.getId());
    }

    @Test
    void 갤러리_조회_실패__존재하지_않는_ID() {
        Assertions.assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> galleryService.get(-1L));

    }

    @Test
    void 갤러리_변경_성공() {
        Gallery changeGallery = galleryService.change(gallery.getId(),"제목1","본문1","originalFileName1","savedFileName1");
        Assertions.assertThat(changeGallery.getTitle()).isEqualTo("제목1");
        Assertions.assertThat(changeGallery.getContent()).isEqualTo("본문1");
        Assertions.assertThat(changeGallery.getOriginalFileName()).isEqualTo("originalFileName1");
        Assertions.assertThat(changeGallery.getSavedFileName()).isEqualTo("savedFileName1");
    }

    @Test
    void 갤러리_변경_실패__존재하지_않는_ID() {
        Assertions.assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> galleryService.change(-1L,"제목1","본문1","originalFileName1","savedFileName1"));
    }

    @Test
    void 갤러리_삭제_성공() {
        galleryService.delete(gallery.getId());

        Assertions.assertThat(gallery.getStatus()).isEqualTo(GalleryStatus.DELETED);
    }

    @Test
    void 갤러리_삭제_실패__존재하지_않는_ID() {
        Assertions.assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> galleryService.delete(-1L));
    }

    @Test
    void 갤러리_private_변경_성공() {
        galleryService.changePrivate(gallery.getId());

        Assertions.assertThat(gallery.getStatus()).isEqualTo(GalleryStatus.PRIVATE);
    }

    @Test
    void 갤러리_private_변경_실패__존재하지_않는_ID() {
        Assertions.assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> galleryService.changePrivate(-1L));
    }

    @Test
    void 갤러리_public_변경_성공() {
        gallery.changeStatus(GalleryStatus.PRIVATE);

        galleryService.changePublic(gallery.getId());

        Assertions.assertThat(gallery.getStatus()).isEqualTo(GalleryStatus.PUBLIC);
    }

    @Test
    void 갤러리_public_변경_실패__존재하지_않는_ID() {
        Assertions.assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> galleryService.changePublic(-1L));
    }
}

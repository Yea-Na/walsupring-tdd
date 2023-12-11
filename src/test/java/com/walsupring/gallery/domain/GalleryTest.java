package com.walsupring.gallery.domain;

import com.walsupring.user.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class GalleryTest {

    @Test
    void 갤러리_생성_성공() {
        User user = new User("loginId","123","name","nickname");
        Gallery gallery =
                new Gallery("제목","본문","originalFileName","SavedFileName",user);

        assertThat(gallery.getId()).isNull();
        assertThat(gallery.getTitle()).isEqualTo("제목");
        assertThat(gallery.getContent()).isEqualTo("본문");
        assertThat(gallery.getOriginalFileName()).isEqualTo("originalFileName");
        assertThat(gallery.getSavedFileName()).isEqualTo("SavedFileName");
        assertThat(gallery.getCreatedBy()).isSameAs(user);
        assertThat(gallery.getCreatedAt()).isNotNull();
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 갤러리_생성_실패__title이_없거나_null인_경우(String title){
        User user = new User("loginId","123","name","nickname");

        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Gallery(title,"본문","originalFileName","SavedFileName",user));

    }

    @ParameterizedTest
    @NullAndEmptySource
    void 갤러리_생성_실패__content가_없거나_null인_경우(String content){
        User user = new User("loginId","123","name","nickname");

        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Gallery("제목",content,"originalFileName","SavedFileName",user));

    }

    @ParameterizedTest
    @NullAndEmptySource
    void 갤러리_생성_실패__originalFileName이_없거나_null인_경우(String originalFileName){
        User user = new User("loginId","123","name","nickname");

        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Gallery("제목","content",originalFileName,"SavedFileName",user));

    }

    @ParameterizedTest
    @NullAndEmptySource
    void 갤러리_생성_실패__SavedFileName이_없거나_null인_경우(String SavedFileName){
        User user = new User("loginId","123","name","nickname");

        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Gallery("제목","content","originalFileName",SavedFileName,user));

    }

    @ParameterizedTest
    @NullSource
    void 갤러리_생성_실패__createdBy가_없거나_null인_경우(User createdBy){
        User user = new User("loginId","123","name","nickname");

        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Gallery("제목","content","originalFileName","SavedFileName",createdBy));

    }

    @Test
    void 갤러리_변경_성공() {
        User user = new User("loginId", "123", "name", "nickname");
        Gallery gallery =
                new Gallery("제목", "본문", "originalFileName", "SavedFileName", user);

        gallery.change("제목1", "본문1", "originalFileName1", "SavedFileName1");

        Assertions.assertThat(gallery.getTitle()).isEqualTo("제목1");
        Assertions.assertThat(gallery.getContent()).isEqualTo("본문1");
        Assertions.assertThat(gallery.getOriginalFileName()).isEqualTo("originalFileName1");
        Assertions.assertThat(gallery.getSavedFileName()).isEqualTo("SavedFileName1");

    }

    @ParameterizedTest
    @NullAndEmptySource
    void 갤러리_변경_실패__변경하는_제목이_빈값_또는_Null일때(String title) {
        User user = new User("loginId", "123", "name", "nickname");
        Gallery gallery =
                new Gallery("제목", "본문", "originalFileName", "SavedFileName", user);

        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> gallery.change(title, "본문1", "originalFileName1", "SavedFileName1"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 갤러리_변경_실패__변경하는_본문이_빈값_또는_Null일때(String content) {
        User user = new User("loginId", "123", "name", "nickname");
        Gallery gallery =
                new Gallery("제목", "본문", "originalFileName", "SavedFileName", user);

        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> gallery.change("제목1", content, "originalFileName1", "SavedFileName1"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 갤러리_변경_실패__변경하는_originalFileName이_빈값_또는_Null일때(String originalFileName) {
        User user = new User("loginId", "123", "name", "nickname");
        Gallery gallery =
                new Gallery("제목", "본문", "originalFileName", "SavedFileName", user);

        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> gallery.change("제목1", "본문1", originalFileName, "SavedFileName1"));
    }
    @ParameterizedTest
    @NullAndEmptySource
    void 갤러리_변경_실패__변경하는_SavedFileName이_빈값_또는_Null일때(String SavedFileName) {
        User user = new User("loginId", "123", "name", "nickname");
        Gallery gallery =
                new Gallery("제목", "본문", "originalFileName", "SavedFileName", user);

        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> gallery.change("제목1", "본문1", "originalFileName1", SavedFileName));
    }

    @Test
    void 스테이터스_변경_성공__비공개() {
        User user = new User("loginId", "123", "name", "nickname");
        Gallery gallery =
                new Gallery("제목", "본문", "originalFileName", "SavedFileName", user);

        gallery.changeStatus(GalleryStatus.PRIVATE);

        Assertions.assertThat(gallery.getStatus()).isEqualTo( GalleryStatus.PRIVATE);
    }
    @Test
    void 스테이터스_변경_성공__공개() {
        User user = new User("loginId", "123", "name", "nickname");
        Gallery gallery =
                new Gallery("제목", "본문", "originalFileName", "SavedFileName", user);

        gallery.changeStatus(GalleryStatus.PRIVATE);
        gallery.changeStatus(GalleryStatus.PUBLIC);

        Assertions.assertThat(gallery.getStatus()).isEqualTo( GalleryStatus.PUBLIC);
    }

    @ParameterizedTest
    @NullSource
    void 스테이터스_변경_실패__스테이터스가_null일때(GalleryStatus status) {
        User user = new User("loginId", "123", "name", "nickname");
        Gallery gallery =
                new Gallery("제목", "본문", "originalFileName", "SavedFileName", user);

        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> gallery.changeStatus(status));
    }

    @Test
    void 스테이터스_변경_실패__delete() {
        User user = new User("loginId", "123", "name", "nickname");
        Gallery gallery =
                new Gallery("제목", "본문", "originalFileName", "SavedFileName", user);

        Assertions.assertThatIllegalStateException().isThrownBy(() -> gallery.changeStatus(GalleryStatus.DELETED));
    }//상태오류


    @Test
    void 갤러리_삭제_성공() {
        User user = new User("loginId", "123", "name", "nickname");
        Gallery gallery =
                new Gallery("제목", "본문", "originalFileName", "SavedFileName", user);

        gallery.delete();

        Assertions.assertThat(gallery.getStatus()).isEqualTo(GalleryStatus.DELETED);
    }


}
package com.walsupring.gallery.service;


import com.walsupring.core.exception.CustomException;
import com.walsupring.core.exception.ErrorCode;
import com.walsupring.gallery.domain.Gallery;
import com.walsupring.gallery.domain.GalleryRepository;
import com.walsupring.gallery.domain.GalleryStatus;
import com.walsupring.user.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GalleryServiceImpl implements GalleryService {

    private final GalleryRepository galleryRepository;

    public GalleryServiceImpl(GalleryRepository galleryRepository) {
        this.galleryRepository = galleryRepository;
    }

    @Override
    public Gallery create(String title, String content, String originalFileName, String savedFileName, User createdBy) {
        return galleryRepository.save(new Gallery(title, content, originalFileName, savedFileName, createdBy));
    }

    @Override
    public Gallery get(Long id) {
        Gallery gallery = galleryRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ADMIN));
        return gallery;
    }

    @Override
    public Gallery change(Long id, String title, String content, String originalFileName, String savedFileName) {
        Gallery gallery = galleryRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ADMIN));

        gallery.change(title,content,originalFileName,savedFileName);

        return gallery;
    }

    @Override
    public void delete(Long id) {
        Gallery gallery = galleryRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ADMIN));
        gallery.delete();
    }

    @Override
    public void changePublic(Long id) {
        Gallery gallery = galleryRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ADMIN));
        gallery.changeStatus(GalleryStatus.PUBLIC);
    }

    @Override
    public void changePrivate(Long id) {
        Gallery gallery = galleryRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ADMIN));
        gallery.changeStatus(GalleryStatus.PRIVATE);
    }
}

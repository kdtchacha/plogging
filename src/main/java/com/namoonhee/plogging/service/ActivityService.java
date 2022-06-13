package com.namoonhee.plogging.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.namoonhee.plogging.model.ActFile;
import com.namoonhee.plogging.model.Activity;

public interface ActivityService {
    
    List<ActFile> download(Activity activity);

    void delete_activity(Long id);

    void visibility(Long id, HttpSession session);

    String lineData(Long actid);

    void answerCreate(String content, long actid, HttpSession session);

    Long answerDelete(Long id, HttpSession session);

    void activityCreate(HttpServletRequest req, HttpSession session, List<MultipartFile> photos);

    Optional<Activity> actUpdateForm(Long actid);

    void activityUpdate(HttpServletRequest req, HttpSession session, List<MultipartFile> photos);

    void actLike(HttpSession session, HttpServletRequest req);

}

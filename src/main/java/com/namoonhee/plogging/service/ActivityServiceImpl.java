package com.namoonhee.plogging.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.namoonhee.plogging.model.ActAnswer;
import com.namoonhee.plogging.model.ActFile;
import com.namoonhee.plogging.model.ActLike;
import com.namoonhee.plogging.model.Activity;
import com.namoonhee.plogging.model.User;
import com.namoonhee.plogging.repository.ActAnswerRepository;
import com.namoonhee.plogging.repository.ActFileRepository;
import com.namoonhee.plogging.repository.ActLikeRepository;
import com.namoonhee.plogging.repository.ActivityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ActivityServiceImpl implements ActivityService {

  @Autowired
  ActivityRepository activityRepository;

  @Autowired
  ActFileRepository actFileRepository;

  @Autowired
  ActAnswerRepository actAnswerRepository;

  @Autowired
  ActLikeRepository actLikeRepository;

  @Override
  public void activityCreate(HttpServletRequest req, HttpSession session, List<MultipartFile> photos) {
    String hour = req.getParameter("hour");
    String min = req.getParameter("min");
    String sec = req.getParameter("sec");
    String dist = req.getParameter("dist");
    String actname = req.getParameter("actname");
    String actmemo = req.getParameter("actmemo");
    String latlng = req.getParameter("latlng");

    String acttime = hour + ":" + min + ":" + sec;

    dist += "m";

    Activity act = new Activity();
    act.setActTime(acttime);
    act.setActDistance(dist);
    act.setActName(actname);
    act.setActMemo(actmemo);
    act.setLatlng(latlng);

    User user = (User) session.getAttribute("user");
    act.setUser(user);
    act.setCreateDate(new Date());
    activityRepository.save(act);

    for (MultipartFile mFile : photos) {
      String oName = mFile.getOriginalFilename();
      if (oName == null || oName.equals("")) {
        break;
      }

      /* 중복파일 검사 - 파일명 변경 */
      File f = new File("c:/project/" + oName);
      String sName = "";

      if (f.isFile()) { // 파일이 존재하는가?
        String fileName = oName.substring(0, oName.lastIndexOf("."));
        String fileExt = oName.substring(oName.lastIndexOf("."));
        sName = fileName + System.currentTimeMillis() + fileExt;
      } else {
        sName = oName;
      }

      try {
        ActFile af = new ActFile();
        af.setOriginalFileName(oName);
        af.setSaveFileName(sName);
        af.setActivity(act);
        actFileRepository.save(af);

        mFile.transferTo(new File("c:/project/" + sName));
      } catch (IllegalStateException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

  }

  @Override
  public List<ActFile> download(Activity activity) {
    List<ActFile> fList = actFileRepository.findByActivity(activity);
    return fList;
  }

  @Override
  public void delete_activity(Long id) {
    activityRepository.deleteById(id);
  }

  @Override
  public void visibility(Long id, HttpSession session) {
    Optional<Activity> act = activityRepository.findById(id);

    Activity vv = act.get();

    User suser = (User) session.getAttribute("user");

    if (vv.getUser().getId() == suser.getId()) {

      if (vv.getVisibility() == 0) {
        vv.setVisibility(1);
        activityRepository.save(vv);
      } else {
        vv.setVisibility(0);
        activityRepository.save(vv);
      }
    }
  }

  @Override
  public String lineData(Long actid) {
    Optional<Activity> opt = activityRepository.findById(actid);

    Activity act = opt.get();
    String linedata = act.getLatlng();

    return linedata;
  }

  @Override
  public void answerCreate(String content, long actid, HttpSession session) {
    ActAnswer answer = new ActAnswer();
    answer.setContent(content);
    answer.setCreateDate(new Date());

    Activity act = new Activity();
    act.setId(actid);

    User user = (User) session.getAttribute("user");

    answer.setUser(user);
    answer.setActivity(act);

    actAnswerRepository.save(answer);
  }

  @Override
  public Long answerDelete(Long id, HttpSession session) {
    User suser = (User) session.getAttribute("user");
    Optional<ActAnswer> dbanswer = actAnswerRepository.findById(id);

    if (suser.getId() == dbanswer.get().getUser().getId()) {
      actAnswerRepository.deleteById(id);
    }

    Long actid = dbanswer.get().getActivity().getId();

    return actid;
  }

  @Override
  public Optional<Activity> actUpdateForm(Long actid) {

    Optional<Activity> act = activityRepository.findById(actid);

    return act;
  }

  @Override
  public void activityUpdate(HttpServletRequest req, HttpSession session, List<MultipartFile> photos) {
    String acttime = req.getParameter("time");
    String dist = req.getParameter("dist");
    dist += "m";
    String actname = req.getParameter("actname");
    String actmemo = req.getParameter("actmemo");
    String latlng = req.getParameter("latlng");
    Long actid = Long.decode(req.getParameter("actid"));

    Activity act = activityRepository.findById(actid).get();

    act.setActTime(acttime);
    act.setActDistance(dist);
    act.setActName(actname);
    act.setActMemo(actmemo);
    act.setLatlng(latlng);

    activityRepository.save(act);

    // 에러나는 코드
    // actFileRepository.deleteByActivity(act);

    List<ActFile> files = actFileRepository.findByActivity(act);

    for (ActFile file : files) {
      actFileRepository.deleteById(file.getAfId());
    }

    for (MultipartFile mFile : photos) {
      String oName = mFile.getOriginalFilename();
      if (oName == null || oName.equals("")) {
        break;
      }

      /* 중복파일 검사 - 파일명 변경 */
      File f = new File("c:/project/" + oName);
      String sName = "";

      if (f.isFile()) { // 파일이 존재하는가?
        String fileName = oName.substring(0, oName.lastIndexOf("."));
        String fileExt = oName.substring(oName.lastIndexOf("."));
        sName = fileName + System.currentTimeMillis() + fileExt;
      } else {
        sName = oName;
      }

      try {
        ActFile af = new ActFile();
        af.setOriginalFileName(oName);
        af.setSaveFileName(sName);
        af.setActivity(act);
        actFileRepository.save(af);

        mFile.transferTo(new File("c:/project/" + sName));
      } catch (IllegalStateException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public void actLike(HttpSession session, HttpServletRequest req) {
    Long actid = Long.decode(req.getParameter("actid"));

    Activity dbact = activityRepository.findById(actid).get();

    User suser = (User) session.getAttribute("user");

    Optional<ActLike> opt = actLikeRepository.findByActivityAndUser(dbact,suser);

    if(!opt.isPresent()){
      ActLike actLike = new ActLike();
  
      actLike.setActivity(dbact);
      actLike.setUser(suser);
  
      actLikeRepository.save(actLike);
    }

  }

}

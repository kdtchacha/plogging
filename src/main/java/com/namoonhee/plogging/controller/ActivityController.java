package com.namoonhee.plogging.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.namoonhee.plogging.model.ActFile;
import com.namoonhee.plogging.model.Activity;
import com.namoonhee.plogging.model.User;
import com.namoonhee.plogging.repository.ActFileRepository;
import com.namoonhee.plogging.repository.ActivityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ActivityController {

    @Autowired
    ActivityRepository actRepository;

    @Autowired
    ActFileRepository actFileRepository;




    @GetMapping("/activityform")
    public String actForm() {
        return "activityform";
    }

    @ResponseBody
    @PostMapping("/act")
    public String actTimeTest(HttpServletRequest req, HttpSession session, List<MultipartFile> photos) {
        for (MultipartFile mFile : photos) {
            System.out.println(mFile.getOriginalFilename());

        }
        String aa = req.getParameter("hour");
        String a = req.getParameter("min");
        String b = req.getParameter("sec");
        String dist = req.getParameter("dist");
        String actname = req.getParameter("actname");
        String actmemo = req.getParameter("actmemo");
        String latlng = req.getParameter("latlng");

        System.out.println(a);
        System.out.println(b);

        String c = aa + ":" + a + ":" + b;
        dist += "m";
        System.out.println(c);

        Activity act = new Activity();
        act.setActTime(c);
        act.setActDistance(dist);
        act.setActName(actname);
        act.setActMemo(actmemo);
        act.setLatlng(latlng);

        User user = (User) session.getAttribute("user");
        act.setUser(user);
        act.setCreateDate(new Date());
        actRepository.save(act);

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

        return "aaa";
    }

    // @PostMapping("/imgupload")
    // public String imgUpload(MultipartHttpServletRequest mRequest, HttpSession
    // session) {
    // /* 첨부파일 저장 및 DB 저장 */
    // Iterator<String> iter = mRequest.getFileNames();
    // while (iter.hasNext()) {
    // String inputName = iter.next();
    // List<MultipartFile> mFiles = mRequest.getFiles(inputName);
    // for (MultipartFile mFile : mFiles) {
    // String oName = mFile.getOriginalFilename();
    // if (oName == null || oName.equals("")) {
    // break;
    // }

    // /* 중복파일 검사 - 파일명 변경 */
    // File f = new File("c:/project/" + oName);
    // String sName = "";

    // if (f.isFile()) { // 파일이 존재하는가?
    // String fileName = oName.substring(0, oName.lastIndexOf("."));
    // String fileExt = oName.substring(oName.lastIndexOf("."));
    // sName = fileName + System.currentTimeMillis() + fileExt;
    // } else {
    // sName = oName;
    // }

    // try {
    // User user = (User) session.getAttribute("user");
    // Sort sort = Sort.by(Sort.Direction.DESC, "createDate");
    // Pageable p = PageRequest.of(0, 1, sort);
    // List<Activity> dbact = actRepository.findByUser_id(user.getId(), p);
    // Activity act = dbact.get(0);
    // ActFile af = new ActFile();
    // af.setOriginalFileName(oName);
    // af.setSaveFileName(sName);
    // af.setActivity(act);
    // actFileRepository.save(af);

    // mFile.transferTo(new File("c:/project/" + sName));
    // } catch (IllegalStateException e) {
    // e.printStackTrace();
    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    // }
    // }

    // return "redirect:/mypage";
    // }

    @GetMapping("/camera")
    public String camera() {
        return "camera";
    }


    @GetMapping("/slide")
    public String slide() {
        return "slide";
    }



    @GetMapping(value = "/visibility")
    public String visibility(Long id) {

        Optional<Activity> actidd = actRepository.findById(id);

        Activity vv = actidd.get();

        if (vv.getVisibility() == 0) {
            vv.setVisibility(1);
            actRepository.save(vv);
        } else {
            vv.setVisibility(0);
            actRepository.save(vv);
        }

        return "redirect:/mypage";
    }

    @ResponseBody
    @GetMapping("linetest")
    public String linedata(Long actid) {

        Optional<Activity> a = actRepository.findById(actid);

        Activity b = a.get();

        String c = b.getLatlng();

        System.out.println(c);

        return c;
    }

}

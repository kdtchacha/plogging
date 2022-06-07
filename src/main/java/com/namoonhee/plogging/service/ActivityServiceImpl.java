package com.namoonhee.plogging.service;

import java.util.List;

import com.namoonhee.plogging.model.ActFile;
import com.namoonhee.plogging.model.Activity;
import com.namoonhee.plogging.repository.ActFileRepository;
import com.namoonhee.plogging.repository.ActivityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityServiceImpl implements ActivityService{


    @Autowired
    ActFileRepository actFileRepository;

    @Autowired
    ActivityRepository activityRepository;

    @Override
    public List<ActFile> download(Activity activity) {
      List<ActFile> fList = actFileRepository.findByActivity(activity);
        return fList;
    }

    @Override
    public void delete_activity(Long id) {
       activityRepository.deleteById(id);
      
    }
    
}

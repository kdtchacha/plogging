package com.namoonhee.plogging.service;

import java.util.List;

import com.namoonhee.plogging.model.ActFile;
import com.namoonhee.plogging.model.Activity;

public interface ActivityService {
    
    List<ActFile> download(Activity activity);

    void delete_activity(Long id);


}

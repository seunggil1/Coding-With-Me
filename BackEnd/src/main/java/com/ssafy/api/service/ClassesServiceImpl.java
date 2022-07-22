package com.ssafy.api.service;

//import com.ssafy.api.request.ClassesModifyPostReq;
import com.ssafy.api.request.ClassesModifyPostReq;
import com.ssafy.api.request.ClassesRegisterPostReq;

import com.ssafy.db.entity.Classes;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.ClassesRepository;
import com.ssafy.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("classesService")
public class ClassesServiceImpl implements ClassesService {

    @Autowired
    ClassesRepository classesRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Classes createClass(ClassesRegisterPostReq classesRegisterInfo) {
        Classes classes =new Classes();
        classes.setClassName(classesRegisterInfo.getClassName());
        classes.setClassDescription(classesRegisterInfo.getClassDescription());
        User user= userRepository.findByUserId(classesRegisterInfo.getTutorId()).get();
        classes.setUser(user);

        return classesRepository.save(classes);
    }


    @Override
    public Classes modifyClass(ClassesModifyPostReq classesModifyPostReq) {
        Classes classes = classesRepository.findByUserUserIdAndClassName(classesModifyPostReq.getTutorId(), classesModifyPostReq.getClassName()).get();

        classes.setClassName(classesModifyPostReq.getNewclassName());
        classes.setClassDescription(classesModifyPostReq.getClassDescription());

        return classesRepository.save(classes);
    }


}

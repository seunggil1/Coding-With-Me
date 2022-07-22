package com.ssafy.api.service;

import com.ssafy.api.request.ClassesRegisterPostReq;
import com.ssafy.db.entity.Classes;
import com.ssafy.db.repository.ClassesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("classesService")
public class ClassesServiceImpl implements ClassesService {

    @Autowired
    ClassesRepository classesRepository;

    @Override
    public Classes createClass(ClassesRegisterPostReq classesRegisterInfo) {
        Classes classes =new Classes();
        classes.setClassName(classesRegisterInfo.getClassName());
        classes.setClassDescription(classesRegisterInfo.getClassDescription());



        return classesRepository.save(classes);
    }
}

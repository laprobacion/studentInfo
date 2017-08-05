package main.dao;


import main.cache.MockData;
import main.model.Subject;

import java.util.List;

public class SubjectDAO {

    public Subject findSubjectByName(String name){
        List<Subject> subjects = MockData.getInstance().getSubjectList();
        for(Subject subj : subjects){
            if(subj.getName().equals(name)){
                return subj;
            }
        }
        return null;
    }
}

package com.example.eazyschool.service;

import com.example.eazyschool.constants.EazySchoolConstants;
import com.example.eazyschool.model.Contact;
import com.example.eazyschool.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequestScope
//@SessionScope
//@ApplicationScope

public class ContactService {
    private int counter = 0;

    @Autowired
    private ContactRepository contactRepository;


    public ContactService(){
        System.out.println("Contact Service Bean is created.");
    }

    public boolean saveMessageDetails(Contact contact){
        boolean isSaved = false;
        contact.setStatus(EazySchoolConstants.OPEN);
        contact.setCreatedBy((EazySchoolConstants.ANONYMOUS));
        contact.setCreatedAt(LocalDateTime.now());
        int result = contactRepository.saveContactMsg(contact);
        if(result>0){
            isSaved = true;
        }
        else{
            isSaved = false;
        }
        return isSaved;
    }

    public List<Contact> findMsgWithOpenStatus(){
        List<Contact> contactMsgs = contactRepository.findMsgsWithStatus(EazySchoolConstants.OPEN);
        return contactMsgs;
    }

    public boolean updatedMsgStatus(int contactId,String updatedBy){
        boolean isUpdated = false;
        int result = contactRepository.updateMsgStatus(contactId,EazySchoolConstants.CLOSE,updatedBy);
        if(result>0){
            isUpdated = true;
        }
        return isUpdated;
    }

    public int getCounter(){
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}

package xcu.lxj.ssmchat.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xcu.lxj.ssmchat.mapper.UserContactMapper;
import xcu.lxj.ssmchat.mapper.UserMapper;
import xcu.lxj.ssmchat.pojo.User;
import xcu.lxj.ssmchat.pojo.UserContact;
import xcu.lxj.ssmchat.service.UserContactService;

import java.util.ArrayList;
import java.util.List;


@Transactional
@Service
public class UserContactServiceImpl implements UserContactService {

    @Resource
    UserContactMapper userContactMapper;
    @Resource
    UserMapper userMapper;

    @Override
    public List<UserContact> getContact(String uid) {

        List<UserContact> userContacts = userContactMapper.selectContact(uid);
        List<UserContact> filteredContacts = new ArrayList<UserContact>();
        for(UserContact contact : userContacts){
            if(contact.getFriend()!=null || contact.getGroup()!=null){
                filteredContacts.add(contact);
            }
        }
        return filteredContacts;
    }

    @Override
    public UserContact saveContact(String token, UserContact userContact) {
        User user = userMapper.selectOneByToken(token);
        String uid=user.getUid();
        userContactMapper.insertOne(uid,userContact);
        return userContact;
    }
}

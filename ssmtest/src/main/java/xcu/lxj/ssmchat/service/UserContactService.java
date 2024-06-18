package xcu.lxj.ssmchat.service;

import xcu.lxj.ssmchat.pojo.UserContact;

import java.util.List;

public interface UserContactService {

    List<UserContact> getContact(String uid);

    UserContact saveContact(String token, UserContact userContact);

}

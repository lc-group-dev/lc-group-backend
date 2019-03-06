package org.whu.cs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.whu.cs.bean.GroupContantValue;
import org.whu.cs.bean.UserAddress;
import org.whu.cs.repository.UserAddressRepository;

import java.util.Date;
import java.util.List;

@Service
public class UserAddressService {

    @Autowired
    private UserAddressRepository userAddressRepository;

    public UserAddress create(UserAddress userAddress) {
        userAddress.setGmt_create(new Date());
        userAddress.setStatus(GroupContantValue.getNormal());
        return userAddressRepository.save(userAddress);
    }

    public List<UserAddress> getUserAddressByStatus(int status) {
        return userAddressRepository.findByStatus(status);
    }

    public UserAddress findByAddress(String address){
        return userAddressRepository.findByAddress(address);
    }
}

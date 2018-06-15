package com.e3expo.mms.bean.model;

import com.e3expo.mms.bean.param.UserParam;

public class UnmodifiableUser extends User{

    private User user;

    public UnmodifiableUser() {
    }

    public UnmodifiableUser(User user) {
        // todo
    }

    @Override
    public Role getRole() {
        if (super.getRole() == null) return null;
        Role snapshot = new Role();
        snapshot.setId(super.getRole().getId());
        snapshot.setFullName(super.getRole().getFullName());
        snapshot.setName(super.getRole().getName());
        return snapshot;
    }

    @Override
    public City getCity() {
        if (super.getCity() == null) return null;
        return new City(super.getCity().getId(), super.getCity().getName(),
                super.getCity().getNickName(), super.getCity().getStatus());
    }

    @Override
    public void setId(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setCityID(byte cityID) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setName(String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setPhoneAreaCode(String phoneAreaCode) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setEmail(String email) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setIsResigned(byte isResigned) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setPassword(String password) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setCreateTime(long createTime) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setModifiedTime(long modifiedTime) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setRole(Role role) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setCity(City city) {
        throw new UnsupportedOperationException();
    }
}

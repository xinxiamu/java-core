package com.design.pattern.proxy.sample02;

public interface AbstractPermission {
    void modifyUserInfo();

    void viewNote();

    void publishNote();

    void modifyNote();

    void setLevel(int level);
}
package com.simin.rxjava2.http.model;

import java.io.Serializable;

/**
 * @author fsm
 * @ClassName: User
 * @Description: 登录用户, 及联络人
 * @date 2015-12-28 下午3:45:04
 */
public class User extends BaseModel implements Serializable {

    private String loginid;
    private String orgid;
    private String orgname;
    private String imagepath;
    private String registerid;
    private String opername;
    private String rolename;
    private String operphone;
    private String operid;
    private String fax;
    private String orgphone;
    private String formname;
    private String appupdate;//0:当前最新版本，1：有新版本，请更新，2：新版本强制更新
    private String appupdatepath;//APP更新地址
    private String topmanager;//是否总经理
    private String demandFormName;//潜在需求表form名
    private String qrcodepath;//二维码拼写前缀

    public User() {
    }

    public User(String opername, String rolename, String operphone) {
        this.opername = opername;
        this.rolename = rolename;
        this.operphone = operphone;
    }

    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    public String getRegisterid() {
        return registerid;
    }

    public void setRegisterid(String registerid) {
        this.registerid = registerid;
    }

    public String getOpername() {
        return opername;
    }

    public void setOpername(String opername) {
        this.opername = opername;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getOperphone() {
        return operphone;
    }

    public void setOperphone(String operphone) {
        this.operphone = operphone;
    }

    public String getOperid() {
        return operid;
    }

    public void setOperid(String operid) {
        this.operid = operid;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getOrgphone() {
        return orgphone;
    }

    public void setOrgphone(String orgphone) {
        this.orgphone = orgphone;
    }

    public String getFormname() {
        return formname;
    }

    public void setFormname(String formname) {
        this.formname = formname;
    }

    public String getAppupdate() {
        return appupdate;
    }

    public void setAppupdate(String appupdate) {
        this.appupdate = appupdate;
    }

    public String getAppupdatepath() {
        return appupdatepath;
    }

    public void setAppupdatepath(String appupdatepath) {
        this.appupdatepath = appupdatepath;
    }

    public String getTopmanager() {
        return topmanager;
    }

    public void setTopmanager(String topmanager) {
        this.topmanager = topmanager;
    }

    public String getDemandFormName() {
        return demandFormName;
    }

    public void setDemandFormName(String demandFormName) {
        this.demandFormName = demandFormName;
    }

    public String getQrcodepath() {
        return qrcodepath;
    }

    public void setQrcodepath(String qrcodepath) {
        this.qrcodepath = qrcodepath;
    }

    @Override
    public String toString() {
        return "User{" +
                "loginid='" + loginid + '\'' +
                ", orgid='" + orgid + '\'' +
                ", orgname='" + orgname + '\'' +
                ", imagepath='" + imagepath + '\'' +
                ", registerid='" + registerid + '\'' +
                ", opername='" + opername + '\'' +
                ", rolename='" + rolename + '\'' +
                ", operphone='" + operphone + '\'' +
                ", operid='" + operid + '\'' +
                ", fax='" + fax + '\'' +
                ", orgphone='" + orgphone + '\'' +
                ", formname='" + formname + '\'' +
                ", appupdate='" + appupdate + '\'' +
                ", appupdatepath='" + appupdatepath + '\'' +
                ", topmanager='" + topmanager + '\'' +
                ", demandFormName='" + demandFormName + '\'' +
                ", qrcodepath='" + qrcodepath + '\'' +
                '}';
    }
}

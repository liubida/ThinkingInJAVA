package com.liubida.ThinkingInJAVA.basic;

/*
 * Copyright 2011 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */

/**
 * @author liubida 2011-12-3 上午9:10:09
 */
public class Server1 {

    public Integer  id;
    public Integer  machineRoomId;
//    private Integer  logicSiteId;
//    private Integer  cabinetId;
//    private Integer  deviceModelId;
//    private Integer  departmentId;
//    private String   serviceTag;
//    private String   comments;
//    private String   hostname;
//    private String   ip;
//    private String   cabinetPositionNum;
//    private String   cabinetSlotNum;
//    private String   manageIp;
//    private Integer  responsibilityPersonUserId;
//    private Integer  secondResponsibilityPersonUserId;
//    private UseState useState;

    // private Integer lockedUserId;
    // 默认值不可靠,json转换后可能为null,所以保存的地方需要处理
    // private Boolean isWaitingCheck = false;
    // private String multipleIp;
    // private String loopbackIp;
    // private Integer hardwareOwner;
    // private String updateStateMsg;
    // private ManagerState managerState;
    // private AssetsState assetsState;
    // private Integer parentDeviceId;

    // 资产属性,从财务系统更新
    // private Date buyTime;
    // private Double originalCost;
    // private Date outGuaranteeTime;
    // private String po;

    // 用于邮件发送的临时时间保存,不用于其他任何地方
    // private Date modifyTmpTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMachineRoomId() {
        return machineRoomId;
    }

    public void setMachineRoomId(Integer machineRoomId) {
        this.machineRoomId = machineRoomId;
    }
//
//    public Integer getLogicSiteId() {
//        return logicSiteId;
//    }
//
//    public void setLogicSiteId(Integer logicSiteId) {
//        this.logicSiteId = logicSiteId;
//    }
//
//    public Integer getCabinetId() {
//        return cabinetId;
//    }
//
//    public void setCabinetId(Integer cabinetId) {
//        this.cabinetId = cabinetId;
//    }
//
//    public Integer getDeviceModelId() {
//        return deviceModelId;
//    }
//
//    public void setDeviceModelId(Integer deviceModelId) {
//        this.deviceModelId = deviceModelId;
//    }
//
//    public String getServiceTag() {
//        return serviceTag;
//    }
//
//    public void setServiceTag(String serviceTag) {
//        this.serviceTag = serviceTag;
//    }
//
//    public String getComments() {
//        return comments;
//    }
//
//    public void setComments(String comments) {
//        this.comments = comments;
//    }
//
//    public String getHostname() {
//        return hostname;
//    }
//
//    public void setHostname(String hostname) {
//        this.hostname = hostname;
//    }
//
//    public String getIp() {
//        return ip;
//    }
//
//    public void setIp(String ip) {
//        this.ip = ip;
//    }
//
//    public String getCabinetPositionNum() {
//        return cabinetPositionNum;
//    }
//
//    public void setCabinetPositionNum(String cabinetPositionNum) {
//        this.cabinetPositionNum = cabinetPositionNum;
//    }
//
//    public String getCabinetSlotNum() {
//        return cabinetSlotNum;
//    }
//
//    public void setCabinetSlotNum(String cabinetSlotNum) {
//        this.cabinetSlotNum = cabinetSlotNum;
//    }
//
//    public String getManageIp() {
//        return manageIp;
//    }
//
//    public void setManageIp(String manageIp) {
//        this.manageIp = manageIp;
//    }
//
//    public Integer getResponsibilityPersonUserId() {
//        return responsibilityPersonUserId;
//    }
//
//    public void setResponsibilityPersonUserId(Integer responsibilityPersonUserId) {
//        this.responsibilityPersonUserId = responsibilityPersonUserId;
//    }
//
////    public UseState getUseState() {
////        return useState;
////    }
////
////    public void setUseState(UseState useState) {
////        this.useState = useState;
////    }
//
//    public Integer getSecondResponsibilityPersonUserId() {
//        return secondResponsibilityPersonUserId;
//    }
//
//    public void setSecondResponsibilityPersonUserId(Integer secondResponsibilityPersonUserId) {
//        this.secondResponsibilityPersonUserId = secondResponsibilityPersonUserId;
//    }
//
//    public Integer getDepartmentId() {
//        return departmentId;
//    }
//
//    public void setDepartmentId(Integer departmentId) {
//        this.departmentId = departmentId;
//    }
}

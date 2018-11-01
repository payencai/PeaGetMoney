package com.yichan.pea.basic.api;

public class Api {
    public static final String baseUrl="http://47.106.115.182:8085/";
    public static class MerchantUser{
         public static final String login="merchantUser/Merchantlogin";
         public static final String sendCode="merchantUser/sendCode";
        public static final String modifyPwd= "merchantUser/updateMerchantUserPassword";
        public static final String register="merchantUser/Merchantregister";
        public static final String forgetPwd="merchantUser/forgetMerchantUserPassword";
    }
}

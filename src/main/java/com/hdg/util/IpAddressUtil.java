package com.hdg.util;

import com.hdg.entity.ExecuteResult;
import com.hdg.entity.IpAddress;
import com.hdg.entity.PageParam;
import com.hdg.entity.QueryResult;
import com.hdg.other.Constant;
import com.hdg.service.BasicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by BlueBuff on 2017/7/15.
 */
public class IpAddressUtil {


    public IpAddressUtil(){
        if(logger.isInfoEnabled()){
            logger.info("ip工具类");
        }
    }

    private BasicService basicService;

    private static Logger logger= LoggerFactory.getLogger(IpAddressUtil.class);
    private Set<IpAddress> ipAddressSet;

    public boolean isExist(IpAddress ipAddress){
        if(ipAddressSet==null || ipAddressSet.isEmpty()){
            synchronized (this){
                if(ipAddressSet==null || ipAddressSet.isEmpty()){
                    ipAddressSet=new HashSet<>();
                    init();
                }
            }
        }
        return ipAddressSet.contains(ipAddress);
    }

    private void init(){
        if(logger.isInfoEnabled()){
            logger.info("正在加载ip信息");
        }
        PageParam pageParam=new PageParam();
        pageParam.setIgnore(true);
        ExecuteResult<QueryResult<List<IpAddress>>> executeResult = basicService.queryIpAddress(pageParam);
        if(executeResult.getCode()== Constant.SUCCESS){
            QueryResult<List<IpAddress>> queryResult=executeResult.getObj();
            List<IpAddress> list=queryResult.getData();
            ipAddressSet.addAll(list);
        }
        if(logger.isInfoEnabled()){
            logger.info("Ip信息加载成功，当前IP数量为,[{}]",ipAddressSet.size());
        }
    }



    /**
     * 获得本机的IP地址,优先得到的是局域网地址
     * @return
     * @throws Exception
     */
    public InetAddress getLocalHostLANAddress() throws Exception {
        try {
            InetAddress candidateAddress = null;
            // 遍历所有的网络接口
            for (Enumeration ifaces = NetworkInterface.getNetworkInterfaces(); ifaces.hasMoreElements(); ) {
                NetworkInterface iface = (NetworkInterface) ifaces.nextElement();
                // 在所有的接口下再遍历IP
                for (Enumeration inetAddrs = iface.getInetAddresses(); inetAddrs.hasMoreElements(); ) {
                    InetAddress inetAddr = (InetAddress) inetAddrs.nextElement();
                    if (!inetAddr.isLoopbackAddress()) {// 排除loopback类型地址
                        if (inetAddr.isSiteLocalAddress()) {
                            // 如果是site-local地址，就是它了
                            return inetAddr;
                        } else if (candidateAddress == null) {
                            // site-local类型的地址未被发现，先记录候选地址
                            candidateAddress = inetAddr;
                        }
                    }
                }
            }
            if (candidateAddress != null) {
                return candidateAddress;
            }
            // 如果没有发现 non-loopback地址.只能用最次选的方案
            InetAddress jdkSuppliedAddress = InetAddress.getLocalHost();
            return jdkSuppliedAddress;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址;
     *
     * @param request
     * @return
     */
    public final String getIpAddress(HttpServletRequest request){
        // 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址
        String ip = request.getHeader("X-Forwarded-For");

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
        } else if (ip.length() > 15) {// 通过代理访问，返回多个IP取第一个
            String[] ips = ip.split(",");
            for (int index = 0; index < ips.length; index++) {
                String strIp = (String) ips[index];
                if (!("unknown".equalsIgnoreCase(strIp))) {
                    ip = strIp;
                    break;
                }
            }
        }
        return ip;
    }

    public BasicService getBasicService() {
        return basicService;
    }

    public void setBasicService(BasicService basicService) {
        this.basicService = basicService;
    }
}

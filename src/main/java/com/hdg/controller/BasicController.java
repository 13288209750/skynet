package com.hdg.controller;

import com.github.bingoohuang.patchca.color.ColorFactory;
import com.github.bingoohuang.patchca.color.SingleColorFactory;
import com.github.bingoohuang.patchca.custom.ConfigurableCaptchaService;
import com.github.bingoohuang.patchca.filter.predefined.CurvesRippleFilterFactory;
import com.github.bingoohuang.patchca.utils.encoder.EncoderHelper;
import com.github.bingoohuang.patchca.word.RandomWordFactory;
import com.hdg.annotation.IpAspectAnnotation;
import com.hdg.entity.*;
import com.hdg.service.BasicService;
import com.hdg.util.ConfigUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by BlueBuff on 2017/7/15.
 */
@Controller
@RequestMapping(value = {"/basic"})
public class BasicController {

    @Autowired
    private BasicService basicService;

    @ResponseBody
    @RequestMapping(value = "/getIpAddressInfo")
    @IpAspectAnnotation(description = "获取IP地址信息接口")
    public Result<List<IpAddress>> getIpAddressInfo(PageParam pageParam){
        if(pageParam.getPageNo()==null||pageParam.getPageSize()==null){
            pageParam.setPageNo(1);
            pageParam.setPageSize(10);
        }
        ExecuteResult<QueryResult<List<IpAddress>>> executeResult=basicService.queryIpAddress(pageParam);
        return new Result(executeResult.getCode(),executeResult.getObj(),executeResult.getMsg());
    }

    /**
     * 图形验证码
     * @param response
     * @param request
     */
    @RequestMapping(value = { "/getImgCode" }, method = RequestMethod.GET)
    @ResponseBody
    public void checkImg(HttpServletResponse response, HttpServletRequest request, HttpSession session) {
        ConfigurableCaptchaService cs = new ConfigurableCaptchaService();
        ColorFactory cf = new SingleColorFactory(new Color(2, 170, 0));
        RandomWordFactory wf = new RandomWordFactory();
        CurvesRippleFilterFactory crff = new CurvesRippleFilterFactory(cs.getColorFactory());// 曲线波纹
        cs.setWordFactory(wf);
        cs.setColorFactory(cf);
        cs.setWidth(100);
        cs.setHeight(50);
        wf.setMaxLength(4);
        wf.setMinLength(4);
        response.setHeader("P3P","CP=CAO PSA OUR");
        response.setContentType("image/png");
        response.setHeader("cache", "no-cache");
        session = request.getSession(true);
        try {
            OutputStream os = response.getOutputStream();
            cs.setFilterFactory(crff);
            ImageIO.setUseCache(false);
            String patchca = EncoderHelper.getChallangeAndWriteImage(cs, "png",os);
            session.setAttribute(ConfigUtil.getConfigInfo("PHOTO_CODE"), patchca);
            System.out.println(patchca);
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

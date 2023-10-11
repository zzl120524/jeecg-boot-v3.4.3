package org.jeecg.modules.demo.xxljob;

import com.sdt.omap.protocol.cdr.CDRFile;
import com.sdt.omap.protocol.cdr.CDRRecordSip;
import com.sdt.omap.protocol.cdr.HeadRecord;
import com.sdt.omap.protocol.cdr.TailRecord;
import com.sdt.omap.protocol.enumUtil.ParaFlagType;
import com.sdt.omap.protocol.enumUtil.RecType;
import com.sdt.omap.protocol.enumUtil.ReleaseCauseType;
import com.sdt.omap.utils.BCDUtils;
import com.sdt.util.standard.ByteUtil;
import org.jeecg.common.util.ByteConversionUtils;
import org.jeecg.modules.demo.cdrManage.entity.CdrFile;
import org.jeecg.modules.demo.cdrManage.entity.CdrManage;
import org.jeecg.modules.demo.cdrManage.mapper.CdrFileMapper;
import org.jeecg.modules.demo.cdrManage.service.ICdrManageService;
import org.jeecg.modules.demo.workLog.mapper.WorkLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
@EnableScheduling
public class DeleteWorkLogTask {


    @Value("${deleteWorkLogDay}")
    private int day;

    @Autowired
    private WorkLogMapper workLogMapper;

    //每10秒执行一次

    /**
     * 注解中的Cron表达式: {秒数} {分钟} {小时} {日期} {月份} {星期} {年份(可为空)}
     * 注意：日和周其中的一个必须为"?"
     * 10/5 20 10 * * ? 每天10点20分第10秒以后，每5秒执行一次，到10点21分就不会执行了
     */
    //    @Scheduled(cron = "0/10 * * * * ?")
    @Scheduled(cron = "0 46 10 * * ?")
    public void startDeleteWorkLogTask() throws Exception {
        System.out.println("定时删除工作日志启动：" + new Date());

        try {
            //删除30天前的数据
            Date now=new Date();
            String deleteDate = getDeleteDate(now,day);
            workLogMapper.deleteByDate(deleteDate);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    public static String getDeleteDate(Date now,int days){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.DATE, -days);
        Date delete=calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateString=sdf.format(delete);
        return dateString;
    }


}

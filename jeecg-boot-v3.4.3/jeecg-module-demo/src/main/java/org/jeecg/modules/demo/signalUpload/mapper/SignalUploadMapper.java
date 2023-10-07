package org.jeecg.modules.demo.signalUpload.mapper;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.demo.signalUpload.entity.SignalUpload;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.demo.signalUpload.entity.SignalUploadVo;

/**
 * @Description: 信令上传
 * @Author: jeecg-boot
 * @Date:   2023-08-21
 * @Version: V1.0
 */
public interface SignalUploadMapper extends BaseMapper<SignalUpload> {

    Page<SignalUpload> pageList(Page<SignalUpload> page,  @Param("param") SignalUploadVo param);

    List<SignalUpload> pageList(@Param("param") SignalUploadVo param);

    Page<SignalUpload> getCdrByRelationId(Page<SignalUpload> page, @Param("id")String id);

    Page<SignalUpload> currentUser(Page<SignalUpload> page, @Param("param")SignalUploadVo param);
}

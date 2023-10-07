package org.jeecg.modules.demo.facAuthKey.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.demo.facAuthKey.entity.FacAuthKey;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: facId认证秘钥
 * @Author: jeecg-boot
 * @Date:   2023-08-11
 * @Version: V1.0
 */
public interface FacAuthKeyMapper extends BaseMapper<FacAuthKey> {

    void updatebyFacId(@Param("param") FacAuthKey param);

    IPage<FacAuthKey> pagelist(Page<FacAuthKey> page, @Param("param")FacAuthKey param);

    IPage<FacAuthKey> getList(Page<FacAuthKey> page);

    List<Integer> allFacId();

}

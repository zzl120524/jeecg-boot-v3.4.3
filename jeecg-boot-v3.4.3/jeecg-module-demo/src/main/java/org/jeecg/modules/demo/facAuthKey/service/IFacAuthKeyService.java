package org.jeecg.modules.demo.facAuthKey.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sdt.omap.protocol.auth.AuthReq;
import org.jeecg.modules.demo.facAuthKey.entity.FacAuthKey;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: facId认证秘钥
 * @Author: jeecg-boot
 * @Date:   2023-08-11
 * @Version: V1.0
 */
public interface IFacAuthKeyService extends IService<FacAuthKey> {

    void add(FacAuthKey facAuthKey);

    void updateByFacId(FacAuthKey facAuthKey);

    Boolean checking(AuthReq authReq) throws Exception;

    IPage<FacAuthKey> pagelist(Page<FacAuthKey> page, FacAuthKey facAuthKey);

    IPage<FacAuthKey> getList(Page<FacAuthKey> page);

    byte[] authKey(AuthReq authReq)throws Exception;
}

package com.purchase.file.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.purchase.file.entity.File;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author ZhengHuaJing
 * @since 2020-12-11
 */
public interface FileService extends IService<File> {

    /**
     * 保存文件
     *
     * @param multipartFile
     * @return
     */
    File saveFile(MultipartFile multipartFile);
}

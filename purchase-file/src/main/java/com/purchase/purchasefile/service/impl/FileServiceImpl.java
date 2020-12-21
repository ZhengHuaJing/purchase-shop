package com.purchase.purchasefile.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.purchase.purchasefile.entity.File;
import com.purchase.purchasefile.mapper.FileMapper;
import com.purchase.purchasefile.service.FileService;
import com.purchase.purchasefile.util.FastDFSUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ZhengHuaJing
 * @since 2020-12-11
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements FileService {

    /**
     * 保存文件
     *
     * @param multipartFile
     * @return
     */
    @Override
    public File saveFile(MultipartFile multipartFile) {
        File file = new File();
        file.setName(multipartFile.getOriginalFilename());
        file.setExt(file.getName().substring(file.getName().lastIndexOf(".") + 1));
        String[] fileAbsolutePath = {};
        byte[] file_buff = null;

        try (InputStream inputStream = multipartFile.getInputStream()) {
            int len1 = inputStream.available();
            file_buff = new byte[len1];
            inputStream.read(file_buff);
            file.setContent(file_buff);

            fileAbsolutePath = FastDFSUtil.upload(file);
            String path = FastDFSUtil.getTrackerUrl() + fileAbsolutePath[0] + "/" + fileAbsolutePath[1];
            file.setPath(path);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return file;
    }
}

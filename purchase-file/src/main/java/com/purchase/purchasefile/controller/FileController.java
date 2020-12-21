package com.purchase.purchasefile.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.purchase.purchasecommon.lang.Response;
import com.purchase.purchasefile.entity.File;
import com.purchase.purchasefile.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ZhengHuaJing
 * @since 2020-12-11
 */
@Api(value = "文件管理", tags = "文件管理")
@RestController
@RequestMapping("/files")
public class FileController {

    @Autowired
    private FileService fileService;

    @ApiOperation("添加文件")
//    @PreAuthorize("hasAuthority('FileController:addFile')")
    @PostMapping("")
    public Response addFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return Response.fail("请选择上传文件");
        }

        File fsFile = fileService.saveFile(file);
        if (fsFile.getPath().isEmpty()) {
            return Response.fail("保存文件失败");
        }

        fsFile.setSize(file.getSize());

        if (!fileService.save(fsFile)) {
            return Response.fail("文件信息写入数据库失败");
        }

        return Response.success(fsFile.getPath());
    }

    @ApiOperation("删除文件")
    @ApiImplicitParam(name = "id", value = "文件ID", dataType = "Integer", paramType = "path", required = true)
    @PreAuthorize("hasAuthority('FileController:deleteFile')")
    @DeleteMapping("/{id}")
    public Response deleteFile(@PathVariable(name = "id") Integer id) {
        return Response.assess(fileService.removeById(id));
    }

    @ApiOperation("更新文件")
    @ApiImplicitParam(name = "id", value = "文件ID", dataType = "Integer", paramType = "path", required = true)
    @PreAuthorize("hasAuthority('FileController:updateFile')")
    @PutMapping("/{id}")
    public Response updateFile(@PathVariable(name = "id") Integer id, @Valid @RequestBody File file) {
        file.setId(id);
        return Response.assess(fileService.updateById(file));
    }

    @ApiOperation("详情文件")
    @ApiImplicitParam(name = "id", value = "文件ID", dataType = "Integer", paramType = "path", required = true)
    @PreAuthorize("hasAuthority('FileController:getFile')")
    @GetMapping("/{id}")
    public Response getFile(@PathVariable(name = "id") Integer id) {
        return Response.success(fileService.getById(id));
    }

    @ApiOperation("列表文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", dataType = "Integer", paramType = "query", required = false),
            @ApiImplicitParam(name = "size", value = "每页显示数", dataType = "Integer", paramType = "query", required = false),
            @ApiImplicitParam(name = "search", value = "搜索词", dataType = "String", paramType = "query", required = false),
            @ApiImplicitParam(name = "idOrder", value = "按照ID升序或降序", dataType = "Boolean", paramType = "query", required = false),
    })
    @PreAuthorize("hasAuthority('FileController:listFile')")
    @GetMapping("")
    public Response listFile(@RequestParam(name = "page", defaultValue = "1") Integer page,
                             @RequestParam(name = "size", defaultValue = "10") Integer size,
                             String search,
                             Boolean idOrder) {
        // 搜索查询
        LambdaQueryWrapper<File> qw = new QueryWrapper<File>().lambda()
                .like(search != null && !search.equals(""), File::getId, search)
                .orderBy(idOrder != null, idOrder != null && idOrder, File::getId);

        Page<File> p = new Page<>(page, size);
        IPage<File> iPage = fileService.page(p, qw);

        Map<String, Object> map = new HashMap<>();
        map.put("items", iPage.getRecords());
        map.put("total", iPage.getTotal());

        return Response.success(map);
    }

}

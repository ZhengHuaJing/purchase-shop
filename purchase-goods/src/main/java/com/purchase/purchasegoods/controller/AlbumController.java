package com.purchase.purchasegoods.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.purchase.purchasecommon.pojo.PageResult;
import com.purchase.purchasecommon.pojo.Result;
import com.purchase.purchasegoods.entity.Album;
import com.purchase.purchasegoods.service.AlbumService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ZhengHuaJing
 * @since 2020-12-20
 */
@Api(value = "相册管理", tags = "相册管理")
@RestController
@RequestMapping("/albums")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @ApiOperation("添加相册")
    @PreAuthorize("hasAuthority('AlbumController:addAlbum')")
    @PostMapping()
    public Result addAlbum(@Valid @RequestBody Album album) {
        return Result.assess(albumService.save(album));
    }

    @ApiOperation("删除相册")
    @ApiImplicitParam(name = "id", value = "相册ID", dataType = "Integer", paramType = "path", required = true)
    @PreAuthorize("hasAuthority('AlbumController:deleteAlbum')")
    @DeleteMapping("/{id}")
    public Result deleteAlbum(@PathVariable(name = "id") Integer id) {
        return Result.assess(albumService.removeById(id));
    }

    @ApiOperation("更新相册")
    @ApiImplicitParam(name = "id", value = "相册ID", dataType = "Integer", paramType = "path", required = true)
    @PreAuthorize("hasAuthority('AlbumController:updateAlbum')")
    @PutMapping("/{id}")
    public Result updateAlbum(@PathVariable(name = "id") Long id, @Valid @RequestBody Album album) {
        album.setId(id);
        return Result.assess(albumService.updateById(album));
    }

    @ApiOperation("相册详情")
    @ApiImplicitParam(name = "id", value = "相册ID", dataType = "Integer", paramType = "path", required = true)
    @PreAuthorize("hasAuthority('AlbumController:getAlbum')")
    @GetMapping("/{id}")
    public Result getAlbum(@PathVariable(name = "id") Integer id) {
        return Result.success(albumService.getById(id));
    }

    @ApiOperation("相册全部数据")
    @PreAuthorize("hasAuthority('AlbumController:listAlbum')")
    @GetMapping()
    public Result listAlbum() {
        List<Album> list = albumService.list();
        PageResult<Album> albumPageResult = new PageResult<>(list, list.size());
        return Result.success(albumPageResult);
    }

    @ApiOperation("相册分页和条件搜索")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", dataType = "Integer", paramType = "path", required = true),
            @ApiImplicitParam(name = "size", value = "数量", dataType = "Integer", paramType = "path", required = true)
    })
    @PreAuthorize("hasAuthority('AlbumController:listAlbumQueryPage')")
    @PostMapping("/search/{page}/{size}")
    public Result listAlbumQueryPage(@RequestBody @Valid Album album, @PathVariable Integer page, @PathVariable Integer size) {
        IPage<Album> iPage = albumService.page(new Page<>(page, size), new QueryWrapper<>(album));
        PageResult<Album> albumPageResult = new PageResult<>(iPage.getRecords(), (int) iPage.getTotal());
        return Result.success(albumPageResult);
    }

    @ApiOperation("相册分页搜索")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", dataType = "Integer", paramType = "path", required = true),
            @ApiImplicitParam(name = "size", value = "数量", dataType = "Integer", paramType = "path", required = true)
    })
    @PreAuthorize("hasAuthority('AlbumController:listAlbumPage')")
    @GetMapping("/search/{page}/{size}")
    public Result listAlbumPage(@PathVariable Integer page, @PathVariable Integer size) {
        IPage<Album> iPage = albumService.page(new Page<>(page, size), new QueryWrapper<>());
        PageResult<Album> albumPageResult = new PageResult<>(iPage.getRecords(), (int) iPage.getTotal());
        return Result.success(albumPageResult);
    }

    @ApiOperation("相册条件搜索")
    @PreAuthorize("hasAuthority('AlbumController:ListAlbumQuery')")
    @PostMapping("/search")
    public Result ListAlbumQuery(@RequestBody @Valid Album album) {
        List<Album> list = albumService.list(new QueryWrapper<>(album));
        PageResult<Album> albumPageResult = new PageResult<>(list, list.size());
        return Result.success(albumPageResult);
    }
}

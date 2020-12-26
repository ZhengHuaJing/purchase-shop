package com.purchase.purchaseservicegoods.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.purchase.purchasecommon.pojo.PageResult;
import com.purchase.purchasecommon.pojo.Result;
import com.purchase.purchaseservicegoods.service.ParaService;
import com.purchase.purchaseservicegoodsapi.entity.Para;
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
 * @ 2020-12-20
 */
@Api(value = "参数管理", tags = "参数管理")
@RestController
@RequestMapping("/para")
public class ParaController {

    @Autowired
    private ParaService paraService;

    @ApiOperation("添加参数")
    @PreAuthorize("hasAuthority('ParaController:addPara')")
    @PostMapping()
    public Result addPara(@Valid @RequestBody Para para) {
        return Result.assess(paraService.save(para));
    }

    @ApiOperation("删除参数")
    @ApiImplicitParam(name = "id", value = "参数ID", dataType = "Integer", paramType = "path", required = true)
    @PreAuthorize("hasAuthority('ParaController:deletePara')")
    @DeleteMapping("/{id}")
    public Result deletePara(@PathVariable(name = "id") Integer id) {
        return Result.assess(paraService.removeById(id));
    }

    @ApiOperation("更新参数")
    @ApiImplicitParam(name = "id", value = "参数ID", dataType = "Integer", paramType = "path", required = true)
    @PreAuthorize("hasAuthority('ParaController:updatePara')")
    @PutMapping("/{id}")
    public Result updatePara(@PathVariable(name = "id") Integer id, @RequestBody Para para) {
        para.setId(id);
        return Result.assess(paraService.updateById(para));
    }

    @ApiOperation("参数详情")
    @ApiImplicitParam(name = "id", value = "参数ID", dataType = "Integer", paramType = "path", required = true)
    @PreAuthorize("hasAuthority('ParaController:getPara')")
    @GetMapping("/{id}")
    public Result getPara(@PathVariable(name = "id") Integer id) {
        return Result.success(paraService.getById(id));
    }

    @ApiOperation("参数全部数据")
    @PreAuthorize("hasAuthority('ParaController:listPara')")
    @GetMapping()
    public Result listPara() {
        List<Para> list = paraService.list();
        PageResult<Para> paraPageResult = new PageResult<>(list, list.size());
        return Result.success(paraPageResult);
    }

    @ApiOperation("参数分页和条件搜索")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", dataType = "Integer", paramType = "path", required = true),
            @ApiImplicitParam(name = "size", value = "数量", dataType = "Integer", paramType = "path", required = true)
    })
    @PreAuthorize("hasAuthority('ParaController:listParaQueryPage')")
    @PostMapping("/search/{page}/{size}")
    public Result listParaQueryPage(@RequestBody Para para, @PathVariable Integer page, @PathVariable Integer size) {
        IPage<Para> iPage = paraService.page(new Page<>(page, size), new QueryWrapper<>(para));
        PageResult<Para> paraPageResult = new PageResult<>(iPage.getRecords(), (int) iPage.getTotal());
        return Result.success(paraPageResult);
    }

    @ApiOperation("参数分页搜索")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", dataType = "Integer", paramType = "path", required = true),
            @ApiImplicitParam(name = "size", value = "数量", dataType = "Integer", paramType = "path", required = true)
    })
    @PreAuthorize("hasAuthority('ParaController:listParaPage')")
    @GetMapping("/search/{page}/{size}")
    public Result listParaPage(@PathVariable Integer page, @PathVariable Integer size) {
        IPage<Para> iPage = paraService.page(new Page<>(page, size), new QueryWrapper<>());
        PageResult<Para> paraPageResult = new PageResult<>(iPage.getRecords(), (int) iPage.getTotal());
        return Result.success(paraPageResult);
    }

    @ApiOperation("参数条件搜索")
    @PreAuthorize("hasAuthority('ParaController:ListParaQuery')")
    @PostMapping("/search")
    public Result ListParaQuery(@RequestBody Para para) {
        List<Para> list = paraService.list(new QueryWrapper<>(para));
        PageResult<Para> paraPageResult = new PageResult<>(list, list.size());
        return Result.success(paraPageResult);
    }
}

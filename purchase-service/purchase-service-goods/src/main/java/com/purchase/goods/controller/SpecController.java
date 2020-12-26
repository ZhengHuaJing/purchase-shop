package com.purchase.goods.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.purchase.common.pojo.PageResult;
import com.purchase.common.pojo.Result;
import com.purchase.goods.service.SpecService;
import com.purchase.purchaseservicegoodsapi.entity.Spec;
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
 * @2020-12-20
 */
@Api(value = "规格管理", tags = "规格管理")
@RestController
@RequestMapping("/specs")
public class SpecController {

    @Autowired
    private SpecService specService;

    @ApiOperation("添加规格")
    @PreAuthorize("hasAuthority('SpecController:addSpec')")
    @PostMapping()
    public Result addSpec(@Valid @RequestBody Spec spec) {
        return Result.assess(specService.save(spec));
    }

    @ApiOperation("删除规格")
    @ApiImplicitParam(name = "id", value = "规格ID", dataType = "Integer", paramType = "path", required = true)
    @PreAuthorize("hasAuthority('SpecController:deleteSpec')")
    @DeleteMapping("/{id}")
    public Result deleteSpec(@PathVariable(name = "id") Integer id) {
        return Result.assess(specService.removeById(id));
    }

    @ApiOperation("更新规格")
    @ApiImplicitParam(name = "id", value = "规格ID", dataType = "Integer", paramType = "path", required = true)
    @PreAuthorize("hasAuthority('SpecController:updateSpec')")
    @PutMapping("/{id}")
    public Result updateSpec(@PathVariable(name = "id") Integer id, @RequestBody Spec spec) {
        spec.setId(id);
        return Result.assess(specService.updateById(spec));
    }

    @ApiOperation("规格详情")
    @ApiImplicitParam(name = "id", value = "规格ID", dataType = "Integer", paramType = "path", required = true)
    @PreAuthorize("hasAuthority('SpecController:getSpec')")
    @GetMapping("/{id}")
    public Result getSpec(@PathVariable(name = "id") Integer id) {
        return Result.success(specService.getById(id));
    }

    @ApiOperation("规格全部数据")
    @PreAuthorize("hasAuthority('SpecController:listSpec')")
    @GetMapping()
    public Result listSpec() {
        List<Spec> list = specService.list();
        PageResult<Spec> specPageResult = new PageResult<>(list, list.size());
        return Result.success(specPageResult);
    }

    @ApiOperation("规格分页和条件搜索")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", dataType = "Integer", paramType = "path", required = true),
            @ApiImplicitParam(name = "size", value = "数量", dataType = "Integer", paramType = "path", required = true)
    })
    @PreAuthorize("hasAuthority('SpecController:listSpecQueryPage')")
    @PostMapping("/search/{page}/{size}")
    public Result listSpecQueryPage(@RequestBody Spec spec, @PathVariable Integer page, @PathVariable Integer size) {
        IPage<Spec> iPage = specService.page(new Page<>(page, size), new QueryWrapper<>(spec));
        PageResult<Spec> specPageResult = new PageResult<>(iPage.getRecords(), (int) iPage.getTotal());
        return Result.success(specPageResult);
    }

    @ApiOperation("规格分页搜索")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", dataType = "Integer", paramType = "path", required = true),
            @ApiImplicitParam(name = "size", value = "数量", dataType = "Integer", paramType = "path", required = true)
    })
    @PreAuthorize("hasAuthority('SpecController:listSpecPage')")
    @GetMapping("/search/{page}/{size}")
    public Result listSpecPage(@PathVariable Integer page, @PathVariable Integer size) {
        IPage<Spec> iPage = specService.page(new Page<>(page, size), new QueryWrapper<>());
        PageResult<Spec> specPageResult = new PageResult<>(iPage.getRecords(), (int) iPage.getTotal());
        return Result.success(specPageResult);
    }

    @ApiOperation("规格条件搜索")
    @PreAuthorize("hasAuthority('SpecController:ListSpecQuery')")
    @PostMapping("/search")
    public Result ListSpecQuery(@RequestBody Spec spec) {
        List<Spec> list = specService.list(new QueryWrapper<>(spec));
        PageResult<Spec> specPageResult = new PageResult<>(list, list.size());
        return Result.success(specPageResult);
    }
}

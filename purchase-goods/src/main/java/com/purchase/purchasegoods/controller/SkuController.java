package com.purchase.purchasegoods.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.purchase.purchasecommon.pojo.PageResult;
import com.purchase.purchasecommon.pojo.Result;
import com.purchase.purchasegoods.entity.Sku;
import com.purchase.purchasegoods.service.SkuService;
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
 * 商品表 前端控制器
 * </p>
 *
 * @author ZhengHuaJing
 * @since 2020-12-25
 */
@Api(value = "商品管理", tags = "商品管理")
@RestController
@RequestMapping("/skus")
public class SkuController {

    @Autowired
    private SkuService skuService;

    @ApiOperation("添加商品")
    @PreAuthorize("hasAuthority('SkuController:addSku')")
    @PostMapping()
    public Result addSku(@Valid @RequestBody Sku sku) {
        return Result.assess(skuService.save(sku));
    }

    @ApiOperation("删除商品")
    @ApiImplicitParam(name = "id", value = "商品ID", dataType = "Integer", paramType = "path", required = true)
    @PreAuthorize("hasAuthority('SkuController:deleteSku')")
    @DeleteMapping("/{id}")
    public Result deleteSku(@PathVariable(name = "id") Integer id) {
        return Result.assess(skuService.removeById(id));
    }

    @ApiOperation("更新商品")
    @ApiImplicitParam(name = "id", value = "商品ID", dataType = "Integer", paramType = "path", required = true)
    @PreAuthorize("hasAuthority('SkuController:updateSku')")
    @PutMapping("/{id}")
    public Result updateSku(@PathVariable(name = "id") String id, @Valid @RequestBody Sku sku) {
        sku.setId(id);
        return Result.assess(skuService.updateById(sku));
    }

    @ApiOperation("商品详情")
    @ApiImplicitParam(name = "id", value = "商品ID", dataType = "Integer", paramType = "path", required = true)
    @PreAuthorize("hasAuthority('SkuController:getSku')")
    @GetMapping("/{id}")
    public Result getSku(@PathVariable(name = "id") Integer id) {
        return Result.success(skuService.getById(id));
    }

    @ApiOperation("商品全部数据")
    @PreAuthorize("hasAuthority('SkuController:listSku')")
    @GetMapping()
    public Result listSku() {
        List<Sku> list = skuService.list();
        PageResult<Sku> skuPageResult = new PageResult<>(list, list.size());
        return Result.success(skuPageResult);
    }

    @ApiOperation("商品分页和条件搜索")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", dataType = "Integer", paramType = "path", required = true),
            @ApiImplicitParam(name = "size", value = "数量", dataType = "Integer", paramType = "path", required = true)
    })
    @PreAuthorize("hasAuthority('SkuController:listSkuQueryPage')")
    @PostMapping("/search/{page}/{size}")
    public Result listSkuQueryPage(@RequestBody @Valid Sku sku, @PathVariable Integer page, @PathVariable Integer size) {
        IPage<Sku> iPage = skuService.page(new Page<>(page, size), new QueryWrapper<>(sku));
        PageResult<Sku> skuPageResult = new PageResult<>(iPage.getRecords(), (int) iPage.getTotal());
        return Result.success(skuPageResult);
    }

    @ApiOperation("商品分页搜索")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", dataType = "Integer", paramType = "path", required = true),
            @ApiImplicitParam(name = "size", value = "数量", dataType = "Integer", paramType = "path", required = true)
    })
    @PreAuthorize("hasAuthority('SkuController:listSkuPage')")
    @GetMapping("/search/{page}/{size}")
    public Result listSkuPage(@PathVariable Integer page, @PathVariable Integer size) {
        IPage<Sku> iPage = skuService.page(new Page<>(page, size), new QueryWrapper<>());
        PageResult<Sku> skuPageResult = new PageResult<>(iPage.getRecords(), (int) iPage.getTotal());
        return Result.success(skuPageResult);
    }

    @ApiOperation("商品条件搜索")
    @PreAuthorize("hasAuthority('SkuController:ListSkuQuery')")
    @PostMapping("/search")
    public Result ListSkuQuery(@RequestBody @Valid Sku sku) {
        List<Sku> list = skuService.list(new QueryWrapper<>(sku));
        PageResult<Sku> skuPageResult = new PageResult<>(list, list.size());
        return Result.success(skuPageResult);
    }
}

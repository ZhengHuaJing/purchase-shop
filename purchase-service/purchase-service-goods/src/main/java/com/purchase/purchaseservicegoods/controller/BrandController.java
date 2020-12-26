package com.purchase.purchaseservicegoods.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.purchase.purchasecommon.pojo.PageResult;
import com.purchase.purchasecommon.pojo.Result;
import com.purchase.purchaseservicegoods.service.BrandService;
import com.purchase.purchaseservicegoodsapi.entity.Brand;
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
 * 品牌表 前端控制器
 * </p>
 *
 * @author ZhengHuaJing
 * @since 2020-12-20
 */
@Api(value = "品牌管理", tags = "品牌管理")
@RestController
@RequestMapping("/brands")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @ApiOperation("添加品牌")
    @PreAuthorize("hasAuthority('BrandController:addBrand')")
    @PostMapping()
    public Result addBrand(@Valid @RequestBody Brand brand) {
        return Result.assess(brandService.save(brand));
    }

    @ApiOperation("删除品牌")
    @ApiImplicitParam(name = "id", value = "品牌ID", dataType = "Integer", paramType = "path", required = true)
    @PreAuthorize("hasAuthority('BrandController:deleteBrand')")
    @DeleteMapping("/{id}")
    public Result deleteBrand(@PathVariable(name = "id") Integer id) {
        return Result.assess(brandService.removeById(id));
    }

    @ApiOperation("更新品牌")
    @ApiImplicitParam(name = "id", value = "品牌ID", dataType = "Integer", paramType = "path", required = true)
    @PreAuthorize("hasAuthority('BrandController:updateBrand')")
    @PutMapping("/{id}")
    public Result updateBrand(@PathVariable(name = "id") Integer id, @RequestBody Brand brand) {
        brand.setId(id);
        return Result.assess(brandService.updateById(brand));
    }

    @ApiOperation("品牌详情")
    @ApiImplicitParam(name = "id", value = "品牌ID", dataType = "Integer", paramType = "path", required = true)
    @PreAuthorize("hasAuthority('BrandController:getBrand')")
    @GetMapping("/{id}")
    public Result getBrand(@PathVariable(name = "id") Integer id) {
        return Result.success(brandService.getById(id));
    }

    @ApiOperation("品牌全部数据")
    @PreAuthorize("hasAuthority('BrandController:listBrand')")
    @GetMapping()
    public Result listBrand() {
        List<Brand> list = brandService.list();
        PageResult<Brand> brandPageResult = new PageResult<>(list, list.size());
        return Result.success(brandPageResult);
    }

    @ApiOperation("品牌分页和条件搜索")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", dataType = "Integer", paramType = "path", required = true),
            @ApiImplicitParam(name = "size", value = "数量", dataType = "Integer", paramType = "path", required = true)
    })
    @PreAuthorize("hasAuthority('BrandController:listBrandQueryPage')")
    @PostMapping("/search/{page}/{size}")
    public Result listBrandQueryPage(@RequestBody Brand brand, @PathVariable Integer page, @PathVariable Integer size) {
        IPage<Brand> iPage = brandService.page(new Page<>(page, size), new QueryWrapper<>(brand));
        PageResult<Brand> brandPageResult = new PageResult<>(iPage.getRecords(), (int) iPage.getTotal());
        return Result.success(brandPageResult);
    }

    @ApiOperation("品牌分页搜索")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", dataType = "Integer", paramType = "path", required = true),
            @ApiImplicitParam(name = "size", value = "数量", dataType = "Integer", paramType = "path", required = true)
    })
    @PreAuthorize("hasAuthority('BrandController:listBrandPage')")
    @GetMapping("/search/{page}/{size}")
    public Result listBrandPage(@PathVariable Integer page, @PathVariable Integer size) {
        IPage<Brand> iPage = brandService.page(new Page<>(page, size), new QueryWrapper<>());
        PageResult<Brand> brandPageResult = new PageResult<>(iPage.getRecords(), (int) iPage.getTotal());
        return Result.success(brandPageResult);
    }

    @ApiOperation("品牌条件搜索")
    @PreAuthorize("hasAuthority('BrandController:ListBrandQuery')")
    @PostMapping("/search")
    public Result ListBrandQuery(@RequestBody Brand brand) {
        List<Brand> list = brandService.list(new QueryWrapper<>(brand));
        PageResult<Brand> brandPageResult = new PageResult<>(list, list.size());
        return Result.success(brandPageResult);
    }
}

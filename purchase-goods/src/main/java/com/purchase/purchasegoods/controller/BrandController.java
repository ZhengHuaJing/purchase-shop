package com.purchase.purchasegoods.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.purchase.purchasecommon.lang.Response;
import com.purchase.purchasegoods.entity.Brand;
import com.purchase.purchasegoods.service.BrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

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
//    @PreAuthorize("hasAuthority('BrandController:addBrand')")
    @PostMapping("")
    public Response addBrand(@Valid @RequestBody Brand brand) {
        return Response.assess(brandService.save(brand));
    }

    @ApiOperation("删除品牌")
    @ApiImplicitParam(name = "id", value = "品牌ID", dataType = "Integer", paramType = "path", required = true)
    @PreAuthorize("hasAuthority('BrandController:deleteBrand')")
    @DeleteMapping("/{id}")
    public Response deleteBrand(@PathVariable(name = "id") Integer id) {
        return Response.assess(brandService.removeById(id));
    }

    @ApiOperation("更新品牌")
    @ApiImplicitParam(name = "id", value = "品牌ID", dataType = "Integer", paramType = "path", required = true)
    @PreAuthorize("hasAuthority('BrandController:updateBrand')")
    @PutMapping("/{id}")
    public Response updateBrand(@PathVariable(name = "id") Integer id, @Valid @RequestBody Brand brand) {
        brand.setId(id);
        return Response.assess(brandService.updateById(brand));
    }

    @ApiOperation("详情品牌")
    @ApiImplicitParam(name = "id", value = "品牌ID", dataType = "Integer", paramType = "path", required = true)
    @PreAuthorize("hasAuthority('BrandController:getBrand')")
    @GetMapping("/{id}")
    public Response getBrand(@PathVariable(name = "id") Integer id) {
        return Response.success(brandService.getById(id));
    }

    @ApiOperation("列表品牌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", dataType = "Integer", paramType = "query", required = false),
            @ApiImplicitParam(name = "size", value = "每页显示数", dataType = "Integer", paramType = "query", required = false),
            @ApiImplicitParam(name = "search", value = "搜索词", dataType = "String", paramType = "query", required = false),
            @ApiImplicitParam(name = "idOrder", value = "按照ID升序或降序", dataType = "Boolean", paramType = "query", required = false),
    })
//    @PreAuthorize("hasAuthority('BrandController:listBrand')")
    @GetMapping("")
    public Response listBrand(@RequestParam(name = "page", defaultValue = "1") Integer page,
                              @RequestParam(name = "size", defaultValue = "10") Integer size,
                              String search,
                              Boolean idOrder) throws Exception {
        // 搜索查询
        LambdaQueryWrapper<Brand> qw = new QueryWrapper<Brand>().lambda()
                .like(search != null && !search.equals(""), Brand::getId, search)
                .orderBy(idOrder != null, idOrder != null && idOrder, Brand::getId);

        Page<Brand> p = new Page<>(page, size);
        IPage<Brand> iPage = brandService.page(p, qw);

        Map<String, Object> map = new HashMap<>();
        map.put("items", iPage.getRecords());
        map.put("total", iPage.getTotal());

//        return Response.success(map);

        throw new Exception("sdf");
    }
}

package com.purchase.purchaseservicegoods.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.purchase.purchasecommon.pojo.PageResult;
import com.purchase.purchasecommon.pojo.Result;
import com.purchase.purchaseservicegoods.service.CategoryService;
import com.purchase.purchaseservicegoodsapi.entity.Category;
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
 * 商品类目 前端控制器
 * </p>
 *
 * @author ZhengHuaJing
 * @since 2020-12-20
 */
@Api(value = "分类管理", tags = "分类管理")
@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @ApiOperation("添加分类")
    @PreAuthorize("hasAuthority('CategoryController:addCategory')")
    @PostMapping()
    public Result addCategory(@Valid @RequestBody Category category) {
        return Result.assess(categoryService.save(category));
    }

    @ApiOperation("删除分类")
    @ApiImplicitParam(name = "id", value = "分类ID", dataType = "Integer", paramType = "path", required = true)
    @PreAuthorize("hasAuthority('CategoryController:deleteCategory')")
    @DeleteMapping("/{id}")
    public Result deleteCategory(@PathVariable(name = "id") Integer id) {
        return Result.assess(categoryService.removeById(id));
    }

    @ApiOperation("更新分类")
    @ApiImplicitParam(name = "id", value = "分类ID", dataType = "Integer", paramType = "path", required = true)
    @PreAuthorize("hasAuthority('CategoryController:updateCategory')")
    @PutMapping("/{id}")
    public Result updateCategory(@PathVariable(name = "id") Integer id, @RequestBody Category category) {
        category.setId(id);
        return Result.assess(categoryService.updateById(category));
    }

    @ApiOperation("分类详情")
    @ApiImplicitParam(name = "id", value = "分类ID", dataType = "Integer", paramType = "path", required = true)
    @PreAuthorize("hasAuthority('CategoryController:getCategory')")
    @GetMapping("/{id}")
    public Result getCategory(@PathVariable(name = "id") Integer id) {
        return Result.success(categoryService.getById(id));
    }

    @ApiOperation("分类全部数据")
    @PreAuthorize("hasAuthority('CategoryController:listCategory')")
    @GetMapping()
    public Result listCategory() {
        List<Category> list = categoryService.list();
        PageResult<Category> categoryPageResult = new PageResult<>(list, list.size());
        return Result.success(categoryPageResult);
    }

    @ApiOperation("分类分页和条件搜索")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", dataType = "Integer", paramType = "path", required = true),
            @ApiImplicitParam(name = "size", value = "数量", dataType = "Integer", paramType = "path", required = true)
    })
    @PreAuthorize("hasAuthority('CategoryController:listCategoryQueryPage')")
    @PostMapping("/search/{page}/{size}")
    public Result listCategoryQueryPage(@RequestBody Category category, @PathVariable Integer page, @PathVariable Integer size) {
        IPage<Category> iPage = categoryService.page(new Page<>(page, size), new QueryWrapper<>(category));
        PageResult<Category> categoryPageResult = new PageResult<>(iPage.getRecords(), (int) iPage.getTotal());
        return Result.success(categoryPageResult);
    }

    @ApiOperation("分类分页搜索")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", dataType = "Integer", paramType = "path", required = true),
            @ApiImplicitParam(name = "size", value = "数量", dataType = "Integer", paramType = "path", required = true)
    })
    @PreAuthorize("hasAuthority('CategoryController:listCategoryPage')")
    @GetMapping("/search/{page}/{size}")
    public Result listCategoryPage(@PathVariable Integer page, @PathVariable Integer size) {
        IPage<Category> iPage = categoryService.page(new Page<>(page, size), new QueryWrapper<>());
        PageResult<Category> categoryPageResult = new PageResult<>(iPage.getRecords(), (int) iPage.getTotal());
        return Result.success(categoryPageResult);
    }

    @ApiOperation("分类条件搜索")
    @PreAuthorize("hasAuthority('CategoryController:ListCategoryQuery')")
    @PostMapping("/search")
    public Result ListCategoryQuery(@RequestBody Category category) {
        List<Category> list = categoryService.list(new QueryWrapper<>(category));
        PageResult<Category> categoryPageResult = new PageResult<>(list, list.size());
        return Result.success(categoryPageResult);
    }
}

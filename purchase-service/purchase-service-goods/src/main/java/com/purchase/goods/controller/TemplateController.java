package com.purchase.goods.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.purchase.common.pojo.PageResult;
import com.purchase.common.pojo.Result;
import com.purchase.goods.service.TemplateService;
import com.purchase.purchaseservicegoodsapi.entity.Template;
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
@Api(value = "模板管理", tags = "模板管理")
@RestController
@RequestMapping("/template")
public class TemplateController {

    @Autowired
    private TemplateService templateService;

    @ApiOperation("添加模板")
    @PreAuthorize("hasAuthority('TemplateController:addTemplate')")
    @PostMapping()
    public Result addTemplate(@Valid @RequestBody Template template) {
        return Result.assess(templateService.save(template));
    }

    @ApiOperation("删除模板")
    @ApiImplicitParam(name = "id", value = "模板ID", dataType = "Integer", paramType = "path", required = true)
    @PreAuthorize("hasAuthority('TemplateController:deleteTemplate')")
    @DeleteMapping("/{id}")
    public Result deleteTemplate(@PathVariable(name = "id") Integer id) {
        return Result.assess(templateService.removeById(id));
    }

    @ApiOperation("更新模板")
    @ApiImplicitParam(name = "id", value = "模板ID", dataType = "Integer", paramType = "path", required = true)
    @PreAuthorize("hasAuthority('TemplateController:updateTemplate')")
    @PutMapping("/{id}")
    public Result updateTemplate(@PathVariable(name = "id") Integer id, @RequestBody Template template) {
        template.setId(id);
        return Result.assess(templateService.updateById(template));
    }

    @ApiOperation("模板详情")
    @ApiImplicitParam(name = "id", value = "模板ID", dataType = "Integer", paramType = "path", required = true)
    @PreAuthorize("hasAuthority('TemplateController:getTemplate')")
    @GetMapping("/{id}")
    public Result getTemplate(@PathVariable(name = "id") Integer id) {
        return Result.success(templateService.getById(id));
    }

    @ApiOperation("模板全部数据")
    @PreAuthorize("hasAuthority('TemplateController:listTemplate')")
    @GetMapping()
    public Result listTemplate() {
        List<Template> list = templateService.list();
        PageResult<Template> templatePageResult = new PageResult<>(list, list.size());
        return Result.success(templatePageResult);
    }

    @ApiOperation("模板分页和条件搜索")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", dataType = "Integer", paramType = "path", required = true),
            @ApiImplicitParam(name = "size", value = "数量", dataType = "Integer", paramType = "path", required = true)
    })
    @PreAuthorize("hasAuthority('TemplateController:listTemplateQueryPage')")
    @PostMapping("/search/{page}/{size}")
    public Result listTemplateQueryPage(@RequestBody Template template, @PathVariable Integer page, @PathVariable Integer size) {
        IPage<Template> iPage = templateService.page(new Page<>(page, size), new QueryWrapper<>(template));
        PageResult<Template> templatePageResult = new PageResult<>(iPage.getRecords(), (int) iPage.getTotal());
        return Result.success(templatePageResult);
    }

    @ApiOperation("模板分页搜索")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", dataType = "Integer", paramType = "path", required = true),
            @ApiImplicitParam(name = "size", value = "数量", dataType = "Integer", paramType = "path", required = true)
    })
    @PreAuthorize("hasAuthority('TemplateController:listTemplatePage')")
    @GetMapping("/search/{page}/{size}")
    public Result listTemplatePage(@PathVariable Integer page, @PathVariable Integer size) {
        IPage<Template> iPage = templateService.page(new Page<>(page, size), new QueryWrapper<>());
        PageResult<Template> templatePageResult = new PageResult<>(iPage.getRecords(), (int) iPage.getTotal());
        return Result.success(templatePageResult);
    }

    @ApiOperation("模板条件搜索")
    @PreAuthorize("hasAuthority('TemplateController:ListTemplateQuery')")
    @PostMapping("/search")
    public Result ListTemplateQuery(@RequestBody Template template) {
        List<Template> list = templateService.list(new QueryWrapper<>(template));
        PageResult<Template> templatePageResult = new PageResult<>(list, list.size());
        return Result.success(templatePageResult);
    }
}

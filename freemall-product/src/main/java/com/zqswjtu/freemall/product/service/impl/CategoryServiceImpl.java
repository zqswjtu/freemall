package com.zqswjtu.freemall.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zqswjtu.common.utils.PageUtils;
import com.zqswjtu.common.utils.Query;

import com.zqswjtu.freemall.product.dao.CategoryDao;
import com.zqswjtu.freemall.product.entity.CategoryEntity;
import com.zqswjtu.freemall.product.service.CategoryService;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {
//    @Autowired
//    CategoryDao categoryDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listWithTree() {
        // 查出所有分类
        List<CategoryEntity> entities = baseMapper.selectList(null);
        // 组装成父子的树形结构
        // 1、首先找到一级分类，一级分类的父菜单id为0
        List<CategoryEntity> topLevelMenu = entities.stream()
                .filter((categoryEntity) -> categoryEntity.getParentCid() == 0)
                .peek((categoryEntity -> categoryEntity.setChildren(getChildMenus(categoryEntity.getCatId(), entities))))
                .collect(Collectors.toList());
        // 2、二级分类以及后续的三、四等等级分类都有自己对应的父类菜单
        return topLevelMenu;
    }

    @Override
    public void removeMenuByIds(List<Long> list) {
        // TODO 1、检查当前删除的菜单是否被别的地方引用
        // 逻辑删除
        baseMapper.deleteBatchIds(list);
    }

    // 递归查询所有子菜单的子菜单
    private List<CategoryEntity> getChildMenus(Long parentCid, List<CategoryEntity> entities) {
        assert parentCid != null;
        List<CategoryEntity> childMenus = entities.stream()
                .filter((categoryEntity) -> categoryEntity.getParentCid().equals(parentCid))
                .peek((childMenu) -> childMenu.setChildren(getChildMenus(childMenu.getCatId(), entities)))
                .collect(Collectors.toList());
        return childMenus;
    }
}
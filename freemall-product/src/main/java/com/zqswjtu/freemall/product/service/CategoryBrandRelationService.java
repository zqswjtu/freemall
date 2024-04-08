package com.zqswjtu.freemall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zqswjtu.common.utils.PageUtils;
import com.zqswjtu.freemall.product.entity.CategoryBrandRelationEntity;

import java.util.Map;

/**
 * 品牌分类关联
 *
 * @author chaoching
 * @email swjtuqzhao@gmail.com
 * @date 2024-03-25 21:40:14
 */
public interface CategoryBrandRelationService extends IService<CategoryBrandRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


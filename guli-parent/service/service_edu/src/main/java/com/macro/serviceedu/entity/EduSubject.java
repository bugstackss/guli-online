package com.macro.serviceedu.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 课程科目
 * </p>
 *
 * @author macro
 * @since 2023-11-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "EduSubject对象", description = "课程科目")
public class EduSubject implements Serializable
{

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "课程类别ID")
	@TableId(value = "id", type = IdType.ID_WORKER_STR)
	private String id;

	@ApiModelProperty(value = "类别名称")
	private String title;

	@ApiModelProperty(value = "父ID")
	private String parentId;

	@ApiModelProperty(value = "排序字段")
	private Integer sort;

	@ApiModelProperty(value = "创建时间")
	@TableField(fill = FieldFill.INSERT)
	private Date gmtCreate;

	@ApiModelProperty(value = "更新时间")
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date gmtModified;

}

package com.macro.serviceedu.controller.front;import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;import com.macro.commonutils.R;import com.macro.serviceedu.entity.EduCourse;import com.macro.serviceedu.entity.EduTeacher;import com.macro.serviceedu.service.EduCourseService;import com.macro.serviceedu.service.EduTeacherService;import lombok.RequiredArgsConstructor;import org.springframework.web.bind.annotation.CrossOrigin;import org.springframework.web.bind.annotation.GetMapping;import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.bind.annotation.RestController;import java.util.List;/** * @author macro * @description * @date 2023/12/28 14:34 * @github https://github.com/bugstackss */@RestController@CrossOrigin@RequiredArgsConstructor@RequestMapping("/eduservice/indexfront")public class IndexFrontController {    private final EduCourseService courseService;    private final EduTeacherService teacherService;    /**     * 查询前8条热门课程，查询前4条名师     *     * @return R     */    @GetMapping("/index")    public R index() {        //查询前8条热门课程        final QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();        queryWrapper.orderByDesc("id").last("limit 8");        final List<EduCourse> eduCourseList = courseService.list(queryWrapper);        //查询前4条名师        final QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();        wrapper.orderByDesc("id").last("limit 4");        final List<EduTeacher> eduTeacherList = teacherService.list(wrapper);        return R.ok().data("eduCourseList", eduCourseList).data("eduTeacherList", eduTeacherList);    }}
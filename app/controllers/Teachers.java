package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.persistence.EntityManager;

import models.BaseModel;
import models.School;
import models.Teacher;

import com.google.gson.Gson;

import play.Play;
import play.db.jpa.JPA;


public class Teachers extends CRUD {
    public static void list() {
        long sunTeacher = Teacher.count("state = ? and employeType = ?",
                BaseModel.ACTIVE, Teacher.EM_TYPE.TEACHER.toString());
        List<School> schools = School.find("state !=?", BaseModel.DELETE)
                .fetch();
        renderArgs.put("schools", schools);
        renderArgs.put("sunTeacher", sunTeacher);
        
        StringBuffer bf = new StringBuffer("state != '"+ BaseModel.DELETE+"'");
        if (params.get("sname") != null && !"".equals(params.get("sname")))
            bf.append("\n and name like '%"+params.get("sname")+"%'");
        
        int pageNum = Integer.parseInt((params.get("pageNum")==null||"".equals(params.get("pageNum")))?"1":params.get("pageNum"));
        int numPerPage = getPageSize();
        System.out.println("======================================");
        List<Teacher> teachers = Teacher.find(bf.toString()).fetch(pageNum,numPerPage);
        long teachersl = Teacher.count(bf.toString());
        DWZPageAndOrder(teachersl);
        renderArgs.put("teachers", teachers);
    }

    public static void detail(long id) {
        /*try {
            Teacher t = Teacher.findById(id);
            Map workdate = MyDateUtils.getYearAndMonthSinceNow(
                    t.teacherDetail.hireDate, "yyyy-MM-dd");
            List<Lesson> lessons = Lesson.find("teacher = ? and state != ?", t,
                    BaseModel.DELETE).fetch();
            List<HashMap<String, Object>> calendarSource = new ArrayList<HashMap<String, Object>>();

            Gson gson = new Gson();
            renderArgs.put("showTeacher", t);
            //renderArgs.put("calendarSource", gson.toJson(calendarSource));
            renderArgs.put("lessons", lessons);
            renderArgs.put("workdate", workdate);
            render();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/

    }

    public static void teacherCalender(long id) {
        Teacher t = Teacher.findById(id);
        renderArgs.put("showTeacher", t);
        render();
    }


}

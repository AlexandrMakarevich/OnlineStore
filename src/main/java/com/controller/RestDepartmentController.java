package com.controller;

import com.client.Department;
import com.dao.DepartmentDao;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController("restDepartmentController")
@Transactional
public class RestDepartmentController {

    @Resource(name = "departmentDaoImpl")
    private DepartmentDao departmentDao;

    @RequestMapping(value = "/department/{id}", method = RequestMethod.GET)
    public Department getIdById(@PathVariable("id") int id) {
        return  departmentDao.getById(id);
    }

    @RequestMapping(value = "/allDepartment", method = RequestMethod.GET)
    public List<Department> getAllDepartment(){
        return departmentDao.getAll();
    }

    @RequestMapping(value = "/addDepartment", method = RequestMethod.POST)
    public void addDepartment(@RequestBody String name) {
        departmentDao.add(name);
    }
}

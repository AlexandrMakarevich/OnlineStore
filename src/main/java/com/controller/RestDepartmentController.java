package com.controller;

import com.client.Department;
import com.dao.DepartmentDao;
import com.google.common.base.Optional;
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
    public Department getIdByName(@PathVariable("id") int id) {
        Optional<Department> departmentOptional = departmentDao.getById(id);
        if (!departmentOptional.isPresent()) {
            throw new IllegalStateException("No column was found!");
        }
        return departmentOptional.get();
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

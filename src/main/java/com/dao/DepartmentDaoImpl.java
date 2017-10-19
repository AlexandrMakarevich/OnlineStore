package com.dao;

import com.client.Department;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository("departmentDaoImpl")
public class DepartmentDaoImpl extends BaseDao implements DepartmentDao {

    public int add(Department department) {
        getSession().save(department);
        return department.getId();
    }

    public Department getById(int id) {
        return getSession().load(Department.class, id);
    }

    public Department getByIdWithCriteria(int id) {
        Criteria criteria = getSession().createCriteria(Department.class);
        criteria.add(Restrictions.eq("id", id));
        return (Department) criteria.uniqueResult();
    }

    public List<Department> getAllDepartment() {
        String hql = "from Department";
        Query query = getSession().createQuery(hql);
        return query.list();
    }
}

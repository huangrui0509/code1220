package cn.com.taiji.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import cn.com.taiji.domain.Department;
import cn.com.taiji.domain.Employee;
@Repository
public interface DepartmentRepository extends JpaRepository<Department, String>,JpaSpecificationExecutor<Department>,PagingAndSortingRepository<Department,String>{

}

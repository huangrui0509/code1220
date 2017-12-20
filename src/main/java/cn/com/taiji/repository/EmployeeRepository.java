package cn.com.taiji.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import cn.com.taiji.domain.Employee;
import cn.com.taiji.domain.Menu;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String>,JpaSpecificationExecutor<Employee>,PagingAndSortingRepository<Employee,String>{

}

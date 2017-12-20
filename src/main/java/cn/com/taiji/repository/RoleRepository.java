package cn.com.taiji.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import cn.com.taiji.domain.Role;
import cn.com.taiji.domain.User;
@Repository
public interface RoleRepository extends JpaRepository<Role, String>,JpaSpecificationExecutor<Role>,PagingAndSortingRepository<Role,String>{

}

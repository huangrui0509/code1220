package cn.com.taiji.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.com.taiji.domain.User;


@Repository
public interface UserRepository extends JpaRepository<User, String>,JpaSpecificationExecutor<User>,PagingAndSortingRepository<User,String>{

	
}

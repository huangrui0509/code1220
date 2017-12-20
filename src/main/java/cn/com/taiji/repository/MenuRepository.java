package cn.com.taiji.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import cn.com.taiji.domain.Menu;
import cn.com.taiji.domain.Role;

@Repository
public interface MenuRepository extends JpaRepository<Menu, String>,JpaSpecificationExecutor<Menu>,PagingAndSortingRepository<Menu,String>{

	
	Menu saveAndFlush(Menu menu);
}

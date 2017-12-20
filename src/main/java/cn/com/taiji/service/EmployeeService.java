package cn.com.taiji.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.hql.internal.ast.tree.FromElement;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.taiji.domain.Department;
import cn.com.taiji.domain.Employee;
import cn.com.taiji.dto.EmployeeDto;
import cn.com.taiji.repository.EmployeeRepository;
import cn.com.taiji.utils.PinYinUtils;

@Service
@SuppressWarnings("all")
public class EmployeeService {


	@Autowired
	EmployeeRepository  employeeRepository;

	/**
	 * @Description:通过id获取emp对象信息
	 * @param id
	 * @return EmployeeDto  
	 * @throws
	 * @author lenovo
	 * @date 2017年12月18日
	 */
	public EmployeeDto findOneEmp(String id) {
		Employee emp = employeeRepository.findOne(id);
		EmployeeDto ed = new EmployeeDto();
		BeanUtils.copyProperties(ed, emp);
		return ed;
	}

	/**
	 * @Description: 查询所有emp对象信息
	 * @return List<EmployeeDto>  
	 * @throws
	 * @author lenovo
	 * @date 2017年12月18日
	 */
	public List<EmployeeDto> findAllEmp(){
		List<Employee> allEmp = employeeRepository.findAll();
		List<EmployeeDto> allEmpDto = new ArrayList();
		for (Employee employee : allEmp) {
			EmployeeDto ed = new EmployeeDto();
			BeanUtils.copyProperties(ed, employee);
			allEmpDto.add(ed);
		}
		return allEmpDto;
	}

	/**
	 * @Description: 新增和修改emp对象信息
	 * @param ed void  
	 * @throws
	 * @author lenovo
	 * @date 2017年12月18日
	 */
	public void insertEmp(EmployeeDto ed) {
		if(null==ed.getId()) {
			ed.setId(UUID.randomUUID().toString().replace("-", ""));
		}
		Employee emp = new Employee();
		BeanUtils.copyProperties(ed, emp);
		//System.out.println(emp.getId()+"----------");
		employeeRepository.saveAndFlush(emp);
	}

	/**
	 * @Description: 逻辑删除emp对象信息，将status='0'
	 * @param ed void  
	 * @throws
	 * @author lenovo
	 * @date 2017年12月18日
	 */
	public void removeEmp(EmployeeDto ed) {
		ed.setStatus("0");
		Employee emp = new Employee();
		BeanUtils.copyProperties(ed, emp);
		employeeRepository.saveAndFlush(emp);
	}
	//-------------poi-excel----
	/**
	 * @Description:实现Excel表的数据的获取，返回一个map
	 * @param path
	 * @return
	 * @throws IOException Map  
	 * @throws
	 * @author lenovo
	 * @date 2017年12月20日
	 */
	public   Map  print(String path) throws IOException{
		File file = new File(path);
		XSSFWorkbook xssfWorkbook = null;
		try {
			xssfWorkbook = new XSSFWorkbook(file);
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//导入指定张表的数据
		XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(2);

		int rowstart = xssfSheet.getFirstRowNum();
		int rowEnd = xssfSheet.getLastRowNum();
		Map deptEmpMap=new HashMap();
		List dept=new ArrayList();
		for(int i=rowstart+1;i<=rowEnd;i++)
		{
			//User user=new User();
			Employee emp = new Employee();
			XSSFRow row = xssfSheet.getRow(i);
			if(null == row) continue;
			int cellStart = row.getFirstCellNum();
			int cellEnd = row.getLastCellNum();//获取列数，比最后一列列标大1
			String deptName="";
			for(int k=cellStart;k<=cellEnd;k++)
			{
				XSSFCell cell = row.getCell(k);
				if(null==cell) continue;
				String str="";

				switch (cell.getCellType())
				{
				case HSSFCell.CELL_TYPE_NUMERIC: // 数字
					str=cell.getNumericCellValue()+"";
					//       System.out.print(cell.getNumericCellValue()      + "   ");
					break;
				case HSSFCell.CELL_TYPE_STRING: // 字符串\
					str=cell.getStringCellValue()+"";
					//    System.out.print(cell.getStringCellValue() + "   ");
					break;
				case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
					str=cell.getBooleanCellValue()+"";
					System.out.println(cell.getBooleanCellValue()  + "   ");
					break;
				case HSSFCell.CELL_TYPE_FORMULA: // 公式
					str=cell.getCellFormula()+"";
					//    System.out.print(cell.getCellFormula() + "   ");
					break;
				case HSSFCell.CELL_TYPE_BLANK: // 空值
					//    System.out.println(" ");
					break;
				case HSSFCell.CELL_TYPE_ERROR: // 故障
					System.out.println(" ");
					break;
				default:
					//   System.out.print("未知类型   ");
					break;
				}
				if(k==1){
					emp.setEmpName(str);
					emp.setLoginName(PinYinUtils.toQuanPin(str));

				}else  if(k==0){
					emp.setEmpNumber(str);
				}else  if(k==2){
					emp.setGender(str);
				}else  if(k==3){
					emp.setCategory(str);
				}else  if(k==4){
					emp.setCity(str);
				}else  if(k==5){
					emp.setDepartDesc(str);
				}else  if(k==6){
					emp.setSecondaryDepart(str);
				}else  if(k==7){
					emp.setJobSequence(str);
				}else  if(k==8){
					emp.setJobLevel(str);
				}else  if(k==9){
					emp.setJobName(str);
					dept.add(str);

					String key=deptName+"_"+str;
					// 	key=str!=null?deptNamestr:deptName;
					List list=new ArrayList();
					if(deptEmpMap.get(key)!=null){
						list=(List)deptEmpMap.get(key);
					}
					list.add(emp);
					deptEmpMap.put(key, list);

				}
			}
		}
		dept=removeDuplicate(dept);
		// System.out.print(dept.isEmpty());
		return deptEmpMap;
	}
	/**
	 * 去掉重复
	 * @param list
	 * @return
	 */
	public  List<String> removeDuplicate(List<String> list) {        
		HashSet<String> h = new  HashSet<String>(list);        
		list.clear();        
		list.addAll(h);        
		return list;     
	}  





	/**
	 * @Description: 将数据从map对象中取出，实现员工和部门数据条数存储到数据库中
	 * @param deptEmpMap void  
	 * @throws
	 * @author lenovo
	 * @date 2017年12月20日
	 */
	public  void initDept(Map deptEmpMap){
		Set set = deptEmpMap.entrySet();         
		Iterator item = set.iterator();         
//		List<EmployeeDto> dept=new ArrayList();
//		//初始化一级目录菜单
		Map<String,String> departMap = new HashMap();

		while(item.hasNext()){      
			Map.Entry<String, List> entry1=(Map.Entry<String, List>)item.next();    

			String deptName=entry1.getKey();
			EmployeeDto dto=new EmployeeDto();
			dto.setEmpName(entry1.getKey());

			List list=entry1.getValue();
			Iterator it1 = list.iterator();
			
			
			
			while(it1.hasNext()){
				List<Department> departList = new ArrayList();
				Employee emp = (Employee)it1.next();
				//判断一级部门
				Department depart = new Department();
				if(null!=emp.getDepartDesc()) {
					int count = 0;
					Iterator<Map.Entry<String, String>> it = departMap.entrySet().iterator();
					while (it.hasNext()) {
						Map.Entry<String, String> entry = it.next();
						System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());

						
						if(emp.getDepartDesc().equals(entry.getKey())) {
							
							depart.setId(entry.getValue());
							depart.setDepartName(emp.getDepartDesc());
							
						}else{
							count++;
						}

					}
			
					if(count==departMap.size()) {
					//if(count==0) {
						depart.setId(UUID.randomUUID().toString().replace("-", ""));
						depart.setDepartName(emp.getDepartDesc());
						
					}
					
			
					//存到map中
					departMap.put(depart.getDepartName(), depart.getId());
			
					//存到list里面
					departList.add(depart);

				}
				//判断二级部门
				Department depart2 = new Department();
				if(null!=emp.getSecondaryDepart()) {
					List<Department> departList2 = new ArrayList();
					int count = 0;
					Iterator<Map.Entry<String, String>> it = departMap.entrySet().iterator();
					while (it.hasNext()) {
						Map.Entry<String, String> entry = it.next();
						//System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());

						if(emp.getSecondaryDepart().equals(entry.getKey())) {
							depart2.setId(entry.getValue());
							depart2.setDepartName(entry.getKey());
							
						}else {
							count++;
						}
					}
					
					if(count==departMap.size()) {
						depart2.setId(UUID.randomUUID().toString().replace("-", ""));
						depart2.setDepartName(emp.getSecondaryDepart());
					}
					
					departList2.add(depart2);
					depart.setDepartments(departList2);
					departMap.put(depart2.getDepartName(), depart2.getId());
				}	 
				emp.setId(UUID.randomUUID().toString().replace("-", ""));
				emp.setDepartments(departList);

				employeeRepository.saveAndFlush(emp);
				System.out.println(departMap);
			}
		}  
	}

}

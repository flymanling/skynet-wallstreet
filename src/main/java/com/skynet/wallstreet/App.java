package com.skynet.wallstreet;

import java.io.File;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.skynet.wallstreet.common.Constants;
import com.skynet.wallstreet.service.interfaces.ICovertService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//        System.out.println( "Hello World!" );
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring.xml");
//        IBaseService service = (IBaseService) ac.getBean("baseService");
//        User user = new User();
//        user.setName("ethan ling");
//        service.save(user);
        
        ICovertService importService = (ICovertService) ac.getBean("importService");
//        importService.importFinance();
        String stockPath = Constants.BASE_PATH + "通达信数据/股票/";
		String[] files = new File(stockPath).list();
		for(String fileName : files) {
			if(!fileName.endsWith(".xls")) {
				continue;
			}
			importService.importStock(stockPath, fileName);
		}
        
//        IAnalyseService analyseService = (IAnalyseService) ac.getBean("analyseService");
//        analyseService.findIncreaseCompany();
        
//        Map<String, String> companys = PropUtil.readValue(Constants.COMPANY_PATH);
//        String hql = "from finance as f where f.code='000001' order by f.reportDate desc";
//		
//        IBaseService baseService = (IBaseService) ac.getBean("baseService");
//        baseService.list(hql, null);
    }

}

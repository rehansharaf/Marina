package com.marina.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.marina.base.BrowserFactory;
import com.marina.base.TestBase;
import com.marina.pages.HomePage;
import com.marina.pages.LoginPage;
import com.marina.pages.SpaceTypesAddTypesPage;
import com.marina.pages.SpaceTypesPage;
import com.marina.utils.ExcelLibrary;
import com.marina.utils.Log;
import com.marina.dataproviders.DataProviders;

/*
 * OOPS Major Concepts (major pillar)
 * 1. Inheritance 
 * 2. Abstraction 
 * 3. Polymorphism
 * 4. Encapsulation
 */
public class Mod_2_SpaceTypes extends TestBase {

	LoginPage lp;
	HomePage hp;
	SpaceTypesPage stp;
	SpaceTypesAddTypesPage statp;
	Object[][] added_space;
	String [] added_space_s;

	
	String form_data_create[];
	String[] tableResult;
	
	String[] reset_formdata;
	String[] reset_tableresult;
	
//	String userFName= prop.getProperty("space_name");
	String parking;
	
	
	
	
	@BeforeMethod
	public void beforeTest() {

		System.out.println("teseting");
		browserIntialization();
		lp = new LoginPage(driver);
		hp = lp.login(prop.getProperty("email"), prop.getProperty("password"));
		stp = hp.spaces_dropdown_SpaceTypes();

	}

	
	
	
	
//	@Test(groups = "regression,sanity,smoke", priority = 1 )
//	public void verifySpacePage_Tc_201() {
//
//		Log.startTestCase("Verify  space type page Page");
//		Assert.assertEquals(stp.space_type_page_verify(), "Space Types");
//		Log.endTestCase("Verify  space type page");
//
//	}
//	
//	
//	
//
//	
//	@Test (groups ="regression,smoke", priority = 2)
//	public void verifyPopupAddSpace_TC_202() {
//		
//		
//		statp = stp.add_space_type();
//		Log.startTestCase(" add new space type window should appear");
//		Assert.assertEquals(statp.click_add_space_type(), "FIXED $ OR $/UNIT/PERIOD");
//		Log.endTestCase(" add new space type window should appear");
//	
//		
//	}
	
	
	
	@Test (groups ="regression",  priority=4)
	public void multipleUsersWithMutlipleSelections_TC_203() throws InterruptedException {
		

		statp = stp.add_space_type();
		boolean alpha = false;
		added_space = statp.addingNewSpace();
		Assert.assertTrue(alpha);
				
	
	}
	
	
	
	
//	@Test
//	public void skippingInputFieldInSpaceAddForm_TC_204() throws InterruptedException {
//		
//
//		statp = stp.add_space_type();
//		boolean alpha = false;
//		added_space = statp.addingNewSpace();
//		Assert.assertTrue(alpha);
//				
//	
//	}
	
	
	
	
	
	
//	@Test(groups="regression,smoke", priority=5)
//	public void verifySpaceFromTableAfterAdd_TC_205() throws InterruptedException {
//		
//	
//	
//
//				statp = stp.add_space_type();
//				added_space = statp.singleAddingSpace();
//				String user =(String) added_space[0][0];
//				tableResult = stp.verify_spaceAdd__with_sheet(user);
//				formData_and_TableData_reset();
//			
//				Assert.assertEquals(reset_formdata, reset_tableresult);
//		
//				System.out.println("test");
//		
//		
//	}
	
	
	
	
	/*
	 * *************************
	 *  ****************
	 *  ***********
	 *  *****************
	 *  *************************
	 *  ****
	 *  *****************
	 *  ************
	 *  *************************
	 * /
	 */
	
	
	
	
	
	
//	@Test
//	public void skippingInputFieldInSpaceAddForm_TC_204() throws InterruptedException {
//		
//
//		statp = stp.add_space_type();
//		boolean alpha = false;
//		added_space = statp.addingNewSpace();
//		Assert.assertTrue(alpha);
//				
//	
//	}
	
	
	
	
	
	
	
//	@Test(groups="regression,smoke", priority=4)
//	public void verifySpaceAfterAdd_TC_203() throws InterruptedException {
//		
//		statp = stp.add_space_type();
//		added_space = statp.addingNewSpace(prop.getProperty("space_name"));
//		
//		System.out.println("test");
//		
//		
//		
//	}
	
	
	
	
	
	
	

	
	

	
	
	public void formData_and_TableData_reset() {
		
	
		reset_tableresult = new String[6];
		reset_formdata = new String[6];

		reset_tableresult[0] = tableResult[1];
		reset_tableresult[1] = tableResult[2];
		reset_tableresult[2] = tableResult[3];
		reset_tableresult[3] = tableResult[4];
		reset_tableresult[4] = tableResult[5];
		reset_tableresult[5] = tableResult[6];

		String space_name = (String) added_space[0][0];
		String add_to_group = (String) added_space[0][5];
		String fixed = (String) added_space[0][1];
		String capacity = (String) added_space[0][2];
		String pricing_type = (String) added_space[0][3];
		String p_plane = (String) added_space[0][4];

		reset_formdata[0] = space_name;
		reset_formdata[1] = add_to_group;
		reset_formdata[2] = fixed;
		reset_formdata[3] = capacity;
		reset_formdata[4] = pricing_type;
		reset_formdata[5] = p_plane;


	
	}
		
	
	
		
//	@Test(groups ="regression,smoke", priority=4)
//	public void skippingFileds_TC_204() {
//		
//		
//		
//	}
//	
//	@Test(groups="regression,smoke", priority=5)
//	public void checkNewSpaceShowing_TC_() {
//		
//	}
//	
	
	
//	@Test(dataProvider = "add_spaces_types", dataProviderClass = DataProviders.class)
	
	
	
	
//	public Object[][] excel_read() {
//	
//	String path = System.getProperty("user.dir") + "\\src\\test\\resources\\testdata\\addSpaceTypes2.xlsx";
//	ExcelLibrary obj = new ExcelLibrary(path);
//	
//	// Totals rows count
//	int rows = obj.getRowCount("loginData");
//	// Total Columns
//	int column = obj.getColumnCount("loginData");
//	int actRows = rows - 1;
//
//	Object[][] data2 = new Object[actRows][column];
//
//	for (int i = 0; i < actRows; i++) {
//		for (int j = 0; j < column; j++) {
//			data2[i][j] = obj.getCellData("loginData", j, i + 2);
//		}
//	
//		System.out.println("testing");
//	}
//	return data2;
//}
	
	

	@AfterMethod
	public void afterTest() {

		driver.get("https://staging.appedology.pk/marina/login");
		BrowserFactory.getInstance().removeDriver();
	}

}

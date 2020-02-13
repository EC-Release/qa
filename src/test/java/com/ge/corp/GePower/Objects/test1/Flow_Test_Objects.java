package com.ge.corp.GePower.Objects.test1;

public enum Flow_Test_Objects {
	
	username_ID("username"),
	password_ID("password"),
	login_btn_ID("Login"),		
	home_page_XPATH("//a[@title='Home Tab - Selected']"),
	opportunity_tab_XPATH("//li[@id='Opportunity_Tab']/a"),
	account_tab_XPATH("//li[@id='Account_Tab']/a"),
	new_oppty_btn_XPATH("//input[@value='New Opportunity']"),
	continue_btn_XPATH("//input[@value='Continue']"),
	opportunity_name_input_XPATH("//label[text()='Opportunity Name']//ancestor::td[1]//following-sibling::td[1]//input"),
	save_oppty_btn_XPATH("(//*[@name='save_new'])[1]"),
	validation_msg_on_save_XPATH("//*[@id='errorDiv_ep']"),
	account_name_search_icon_XPATH("//img[@alt='Account Name Lookup (New Window)']"),
	search_account_frame_ID("searchFrame"),
	search_account_input_ID("lksrch"),
	go_btn_XPATH("//input[@class='btn' and @name='go']"),	
	estimated_order_date_XPATH("//label[text()='Estimated Order Date']//ancestor::td[1]//following-sibling::td[1]//input"),
	oppty_step_label_XPATH("//label[text()='Opportunity Step']"),
	select_today_date_XPATH("//a[@class='calToday' and text()='Today']"),
	opportunity_step_XPATH("//label[text()='Opportunity Step']//ancestor::td[1]//following-sibling::td[1]//select"),
	save_portfolio_btn_XPATH("//input[@name='save']"),
	cancel_btn_XPATH("//input[@name='cancel']"),
	edit_btn_XPATH("(//input[@name='edit'])[1]"),
	oppty_setup_drodpown_XPATH("//label[text()='Opportunity Step']//ancestor::td[1]//following-sibling::td[1]//select"),
	order_forecast_dropdown_XPATH("//label[text()='Order Forecast Category']//ancestor::td[1]//following-sibling::td[1]//select"),
	lastpage_icon_XPATH("//img[@title='Last Page']"),
	table_XPATH("//div[contains(@class,'header')]//following-sibling::div[contains(@class,'scroller')]//div[contains(@class,'body')]//table"),
	records_perpage_XPATH("//table[contains(@id,'paginator')]//tr[contains(@class,'optSelected')]//td[2]")
	
	;
	private final String enumValue;       

	private Flow_Test_Objects (String s) {
	 	enumValue = s;
	}
		   
	public String getValue () {
		return enumValue;
	}
	
	public String toString() {
		return getValue();	
	}

}

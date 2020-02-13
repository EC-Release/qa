package com.ge.corp.GePower.Objects.test1;

public enum LoginPageObject {
	
	//login_XPATH("//textfile"),
	
	//footer_CSS("#test"),
	
	username_ID("username"),
	
	password_ID("password"),
	
	loginbtn_ID("submitFrm"), 
	
	GetConfirm_XPATH("//section[@class='flex-end-justified style-scope seed-app']/child :: button[@type='button']"),
	
	Profile_XPATH("//ul[@id='navitemlist']//following :: a[@id='#/profile']"),
	
	Save_XPATH("//div/section[@class='style-scope profile-view']/following :: button[@id='nextBtnRequestId']"),
	
	ok_XPATH("//paper-dialog[@id='modalsuccess']//div/button"),
	
	Create_XPATH("//a[@id='#/createRequest/']//child :: span"),

	Projectid_XPATH("//input[@name='projectId']"),
	
	ProjectName_XPATH("//div[@id=\"labelAndInputContainer\"]//following :: input[@name='projectName']"),
	
	ProjectOwner_XPATH("//div[@id=\"labelAndInputContainer\"]//following :: input[@name='projectOwner']"),
	
	ProjectLocation_XPATH("//div[@id=\"labelAndInputContainer\"]//following :: input[@name='projectLocation']"),
	
	ProjectSTARTdate_XPATH("//div[@id=\"labelAndInputContainer\"]//following :: input[@name='projectStartDate']"),
	
	ProjectENDdate_XPATH("//div[@id=\"labelAndInputContainer\"]//following :: input[@name='projectEndDate']"),
	
	ProjectTimeline_XPATH("//div[@id=\"labelAndInputContainer\"]//following :: textarea[@name='projectMilestones']"),
	
	ProjectTeam_XPATH("//div[@id=\"labelAndInputContainer\"]//following :: input[@name='projectT']"),
	
	ProjectCurrSituation_XPATH("//div[@id=\"labelAndInputContainer\"]//following :: textarea[@name='currentSituation']"),
	
	ProjectProjSituation_XPATH("//div[@id=\"labelAndInputContainer\"]//following :: textarea[@name='projectedSituation']"),
	
	ProjectDataRetenPeriod_XPATH("//div[@id=\"labelAndInputContainer\"]//following :: input[@name='validTill']"),
	
	ProjectSave_XPATH("//div/section/button[@id='saveBtnRequestId']"),
	
	ProjectNext_XPATH("//div/section/button[@id='nextBtnRequestId']"),
	
	Clickdropdown_XPATH("//px-dropdown[@id='domainId']//px-dropdown-text[@class='style-scope px-dropdown x-scope px-dropdown-text-0']"),
	
	//Selectdomain_XPATH("//ul[@id='list']/li[normalize-space(text())='End Products/Raw Materials']"),
	
	ChangeTypedropdown_XPATH("//px-dropdown[@id='changeSelId']//px-dropdown-text[@class='style-scope px-dropdown x-scope px-dropdown-text-0']/child :: span[text()='Select Change Type']"),
	
	//ChangeType_XPATH("//ul[@id='list']/li[normalize-space(text())='Equipment specifications/End products']"),
	
	PriorityDropdown_XPATH("//px-dropdown[@id='priorityValue']//iron-icon[@class=' angle-down style-scope px-dropdown-chevron x-scope iron-icon-0']"),
	
	//PriorityValue_XPATH("//ul[@id='list']/li[normalize-space(text())='Low']"),
	
	ChooseYes_XPATH("//div[@class='div-tab-col div-width-s style-scope moc-ques']//div[@id='radioContainer']//div[@id='offRadio']"),
	
	ClickAddReviewer_XPATH("//div[@class='div-tab-col div-width-si style-scope moc-ques']/iron-icon[@class='style-scope moc-ques x-scope iron-icon-0']"),
	
	ClickReviewerdropdown_XPATH("//px-dropdown[@id='reviewerDDID']//px-dropdown-text[@class='style-scope px-dropdown x-scope px-dropdown-text-0']/child :: span[text()='Select Reviewer']"),
	
	//SelectReviewer_XPATH("//ul[@id='list']/li[normalize-space(text())='kondaveeti,venkata koteswari']"),
	
	SearchReviewer_XPATH("//div/input[@id='search' and @placeholder='Select Reviewer']"),
	
	//EnterReviewer_XPATH("//div/ul[@id='searchResults']/li"),
	
	ClickonADD_XPATH("//div[@class='buttons style-scope moc-ques']/button[@id='nextPopId']"),
	
	ClickSave_XPATH("//Section[@class='flex-end-justified style-scope moc-ques']/button[@id='saveBtnId']"),
	
	SubmitRequest_XPATH("//paper-dialog[@id='modalsuccess']//div//button[text()='Ok']"),
	
	SubmitButton_XPATH("//section[@class='flex-end-justified style-scope moc-ques']/button[@id='popupBtnId']"),
	
	Popup_XPATH("//paper-dialog[@id='submitchangerequestPopup']//div//button[normalize-space(text())='Ok']"),
	
	AfterSubmitOk_XPATH("//*[@id='modalsuccess']//div//button[normalize-space(text())='Ok']"),
	
	NextProfile_XPATH("//ul[@id='navitemlist']//following :: a[@id='#/profile']"),
	
	ClickBusiness_XPATH("//input[@placeholder='Enter Business']"),
	
	ClickOrganisation_XPATH("//input[@placeholder='Enter Organization']"),
	
	ClickSubOrganisation_XPATH("//input[@placeholder='Enter Sub-Organization']"),
	
	ClickRegion_XPATH("//input[@placeholder='Enter Region']"),
	
	ClickCountry_XPATH("//input[@placeholder='Enter Country']"),
	
	ClickLocation_XPATH("//input[@placeholder='Enter Location/Site']/following :: span/iron-icon")
	
//	ChooseBusiness_XPATH("")
	
	//ChooseNo_XPATH("")
	
	
	//(""),text_XPATH
	
	/*_ID(),
	
	_CLASSNAME(),
	
	_LINK(),
	
	_PARTIALLIN(),*/
	
	
	
	
	
	
	
	
	;	
	private final String enumValue;       

	private LoginPageObject (String s) {
	 	enumValue = s;
	}
		   
	public String getValue () {
		return enumValue;
	}
	
	public String toString() {
		return getValue();	
	}
	
	
	

}

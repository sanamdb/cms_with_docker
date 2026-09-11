package com.learning.college.CollegeManagement.service;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("College Services Suite")
@SelectClasses(
			{
				DepartmentServiceTest.class,
				CampusServiceTest.class
			}
		)
public class CollegeServicesSuite {

}

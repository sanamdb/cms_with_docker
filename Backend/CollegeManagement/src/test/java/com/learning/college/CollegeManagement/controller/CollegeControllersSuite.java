package com.learning.college.CollegeManagement.controller;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("College Controller Suite")
@SelectClasses(
			{
				DepartmentControllerTest.class,
				CampusControllerTest.class
			}
		)
public class CollegeControllersSuite {

}

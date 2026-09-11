package com.learning.college.CollegeManagement.repository;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("College Repositories Suite")
@SelectClasses(
		{
			DepartmentRepoTest.class,
			CampusRepoTest.class
		}
	)
public class CollegeRepositoriesSuite {

}

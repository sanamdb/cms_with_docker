import { AfterViewChecked, AfterViewInit, ChangeDetectionStrategy, Component, OnInit, ViewChild } from '@angular/core';
import { LoggedInProfile } from './interfaces/LoggedInProfile';
import { LoggedUserProfileService } from './services/logged-user-profile.service';
import { Router } from '@angular/router';
import { CampusDisplayComponent } from './components/campus-display/campus-display.component';
import { PortalInfoComponent } from './components/portal-info/portal-info.component';
import { DepartmentService } from './services/department.service';
import { Department } from './interfaces/Department';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss'],
})
export class AdminComponent implements OnInit, AfterViewInit, AfterViewChecked {


  loggedInProfile!: LoggedInProfile;
  role: string | null = '';

  // Active navigation tab state
  activeTab: string = 'dashboard';

  // Collapsible Menu States
  isCampusOpen: boolean = false;
  isDepartmentOpen: boolean = false;
  expireAt!: string | null;
  departments: Department[] = [];
  toEdit: boolean = false;
  departmentDataToEdit!: Department;

  // @ViewChild(PortalInfoComponent, {static: true}) portalInfo!: PortalInfoComponent;
  @ViewChild(PortalInfoComponent) portalInfo!: PortalInfoComponent;
  
  constructor(
    private loggedInService: LoggedUserProfileService,
    private router: Router,
    private deptService: DepartmentService
  ) { }

  ngOnInit(): void {
    this.loadLoggedInUserProfile();
    this.expireAt = sessionStorage.getItem("expireAt");
    this.getAllDepartment();
  }

  ngAfterViewInit(): void {
    this.portalInfo.loginExpire = this.expireAt?this.expireAt:'';
  }

  ngAfterViewChecked(): void {
    console.log("view checked")
    console.log(this.portalInfo)
  }

  loadLoggedInUserProfile() {
    let username: string | null = sessionStorage.getItem("username");
    this.role = sessionStorage.getItem("role");
    if (username != undefined && username != null) {
      this.loggedInService.getLoggedUserProfile(username).subscribe({
        next: (data) => {
          this.loggedInProfile = data;
        },
        error: (error) => {
          
        }
      })
    } else {
      this.router.navigate(['login']);
    }
  }

  setTab(tab: string) {
    this.activeTab = tab;
  }

  toggleCampusMenu() {
    this.isCampusOpen = !this.isCampusOpen;
  }

  toggleDepartmentMenu() {
    this.isDepartmentOpen = !this.isDepartmentOpen;
  }

  logoutUser() {
    sessionStorage.clear();
    this.router.navigate(['login'])
  }


  getAllDepartment() {
    this.deptService.getDepartments().subscribe(department => {
      this.departments = department;
    })
  }

  addNewDepartment(dept: Department) {
    console.log(dept);
    this.departments.push(dept);
  }
  
  maintainProfessor() {
    this.router.navigate(['portal', 'admin', 'professor'])
  }

  toBeEdit(edit: boolean){
    this.toEdit = edit;
    this.activeTab='dept-add'
  }

  departmentToEdit(dept: Department) {
    this.departmentDataToEdit = dept;
  }

}
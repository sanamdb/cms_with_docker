import { Component, OnInit } from '@angular/core';
import { LoggedInProfile } from '../../interfaces/LoggedInProfile';
import { LoggedUserProfileService } from '../../services/logged-user-profile.service';
import { Admission } from '../../interfaces/Admission';
import { Campus } from '../../interfaces/Campus';
import { Department } from '../../interfaces/Department';

@Component({
  selector: 'app-student-profile',
  templateUrl: './student-profile.component.html',
  styleUrls: ['./student-profile.component.scss']
})
export class StudentProfileComponent implements OnInit {

  loggedInProfile!: LoggedInProfile;
  role: string | null = '';
  username: string | null = '';
  admission!: Admission;
  campus!: Campus;
  department!: Department;

  constructor(
    private loggedInService: LoggedUserProfileService
  ) { }

  ngOnInit(): void {
    this.username = sessionStorage.getItem("username");
    this.role = sessionStorage.getItem("role");
    this.loadLoggedInUserProfile();
    this.loadAdmission();
  }

  loadLoggedInUserProfile() {
    if (this.username != undefined && this.username != null) {
      this.loggedInService.getLoggedUserProfile(this.username).subscribe({
        next: (profile) => {
          this.loggedInProfile = profile;
        },
        error: (error) => {

        }
      })
    }
  }

  loadAdmission() {
    if (this.username != undefined && this.username != null) {
      this.loggedInService.getAdmission(this.username).subscribe(admission => {
        this.admission = admission;
        if (admission.campusId) {
          this.loggedInService.getStudentCampus(this.admission.campusId).subscribe(campus => {
            this.campus = campus;
          });
        }
        if(admission.departmentId){
          this.loggedInService.getStudentDepartment(this.admission.departmentId).subscribe(department => {
            this.department = department;
          });
        }
      })
    }
  }



}

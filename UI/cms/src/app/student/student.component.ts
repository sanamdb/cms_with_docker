import { Component, OnInit } from '@angular/core';
import { LoggedUserProfileService } from './services/logged-user-profile.service';
import { LoggedInProfile } from './interfaces/LoggedInProfile';
import { error } from 'console';
import { Router } from '@angular/router';

@Component({
  selector: 'app-student',
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.scss']
})
export class StudentComponent implements OnInit {

  loggedInProfile!: LoggedInProfile;
  role: string | null = '';

  // Active navigation tab state
  activeTab: string = '';
  isApplicationsOpen: boolean = true;

  constructor(
    private loggedInService: LoggedUserProfileService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.loadLoggedInUserProfile();
  }

  setTab(tab: string) {
    this.activeTab = tab;
  }

  loadLoggedInUserProfile() {
    let username: string | null = sessionStorage.getItem("username");
    this.role = sessionStorage.getItem("role");
    if (username != undefined && username != null) {
      this.loggedInService.getLoggedUserProfile(username).subscribe({
        next: (data) => {
          this.loggedInProfile = data;
          this.activeTab='profile';
        },
        error: (error) => {
          this.activeTab='';
        }
      })
    } else {
      this.router.navigate(['login']);
    }
  }

  toggleApplicationsMenu() {
    this.isApplicationsOpen = !this.isApplicationsOpen;
  }

  logoutUser() {
    sessionStorage.clear();
    this.router.navigate(['login'])
  }

}

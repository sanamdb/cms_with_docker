import { Component, OnInit } from '@angular/core';
import { LoggedInProfile } from '../../interfaces/LoggedInProfile';
import { LoggedUserProfileService } from '../../services/logged-user-profile.service';

@Component({
  selector: 'app-admin-profile',
  templateUrl: './admin-profile.component.html',
  styleUrls: ['./admin-profile.component.scss']
})
export class AdminProfileComponent implements OnInit {

   loggedInProfile!: LoggedInProfile;
   role: string | null = '';
   username: string | null = '';
 
   constructor(
     private loggedInService: LoggedUserProfileService
   ) { }
 
   ngOnInit(): void {
     this.username = sessionStorage.getItem("username");
     this.role = sessionStorage.getItem("role");
     this.loadLoggedInUserProfile();
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
}

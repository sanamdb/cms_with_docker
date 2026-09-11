import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-professor-home',
  templateUrl: './professor-home.component.html',
  styleUrls: ['./professor-home.component.scss']
})
export class ProfessorHomeComponent implements OnInit {

  menuItems = [
    { label: 'Dashboard', route: './dashboard', icon: 'dashboard' },
    { label: 'My Classes', route: './classes', icon: 'school' },
    { label: 'Create Question', route: './question/create', icon: 'assignment' },
    { label: 'Students', route: './students', icon: 'people' },
    { label: 'Schedule Exam', route: './schedule-exam', icon: 'event' },
    { label: 'Profile Settings', route: './settings', icon: 'settings' }
  ];

  constructor(
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  logoutProfessor() {
    sessionStorage.clear();
    this.router.navigate(['login']);
  }

}

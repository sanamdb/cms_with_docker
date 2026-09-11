import { Component, OnInit } from '@angular/core';
import { Login } from '../../interfaces/Login';
import { HttpClient } from '@angular/common/http';
import { LoginService } from '../../services/login.service';
import { ActivatedRoute, Data, Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  erroMessage:string = '';
  constructor(
    private loginService: LoginService,
    private router: Router
  ) { }

  loginForm: Login = {
    username: '',
    password: ''
  }

  ngOnInit(): void {
  }

  login() {
    this.loginService.login(this.loginForm).subscribe({
      next: (data) => {
        if (data.status === 'ACTIVE') {
          sessionStorage.setItem('username', data.username);
          sessionStorage.setItem('token', data.token);
          sessionStorage.setItem('status', data.status);
          sessionStorage.setItem('role', data.role);
          sessionStorage.setItem("expireAt", data.expireAt);
          if (data.role === 'ADMIN') {
            this.router.navigate(['/portal/admin']);
          } else if (data.role === 'STUDENT') {
            this.router.navigateByUrl('/portal/student');
          } else if(data.role === 'PROFESSOR') {
            this.router.navigateByUrl('/portal/professor');
          }
        } else {
          this.erroMessage = 'Login failed: Account is not active.';
        }
      },
      error: (error) => {
        this.erroMessage = 'Login failed: Username and password is incorrect';
      }
    });
  }

}

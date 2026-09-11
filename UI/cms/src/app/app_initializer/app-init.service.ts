import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AppInitService {

  openEndpoint: any;
  studentEndpoint: any;
  adminEndpoint: any;

  constructor() { }

  init() {
    this.loadOpenEndpoints();
    this.loadStudentEndpoints();
    this.loadAdminEndpoints();
  }

  loadOpenEndpoints() {
    this.openEndpoint = [
      { method: 'POST', url: '/users/api/v1/user' },
      { method: 'POST', url: '/users/api/v1/otp/validate' },
      { method: 'POST', url: '/auth/api/v1/login' },
      { method: 'GET', url: '/college/api/v1/campus/**' },
      { method: 'GET', url: '/college/api/v1/department/**' },
      { method: 'GET', url: '/college/api/v1/campus/department/**' },
      { method: 'GET', url: '/users/api/v1/user/active/**' },
      { method: 'GET', url: '/users/user/.*' }, // Regex/Pattern matching handled in interceptor logic
      { method: 'GET', url: '/users/user/**' },
      { method: 'GET', url: '/college/api/v1/batch/**' },
      { method: 'GET', url: '/question/api/v1/question/**' },
      { method: 'GET', url: '/college/api/v1/event'},
      { method: 'POST', url: '/student/api/v1/student/profile/picture' },
      { method: 'POST', url: '/student/api/v1/student' },
    ];
  }

  loadStudentEndpoints() {
    this.studentEndpoint = [
      { method: 'GET', url: '/users/api/v1/user/active/**' },
      { method: 'GET', url: '/users/user/**' },
      { method: 'POST', url: '/student/api/v1/apply' },
      { method: 'GET', url: '/exam/api/v1/examination/**' },
      { method: 'POST', url: '/exam/api/v1/submit' },
      { method: 'POST', url: '/exam/api/v1/student/result' },
      { method: 'GET', url: '/college/api/v1/admission/**' },
      { method: 'GET', url: '/student/api/v1/student/**' },
      { method: 'GET', url: '/student/api/v1/apply' }
    ];
  }

  loadAdminEndpoints() {
    this.adminEndpoint = [
      { method: 'POST', url: '/college/api/v1/campus' },
      { method: 'PUT', url: '/college/api/v1/campus/**' },
      { method: 'DELETE', url: '/college/api/v1/campus/**' },
      { method: 'POST', url: '/college/api/v1/department' },
      { method: 'POST', url: '/college/api/v1/campus/department' },
      { method: 'GET', url: '/users/api/v1/user/active/**' },
      { method: 'GET', url: '/users/user/**' },
      { method: 'POST', url: '/notification/api/v1/email' },
      { method: 'POST', url: '/college/api/v1/batch' },
      { method: 'PUT', url: '/college/api/v1/batch' },
      { method: 'POST', url: '/question/api/v1/question' },
      { method: 'PUT', url: '/question/api/v1/question/**' },
      { method: 'DELETE', url: '/question/api/v1/question/**' },
      { method: 'POST', url: '/exam/api/v1/examination' },
      { method: 'POST', url: '/exam/api/v1/calculate/result' },
      { method: 'POST', url: '/college/api/v1/admission' },
      { method: 'GET', url: '/college/api/v1/admin/profile/**'}
    ];
  }

}

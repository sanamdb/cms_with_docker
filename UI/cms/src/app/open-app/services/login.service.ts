import { Injectable } from '@angular/core';
import { Login } from '../interfaces/Login';
import { LoginResponse } from '../interfaces/LoginResponse';
import { HttpClient } from '@angular/common/http';
import { Observable, Observer } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) { }

  login(loginPayload: Login): Observable<LoginResponse> {
    return this.http.post<LoginResponse>('/auth/api/v1/login', loginPayload);
  }

}

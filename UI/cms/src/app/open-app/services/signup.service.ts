import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../interfaces/User';
import { Observable } from 'rxjs';
import { UserResponse } from './UserResponse';
import { OTP } from '../interfaces/OTP';
import { OtpResponse } from '../interfaces/OtpResponse';
import { UserProfile } from '../interfaces/UserProfile';
import { PictureUploadResponse } from '../interfaces/PictureUploadResponse';

@Injectable({
  providedIn: 'root'
})
export class SignupService {

  constructor(
    private http: HttpClient
  ) { }

  registerUser(user: User): Observable<UserResponse> {
    return this.http.post<UserResponse>('/users/api/v1/user', user);
  }

  verifyOtp(otp: OTP, username: string): Observable<OtpResponse> {
    otp.username = username;
    return this.http.post<OtpResponse>('/users/api/v1/otp/validate', otp);
  }

  createUserProfile(user: UserProfile, username: string): Observable<UserProfile> {
    let headersObj = new HttpHeaders({'userId': username});
    return this.http.post<UserProfile>(
      '/student/api/v1/student', 
      user,
      {headers: headersObj}
    );
  } 

  uploadProfilePicture(file: FormData, username: string): Observable<PictureUploadResponse> {
    let headersObj = new HttpHeaders({'userId': username});
    return this.http.post<PictureUploadResponse>(
      '/student/api/v1/student/profile/picture', 
      file,
      {headers: headersObj}
    );
  }

}
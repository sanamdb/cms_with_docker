import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoggedInProfile } from '../interfaces/LoggedInProfile';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoggedUserProfileService {

  constructor(
      private http: HttpClient
    ) { }
  
    getLoggedUserProfile(userId: string): Observable<LoggedInProfile> {
      return this.http.get<LoggedInProfile>(`/college/api/v1/admin/profile/${userId}`);
    }
}

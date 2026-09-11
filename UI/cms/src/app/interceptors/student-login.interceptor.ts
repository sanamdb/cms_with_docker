import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpHeaders
} from '@angular/common/http';
import { catchError, map, Observable, throwError } from 'rxjs';
import { AppInitService } from '../app_initializer/app-init.service';
import { error } from 'console';
import { Router } from '@angular/router';

@Injectable()
export class StudentLoginInterceptor implements HttpInterceptor {

  constructor(
    private init: AppInitService,
    private router: Router
  ) { }

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    let username: string | null = sessionStorage.getItem("username");
    let token: string | null = sessionStorage.getItem("token");
    let status: string | null = sessionStorage.getItem("status");
    let role: string | null = sessionStorage.getItem("role");

    console.log(request);

    if (this.verifyUrl(request.method, request.url)) {
      if (username && token && status && role) {
        let customHeaders =
          new HttpHeaders(
            {
              'userId': username,
              'Authorization': 'Bearer ' + token,
              'status': status,
              'role': role
            }
          )
        let newRequest = request.clone({
          headers: customHeaders
        })
        return next.handle(newRequest).pipe(catchError((error) => {
          if (error.status == '401' || error.status == 401) {
            sessionStorage.clear();
            this.router.navigate(['login']);
          }
          return throwError(error);
        }));
      } else {
        sessionStorage.clear();
        this.router.navigate(['login']);
      }
    }
    return next.handle(request);
  }

  verifyUrl(method: string, url: string): boolean {
    // Combine student and admin endpoints into a single array to check
    const protectedEndpoints = [
      ...(this.init.studentEndpoint || []),
      ...(this.init.adminEndpoint || [])
    ];

    // Check if the request method and url match any entry in the protected list
    return protectedEndpoints.some(endpoint => {
      if (endpoint.method !== method) {
        return false;
      }

      // Handle wildcard matching (e.g., '/college/api/v1/admission/**')
      if (endpoint.url.includes('**')) {
        const baseUrl = endpoint.url.replace('**', '');
        return url.startsWith(baseUrl);
      }

      // Exact match check
      return endpoint.url === url;
    });
  }

}

import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, CanActivateChild, CanLoad, Route, Router, RouterStateSnapshot, UrlSegment, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class StudentGuard implements CanActivate, CanActivateChild, CanLoad {

  constructor(
    private router: Router
  ) { }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    let usename: string | null = sessionStorage.getItem("username");
    let role: string | null = sessionStorage.getItem("role");
    if (usename && role && role === 'STUDENT') {
      return true;
    } else {
      return this.router.navigate(['login']);
    }
  }
  canActivateChild(
    childRoute: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    let usename: string | null = sessionStorage.getItem("username");
    let role: string | null = sessionStorage.getItem("role");
    if (usename && role && role === 'STUDENT') {
      return true;
    } else {
      return this.router.navigate(['login']);
    }
  }
  canLoad(
    route: Route,
    segments: UrlSegment[]): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    let usename: string | null = sessionStorage.getItem("username");
    let role: string | null = sessionStorage.getItem("role");
    if (usename && role && role === 'STUDENT') {
      return true;
    } else {
      return this.router.navigate(['login']);
    }
  }
}

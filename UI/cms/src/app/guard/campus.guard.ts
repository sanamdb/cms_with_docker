import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Resolve, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { EventPost } from '../open-app/interfaces/Events';
import { EventsService } from '../open-app/services/events.service';

@Injectable({
  providedIn: 'root'
})
export class CampusGuard implements Resolve<EventPost[]> {

  constructor(
    private eventService: EventsService
  ) {}

  resolve(
    route: ActivatedRouteSnapshot, 
    state: RouterStateSnapshot): EventPost[] | Observable<EventPost[]> | Promise<EventPost[]> {
    return this.eventService.events$;
  }
  
}

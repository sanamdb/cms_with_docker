import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { EventPost } from '../interfaces/Events';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class EventsService {

  constructor(
    private http: HttpClient
  ) { }

   events$ = this.http.get<EventPost[]>('/college/api/v1/event');
  
}

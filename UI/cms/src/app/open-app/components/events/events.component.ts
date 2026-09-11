import { Component, OnInit } from '@angular/core';
import { EventsService } from '../../services/events.service';
import { Observable, pluck } from 'rxjs';
import { EventPost } from '../../interfaces/Events';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-events',
  templateUrl: './events.component.html',
  styleUrls: ['./events.component.scss']
})
export class EventsComponent implements OnInit {

  events$ = new Observable<EventPost[]>();

  constructor(
    private eventService: EventsService,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit(): void {
    //this.events$ = this.eventService.events$;
    this.events$ = this.activatedRoute.data.pipe(pluck('events'));
  }

}

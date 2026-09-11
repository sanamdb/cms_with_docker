import { AfterContentChecked, AfterContentInit, AfterViewChecked, AfterViewInit, Component, ContentChild, ElementRef, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { JobService } from '../../services/job.service';

@Component({
  selector: 'app-career-projection',
  templateUrl: './career-projection.component.html',
  styleUrls: ['./career-projection.component.scss'],
  providers: [JobService]
})
export class CareerProjectionComponent implements OnInit, AfterContentInit, AfterContentChecked {

  @ContentChild('message') elmRef!: ElementRef;

  constructor() { }

  ngOnInit(): void {

  }

  ngAfterContentInit(): void {
    this.elmRef.nativeElement.innerText="We have just posted 2 new positions for the upcoming Fall semester!";
  }

  ngAfterContentChecked(): void {
    console.log("Ng Content checked");
  }

}

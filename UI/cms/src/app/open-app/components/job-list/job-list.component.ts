import { Component, Host, OnInit, Self } from '@angular/core';
import { JobService } from '../../services/job.service';

@Component({
  selector: 'app-job-list',
  templateUrl: './job-list.component.html',
  styleUrls: ['./job-list.component.scss'],
})
export class JobListComponent implements OnInit {

  header!: string;
  text!: string;

  constructor(
    @Host() private jobService: JobService
  ) { }

  ngOnInit(): void {
    this.header = this.jobService.htext;
    this.text = this.jobService.spanText;
  }

}

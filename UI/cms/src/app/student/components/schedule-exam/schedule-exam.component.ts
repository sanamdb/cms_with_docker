import { Component, OnInit } from '@angular/core';
import { Department } from '../../interfaces/Department';
import { ApplyAdmissionService } from '../../services/apply-admission.service';
import { StudentExamResponse } from '../../interfaces/StudentExamResponse';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-schedule-exam',
  templateUrl: './schedule-exam.component.html',
  styleUrls: ['./schedule-exam.component.scss']
})
export class ScheduleExamComponent implements OnInit {

  exam: StudentExamResponse[] = [];

  constructor(
    private admissionService: ApplyAdmissionService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.admissionService.getAppliedApplication$.subscribe(applications => {
      applications.forEach(application => {
        this.admissionService.getExaminationDetails(application.departmentId, application.batch).subscribe(examResponse => {
          this.exam.push(examResponse);
        })
      });
    });
    console.log(this.exam)
  }

  // Helper to check if current time is strictly between start and end window
  isExamActive(start: Date | string, end: Date | string): boolean {
    const now = new Date().getTime();
    const startTime = new Date(start).getTime();
    const endTime = new Date(end).getTime();
    return now >= startTime && now <= endTime;
  }

  // Dynamic button label based on schedule timing
  getExamButtonText(start: Date | string, end: Date | string): string {
    const now = new Date().getTime();
    const startTime = new Date(start).getTime();
    const endTime = new Date(end).getTime();

    if (now < startTime) {
      return 'Exam Not Started Yet';
    } else if (now > endTime) {
      return 'Exam Window Closed';
    } else {
      return 'Start Exam Now';
    }
  }

  startExam(examId: string) {
    this.router.navigate(['portal','student','exam', examId]);
  }

}

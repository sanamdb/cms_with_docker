import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { StudentRoutingModule } from './student-routing.module';
import { StudentComponent } from './student.component';
import { StudentProfileComponent } from './components/student-profile/student-profile.component';
import { AdmissionApplyComponent } from './components/admission-apply/admission-apply.component';
import { FormsModule } from '@angular/forms';
import { ExistingApplicationComponent } from './components/existing-application/existing-application.component';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { ScheduleExamComponent } from './components/schedule-exam/schedule-exam.component';
import { ExamComponent } from './components/exam/exam.component';


@NgModule({
  declarations: [
    StudentComponent,
    StudentProfileComponent,
    AdmissionApplyComponent,
    ExistingApplicationComponent,
    ScheduleExamComponent,
    ExamComponent
  ],
  imports: [
    CommonModule,
    StudentRoutingModule,
    FormsModule
  ],
  providers: [
    
  ]
})
export class StudentModule { }

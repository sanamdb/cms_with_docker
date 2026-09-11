import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { StudentComponent } from './student.component';
import { ExamComponent } from './components/exam/exam.component';

const routes: Routes = [
  { path: '', component: StudentComponent },
  {path:'exam/:id', component: ExamComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class StudentRoutingModule { }

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProfessorHomeComponent } from './components/professor-home/professor-home.component';
import { CreateQuestionComponent } from './components/create-question/create-question.component';
import { QuestionsGuard } from './guards/questions.guard';
import { MyClassComponent } from './components/my-class/my-class.component';

const routes: Routes = [
  {path: '', component: ProfessorHomeComponent,
    children: [
      { 
        path: 'question/create',
        canDeactivate: [QuestionsGuard], 
        component: CreateQuestionComponent 
      },
      { 
        path: 'classes',
        component: MyClassComponent 
      },
      // default route redirects to dashboard
      { path: '', redirectTo: 'dashboard', pathMatch: 'full' }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProfessorRoutingModule { }

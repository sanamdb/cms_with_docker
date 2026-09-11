import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProfessorModule } from './professor/professor.module';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { StudentGuard } from './guard/student.guard';
import { AdminGuard } from './guard/admin.guard';
import { ProfessorGuard } from './guard/professor.guard';

const routes: Routes = [
  
  {
    path: 'portal/professor',
    canActivate: [ProfessorGuard],
    canLoad: [ProfessorGuard],
    canActivateChild: [ProfessorGuard],
    loadChildren:()=> import("./professor/professor.module").then((m) => m.ProfessorModule)
  },
  { 
    path: 'portal/admin', 
    canActivate: [AdminGuard],
    canLoad: [AdminGuard],
    loadChildren: () => import('./admin/admin.module').then(m => m.AdminModule) 
  },
  { path: 'portal/student',
    canActivate:[StudentGuard],
    canLoad: [StudentGuard],
    canActivateChild: [StudentGuard],
    loadChildren: () => import('./student/student.module').then(m => m.StudentModule) 
  },
  
  {path: '**', component: PageNotFoundComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

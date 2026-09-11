import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './admin.component';
import { EditCampusComponent } from './components/edit-campus/edit-campus.component';
import { AddProfessorComponent } from './components/add-professor/add-professor.component';


const routes: Routes = [
  { 
    path: '', component: AdminComponent,
    children: [
      {path: ':id', component: EditCampusComponent}
    ] 
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }

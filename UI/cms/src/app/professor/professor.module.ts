import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProfessorRoutingModule } from './professor-routing.module';
import { ProfessorHomeComponent } from './components/professor-home/professor-home.component';
import {MatIconModule} from '@angular/material/icon';
import { CreateQuestionComponent } from './components/create-question/create-question.component';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';
import { ReactiveFormsModule } from '@angular/forms';
import { MyClassComponent } from './components/my-class/my-class.component';

@NgModule({
  declarations: [
    ProfessorHomeComponent,
    CreateQuestionComponent,
    MyClassComponent
  ],
  imports: [
    CommonModule,
    ProfessorRoutingModule,
    MatIconModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatButtonModule,
    MatDialogModule,
    ReactiveFormsModule
  ]
})
export class ProfessorModule { }

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { AdminComponent } from './admin.component';
import { AdminProfileComponent } from './components/admin-profile/admin-profile.component';
import { CampusDisplayComponent } from './components/campus-display/campus-display.component';
import { PortalInfoComponent } from './components/portal-info/portal-info.component';
import { DepartmentDisplayComponent } from './components/department-display/department-display.component';
import { AddCampusComponent } from './components/add-campus/add-campus.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AddDepartmentComponent } from './components/add-department/add-department.component';
import { FaqAdminComponent } from './components/faq-admin/faq-admin.component';
import { FaqDisplayAdminComponent } from './components/faq-display-admin/faq-display-admin.component';
import { FontAdminDirective } from './directives/font-admin.directive';
import { CustomValidatorDirective } from './directives/custom-validator.directive';
import { EditCampusComponent } from './components/edit-campus/edit-campus.component';
import { AddProfessorComponent } from './components/add-professor/add-professor.component';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatButtonModule} from '@angular/material/button';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import {MatIconModule} from '@angular/material/icon';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatSelectModule} from '@angular/material/select';
import {MatRadioModule} from '@angular/material/radio';
import { MatExpansionModule } from '@angular/material/expansion';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { BatchComponent } from './components/batch/batch.component';
import { MatDialogModule } from '@angular/material/dialog';
import { ExamComponent } from './components/exam/exam.component';
import { FilterDeptPipe } from './pipes/filter-dept.pipe';

@NgModule({
  declarations: [
    AdminComponent,
    AdminProfileComponent,
    CampusDisplayComponent,
    PortalInfoComponent,
    DepartmentDisplayComponent,
    AddCampusComponent,
    AddDepartmentComponent,
    FaqAdminComponent,
    FaqDisplayAdminComponent,
    FontAdminDirective,
    CustomValidatorDirective,
    EditCampusComponent,
    AddProfessorComponent,
    DashboardComponent,
    BatchComponent,
    ExamComponent,
    FilterDeptPipe
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule, 
    MatInputModule,
    MatButtonModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatIconModule,
    MatCheckboxModule,
    MatSelectModule,
    MatRadioModule,
    MatExpansionModule,
    MatCardModule,
    MatDividerModule,
    MatProgressSpinnerModule,
    MatDialogModule

  ],
  providers: [
    
  ]
})
export class AdminModule { }

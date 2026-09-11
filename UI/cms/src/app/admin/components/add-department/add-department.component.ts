import { Component, EventEmitter, HostListener, Input, OnInit, Output } from '@angular/core';
import { DepartmentRequest } from '../../interfaces/DepartmentRequest';
import { NgForm } from '@angular/forms';
import { DepartmentService } from '../../services/department.service';
import { Department } from '../../interfaces/Department';

@Component({
  selector: 'app-add-department',
  templateUrl: './add-department.component.html',
  styleUrls: ['./add-department.component.scss']
})
export class AddDepartmentComponent implements OnInit {

  id: string = '';
  departmentForm: DepartmentRequest = {
    code: '',
    name: '',
    description: '',
    duration: 0,
    term_type: ''
  }

  @Output() newDepartment = new EventEmitter<Department>();
  @Input() isEdit: boolean = false;
  @Input() department!: Department;

  sucess: string = '';

  termType: any[] = [
    { code: 'semester', label: "Semester" },
    { code: 'yearly', label: "Yearly" },
    { code: 'quaterly', label: "Quaterly" },
    { code: 'monthly', label: "Monthly" },
    { code: 'flexible', label: "Flexible" },
  ]

  constructor(
    private deptService: DepartmentService
  ) { }

  ngOnInit(): void {
    if (this.isEdit) {
      this.departmentForm.code = this.department.code;
      this.departmentForm.name = this.department.name;
      this.departmentForm.description = this.department.description;
      this.departmentForm.duration = this.department.duration;
      this.departmentForm.term_type = this.department.termType;
      this.id = this.department.id;
    }
  }

  saveDepartment(deptForm: NgForm) {
    if (this.isEdit) {
      this.sucess = `Department has been updated successfully with id ${this.department.id}`;
      deptForm.resetForm({
        code: '',
        name: '',
        description: '',
        duration: 0,
        term_type: ''
      });
      this.isEdit = false;
      this.department.code = '';
      this.department.name = '';
      this.department.description = '';
      this.department.duration = 0;
      this.department.id = '';
      this.department.termType = '';
      this.id = '';

    } else {
      this.deptService.createDepartment(deptForm.value).subscribe(dept => {
        this.sucess = `Department has been saved successfully with id ${dept.id}`;
        this.newDepartment.emit(dept);
        deptForm.resetForm({
          code: '',
          name: '',
          description: '',
          duration: 0,
          term_type: ''
        });
      })
    }
    setTimeout(() => {
      this.sucess = ''
    }, 3000);
  }



}

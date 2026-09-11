import { ChangeDetectionStrategy, Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Department } from '../../interfaces/Department';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-department-display',
  templateUrl: './department-display.component.html',
  styleUrls: ['./department-display.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class DepartmentDisplayComponent implements OnInit {

  @Input() departmentList: Department[] = [];
  showSuccessModal: boolean = false;
  @Output() isEdit = new EventEmitter<boolean>();
  @Output() department = new EventEmitter<Department>();

  duration = 0;

  constructor() { }

  ngOnInit(): void {
  }

  onEdit(department: Department) {
    this.isEdit.emit(true);
    this.department.emit(department);
  }

  onDelete(id: string) {
    this.showSuccessModal = true;
  }

  closeSuccessModal() {
    this.showSuccessModal = false;
    // Optional: this.loadCampuses(this.currentPage); // refresh the table
  }

}

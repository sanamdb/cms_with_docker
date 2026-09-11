import { Component, OnInit } from '@angular/core';
import { CampusRequest } from '../../interfaces/CampusRequest';
import { CampusService } from '../../services/campus.service';

@Component({
  selector: 'app-add-campus',
  templateUrl: './add-campus.component.html',
  styleUrls: ['./add-campus.component.scss']
})
export class AddCampusComponent implements OnInit {

  success: string = '';

  campusForm: CampusRequest = {
    name: '',
    address: '',
    city: ''
  }

  cityList: string[] = ["Mumbai", "Bangalore", "Pune", "Ahmedabad", "Noida"];

  constructor(
    private campusService: CampusService
  ) { }

  ngOnInit(): void {
  }

  createCampus() {
    this.campusService.createCampus(this.campusForm).subscribe(data => {
      this.success = `Campus has been successfully created with id ${data.id}`
    });
    setTimeout(() => {
      this.success = '';
    }, 3000);
  }

}

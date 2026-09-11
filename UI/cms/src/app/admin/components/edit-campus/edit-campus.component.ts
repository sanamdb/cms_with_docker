import { Component, OnInit } from '@angular/core';
import { CampusService } from '../../services/campus.service';
import { CampusRequest } from '../../interfaces/CampusRequest';
import { ActivatedRoute, Router } from '@angular/router';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-edit-campus',
  templateUrl: './edit-campus.component.html',
  styleUrls: ['./edit-campus.component.scss']
})
export class EditCampusComponent implements OnInit {

  id: string = '';
  success: string = '';
  selectedCity: string = '';

  campusForm: CampusRequest = {
    name: '',
    address: '',
    city: ''
  }

  cityList: string[] = ["Mumbai", "Bangalore", "Pune", "Ahmedabad", "Noida"];

  constructor(
    private campusService: CampusService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.route.params.subscribe(data => {
      this.campusService.getCampusById(data['id']).subscribe(campus => {
        this.id = campus.id;
        this.campusForm.name = campus.name;
        this.campusForm.city = campus.city;
        this.campusForm.address = campus.address;
        this.selectedCity = campus.city;
      });
    });
  }

  updateCampus(cmpFrm: NgForm) {
    this.campusService.updateCampusById(this.id, cmpFrm.value).subscribe(data => {
      this.success = "Campus has been sucessfully updated, will be reflected on reload";
      cmpFrm.resetForm({
        name: '',
        address: '',
        city: ''
      })
    })
    setTimeout(() => {
      this.success = '';
      this.router.navigate(['portal', 'admin'])
    }, 3000);
  }

}

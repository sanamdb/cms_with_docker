import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-portal-info',
  templateUrl: './portal-info.component.html',
  styleUrls: ['./portal-info.component.scss']
})
export class PortalInfoComponent implements OnInit {

  loginExpire!: string;

  constructor() { }

  ngOnInit(): void {
  }

}

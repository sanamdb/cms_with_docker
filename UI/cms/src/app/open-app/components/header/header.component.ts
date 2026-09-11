import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  search:string = '';
  

  constructor() { }

  ngOnInit(): void {
  }

  SearchGlobally() {
    console.log("Search globally");
    console.log(this.search);
    this.search= '';
  }

}

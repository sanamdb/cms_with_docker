import { AfterViewInit, ChangeDetectorRef, Component, ElementRef, Inject, OnInit, QueryList, ViewChild, ViewChildren, ViewContainerRef } from '@angular/core';
import { StudentInfoAboutUsComponent } from '../student-info-about-us/student-info-about-us.component';
import { GuestInfoAboutUsComponent } from '../guest-info-about-us/guest-info-about-us.component';
import { ContactInfoComponent } from '../contact-info/contact-info.component';
import { COMMON_THEME } from '../../services/config.token';

@Component({
  selector: 'app-about-us',
  templateUrl: './about-us.component.html',
  styleUrls: ['./about-us.component.scss'],
  providers: [
    {
      provide: COMMON_THEME,
      useValue: 'Dark'
    }
  ]
})
export class AboutUsComponent implements OnInit, AfterViewInit {

  @ViewChild('comp', {read: ViewContainerRef}) contRef!: ViewContainerRef;
  @ViewChild('message', {read: ElementRef}) elmRef!: ElementRef;
  @ViewChildren(ContactInfoComponent) contactInfo!: QueryList<ContactInfoComponent>;

  constructor(
    private cdr: ChangeDetectorRef,
    @Inject(COMMON_THEME) private theme: string
  ) { }

  ngOnInit(): void {
    console.log(this.contRef);
    console.log(`Theme is : ${this.theme}`)
  }

  ngAfterViewInit(): void {
    let role: string | null = sessionStorage.getItem("role");
    let username: string | null = sessionStorage.getItem("username");
    if(role == "STUDENT"){
      this.contRef.createComponent(StudentInfoAboutUsComponent);
      this.elmRef.nativeElement.innerText=username;
    }
    else {
      this.contRef.createComponent(GuestInfoAboutUsComponent);
      this.elmRef.nativeElement.innerText='Guest';
    }

    console.log(this.contactInfo);

    this.contactInfo.forEach((component, index) => {
      if(index==0){
        component.contactType = "Mobile Number";
        component.contactValue = "+91 8210057436";
      }
      if(index == 1) {
        component.contactType = "Email Id";
        component.contactValue = "cms.enquiry@gmail.com";
      }
      if(index == 2) {
        component.contactType = "Fax Number";
        component.contactValue = "5555-4444";
      }
    })
    this.cdr.detectChanges();
  }

}

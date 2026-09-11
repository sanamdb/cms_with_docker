import { ErrorHandler, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { OpenAppRoutingModule } from './open-app-routing.module';
import { HomeComponent } from './components/home/home.component';
import { HeaderComponent } from './components/header/header.component';
import { SupportComponent } from './components/support/support.component';
import { LoginComponent } from './components/login/login.component';
import { FormsModule } from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import { RegisterComponent } from './components/register/register.component';
import { NoticeCardComponent } from './components/notice-card/notice-card.component';
import { NoticeBoardComponent } from './components/notice-board/notice-board.component';
import { CampusComponent } from './components/campus/campus.component';
import { CampusDetailsComponent } from './components/campus-details/campus-details.component';
import { CareerComponent } from './components/career/career.component';
import { FaqComponent } from './components/faq/faq.component';
import { AboutUsComponent } from './components/about-us/about-us.component';
import { StudentInfoAboutUsComponent } from './components/student-info-about-us/student-info-about-us.component';
import { GuestInfoAboutUsComponent } from './components/guest-info-about-us/guest-info-about-us.component';
import { ContactInfoComponent } from './components/contact-info/contact-info.component';
import { CareerProjectionComponent } from './components/career-projection/career-projection.component';
import { AboutUsService } from './services/about-us.service';
import { JobListComponent } from './components/job-list/job-list.component';
import { EventsComponent } from './components/events/events.component';
import { GlobalErrorHandlerService } from '../error_handler/global-error-handler.service';


@NgModule({
  declarations: [
    HomeComponent,
    HeaderComponent,
    SupportComponent,
    LoginComponent,
    RegisterComponent,
    NoticeCardComponent,
    NoticeBoardComponent,
    CampusComponent,
    CampusDetailsComponent,
    CareerComponent,
    FaqComponent,
    AboutUsComponent,
    StudentInfoAboutUsComponent,
    GuestInfoAboutUsComponent,
    ContactInfoComponent,
    CareerProjectionComponent,
    JobListComponent,
    EventsComponent
  ],
  imports: [
    CommonModule,
    OpenAppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  exports: [HeaderComponent],
  providers: [AboutUsService,]
})
export class OpenAppModule {}

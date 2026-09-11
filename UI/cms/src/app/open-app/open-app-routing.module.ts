import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { HeaderComponent } from './components/header/header.component';
import { SupportComponent } from './components/support/support.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { CampusComponent } from './components/campus/campus.component';
import { CampusDetailsComponent } from './components/campus-details/campus-details.component';
import { FaqComponent } from './components/faq/faq.component';
import { AboutUsComponent } from './components/about-us/about-us.component';
import { CareerComponent } from './components/career/career.component';
import { EventsComponent } from './components/events/events.component';
import { CampusGuard } from '../guard/campus.guard';

const routes: Routes = [
  {path: 'home', component: HomeComponent},
  {path: 'support', component: SupportComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'login', component: LoginComponent},
  {path: 'campus', component: CampusComponent},
  {path: 'campus/:id', component: CampusDetailsComponent},
  {path: 'FAQ', component: FaqComponent},
  {path: 'aboutUs', component: AboutUsComponent},
  {path: 'Career', component: CareerComponent},
  { path: 'events', 
    resolve: {'events': CampusGuard},
    component: EventsComponent},
  {path:'', redirectTo:'/home', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class OpenAppRoutingModule { }

import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { CampusService } from '../../services/campus.service';
import { DepartmentOfferByACampusApiResponse } from '../../interfaces/DepartmentOfferByACampusApiResponse';
import { error } from 'console';
import { Notice } from '../../interfaces/Notice';
import { NoticeService } from '../../services/notice.service';
import { NoticeBoardComponent } from '../notice-board/notice-board.component';
import { Department } from '../../interfaces/Department';
import { CampusDepartment } from '../../interfaces/CampusDepartment';

@Component({
  selector: 'app-campus-details',
  templateUrl: './campus-details.component.html',
  styleUrls: ['./campus-details.component.scss']
})
export class CampusDetailsComponent implements OnInit {

  campusDepartment!: DepartmentOfferByACampusApiResponse;

  noticeList: Notice[] = [];
  noticeToDisplay!: Notice;
  noticeBoadOpen!: boolean;
  showAdminWarningModal: boolean = false;

  @ViewChild(NoticeBoardComponent) noticeBoardComp!: NoticeBoardComponent;

  constructor(
    private activatedRoute: ActivatedRoute,
    private campusService: CampusService,
    private noticeService: NoticeService,
    private router: Router
  ) { }

  ngOnInit(): void {
    //console.log( this.activatedRoute.snapshot.params['id']);
    this.noticeList = this.noticeService.getNotices();
    this.activatedRoute.params.subscribe(data => {
      this.fetchCampusDetails(data['id']);
    })
  }

  fetchCampusDetails(id: string) {
    this.campusService.getDepartmentOfferByCampus(id).subscribe({
      next: (response) => {
        this.campusDepartment = response;
      },
      error: (error) => {

      }
    })
  }

  selectedNoticeToDisplay(notice: Notice) {
    this.noticeToDisplay = notice;
    this.noticeBoadOpen = true;
    setTimeout(() => {
      if (this.noticeBoardComp) {
        this.noticeBoardComp.noticeDetails = notice;
      }
    });
  }

  closeNoticeBoard(popUp: boolean) {
    this.noticeBoadOpen = popUp;
  }

  applyForDepartment(item: CampusDepartment) {
    let username: string | null = sessionStorage.getItem("username");
    let token: string | null = sessionStorage.getItem("token");
    let status: string | null = sessionStorage.getItem("status");
    let role: string | null = sessionStorage.getItem("role");

    if (username != undefined && username != null && token != undefined && token != null &&
      status === 'ACTIVE' && role != undefined && role != null) {
      if (role === 'STUDENT') {
        this.router.navigate(['portal','student']);
      } else if (role === 'ADMIN') {
        this.showAdminWarningModal = true;
      }
    } else {
      this.router.navigate(['login']);
    }

  }

  closeAdminModal() {
    this.showAdminWarningModal = false;
  }

  switchToStudentLogin() {
    this.showAdminWarningModal = false;
    sessionStorage.clear(); // Clear admin session
    this.router.navigate(['login']); // Redirect to login page
  }

}

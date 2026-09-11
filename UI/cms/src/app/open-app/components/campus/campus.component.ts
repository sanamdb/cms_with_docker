import { Component, OnInit, ViewChild } from '@angular/core';
import { Notice } from '../../interfaces/Notice';
import { NoticeService } from '../../services/notice.service';
import { NoticeBoardComponent } from '../notice-board/notice-board.component';
import { Campus, CampusApiResponse } from '../../interfaces/Campus';
import { CampusService } from '../../services/campus.service';
import { error } from 'console';
import { Router } from '@angular/router';

@Component({
  selector: 'app-campus',
  templateUrl: './campus.component.html',
  styleUrls: ['./campus.component.scss']
})
export class CampusComponent implements OnInit {

  noticeList: Notice[] = [];
  noticeToDisplay!: Notice;
  noticeBoadOpen!: boolean;
  campuses: Campus[] = [];
  totalPages: number = 0;
  currentPage: number = 0;
  pageSize: number = 6;
  totalElements:number = 0;

  @ViewChild(NoticeBoardComponent) noticeBoardComp!: NoticeBoardComponent;

  constructor(
    private noticeService: NoticeService,
    private campusService: CampusService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.noticeList = this.noticeService.getNotices();
    this.loadCampuses(this.currentPage);
  }

  loadCampuses(page: number) {
    this.campusService.getCampusPaginated(page, this.pageSize).subscribe({
      next: (response: CampusApiResponse) => {
        this.campuses = response.content;
        this.currentPage = response.number;
        this.totalPages = response.totalPages;
        this.totalElements = response.totalElements;
      },
      error: (err) => {
        console.error('Error fetching campuses', err);
      }
    });
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

  exploreCampus(id: string) {
    this.router.navigate(['campus', id]);
  }

  onPageChange(page: number) {
    if (page >= 0 && page < this.totalPages) {
      this.loadCampuses(page);
    }
  }

  getPagesArray(): number[] {
    return Array.from({ length: this.totalPages }, (_, i) => i);
  }

  closeNoticeBoard(popUp: boolean) {
    this.noticeBoadOpen = popUp;
  }

}

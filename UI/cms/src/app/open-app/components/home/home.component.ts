import { AfterViewInit, ChangeDetectorRef, Component, OnChanges, OnDestroy, OnInit, Self, SimpleChanges, ViewChild } from '@angular/core';
import { Notice } from '../../interfaces/Notice';
import { NoticeService } from '../../services/notice.service';
import { NoticeBoardComponent } from '../notice-board/notice-board.component';
import { BannerSlide } from '../../interfaces/BannerSlide';
import { BannerSlideService } from '../../services/banner-slide.service';
import { Campus } from '../../interfaces/Campus';
import { CampusService } from '../../services/campus.service';
import { error } from 'console';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
  providers: [CampusService]
})
export class HomeComponent implements OnInit, OnDestroy {

  constructor(
    private noticeService: NoticeService,
    private bannerService: BannerSlideService,
    @Self() private campuseService: CampusService,
    private router: Router
  ) { }

  noticeList: Notice[] = [];
  description: string = 'desc';
  noticeToDisplay!: Notice;
  noticeBoadOpen: boolean = false;

  campuses: Campus[] = [];

  slides: BannerSlide[] = [];

  @ViewChild(NoticeBoardComponent) noticeBoardComp!: NoticeBoardComponent;

  currentIndex: number = 0;
  private slideInterval: any;

  ngOnInit(): void {
    this.slides = this.bannerService.getBannerSlider();
    this.noticeList = this.noticeService.getNotices();
    this.startAutoSlide();
    
    this.campuseService.getCampus().subscribe({
      next: (data) => {
        this.campuses = data.content;
      },
      error: () => {

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

  ngOnDestroy(): void {
    this.stopAutoSlide();
  }

  startAutoSlide() {
    this.slideInterval = setInterval(() => {
      this.nextSlide();
    }, 5000); // Changes slide every 5 seconds
  }

  stopAutoSlide() {
    if (this.slideInterval) {
      clearInterval(this.slideInterval);
    }
  }

  nextSlide() {
    this.currentIndex = (this.currentIndex + 1) % this.slides.length;
  }

  prevSlide() {
    this.currentIndex = (this.currentIndex - 1 + this.slides.length) % this.slides.length;
  }

  goToSlide(index: number) {
    this.currentIndex = index;
  }

  exploreCampus(id: string) {
    this.router.navigate(['campus', id]);
  }

}

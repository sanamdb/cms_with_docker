import { Component, OnDestroy, OnInit, SkipSelf } from '@angular/core';
import { AboutUsService } from '../../services/about-us.service';
import { FAQ } from '../../interfaces/FAQ';
import { filter, map, Observable, shareReplay, Subscription } from 'rxjs';
import { FaqService } from 'src/app/admin/services/faq.service';
import { NavigationEnd, NavigationStart, Router } from '@angular/router';
import { SSL_OP_NO_TLSv1_1 } from 'constants';

@Component({
  selector: 'app-faq',
  templateUrl: './faq.component.html',
  styleUrls: ['./faq.component.scss'],
  providers: [AboutUsService]
})
export class FaqComponent implements OnInit, OnDestroy {

  faq: FAQ[] = [];
  subscriptions!: Subscription;;

  constructor(
    @SkipSelf() private abtUsService: AboutUsService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.subscriptions = this.abtUsService.allFaq().subscribe(data => {
      this.faq = data;
    });
    this.abtUsService.getAllPhotos();
    this.router.events.subscribe(data => {
      console.log(data);
    });
    this.router.events.pipe(map((event => event instanceof NavigationStart))).subscribe(data => {
      console.log("single event map")
      console.log(data);
    });
    this.router.events.pipe(filter((event => event instanceof NavigationEnd))).subscribe(data => {
      console.log("single event filter")
      console.log(data);
    });

    this.abtUsService.getCacheFaq().pipe(shareReplay(1)).subscribe(data => {
      console.log(" Cached called ");
    })

  }

  ngOnDestroy(): void {
    console.log("Destroyed called");
    if(this.subscriptions){
      console.log("condition mate");
      this.subscriptions.unsubscribe();
    }
  }

}

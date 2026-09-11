import { ChangeDetectionStrategy, Component, DoCheck, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { FAQ } from '../../interfaces/FAQ';

@Component({
  selector: 'app-faq-display-admin',
  templateUrl: './faq-display-admin.component.html',
  styleUrls: ['./faq-display-admin.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class FaqDisplayAdminComponent implements OnInit, OnChanges, DoCheck {

  @Input() faqList: FAQ[] = [];

  constructor() { }

  ngOnInit(): void {
  }

  ngOnChanges(changes: SimpleChanges): void {
    console.log(changes);
    if(changes) {
      //alert('New Faq answered');
    }
    
  }

  ngDoCheck(): void {
    console.log("Do check from faq");
  }

}

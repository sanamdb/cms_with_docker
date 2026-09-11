import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Notice } from '../../interfaces/Notice';

@Component({
  selector: 'app-notice-card',
  templateUrl: './notice-card.component.html',
  styleUrls: ['./notice-card.component.scss']
})
export class NoticeCardComponent implements OnInit {

  @Input() notices: Notice[] = [];
  @Output() notice = new EventEmitter<Notice>();

  constructor() { }

  ngOnInit(): void {
  }

  selectedNotice(notice: Notice):void {
    this.notice.emit(notice);
  }

}

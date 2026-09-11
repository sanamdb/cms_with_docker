import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Notice } from '../../interfaces/Notice';

@Component({
  selector: 'app-notice-board',
  templateUrl: './notice-board.component.html',
  styleUrls: ['./notice-board.component.scss']
})
export class NoticeBoardComponent implements OnInit {

  @Output() noticeClose = new EventEmitter<boolean>();

  noticeDetails!: Notice;

  constructor() { }

  ngOnInit(): void {
  }

  onClose() {
    this.noticeClose.emit(false);
  }

}

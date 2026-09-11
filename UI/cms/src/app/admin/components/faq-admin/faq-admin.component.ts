import { Component, OnInit, Optional } from '@angular/core';
import { FAQ } from '../../interfaces/FAQ';
import { FaqService } from '../../services/faq.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-faq-admin',
  templateUrl: './faq-admin.component.html',
  styleUrls: ['./faq-admin.component.scss']
})
export class FaqAdminComponent implements OnInit {

  faq: FAQ[] = [];
  newFaqToBeAdded:boolean =false

  faqForm: FAQ = {
    id: 0,
    question: '',
    answer: '',
    dept: ''
  }
  
  constructor(
    @Optional() private faqService: FaqService
   ) { }
  
    ngOnInit(): void {
      this.faqService?.allFaq().subscribe(data => {
        this.faq = data;
      })
    }

    addNewFaq() {
      this.newFaqToBeAdded = true;
    }

    publishFaq(faqFrm: NgForm) {
      this.faqService?.createFaq(faqFrm.value).subscribe(data => {
        this.newFaqToBeAdded = false;
        this.faq = [...this.faq, data];
      })
    }

}
